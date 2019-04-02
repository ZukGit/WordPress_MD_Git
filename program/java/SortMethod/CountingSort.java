package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 插入排序
 */
public class CountingSort {

	public synchronized static int[]  countingSort(int[] sourceArray)  {
	        // 对 arr 进行拷贝，不改变参数内容
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
					System.out.println("第" + changeNum + "次交换位置");
	            }
	        }
	        return arr;
	    }
     static int changeNum = 0;  // 交换次数
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
		System.out.println("=======================最快情况=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = CountingSort.countingSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum = 0 ;
		System.out.println("=======================最慢情况=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = CountingSort.countingSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}

/*
=======================最快情况=======================
第1次交换位置
第2次交换位置
第3次交换位置
第4次交换位置
第5次交换位置
第6次交换位置
第7次交换位置
第8次交换位置
第9次交换位置
第10次交换位置
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================最慢情况=======================
第1次交换位置
第2次交换位置
第3次交换位置
第4次交换位置
第5次交换位置
第6次交换位置
第7次交换位置
第8次交换位置
第9次交换位置
第10次交换位置
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

*/

