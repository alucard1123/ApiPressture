package com.thread;

/**
 * Created by Edward on 14-3-12.
 */
public class P_BetDetail {
    String token = null;
    String ip = null;
    int loop = 0;
    static String head = "/CwlProApi/rest/transaction/queryBetDetail?";

    public P_BetDetail(String token,String ip,int loop){
        this.token = token;
        this.ip = ip;
        this.loop = loop;

    }
}
