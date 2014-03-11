package com.file;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edward
 */
public class ReadConfigFile{

    public static Map<String,String> ReadConfigArgs(String filename){
        Map<String,String> ArgMap = new HashMap<String, String>();
        try {
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line=null;
            while((line=br.readLine())!=null){
                //WARRING:test array only for test
                String[] vec = line.split(":");
                ArgMap.put(vec[0], vec[1]);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadConfigFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadConfigFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ArgMap;
    }
}

