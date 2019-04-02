package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ��������
 */
public class QuickSort {
	
	public synchronized static int[]  quickSort(int[] sourceArray) {
        // �� arr ���п��������ı��������
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
        // �趨��׼ֵ��pivot��
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
		System.out.println("��" + changeNum + "�ν���λ��");
    }
    

	public static void main(String[] args) {
		System.out.println("=======================������=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = QuickSort.quickSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum=0; 
		System.out.println("=======================�������=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = QuickSort.quickSort(revert_arr);
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
��31�ν���λ��
��32�ν���λ��
��33�ν���λ��
��34�ν���λ��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


*/