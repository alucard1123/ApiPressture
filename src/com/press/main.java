package com.press;

import com.file.ReadConfigFile;
import com.tool.Debuger;

import java.io.*;
import java.util.Map;

/**
 * Created by Edward on 14-1-20.
 */
public class main {

    public static void main(String[] args){

        //active debug mark
        Debuger.SetDebugModule(false);
        //load debug file into argMap
        Map<String,String> argMap = ReadConfigFile.ReadConfigArgs("config.ini");
        String ip = argMap.get("ServiceIP");
        int apiName = Integer.parseInt(argMap.get("ApiName"));
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("token.txt")));
            String token = null;
            while((token=br.readLine())!=null){
                switch (apiName){
                    case 1 :
                        

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
        int looper = Integer.parseInt(args[1]);

    }
}
