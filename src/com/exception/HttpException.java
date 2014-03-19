package com.exception;

/**
 * Created by Edward on 14-3-19.
 */
public class HttpException extends Exception {
    private int Status = 0;
    public void setHttpStatus(int Status){
        this.Status = Status;
    }
    public String getHttpStatus(){
        String reStr = ""+this.Status;
        return reStr;
    }
}
