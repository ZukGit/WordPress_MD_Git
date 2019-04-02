package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 插入排序
 */
public class ShellSort {

	public synchronized static int[] shellSort(int[] sourceArray) {
		// 对 arr 进行拷贝，不改变参数内容
		int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

		int gap = 1;
		// 对位置移动的次数统计
		int changeNum = 0;

		while (gap < arr.length) {
			gap = gap * 3 + 1;
		}

		while (gap > 0) {
			for (int i = gap; i < arr.length; i++) {
				int tmp = arr[i];
				int j = i - gap;
				while (j >= 0 && arr[j] > tmp) {
					arr[j + gap] = arr[j];
					j -= gap;
					changeNum++;
					System.out.println("第" + changeNum + "次交换位置");
				}
				arr[j + gap] = tmp;
			}
			gap = (int) Math.floor(gap / 3);
		}
		System.out.println("总共交换" + changeNum + "次");
		return arr;
	}

	public static void main(String[] args) {
		System.out.println("=======================最快情况=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = ShellSort.shellSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		System.out.println("=======================最慢情况=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = ShellSort.shellSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}

/*
=======================最快情况=======================
总共交换0次
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
第11次交换位置
第12次交换位置
第13次交换位置
总共交换13次
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

*/