package com.tool;

/**
 * Created by Edward on 14-1-14.
 */
public class Debuger {
    static boolean isDebugModule = true;
    public static void SetDebugModule(boolean debugMark){
        isDebugModule = debugMark;
    }
    public static void DebugOutprint(String dbgStr){
        if(isDebugModule){
            System.out.println(">>>Debug:"+dbgStr+"<<<");
        }
    }

    public static void DebugOutprint(String StrName,String dbgString){
        if(isDebugModule){
            System.out.println(">>>Debug:"+StrName+":"+dbgString+"<<<<");
        }
    }
}
