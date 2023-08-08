#ifndef MEDIAMVS_H
#define MEDIAMVS_H
#include <QThread>
#include <QTcpSocket>
#include <QTimer>
#include <QTime>
#include <QFile>
#include <QtCore/QCoreApplication>
#include <QThread>
#include <QQueue>
#include <stdio.h>
#include<iostream>
#include "MvCameraControl.h"



class mediamvs : public QThread
{
    Q_OBJECT
public:
    explicit mediamvs(QObject *parent=0);
    ~mediamvs();
    int connectSpecCamera();
    int startGrab(void *handle);
    int stopGrab(void *handle);
    int closeDev(void *handle);
    int ImgCallbackGrab();

protected:
    void run();

private:
    QTcpSocket *m_tcpClient;
    void* handle = NULL;
    QTimer *timer;
    unsigned int g_nPayloadSize = 0;

private slots:
    void Run_FunSlot();



signals:
   // void signalData();

};

#endif // MEDIAMVS_H
