/****************************************************************************
** Meta object code from reading C++ file 'jasonthread.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.9.0)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "jasonthread.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'jasonthread.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.9.0. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
QT_WARNING_PUSH
QT_WARNING_DISABLE_DEPRECATED
struct qt_meta_stringdata_JasonThread_t {
    QByteArrayData data[21];
    char stringdata0[283];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_JasonThread_t, stringdata0) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_JasonThread_t qt_meta_stringdata_JasonThread = {
    {
QT_MOC_LITERAL(0, 0, 11), // "JasonThread"
QT_MOC_LITERAL(1, 12, 15), // "SignalStartTime"
QT_MOC_LITERAL(2, 28, 0), // ""
QT_MOC_LITERAL(3, 29, 1), // "b"
QT_MOC_LITERAL(4, 31, 18), // "PostDataItemSignal"
QT_MOC_LITERAL(5, 50, 10), // "weatherStr"
QT_MOC_LITERAL(6, 61, 23), // "PostTcpStatusItemSignal"
QT_MOC_LITERAL(7, 85, 9), // "RTSPcreat"
QT_MOC_LITERAL(8, 95, 9), // "Run_Jason"
QT_MOC_LITERAL(9, 105, 13), // "onConnectSlot"
QT_MOC_LITERAL(10, 119, 19), // "onSocketStateChange"
QT_MOC_LITERAL(11, 139, 28), // "QAbstractSocket::SocketState"
QT_MOC_LITERAL(12, 168, 11), // "socketState"
QT_MOC_LITERAL(13, 180, 19), // "ConnectServerHBSolt"
QT_MOC_LITERAL(14, 200, 19), // "reConnectServerSolt"
QT_MOC_LITERAL(15, 220, 16), // "onDisConnectSlot"
QT_MOC_LITERAL(16, 237, 11), // "tcpReadData"
QT_MOC_LITERAL(17, 249, 12), // "UpdateStatus"
QT_MOC_LITERAL(18, 262, 6), // "update"
QT_MOC_LITERAL(19, 269, 11), // "SlotMessage"
QT_MOC_LITERAL(20, 281, 1) // "s"

    },
    "JasonThread\0SignalStartTime\0\0b\0"
    "PostDataItemSignal\0weatherStr\0"
    "PostTcpStatusItemSignal\0RTSPcreat\0"
    "Run_Jason\0onConnectSlot\0onSocketStateChange\0"
    "QAbstractSocket::SocketState\0socketState\0"
    "ConnectServerHBSolt\0reConnectServerSolt\0"
    "onDisConnectSlot\0tcpReadData\0UpdateStatus\0"
    "update\0SlotMessage\0s"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_JasonThread[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
      13,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       4,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    1,   79,    2, 0x06 /* Public */,
       4,    1,   82,    2, 0x06 /* Public */,
       6,    1,   85,    2, 0x06 /* Public */,
       7,    1,   88,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       8,    0,   91,    2, 0x0a /* Public */,
       9,    0,   92,    2, 0x0a /* Public */,
      10,    1,   93,    2, 0x0a /* Public */,
      13,    0,   96,    2, 0x0a /* Public */,
      14,    0,   97,    2, 0x0a /* Public */,
      15,    0,   98,    2, 0x0a /* Public */,
      16,    0,   99,    2, 0x0a /* Public */,
      17,    1,  100,    2, 0x0a /* Public */,
      19,    1,  103,    2, 0x0a /* Public */,

 // signals: parameters
    QMetaType::Void, QMetaType::QString,    3,
    QMetaType::Void, QMetaType::QByteArray,    5,
    QMetaType::Void, QMetaType::Bool,    2,
    QMetaType::Void, QMetaType::QString,    2,

 // slots: parameters
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, 0x80000000 | 11,   12,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void,
    QMetaType::Void, QMetaType::QByteArray,   18,
    QMetaType::Void, QMetaType::QByteArray,   20,

       0        // eod
};

void JasonThread::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        JasonThread *_t = static_cast<JasonThread *>(_o);
        Q_UNUSED(_t)
        switch (_id) {
        case 0: _t->SignalStartTime((*reinterpret_cast< QString(*)>(_a[1]))); break;
        case 1: _t->PostDataItemSignal((*reinterpret_cast< QByteArray(*)>(_a[1]))); break;
        case 2: _t->PostTcpStatusItemSignal((*reinterpret_cast< bool(*)>(_a[1]))); break;
        case 3: _t->RTSPcreat((*reinterpret_cast< QString(*)>(_a[1]))); break;
        case 4: _t->Run_Jason(); break;
        case 5: _t->onConnectSlot(); break;
        case 6: _t->onSocketStateChange((*reinterpret_cast< QAbstractSocket::SocketState(*)>(_a[1]))); break;
        case 7: _t->ConnectServerHBSolt(); break;
        case 8: _t->reConnectServerSolt(); break;
        case 9: _t->onDisConnectSlot(); break;
        case 10: _t->tcpReadData(); break;
        case 11: _t->UpdateStatus((*reinterpret_cast< QByteArray(*)>(_a[1]))); break;
        case 12: _t->SlotMessage((*reinterpret_cast< QByteArray(*)>(_a[1]))); break;
        default: ;
        }
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        switch (_id) {
        default: *reinterpret_cast<int*>(_a[0]) = -1; break;
        case 6:
            switch (*reinterpret_cast<int*>(_a[1])) {
            default: *reinterpret_cast<int*>(_a[0]) = -1; break;
            case 0:
                *reinterpret_cast<int*>(_a[0]) = qRegisterMetaType< QAbstractSocket::SocketState >(); break;
            }
            break;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (JasonThread::*_t)(QString );
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&JasonThread::SignalStartTime)) {
                *result = 0;
                return;
            }
        }
        {
            typedef void (JasonThread::*_t)(QByteArray );
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&JasonThread::PostDataItemSignal)) {
                *result = 1;
                return;
            }
        }
        {
            typedef void (JasonThread::*_t)(bool );
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&JasonThread::PostTcpStatusItemSignal)) {
                *result = 2;
                return;
            }
        }
        {
            typedef void (JasonThread::*_t)(QString );
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&JasonThread::RTSPcreat)) {
                *result = 3;
                return;
            }
        }
    }
}

const QMetaObject JasonThread::staticMetaObject = {
    { &QThread::staticMetaObject, qt_meta_stringdata_JasonThread.data,
      qt_meta_data_JasonThread,  qt_static_metacall, nullptr, nullptr}
};


const QMetaObject *JasonThread::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *JasonThread::qt_metacast(const char *_clname)
{
    if (!_clname) return nullptr;
    if (!strcmp(_clname, qt_meta_stringdata_JasonThread.stringdata0))
        return static_cast<void*>(const_cast< JasonThread*>(this));
    return QThread::qt_metacast(_clname);
}

int JasonThread::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QThread::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 13)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 13;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 13)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 13;
    }
    return _id;
}

// SIGNAL 0
void JasonThread::SignalStartTime(QString _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 0, _a);
}

// SIGNAL 1
void JasonThread::PostDataItemSignal(QByteArray _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 1, _a);
}

// SIGNAL 2
void JasonThread::PostTcpStatusItemSignal(bool _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 2, _a);
}

// SIGNAL 3
void JasonThread::RTSPcreat(QString _t1)
{
    void *_a[] = { nullptr, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 3, _a);
}
QT_WARNING_POP
QT_END_MOC_NAMESPACE
