package tools;

import java.util.Arrays;

public class sort {
	public static void main(String[] args) {
	    int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48,3};

	    //System.out.println(bubbleSort(array));
	    //System.out.println(selectionSort(array));
	    System.out.println(insertSort(array));
		
	}
	
	/**
	 * 冒泡排序
	 * @param array
	 * @return
	 */
	private static String bubbleSort(int[] array) {
		/*
		 * 如果数组为空或者为null直接输出原数组
		 */
		if(array==null||array.length<=1) {
			return Arrays.toString(array);
		}
		
		int length=array.length;
		for(int i=0;i<length;i++) {
			for(int j=0;j<length-1;j++) {
				if(array[j]>array[j+1]) {
					int temp=array[j+1];
					System.out.println("排序前：array[j]="+array[j]+",array[j+1]="+array[j+1]+",temp="+temp);
					array[j+1]=array[j];
					array[j]=temp;
					System.out.println("排序后：array[j]="+array[j]+",array[j+1]="+array[j+1]+",temp="+temp);
				}
			}
		}
		return Arrays.toString(array);
	}
	
	/**
	 * 选择排序
	 * @param array
	 * @return
	 */
	private static String selectionSort(int[] array) {
		/*
		 * 如果数组为空或者为null直接输出原数组
		 */
		if(array==null||array.length<=1) {
			return Arrays.toString(array);
		}
		
		int length=array.length;
		
		for(int i=0;i<length-1;i++) {
			int minIdex=i;
			for(int j=i+1;j<length;j++) {
				if(array[j]<array[minIdex]) {
					minIdex=j;
				}
			}
			if(i!=minIdex) {
				int temp=array[minIdex];
				array[minIdex]=array[i];
				array[i]=temp;
			}
		}
				
		return Arrays.toString(array);
		// TODO Auto-generated method stub

	}
	
	/**
	 * 插入排序
	 * @param array
	 * @return
	 */
	private static String insertSort(int[] array) {
		
		/*
		 * 如果数组为空或者为null直接输出原数组
		 */
		if(array==null||array.length<=1) {
			return Arrays.toString(array);
		}
		
		int length=array.length;
		
		int insertNum;
		
		for(int i=1;i<length;i++) {
			insertNum=array[i];
			int j=i-1;
			while(j>=0&&array[j]>insertNum) {
				array[j+1]=array[j];
				j--;
			}
			array[j+1]=insertNum;
		}
		
		return Arrays.toString(array);
		// TODO Auto-generated method stub

	}
	
	

}
