#include "mainwindow.h"
#include "MvCameraControl.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent)
{
    m_tcpClient = new QTcpSocket(this);   //实例化 m_tcpClient
    m_tcpClient->abort();                 //取消原有连接
    //ePFConnect_server1();
    m_timeHB = new QTimer();
    connect(m_timeHB,&QTimer::timeout,this,&MainWindow::SendData);
    m_timeHB->start(1000);

    jasonThread = new JasonThread;
    QThread *org_threadjason = new QThread;
    jasonThread->moveToThread(org_threadjason);
    connect(org_threadjason, &JasonThread::finished, jasonThread, &QObject::deleteLater);
    connect(this, &MainWindow::jasonToThread, jasonThread, &JasonThread::Run_Jason);
    org_threadjason->start();
    emit jasonToThread();

    mediamm = new mediamvs;
    QThread *org_thread1 = new QThread;
    org_thread1 = new QThread;
    mediamm->moveToThread(org_thread1);
    connect(this, SIGNAL(mediaToThread()), mediamm, SLOT(Run_FunSlot()));
    //connect(mediamm, SIGNAL(signalData()), this, SLOT(slotSendData()));

    emit mediaToThread();
}

MainWindow::~MainWindow()
{

}

void MainWindow::SendData()
{
    QByteArray  a;
    a.append("1");
    ePFSendDataToserver(a);
}

void  MainWindow::ePFSendDataToserver(QByteArray  tBArray)
{
    m_tcpClient->write(tBArray);
    qDebug()<<"111111111111111111111111";
    if(this->m_tcpClient->waitForBytesWritten(1000))
    {

    }
}
