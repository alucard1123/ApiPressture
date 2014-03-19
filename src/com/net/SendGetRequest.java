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
        URL yahoo = null;
        yahoo = new URL(url);
        URLConnection yc = null;
        InputStreamReader isr = null;
        try{
            yc = yahoo.openConnection();
            isr = new InputStreamReader(yc.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine="";

            inputLine = in.readLine();
            in.close();
            isr.close();
            return inputLine;
        }catch (IOException ex){
            HttpURLConnection hyc = (HttpURLConnection)yc;
            HttpException he = new HttpException();
            he.setHttpStatus(hyc.getResponseCode());
            throw he;
        }
    }
}
