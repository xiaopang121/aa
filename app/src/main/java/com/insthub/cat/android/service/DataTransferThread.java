package com.insthub.cat.android.service;

import com.common.android.flog.KLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * User:macbook
 * DATE:2018/1/7 23:29
 * Desc:${DESC}
 */

public class DataTransferThread extends Thread {

    private Socket msocket;
    public InputStream inputStream;
    public OutputStream outputStream;

    private boolean isruning=true;

    private TcpManager.TcpConnectionCallback mTcpConnectionCallback;


    public DataTransferThread(Socket paramSocket, TcpManager.TcpConnectionCallback  callback)
    {
        this.msocket = paramSocket;

        mTcpConnectionCallback = callback;
    }



    public void sendMessage(String msg) {
        try {
            outputStream =msocket.getOutputStream();
            outputStream.write(msg.getBytes("utf-8"));
            outputStream.flush();
            mTcpConnectionCallback.onConnectionState(TcpManager.CLIENT_STATE_CORRECT_WRITE,"数据发送成功");

        } catch (IOException e) {
            mTcpConnectionCallback.onConnectionState(TcpManager.CLIENT_STATE_ERROR,e.getMessage());
        }
    }


    public void setRunning(boolean running)
    {
        isruning = running;
    }

    @Override
    public void run() {
        try {
            while (isruning) {
                inputStream = msocket.getInputStream();
                while (inputStream.available()==0){
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final byte[] buffer = new byte[1024];//创建接收缓冲区
                final int len = inputStream.read(buffer);//数据读出来，并且数据的长度
                mTcpConnectionCallback.onData(buffer);
            }

            if(msocket!=null){
                try {
                    msocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(msocket!=null){
                try {
                    msocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            KLog.i("关闭连接，释放资源");
        }
    }
}


