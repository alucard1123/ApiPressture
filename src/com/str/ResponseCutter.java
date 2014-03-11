package com.str;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edward on 14-1-20.
 */
public class ResponseCutter {

    public static String HttpResponseCutter(String input){
        Map<String,String> ArgMap = new HashMap();
        String[] argPair = input.replaceAll("[${\"*\"}]", "").split(",");
        for(int i = 0;i<argPair.length;i++){
        	String[] rtnPair = argPair[i].split(":");
        	if(rtnPair.length==2){
        		ArgMap.put(rtnPair[0],rtnPair[1]);
        	}
        	else if(rtnPair[0].equals("ReturnCode")){
        		ArgMap.put("ReturnCode", "99999");
        	}
        }
        return ArgMap.get("ReturnCode");
    }
}
