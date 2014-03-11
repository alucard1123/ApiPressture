package com.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Edward on 14-1-20.
 */
public class SendGetRequest {

    public static String SendUrlRequest(String url) throws Exception{
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine="";

        inputLine = in.readLine();
        in.close();
        return inputLine;
    }

}
