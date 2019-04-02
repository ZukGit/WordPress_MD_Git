package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ������
 */
public class HeapSort {
	
	

	public synchronized static int[]  heapSort(int[] sourceArray) {
        // �� arr ���п��������ı��������
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
		System.out.println("��" + changeNum + "�ν���λ��");
    }

    
	static int changeNum = 0;
	public static void main(String[] args) {
		System.out.println("=======================������������=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = HeapSort.heapSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum =0 ;
		System.out.println("=======================������������=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = HeapSort.heapSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}

/*
=======================������������=======================
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
��11�ν���λ��
��12�ν���λ��
��13�ν���λ��
��14�ν���λ��
��15�ν���λ��
��16�ν���λ��
��17�ν���λ��
��18�ν���λ��
��19�ν���λ��
��20�ν���λ��
��21�ν���λ��
��22�ν���λ��
��23�ν���λ��
��24�ν���λ��
��25�ν���λ��
��26�ν���λ��
��27�ν���λ��
��28�ν���λ��
��29�ν���λ��
��30�ν���λ��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================������������=======================
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
��11�ν���λ��
��12�ν���λ��
��13�ν���λ��
��14�ν���λ��
��15�ν���λ��
��16�ν���λ��
��17�ν���λ��
��18�ν���λ��
��19�ν���λ��
��20�ν���λ��
��21�ν���λ��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 */
