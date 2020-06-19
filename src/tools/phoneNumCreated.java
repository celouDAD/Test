package tools;

import java.util.Random;

public class phoneNumCreated {
	public static String getPhoneNum() {
		Random ran = new Random();
		 String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
		 int index=getNum(0,telFirst.length-1);  
		 StringBuffer stb=new StringBuffer();
		 for(int j = 0;j<8;j++) {
			 int i = ran.nextInt(10);
			 stb.append(i);
		 }
        String first=telFirst[index];
        String phoneNum=first+stb;
		return phoneNum;		
	}
	public static int getNum(int start,int end) {
		 start=0;
		 end=9;
         return (int)(Math.random()*(end-start+1)+start);    
     }
}
