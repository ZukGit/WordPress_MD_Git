package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
	
	public synchronized static int[]  quickSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return quickSortInner(arr, 0, arr.length - 1);
    }

	public  static int[] quickSortInner(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSortInner(arr, left, partitionIndex - 1);
            quickSortInner(arr, partitionIndex + 1, right);
        }
        return arr;
    }

	public  static  int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }
	static int changeNum = 0;
	public  static  void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
		changeNum++;
		System.out.println("第" + changeNum + "次交换位置");
    }
    

	public static void main(String[] args) {
		System.out.println("=======================最快情况=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = QuickSort.quickSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum=0; 
		System.out.println("=======================最慢情况=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = QuickSort.quickSort(revert_arr);
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
第14次交换位置
第15次交换位置
第16次交换位置
第17次交换位置
第18次交换位置
第19次交换位置
第20次交换位置
第21次交换位置
第22次交换位置
第23次交换位置
第24次交换位置
第25次交换位置
第26次交换位置
第27次交换位置
第28次交换位置
第29次交换位置
第30次交换位置
第31次交换位置
第32次交换位置
第33次交换位置
第34次交换位置
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


*/