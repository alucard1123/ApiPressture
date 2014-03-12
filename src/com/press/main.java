package com.press;

import com.file.ReadConfigFile;
import com.thread.P_BaseInfo;
import com.thread.P_BetDetail;
import com.thread.P_GameParam;
import com.thread.P_SubAccount;
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
        int LoopTime = Integer.parseInt(args[0]);
        int apiName = Integer.parseInt(argMap.get("ApiName"));
        String apiArg = argMap.get("ApiArg");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("token.txt")));
            String token;
            while((token=br.readLine())!=null){
                switch (apiName){
                    case 1 :
                        new Thread(new P_BaseInfo(token,ip,LoopTime)).start();
                        break;
                    case 2 :
                        new Thread(new P_SubAccount(token,ip,LoopTime)).start();
                        break;
                    case 3 :
                        new Thread(new P_GameParam(token,ip,LoopTime,apiArg));
                        break;
                    case 4 :
                        break;
                    case 5 :
                        break;
                    case 6 :
                        new Thread(new P_BetDetail(token,ip,LoopTime));
                        break;
                    default:
                        System.out.println("no matched type:"+apiName);
                        break;

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
}
