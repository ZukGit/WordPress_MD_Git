package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

	public synchronized static int[] radixSort(int[] sourceArray) {
		int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

		int maxDigit = getMaxDigit(arr);
		return radixSortInner(arr, maxDigit);
	}

	/**
	 * 获取最高位数
	 */
	public static int getMaxDigit(int[] arr) {
		int maxValue = getMaxValue(arr);
		return getNumLenght(maxValue);
	}

	public static int getMaxValue(int[] arr) {
		int maxValue = arr[0];
		for (int value : arr) {
			if (maxValue < value) {
				maxValue = value;
			}
		}
		return maxValue;
	}

	public static int getNumLenght(long num) {
		if (num == 0) {
			return 1;
		}
		int lenght = 0;
		for (long temp = num; temp != 0; temp /= 10) {
			lenght++;
		}
		return lenght;
	}

	public static int[] radixSortInner(int[] arr, int maxDigit) {
		int mod = 10;
		int dev = 1;

		for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
			// 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
			int[][] counter = new int[mod * 2][0];

			for (int j = 0; j < arr.length; j++) {
				int bucket = ((arr[j] % mod) / dev) + mod;
				counter[bucket] = arrayAppend(counter[bucket], arr[j]);
			}

			int pos = 0;
			for (int[] bucket : counter) {
				for (int value : bucket) {
					arr[pos++] = value;
				}
			}
		}

		return arr;
	}

	/**
	 * 自动扩容，并保存数据
	 *
	 * @param arr
	 * @param value
	 */
	public static int[] arrayAppend(int[] arr, int value) {
		arr = Arrays.copyOf(arr, arr.length + 1);
		arr[arr.length - 1] = value;
		return arr;
	}

	public static void main(String[] args) {
		System.out.println("=======================最快情况=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = RadixSort.radixSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		System.out.println("=======================最慢情况=======================");
		int revert_arr[] = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		int[] newrevert_arr = RadixSort.radixSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}
