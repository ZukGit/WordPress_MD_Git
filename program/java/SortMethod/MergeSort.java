package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ��������
 */
public class MergeSort {

	static int changeNum = 0;
	public synchronized static  int[] mergeSort(int[] sourceArray)  {
        // �� arr ���п��������ı��������
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

		changeNum++;
		System.out.println("��" + changeNum + "�ε���");
		
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }
    


	public synchronized static  int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }
    

	public static void main(String[] args) {
		System.out.println("=======================������=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = MergeSort.mergeSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum = 0 ; // �Ե�����������
		System.out.println("=======================�������=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = MergeSort.mergeSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));
		changeNum = 0 ; // �Ե�����������
		System.out.println("=======================�������=======================");
		int rand_arr[] = new int[]{6,3,7,8,1,2,4,9,5,10};
		int[] newrRand_arr = MergeSort.mergeSort(rand_arr);
		System.out.println(Arrays.toString(newrRand_arr));
		changeNum = 0 ; // �Ե�����������
	}
}
/*
=======================������=======================
��1�ε���
��2�ε���
��3�ε���
��4�ε���
��5�ε���
��6�ε���
��7�ε���
��8�ε���
��9�ε���
��10�ε���
��11�ε���
��12�ε���
��13�ε���
��14�ε���
��15�ε���
��16�ε���
��17�ε���
��18�ε���
��19�ε���
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================�������=======================
��1�ε���
��2�ε���
��3�ε���
��4�ε���
��5�ε���
��6�ε���
��7�ε���
��8�ε���
��9�ε���
��10�ε���
��11�ε���
��12�ε���
��13�ε���
��14�ε���
��15�ε���
��16�ε���
��17�ε���
��18�ε���
��19�ε���
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================�������=======================
��1�ε���
��2�ε���
��3�ε���
��4�ε���
��5�ε���
��6�ε���
��7�ε���
��8�ε���
��9�ε���
��10�ε���
��11�ε���
��12�ε���
��13�ε���
��14�ε���
��15�ε���
��16�ε���
��17�ε���
��18�ε���
��19�ε���
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

*/

