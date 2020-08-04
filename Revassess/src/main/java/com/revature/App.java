package com.revature;

public class App 
{
    public static void main( String[] args )
    {
    	String s1 = "hello";
    	String s2 = "HeLLo";
    	User me = new User(1, "Patrick", "Boronski", "broski", "securePassword", "Admin");
    	int[] nums = {1, 2, 3, 4, 5};
    	int num = 1111;
    	
    	System.out.println(stringsAreEqual(s1, s2));
    	System.out.println(me);
    	System.out.println(sumAllInts(nums));
    	System.out.println(raiseEachDigit(num));
    }
    
    public static boolean stringsAreEqual(String s1, String s2) {
    	return s1.equalsIgnoreCase(s2);
    }
    
    public static int sumAllInts(int[] ints) {
    	int sum = 0;
    	for(int i=0; i<ints.length; i++) {
    		sum += ints[i];
    	}
    	return sum;
    }
    
    public static int raiseEachDigit(int number) {
    	String sNum = Integer.toString(number);
    	int sum = 0;
    	int digit;
    	
    	for(int i=1; i<=sNum.length(); i++) {
    		digit = Integer.parseInt(sNum.substring(i-1, i));
    		sum += Math.pow(digit, sNum.length());
    	}
    	
    	return sum;
    }
    
}

