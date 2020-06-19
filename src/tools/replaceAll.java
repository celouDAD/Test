package tools;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class replaceAll {
	public static void main(String[] args) {
		String s1 ="";
		String rep1 = s1.replaceAll("\r\n","");
		String r=s1.replaceAll("	", "-");
		//String a=r.replaceAll(", ", ",");
		//String b=a.replaceAll(";", "\",\"");
		System.out.println(r);

	}

}
