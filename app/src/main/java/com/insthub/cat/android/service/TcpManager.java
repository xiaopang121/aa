package com.insthub.cat.android.service;

import com.common.android.flog.KLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * User:macbook
 * DATE:2018/1/7 23:26
 * Desc:${DESC}
 */

public class TcpManager  extends Thread{

    private Socket socket;

    private InetAddress inetAddress;                             //IP地址
    private int port;                                        //端口号

    public static int  CLIENT_CONNECTION_OK=100; //链接成功
    public static int  CLIENT_CONNECTION_ERROR=200;//链接失败
    public static int  CLIENT_STATE_CORRECT_READ=7;
    public static int  CLIENT_STATE_CORRECT_WRITE=8;               //正常通信信息
    public static int  CLIENT_STATE_ERROR=9;                 //发生错误异常信息
    public static int  CLIENT_STATE_IOFO=10;                  //发送SOCKET信息

    private TcpConnectionCallback mTcpConnectionCallback;

    private DataTransferThread mDataTransferThread;

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }
    public void setPort(int port) {
        this.port = port;
    }


    public void setTcpConnectionCallback(TcpConnectionCallback param)
    {
        mTcpConnectionCallback = param;
    }



    public void sendData(String msg)
    {
        if(mDataTransferThread!=null)
        {
            mDataTransferThread.sendMessage(msg);
        }
    }



    @Override
    public void run() {
        if(socket == null){
            try {
                KLog.i("启动tcp 连接");
                socket=new Socket(inetAddress,port);
                mDataTransferThread =   new DataTransferThread(socket,mTcpConnectionCallback);  //启动接收线程
                mDataTransferThread.start();
                if(mTcpConnectionCallback!=null)
                {
                    mTcpConnectionCallback.onConnectionState(CLIENT_CONNECTION_OK,"链接成功");
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(mTcpConnectionCallback!=null)
                {
                    mTcpConnectionCallback.onConnectionState(CLIENT_CONNECTION_ERROR,e.getMessage());
                }
            }
        }
    }



    public  void close(){
        if (socket !=null){
            try {

                if(mDataTransferThread!=null)
                {
                    mDataTransferThread.setRunning(false);
                }

                if(socket!=null)
                {
                    socket.close();
                }
            } catch (IOException e) {
            }
        }

        KLog.i("断开服务器链接");
    }





    public interface  TcpConnectionCallback
    {

        //服务器链接状态
        public void onConnectionState(int  state,String msg);


        public void onData(byte[]data);



    }
}
