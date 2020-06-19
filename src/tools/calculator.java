package tools;

import java.text.DecimalFormat;
import java.util.Scanner;

public class calculator {

	public static void main(String[] args) {
		double p=0;//生产总值与现在比正在百分比
		System.out.println("请输入年增长率：");
		Scanner scan=new Scanner(System.in);
		double r=scan.nextDouble();
		System.out.println("请输入年数：");
		int n=scan.nextInt();
		p=Math.pow((1+r),n);
		System.out.println(p);
				
	}

}
