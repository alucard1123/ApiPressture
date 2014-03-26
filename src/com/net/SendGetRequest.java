package com.net;

import com.exception.HttpException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Edward on 14-1-20.
 */
public class SendGetRequest {

    public static String SendUrlRequest(String url) throws HttpException,IOException {
        URL yahoo;
        yahoo = new URL(url);
        URLConnection yc = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
        try{
            yc = yahoo.openConnection();
            if(yc!=null){
                isr = new InputStreamReader(yc.getInputStream());
            }else{
                System.out.println("yc is null");
            }
            if(isr!=null){
                in = new BufferedReader(isr);
            }else{
                System.out.println("isr is null");
            }
            String inputLine = in.readLine();
            if(in!=null) {in.close();}
            if(isr!=null) {isr.close();}
            return inputLine;
        }catch (IOException ex){
            HttpURLConnection hyc = (HttpURLConnection)yc;
            HttpException he = new HttpException();
            he.setHttpStatus(hyc.getResponseCode());
            throw he;
        }
    }
}
