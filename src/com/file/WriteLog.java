package com.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Edward on 14-3-26.
 */
public class WriteLog {
    public static void writeLog(String filename,String content){
        try{
            File file = new File(filename);
            if(!file.exists()){
                if(!file.createNewFile()){
                    System.out.println("error when creating logfile");
                }
            }
            FileWriter fw = new FileWriter(file,true);
            fw.write(content);

        }catch (IOException ex){
            System.out.println("fail in writing content");
        }
    }
}
