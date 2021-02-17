package com.java.plus.springboot.util;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class LogUtil {
	
	public void log() {
		
	    Stopwatch stopwatch = Stopwatch.createStarted();
	    

        long cost = stopwatch.stop().elapsed(TimeUnit.MILLISECONDS);
        
        System.out.println(cost);
		
	}
	
    @Override
    public String toString() {
        return "JsonCouponInfo{" +
//                "redpacketId=" + redpacketId +
//                ", hexiao=" + hexiao +
//                ", qianxiao=" + qianxiao +
                "}";
    }

}
