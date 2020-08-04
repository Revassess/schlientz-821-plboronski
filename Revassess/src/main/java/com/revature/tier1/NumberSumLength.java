package com.revature.tier1;

public class NumberSumLength {

	public static boolean checkNumberPowerLength(long num) {
    	String sNum = Long.toString(num);
    	long sum = 0;
    	int digit;
    	
    	for(int i=1; i<=sNum.length(); i++) {
    		digit = Integer.parseInt(sNum.substring(i-1, i));
    		sum += Math.pow(digit, sNum.length());
    	}
    	
    	return sum == num;
	}
}
