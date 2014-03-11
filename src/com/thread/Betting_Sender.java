package com.thread;

import com.net.SendGetRequest;
import com.str.ResponseCutter;


/**
 * Created by Edward on 14-3-4.
 */
public class Betting_Sender implements Runnable{
    String ip = null;
    int thr = 0;
    int tg = 0;
    int looper =0;
    public Betting_Sender(String ip, int thr, int tg, int looper){
        //thr can't be more than 89999
        this.ip=ip;
        this.thr=thr;
        this.tg=tg;
        this.looper=looper*100;

    }

    @Override
    public void run() {
        try {
        	while (looper > 1) {
                long startTime = System.currentTimeMillis();
                String UidHead = "100900";
                String AccHead = "100000000000";
                int MarkUID = 10000+thr;
                String UID = UidHead+tg+MarkUID;
                int Add = tg*100000+MarkUID;
                String AccountCode = null;
                if(Add>=969998){
                    AccountCode=AccHead+(Add+30001);
                }else{
                    AccountCode=AccHead+"0"+(Add+30001);
                }
                String Url = "http://"+ip+"/fcgi-bin/PCoreService.fcg?Method=Betting&" +
                        "UID="+UID+"&AccountCode="+AccountCode+
                        "&LotteryType=02&WagerIssue=2014193&BetData=111111111" +
                        "&Amount=1&Agencyid=111&OrderNumber=11";
                try {
                    String returned = SendGetRequest.SendUrlRequest(Url);
                    String Code = ResponseCutter.HttpResponseCutter(returned);

                    int retry = 0;
                    if(!Code.equals("00000")) {
                    	System.out.println("error thread group:" + tg  + " error thread id:" + thr);
                        System.out.println("error code:" + Code);
                    }
                    long costTime = System.currentTimeMillis()-startTime;
                    System.out.println(thr+"	costs	"+costTime+"	retrys:	"+retry);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                looper--;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
