package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
	
	

	public synchronized static int[]  heapSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int len = arr.length;

        buildMaxHeap(arr, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

	public static void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

	public static  void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

	public static  void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
		changeNum++;
		System.out.println("第" + changeNum + "次交换位置");
    }

    
	static int changeNum = 0;
	public static void main(String[] args) {
		System.out.println("=======================正序堆排序情况=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = HeapSort.heapSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum =0 ;
		System.out.println("=======================逆序堆排序情况=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = HeapSort.heapSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}

/*
=======================正序堆排序情况=======================
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
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================逆序堆排序情况=======================
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
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 */
