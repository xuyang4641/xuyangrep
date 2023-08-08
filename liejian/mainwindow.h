#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QGraphicsScene>
#include <QGraphicsView>
#include <QMenuBar>
#include <QGraphicsEllipseItem>
#include <QString>
#include <QStringList>
#include <QThread>
#include <QLabel>
#include <QDateTime>
#include <QPushButton>
#include <QTcpSocket>
#include <QHostAddress>
#include <QMessageBox>
#include <QByteArray>
#include <QTimer>
#include <QList>
#include <QNetworkReply>
#include <QFile>
#include <QProgressBar>
#include <QProcess>
#include <QUdpSocket>
#include <QDebug>
#include <QDomComment>
#include <QTime>
#include <QHostAddress>
#include <QMainWindow>
#include <QTcpSocket>
#include"jasonthread.h"
#include"mediamvs.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();
    void ePFSendDataToserver(QByteArray  tBArray) ;

signals:
    void jasonToThread();
    void mediaToThread();

public slots:
    void SendData();

private:
    QTcpSocket * m_tcpClient;
    QTimer * m_timeHB;
    JasonThread *jasonThread;
    mediamvs *mediamm;
};

#endif // MAINWINDOW_H
