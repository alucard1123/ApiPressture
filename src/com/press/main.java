package com.press;

import com.file.ReadConfigFile;
import com.thread.Betting_Sender;
import com.tool.Debuger;

import java.util.Map;

/**
 * Created by Edward on 14-1-20.
 */
public class main {

    public static void main(String[] args){

        //active debug mark
        Debuger.SetDebugModule(false);
        //load debug file into argMap
        Map<String,String> argMap = ReadConfigFile.ReadConfigArgs("config.txt");
        int threads = Integer.parseInt(argMap.get("threads"));
        String ip = argMap.get("ServiceIP");
        //String tg = argMap.get("tgmark");
        
        int tg = Integer.parseInt(args[0]);
        int looper = Integer.parseInt(args[1]);
        
        //int ThreadStart = threads/20;
        //System.out.println("----------------------100%");
        for(int i = 0;i<threads;i++){
            //new Thread(new Sender(cb,ip,i,tg,looper)).start();
        	//v2 new Thread(new Betting_Sender(cb,ip,i,tg,looper)).start();
            new Thread(new Betting_Sender(ip,i,tg,looper)).start();
            /*
            if(i%ThreadStart==0){
                System.out.print("=");
            }
            */
        }
        System.out.println("Done "+threads);

    }
}


        /*built api message
            http://192.168.3.93/fcgi-bin/PCoreService.fcg?Method=Register
                +   &Password=1234&UserName=aaaa&IDNumber=100000000000000000
                        +   &TrueName=bbbbb&Email=abc@ww.com&MobilePhone=13000000000&Agencyid=123
        IDNumber,MobilePhone count is checked
        */