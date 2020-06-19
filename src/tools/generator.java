package tools;

import java.util.Random;

public class generator {

	public static String generator(){
		Random r=new Random();
		String s="";
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < 11; i++) {
			for(int j =1;j<7;j++) {
				s="("+i+","+j+","+"\""+String.valueOf(r.nextInt(51)+50)+"\""+")";
				if(i<10) {
					s=s+",";
					//System.out.print(s);
					sb.append(s);
				}else {
					if(j<6) {
						s=s+",";
						//System.out.print(s);
						sb.append(s);
					}
				}
			}			
		}
		return sb.toString();
	}
}
