package com.thread;

import com.exception.HttpException;
import com.net.SendGetRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Edward on 14-3-12.
 */
public class P_BetDetail implements Runnable{
    String token = null;
    String ip = null;
    int loop = 0;
    static String head = "/CwlProApi/rest/transaction/queryBetDetail?";

    public P_BetDetail(String token,String ip,int loop){
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
            }catch (JSONException je){
                System.out.println("unexpected error in get JSON");
            }
            catch (IOException e) {
                System.out.println("unexpected error in sending request");
            } catch (HttpException e) {
                System.out.println("caught http error "+e.getHttpStatus());
            }
            loop--;
        }
        System.out.println("token:"+token+" complete with:"+successCount);
    }
}
