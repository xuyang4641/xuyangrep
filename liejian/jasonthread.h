#ifndef JASONTHREAD_H
#define JASONTHREAD_H
#include <QThread>
#include <QTcpSocket>
#include <QString>
#include <QTime>
#include <QThread>
#include <QTimer>
#include <QStringList>
#include <QColor>
#include <QUdpSocket>
#include <QNetworkInterface>

struct LCD_BASE_DATA
{
    int nCmdkind;       //命令字
    int nSerialNo;      // 命令流水号
    unsigned int  nTimestamp;    // 发送时间
};

typedef struct HeartBeat
{
    LCD_BASE_DATA base;     //命令字，流水号，时间为基础数据
    char nDeviceID[32]; //设备id
    char nDeviceType[32];     //设备类型

} HEARTBEAT;

enum INFO_SEND_HOST
{
      PLAT=1, DEVICE
};

class JasonThread : public QThread
{
    Q_OBJECT
public:
    explicit JasonThread(QObject *parent=0);
    ~JasonThread();
    QTimer * m_timeHB;
    QTimer * m_connect;
    QTimer * udpTimer;
    void stop();  // 添加stop()方法

private:

    QTcpSocket * m_tcpClient;
    volatile bool m_is_stopped;  // 添加标识变量
    QUdpSocket *udpSocket;//UDP套接字
    QList<QString> onvifPath;

protected:

    void run();
    void initMyTcpClient();
    void ePFConnect_server();
    QString getDirName(QString  path);
    QString getMacAddress();
    void ePFSendHBData_server() ;
    void ePFSendDataToserver(QByteArray tBArray);
    void ePFSend_DEV_SELFCHECKINFO_Data_server(INFO_SEND_HOST host, LCD_BASE_DATA basePlat);

public slots:

    void Run_Jason();
    void onConnectSlot();
    void onSocketStateChange(QAbstractSocket::SocketState socketState);
    void ConnectServerHBSolt();
    void reConnectServerSolt();
    void onDisConnectSlot();
    void tcpReadData();
    void UpdateStatus(QByteArray update);
    void SlotMessage(QByteArray  s);


signals:
     void  SignalStartTime(QString b);
     void PostDataItemSignal(QByteArray weatherStr);
     void  PostTcpStatusItemSignal(bool);
     void RTSPcreat(QString );

};

#endif //
