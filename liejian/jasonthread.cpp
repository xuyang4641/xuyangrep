#include "jasonthread.h"
#include <QTimer>
#include <QString>
#include <QTime>
#include <QStringList>
#include <QDebug>
#include <QHostAddress>
#include <QDataStream>
#include <QDir>
#include <QJsonObject>
#include <QJsonDocument>
#include <QJsonParseError>
#include <QJsonArray>
#include <QProcess>
#include "mainwindow.h"
#include <QGraphicsItemAnimation>
#include <QTimeLine>
#include <QGraphicsScene>
#include <QDebug>
#include <QString>
#include <QPainter>
#include <QMessageBox>
#include <QPixmap>
#include <QLabel>
#include <QStringList>
#include <QThread>
#include <QApplication>
#include <QVBoxLayout>
#include <QLabel>
#include <QPushButton>
#include <QTimer>
#include <QIODevice>
#include <QTcpSocket>
#include <QList>
#include <QGraphicsPixmapItem>
#include <QString>
#include <QTime>
#include <QStringList>
#include <QDebug>
#include <QHostAddress>
#include <QDataStream>
#include <QJsonObject>
#include <QJsonDocument>
#include <QJsonParseError>
#include <QJsonArray>
#include <string.h>

bool m_bServerConnected = false;

JasonThread::JasonThread(QObject *parent)
{
  m_tcpClient = new QTcpSocket(this);   //实例化 m_tcpClient
}

void JasonThread::run()
{

    m_connect = new QTimer();
    connect(m_connect,&QTimer::timeout,this,&JasonThread::reConnectServerSolt);
    m_connect->start(10000);

    m_timeHB = new QTimer();
    connect(m_timeHB,&QTimer::timeout,this,&JasonThread::ConnectServerHBSolt);
    m_timeHB->start(60000);

    this->exec();



}
// 关闭线程


void JasonThread::stop()
{
    m_is_stopped = true;
}

void JasonThread::Run_Jason()
{
    qDebug()<<"RUN 子线程功能函数 Jason ID:"<<QThread::currentThreadId();
    run();
}

JasonThread::~JasonThread()
{
   delete m_tcpClient ;   //实例化 m_tcpClient

}


void JasonThread::initMyTcpClient()
{
    //初始化TCP客户端
    m_tcpClient->abort();                 //取消原有连接
    connect(m_tcpClient, SIGNAL(connected()),this,SLOT(onConnectSlot()));
    connect(m_tcpClient, SIGNAL(disconnected()), this, SLOT(onDisConnectSlot()));
    connect(m_tcpClient, SIGNAL(stateChanged(QAbstractSocket::SocketState)), this, SLOT(onSocketStateChange(QAbstractSocket::SocketState)));
    connect(m_tcpClient, SIGNAL(readyRead()), this,SLOT(tcpReadData()));
    ePFConnect_server();




}

void JasonThread::onDisConnectSlot()
{

}
void JasonThread::tcpReadData()
{
   while(m_tcpClient->canReadLine())
   {
        QByteArray buffer = m_tcpClient->readLine();
        qDebug()<<"Rcv:"<<buffer;
        emit PostDataItemSignal(buffer);
   }
}

void JasonThread::onSocketStateChange(QAbstractSocket::SocketState socketState)
{
    QString SocketState;
    switch (socketState)
    {
    case QAbstractSocket::UnconnectedState:
         qDebug()<<"UnconnectedState";
         qDebug()<<socketState;
         m_bServerConnected = false;
         m_connect->start();
         SocketState = socketState;
        break;
    case  QAbstractSocket::HostLookupState:
         qDebug()<<"HostLookupState";
         qDebug()<<socketState;
          SocketState = socketState;
        break;
    case  QAbstractSocket::ConnectingState:
         qDebug()<<"ConnectingState";
         qDebug()<<socketState;
          SocketState = socketState;
        break;

    case  QAbstractSocket::ConnectedState:
         qDebug()<<"ConnectedState";
         qDebug()<<socketState;
          SocketState = socketState;
        break;
    case  QAbstractSocket::BoundState:
         qDebug()<<"BoundState";
         qDebug()<<socketState;
          SocketState = socketState;
        break;

    case  QAbstractSocket::ClosingState:
         qDebug()<<"ClosingState";
         qDebug()<<socketState;

         SocketState = socketState;
        break;
    case  QAbstractSocket::ListeningState:
         qDebug()<<"ListeningState";
         qDebug()<<socketState;
          SocketState = socketState;
        break;
    default:
        break;
    }
}

void JasonThread::reConnectServerSolt()
{
    //如果设备先开机需要等待服务器在线
    if(m_bServerConnected == false)
    {
        initMyTcpClient();
    }
    else if(m_bServerConnected == true)
    {
        m_connect->stop();
        qDebug()<<"重连成功重连成功重连成功重连成功重连成功重连成功重连成功";
        qDebug()<<"Stop Tcp reconnect Timer.";
        qDebug()<<"Stop Tcp reconnect Timer.";
    }
}
void JasonThread::onConnectSlot()
{
    qDebug()<<"connect to server successed.";
}


void JasonThread::ePFConnect_server()
{
    m_tcpClient->connectToHost( "10.55.202.229" ,9061);
    if (m_tcpClient->waitForConnected(3000))                // 连接成功则进入if{}
    {
        qDebug("State:%d\n",m_tcpClient->state());          // State: 2; 2代表ConnectingState，正确的应该为3（ConnectedState）
       // LCD_BASE_DATA basePlat;
       // ePFSend_DEV_SELFCHECKINFO_Data_server(DEVICE, basePlat);
        m_bServerConnected = true;
    }
    else
    {
        m_bServerConnected = false;
    }
    //emit PostTcpStatusItemSignal(m_bServerConnected);


}


void JasonThread::ePFSend_DEV_SELFCHECKINFO_Data_server(INFO_SEND_HOST host, LCD_BASE_DATA basePlat)
{
/*
    QString strmac;
    QString   softVersion;
    QDateTime time = QDateTime::currentDateTime(); //获取当前时间
    DEVSELFCHECKINFO selfCkInfo;
    memset(&selfCkInfo,0x00,sizeof(selfCkInfo));
    selfCkInfo.base.nCmdkind = CMD_LCD_DEVICE_CHECKSELF;         //命令字
    selfCkInfo.base.nSerialNo = 0x0000001;                       //命令流水号
    selfCkInfo.base.nTimestamp = time.toTime_t();                //将当前时间转为时间戳
    softVersion  =  getDirName("/home/firefly/current/");
    qDebug()<<"  softVersion    softVersion    softVersion    softVersion  "<<softVersion;
    strmac = getMacAddress();
    strmac.append("\0");
    memcpy(selfCkInfo.nDeviceID, strmac.toLatin1().data(), strmac.length());
    memcpy(selfCkInfo.nDeviceType, EPF_DEVICETYPE_NAME , sizeof(EPF_DEVICETYPE_NAME ));
    memcpy(selfCkInfo.hardwareType, Device_Terminal , sizeof(Device_Terminal));
    memcpy(selfCkInfo.OSVersion, OS_Version , sizeof(OS_Version));
    memcpy(selfCkInfo.OSVersionNumber, OS_vesion_number , sizeof(OS_vesion_number));
    memcpy(selfCkInfo.HardwareVer, APP_VERSION , sizeof(APP_VERSION));
    memcpy(selfCkInfo.SoftwareVer, softVersion .toLatin1().data() , softVersion.length());
    memcpy(selfCkInfo.nFactoryName, EPF_FACTARY_NAME, sizeof(EPF_FACTARY_NAME));
    qDebug()<<"命令字:"<<selfCkInfo.base.nCmdkind ;
    qDebug()<<"设备ID"<<selfCkInfo.nDeviceID;
    qDebug()<<selfCkInfo.nDeviceType;
    qDebug()<<selfCkInfo.HardwareVer;
    qDebug()<<selfCkInfo.SoftwareVer;
    qDebug()<<selfCkInfo.nFactoryName;

    if(host == PLAT)
    {
        selfCkInfo.base.nCmdkind = basePlat.nCmdkind;
        selfCkInfo.base.nSerialNo = basePlat.nSerialNo;
    }

    QByteArray tBArray;
    tBArray.append((char *)&selfCkInfo,  sizeof(DEVSELFCHECKINFO));
    ePFSendDataToserver(tBArray);
*/
}

QString JasonThread::getDirName(QString  path)
{

    QDir dir(path);
    QStringList   list =  dir.entryList(QDir::Dirs | QDir::Files | QDir::NoDotAndDotDot );
    QString m = list[0];
    return m;
}

void  JasonThread::SlotMessage(QByteArray s)
{
    qDebug()<<"sssssssss"<<s;
    s = s.toHex();
    s.append("\r\n");
    m_tcpClient->write(s);
    qDebug()<<"成功没:"<<s;
    if(this->m_tcpClient->waitForBytesWritten(1000))
    {
        qDebug() << "发送数据成功:"<<s;
    }
}

QString JasonThread::getMacAddress()
{
        QList<QNetworkInterface> nets = QNetworkInterface::allInterfaces();// 获取所有网络接口列表
        int nCnt = nets.count();
        QString strNetName;
        QString strMacAddr;
        for(int i = 0; i < nCnt; i ++)
        {
            // 如果此网络接口被激活并且正在运行并且不是回环地址，则就是我们需要找的Mac地址
            if(nets[i].flags().testFlag(QNetworkInterface::IsUp) && !nets[i].flags().testFlag(QNetworkInterface::IsLoopBack))
            {
                strNetName = nets[i].name();
                if(strNetName.startsWith("eth0"))
                {
                    strMacAddr = nets[i].hardwareAddress();
                    break;
                }
            }
        }
        return strMacAddr;
}

void JasonThread::ePFSendHBData_server()                  //发送心跳包数据
{
    QDateTime time = QDateTime::currentDateTime(); //获取当前时间
    HEARTBEAT hb;
    hb.base.nCmdkind = 1;
    hb.base.nSerialNo = 0x0000001;
    hb.base.nTimestamp = time.toTime_t();        //将当前时间转为时间戳
    qDebug() <<"时间戳2 = " <<hb.base.nTimestamp<<endl;     //显示时间为2018年
    memcpy(hb.nDeviceID, getMacAddress().toLatin1().data(), getMacAddress().length());
    memcpy(hb.nDeviceType, "ABCD" , 4);
    QByteArray tBArray;                                   QDateTime  sm = QDateTime::currentDateTime();

    tBArray.append((char *)&hb,  sizeof(HEARTBEAT));
    ePFSendDataToserver(tBArray);
}


void JasonThread::ConnectServerHBSolt()
{
    if(m_bServerConnected == true)
    {
        ePFSendHBData_server();
    }
}



void JasonThread::ePFSendDataToserver(QByteArray tBArray)         //发送 TCP 数据
{
    tBArray = tBArray.toHex();
    tBArray.append("\r\n");
    m_tcpClient->write(tBArray);
    if(this->m_tcpClient->waitForBytesWritten(1000))
    {
        qDebug() << "发送数据成功:"<<tBArray;
    }
}

void  JasonThread::UpdateStatus(QByteArray update)
{
    ePFSendDataToserver(update);
}



