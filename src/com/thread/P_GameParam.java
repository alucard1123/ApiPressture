package com.thread;

import com.net.SendGetRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Edward on 14-3-12.
 */
public class P_GameParam implements Runnable{
    String token = null;
    String ip = null;
    int loop = 0;
    String LotteryType = null;
    static String head = "/CwlProApi/rest/transaction/getGameParam?";
    public P_GameParam(String token,String ip,int loop,String LotteryType){
        this.token = token;
        this.ip = ip;
        this.loop = loop;
        this.LotteryType=LotteryType;
    }

    @Override
    public void run(){
        //get a JSON object first
        JSONObject jo = new JSONObject();
        try {
            jo.put("LotteryType",LotteryType);
            int successCount = 0;
            while (loop>0){
                try{
                    String url = "http://"+ip+head+"access_token="+
                            token+"&requestParam="+ URLEncoder.encode(jo.toString(),"UTF-8");
                    //send url here
                    String returned = SendGetRequest.SendUrlRequest(url);
                    JSONObject r_jo = new JSONObject(returned);
                    if(r_jo.get("ReturnCode")=="00000"){
                        successCount++;
                    }
                } catch (UnsupportedEncodingException e) {
                    System.out.println("case url encoding exception");
                } catch (Exception e) {
                    System.out.println("unexpected error in sending request");
                }
                loop--;
            }
            System.out.println("token:"+token+" complete with:"+successCount);
        } catch (JSONException e) {
            System.out.println("Set JSON string fail with exception");
        }
    }
}
