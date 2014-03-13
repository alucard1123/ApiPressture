package com.thread;

import com.net.SendGetRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Edward on 14-3-12.
 */
public class P_Betting implements Runnable{
    String token = null;
    String ip = null;
    int loop = 0;
    static String head = "/CwlProApi/rest/transaction/betting?";
    public P_Betting(String token,String ip,int loop){
        this.token = token;
        this.ip = ip;
        this.loop = loop;
    }
    public void run(){
        int successCount = 0;
        JSONObject jo = new JSONObject();
        //set base parameter
        //ATTENTION:loop can not be greater than 9999999
        try {
            jo.put("amount","200");
            jo.put("betcode","123456");
            jo.put("betdata","1;1;2;1;01|02|03|04|05|06|07|-16|-");
            jo.put("lotterytype","10001");
            while(loop>0){
                jo.put("ordernumber",10000000+loop);
                try {
                    String url = "http://"+head+ip+"access_token="+
                            token+"&requestParam="+ URLEncoder.encode(jo.toString(), "UTF-8");
                    String returned = SendGetRequest.SendUrlRequest(url);
                    JSONObject r_jo = new JSONObject(returned);
                    if(r_jo.get("ReturnCode").equals("00000")){
                        successCount++;
                    }
                } catch (UnsupportedEncodingException e) {
                    System.out.println("Error while encoding url");
                } catch (IOException e) {
                    System.out.println("Error while sending message");
                }catch (JSONException e) {
                    System.out.println("error while doing JSON get");
                }
                loop--;
            }
            System.out.println("token:"+token+" complete with:"+successCount);
        } catch (JSONException e) {
            System.out.println("error while doing JSON put");
        }
    }
}
