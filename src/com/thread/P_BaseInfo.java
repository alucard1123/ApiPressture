package com.thread;

import com.exception.HttpException;
import com.net.SendGetRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * Created by Edward on 14-3-11.
 */
public class P_BaseInfo implements Runnable{

    String token = null;
    String ip = null;
    static String head = "/CwlProApi/rest/user/searchBaseInfo?";
    int loop = 0;
    public P_BaseInfo(String token,String ip,int loop){
        this.token = token;
        this.ip = ip;
        this.loop = loop;
    }

    @Override
    public void run(){
        int successCount = 0;
        while (loop >0){
            String url = "http://"+ip+head+"access_token="+token+"&requestParam=";
            try {
                String returned = SendGetRequest.SendUrlRequest(url);
                if(returned!=null){
                    JSONObject jo = new JSONObject(returned);
                    if(jo.get("ReturnCode").equals("00000")){
                        successCount++;
                    }
                }
                Thread.sleep(100);
            } catch (IOException e) {
                System.out.println("unexpected error in sending request");
                e.printStackTrace();
            } catch (JSONException e) {
                System.out.println("unexpected error in get JSON");
            } catch (InterruptedException e) {
                System.out.println("unexpected error in sleep");
            } catch (HttpException e) {
                System.out.println("caught http error "+e.getHttpStatus());
            }
            loop--;
        }
        System.out.println("token:"+token+" complete with:"+successCount);
    }

}
