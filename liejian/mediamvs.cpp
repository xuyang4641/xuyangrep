#include <QDebug>
#include <QHostAddress>
#include <ctime>
#include <QCoreApplication>
#include <QDebug>
#include <stdio.h>
#include <iostream>
#include "mediamvs.h"

mediamvs::mediamvs(QObject *parent)
  : QThread(parent)
{
    //new_tcpClient = new QTcpSocket(this);

    m_tcpClient = new QTcpSocket(this);   //实例化 m_tcpClient

}

int mediamvs::connectSpecCamera()
{
    int nRet = MV_OK;
    int nPacketSize;
    MV_CC_DEVICE_INFO stDevInfo = {0};
    MV_GIGE_DEVICE_INFO stGigEDev = {0};

    char * nCurrentIp ="192.168.1.1" ;
    char * nNetExport ="192.168.1.1" ;
    unsigned int nIp1, nIp2, nIp3, nIp4, nIp;
    sscanf(nCurrentIp, "%d.%d.%d.%d", &nIp1, &nIp2, &nIp3, &nIp4);
    nIp = (nIp1 << 24) | (nIp2 << 16) | (nIp3 << 8) | nIp4;
    stGigEDev.nCurrentIp = nIp;
    sscanf(nNetExport, "%d.%d.%d.%d", &nIp1, &nIp2, &nIp3, &nIp4);
    nIp = (nIp1 << 24) | (nIp2 << 16) | (nIp3 << 8) | nIp4;
    stGigEDev.nNetExport = nIp;
    stDevInfo.nTLayerType = MV_GIGE_DEVICE;
    stDevInfo.SpecialInfo.stGigEInfo = stGigEDev;

    nRet = MV_CC_CreateHandle(&handle, &stDevInfo);
    if (MV_OK != nRet)
    {
         qDebug()<<"Create Handle fail! nRet ="<<nRet;
         goto end;
    }

    // 打开设备
    nRet = MV_CC_OpenDevice(handle);
    if (MV_OK != nRet)
    {
        qDebug()<<"Open Device fail! nRet ="<<nRet;
        goto end;
    }
    // 探测网络最佳包大小(只对GigE相机有效)
    nPacketSize = MV_CC_GetOptimalPacketSize(handle);
    if (nPacketSize > 0)
    {
        nRet = MV_CC_SetIntValue(handle,"GevSCPSPacketSize",nPacketSize);
        if(nRet != MV_OK)
        {
            qDebug()<<"Warning: Set Packet Size nRet ="<<nRet;
        }
    }
    else
    {
        qDebug()<<"Warning: Get Packet Size fail nRet ="<<nRet;
    }

    // 设置触发模式为off
    nRet = MV_CC_SetEnumValue(handle, "TriggerMode", MV_TRIGGER_MODE_OFF);
    if (MV_OK != nRet)
    {
        qDebug()<<"Set Trigger Mode fail! nRet ="<<nRet;
    }
    return 0;

    end:
    if (handle != NULL)
    {
        MV_CC_DestroyHandle(handle);
        handle = NULL;
    }
    return -1;
}

int mediamvs::startGrab(void *handle)
{
    // 获取数据包大小
    MVCC_INTVALUE stParam;
    memset(&stParam, 0, sizeof(MVCC_INTVALUE));
    int nRet = MV_CC_GetIntValue(handle, "PayloadSize", &stParam);
    if (MV_OK != nRet)
    {
        qDebug()<<"Get PayloadSize fail! nRet ="<<nRet;
        return -1;
    }
    g_nPayloadSize = stParam.nCurValue;
    // 开始取流
    nRet = MV_CC_StartGrabbing(handle);
    if (MV_OK != nRet)
    {
       qDebug()<<"Start Grabbing fail! nRet ="<<nRet;
       return -1;
    }

   return 0;
}

int mediamvs::stopGrab(void *handle)
{
    // 停止取流
   int nRet = MV_CC_StopGrabbing(handle);
   if (MV_OK != nRet)
   {
       qDebug()<<"Start Grabbing fail! nRet ="<<nRet;
       return -1;
   }
   return 0;
}

int mediamvs::closeDev(void *handle)
{
    // 关闭设备
    int nRet = MV_CC_CloseDevice(handle);
    if (MV_OK != nRet)
    {
        qDebug()<<"Close Device fail! nRet ="<<nRet;
        return -1;
    }
    // 销毁句柄
    nRet = MV_CC_DestroyHandle(handle);
    if (MV_OK != nRet)
    {
        qDebug()<<"Destroy Handle fail! nRet ="<<nRet;
        return -1;
    }
    handle = NULL;
   return 0;
}

void mediamvs::run(){
    ImgCallbackGrab();
    this->exec();
}

void mediamvs::Run_FunSlot()
{
    qDebug()<<"RUN 子线程功能函数 mediamvs ID:"<<QThread::currentThreadId();
    run();
}

bool PrintDeviceInfo(MV_CC_DEVICE_INFO* pstMVDevInfo)
{
    if (NULL == pstMVDevInfo)
    {
        qDebug("The Pointer of pstMVDevInfo is NULL!\n");
        return false;
    }
    if (pstMVDevInfo->nTLayerType == MV_GIGE_DEVICE)
    {
        int nIp1 = ((pstMVDevInfo->SpecialInfo.stGigEInfo.nCurrentIp & 0xff000000) >> 24);
        int nIp2 = ((pstMVDevInfo->SpecialInfo.stGigEInfo.nCurrentIp & 0x00ff0000) >> 16);
        int nIp3 = ((pstMVDevInfo->SpecialInfo.stGigEInfo.nCurrentIp & 0x0000ff00) >> 8);
        int nIp4 = (pstMVDevInfo->SpecialInfo.stGigEInfo.nCurrentIp & 0x000000ff);

        // 打印当前相机ip和用户自定义名字
        qDebug("Device Model Name: %s\n", pstMVDevInfo->SpecialInfo.stGigEInfo.chModelName);
        qDebug("CurrentIp: %d.%d.%d.%d\n" , nIp1, nIp2, nIp3, nIp4);
        qDebug("UserDefinedName: %s\n\n" , pstMVDevInfo->SpecialInfo.stGigEInfo.chUserDefinedName);
    }
    else if (pstMVDevInfo->nTLayerType == MV_USB_DEVICE)
    {
        qDebug("Device Model Name: %s\n", pstMVDevInfo->SpecialInfo.stUsb3VInfo.chModelName);
        qDebug("UserDefinedName: %s\n\n", pstMVDevInfo->SpecialInfo.stUsb3VInfo.chUserDefinedName);
    }
    else
    {
        qDebug("Not support.\n");
    }

    return true;
}

void __stdcall ImageCallBackEx(unsigned char * pData, MV_FRAME_OUT_INFO_EX* pFrameInfo, void* pUser)
{
    if (pFrameInfo)
    {
        qDebug("GetOneFrame, Width[%d], Height[%d], nFrameNum[%d]\n",pFrameInfo->nWidth, pFrameInfo->nHeight, pFrameInfo->nFrameNum);
    }
}

int mediamvs::ImgCallbackGrab()
{
    MV_CC_DEVICE_INFO_LIST stDeviceList;
    unsigned int nIndex = 0;
    int nPacketSize;
    memset(&stDeviceList, 0, sizeof(MV_CC_DEVICE_INFO_LIST));
    // 枚举设备
    int nRet = MV_CC_EnumDevices(MV_GIGE_DEVICE | MV_USB_DEVICE, &stDeviceList);
    if (MV_OK != nRet)
    {
        qDebug()<<"MV_CC_EnumDevices fail! nRet ="<<nRet;
        goto end;
    }
    if (stDeviceList.nDeviceNum > 0)
    {
        for (int i = 0; i < stDeviceList.nDeviceNum; i++)
        {
            qDebug("[device %d]:\n", i);
            MV_CC_DEVICE_INFO* pDeviceInfo = stDeviceList.pDeviceInfo[i];
            if (NULL == pDeviceInfo)
            {
                break;
            }
            PrintDeviceInfo(pDeviceInfo);
        }
    }
    else
    {
        qDebug("Find No Devices!\n");
        goto end;
    }

    // 选择设备并创建句柄
    nRet = MV_CC_CreateHandle(&handle, stDeviceList.pDeviceInfo[nIndex]);

    if (MV_OK != nRet)
    {
         qDebug()<<"Create Handle fail! nRet ="<<nRet;
         goto end;
    }

    // 打开设备
    nRet = MV_CC_OpenDevice(handle);
    if (MV_OK != nRet)
    {
        qDebug()<<"Open Device fail! nRet ="<<nRet;
        goto end;
    }
    // 探测网络最佳包大小(只对GigE相机有效)
    nPacketSize = MV_CC_GetOptimalPacketSize(handle);
    if (nPacketSize > 0)
    {
        nRet = MV_CC_SetIntValue(handle,"GevSCPSPacketSize",nPacketSize);
        if(nRet != MV_OK)
        {
            qDebug()<<"Warning: Set Packet Size nRet ="<<nRet;
        }
    }
    else
    {
        qDebug()<<"Warning: Get Packet Size fail nRet ="<<nRet;
    }

    // 设置触发模式为off
    nRet = MV_CC_SetEnumValue(handle, "TriggerMode", MV_TRIGGER_MODE_OFF);
    if (MV_OK != nRet)
    {
        qDebug()<<"Set Trigger Mode fail! nRet ="<<nRet;
    }

    // 注册抓图回调
    nRet = MV_CC_RegisterImageCallBackEx(handle, ImageCallBackEx, handle);
    if (MV_OK != nRet)
    {
        qDebug("MV_CC_RegisterImageCallBackEx fail! nRet [%x]\n", nRet);
        goto end;
    }

    // 开始取流
    nRet = MV_CC_StartGrabbing(handle);
    if (MV_OK != nRet)
    {
        qDebug("MV_CC_StartGrabbing fail! nRet [%x]\n", nRet);
        goto end;
    }

    return 0;
end:
    if (handle != NULL)
    {
        MV_CC_DestroyHandle(handle);
        handle = NULL;
    }
    return -1;
}

mediamvs::~mediamvs()
{
    delete m_tcpClient ;

}
