package com.thread;

/**
 * Created by Edward on 14-3-11.
 */
public class P_SubAccount implements Runnable {

    String token = null;
    String ip = null;
    static String head = "/CwlProApi/rest/account/querySubAccount?";
    int loop = 0;
    public P_SubAccount(String token,String ip,int loop){
        this.token = token;
        this.ip = ip;
        this.loop = loop;
    }
    @Override
    public void run(){

    }
}
