package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ��������
 */
public class BucketSort {

	public static final InsertSort insertSort = new InsertSort();

	public synchronized static int[] bucketSort(int[] sourceArray) {
        // �� arr ���п��������ı��������
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return bucketSortInner(arr, 5);
    }

	public static int[] bucketSortInner(int[] arr, int bucketSize) {
        if (arr.length == 0) {
            return arr;
        }

        int minValue = arr[0];
        int maxValue = arr[0];
        for (int value : arr) {
            if (value < minValue) {
                minValue = value;
            } else if (value > maxValue) {
                maxValue = value;
            }
        }

        int bucketCount = (int) Math.floor((maxValue - minValue) / bucketSize) + 1;
        int[][] buckets = new int[bucketCount][0];

        // ����ӳ�亯�������ݷ��䵽����Ͱ��
        for (int i = 0; i < arr.length; i++) {
            int index = (int) Math.floor((arr[i] - minValue) / bucketSize);
            buckets[index] = arrAppend(buckets[index], arr[i]);
        }

        int arrIndex = 0;
        for (int[] bucket : buckets) {
            if (bucket.length <= 0) {
                continue;
            }
            
			changeNum++;
			System.out.println("��" + changeNum + "��ִ�в������򷽷�");
            // ��ÿ��Ͱ������������ʹ���˲�������
            bucket = insertSort.insertSort(bucket);   // ���������˲���������� insertSort

            for (int value : bucket) {
                arr[arrIndex++] = value;
            }
        }

        return arr;
    }
   static int changeNum = 0 ;

    /**
     * �Զ����ݣ�����������
     *
     * @param arr
     * @param value
     */
	public static int[] arrAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }



	public static void main(String[] args) {
		System.out.println("=======================������=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = BucketSort.bucketSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum=0;
		System.out.println("=======================�������=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = BucketSort.bucketSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}


/*
 =======================������=======================
��1��ִ�в������򷽷�
�ܹ�����0��
��2��ִ�в������򷽷�
�ܹ�����0��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================�������=======================
��1��ִ�в������򷽷�
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
�ܹ�����14��
��2��ִ�в������򷽷�
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
�ܹ�����14��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 */

