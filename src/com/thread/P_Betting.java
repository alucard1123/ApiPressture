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
    JSONObject jo = new JSONObject();
    public P_Betting(String token,String ip,int loop,JSONObject jo){
        this.token = token;
        this.ip = ip;
        this.loop = loop;
        this.jo = jo;
    }
    public void run(){
        int successCount = 0;
        
        //set base parameter
        //ATTENTION:loop can not be greater than 9999999
        try {
            while(loop>0){
                jo.put("OrderNumber",10000000+loop);
                try {
                    String url = "http://"+ip+head+"access_token="+
                            token+"&requestParam="+ URLEncoder.encode(jo.toString(), "UTF-8");
                    String returned = SendGetRequest.SendUrlRequest(url);
                    if(returned!=null){
                        JSONObject r_jo = new JSONObject(returned);
                        if(r_jo.get("ReturnCode").equals("00000")){
                            successCount++;
                        }
                        else{
                        	System.out.println(r_jo.get("ReturnCode"));
                        }
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
            System.out.println("token:"+token+" complete with:"+successCount+"successed");
        } catch (JSONException e) {
            System.out.println("error while doing JSON put");
        }
    }
}
