package com.press;

import com.file.ReadConfigFile;
import com.thread.P_BaseInfo;
import com.thread.P_BetDetail;
import com.thread.P_Betting;
import com.thread.P_GameParam;
import com.thread.P_SubAccount;
import com.tool.Debuger;

import java.io.*;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

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
        JSONObject jo  = new JSONObject();
        long start = System.currentTimeMillis();
        int tokenNumber = 0;
        try {
        	jo.put("Amount", argMap.get("B_Amount"));
        	jo.put("BetCode", argMap.get("B_BetCode"));
        	jo.put("BetData", argMap.get("B_BetData"));
        	jo.put("LotteryType",argMap.get("B_LotteryType"));
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("token.txt")));
            String token;
            long threadstart = System.currentTimeMillis();
            while((token=br.readLine())!=null){
                switch (apiName){
                    case 1 :
                        new Thread(new P_BaseInfo(token,ip,LoopTime)).start();
                        break;
                    case 2 :
                        new Thread(new P_SubAccount(token,ip,LoopTime)).start();
                        break;
                    case 3 :
                        new Thread(new P_GameParam(token,ip,LoopTime,apiArg)).start();
                        break;
                    case 4 :
                        break;
                    case 5 :
                    	new Thread(new P_Betting(token,ip,LoopTime,jo)).start();
                        break;
                    case 6 :
                        new Thread(new P_BetDetail(token,ip,LoopTime)).start();
                        break;
                    default:
                        System.out.println("no matched type:"+apiName);
                        break;
                }
                tokenNumber++;
            }
            long starttotal = System.currentTimeMillis()-threadstart;
            System.out.println("init threads costs:"+starttotal+"ms");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("IO error");
        } catch (JSONException e) {
			System.out.println("init JSON error");
		}

        long fintotal = System.currentTimeMillis()-start;
        double perCost = fintotal/(tokenNumber*LoopTime);
        System.out.println("Press ended.Average cost:"+perCost+"ms");
    }
}
