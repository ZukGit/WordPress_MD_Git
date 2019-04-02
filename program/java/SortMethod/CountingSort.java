package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ��������
 */
public class CountingSort {

	public synchronized static int[]  countingSort(int[] sourceArray)  {
	        // �� arr ���п��������ı��������
	        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

	        int maxValue = getMaxValue(arr);

	        return countingSortInner(arr, maxValue);
	    }

	public  static  int[] countingSortInner(int[] arr, int maxValue) {
	        int bucketLen = maxValue + 1;
	        int[] bucket = new int[bucketLen];

	        for (int value : arr) {
	            bucket[value]++;
	        }

	        int sortedIndex = 0;
	        for (int j = 0; j < bucketLen; j++) {
	            while (bucket[j] > 0) {
	                arr[sortedIndex++] = j;
	                bucket[j]--;
					changeNum++;
					System.out.println("��" + changeNum + "�ν���λ��");
	            }
	        }
	        return arr;
	    }
     static int changeNum = 0;  // ��������
	public  static  int getMaxValue(int[] arr) {
	        int maxValue = arr[0];
	        for (int value : arr) {
	            if (maxValue < value) {
	                maxValue = value;
	            }
	        }
	        return maxValue;
	    }
	    
	public static void main(String[] args) {
		System.out.println("=======================������=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = CountingSort.countingSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum = 0 ;
		System.out.println("=======================�������=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = CountingSort.countingSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}

/*
=======================������=======================
��1�ν���λ��
��2�ν���λ��
��3�ν���λ��
��4�ν���λ��
��5�ν���λ��
��6�ν���λ��
��7�ν���λ��
��8�ν���λ��
��9�ν���λ��
��10�ν���λ��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================�������=======================
��1�ν���λ��
��2�ν���λ��
��3�ν���λ��
��4�ν���λ��
��5�ν���λ��
��6�ν���λ��
��7�ν���λ��
��8�ν���λ��
��9�ν���λ��
��10�ν���λ��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

*/

