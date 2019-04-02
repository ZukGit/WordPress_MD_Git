package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ��������
 */
public class ShellSort {

	public synchronized static int[] shellSort(int[] sourceArray) {
		// �� arr ���п��������ı��������
		int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

		int gap = 1;
		// ��λ���ƶ��Ĵ���ͳ��
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
					System.out.println("��" + changeNum + "�ν���λ��");
				}
				arr[j + gap] = tmp;
			}
			gap = (int) Math.floor(gap / 3);
		}
		System.out.println("�ܹ�����" + changeNum + "��");
		return arr;
	}

	public static void main(String[] args) {
		System.out.println("=======================������=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = ShellSort.shellSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		System.out.println("=======================�������=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = ShellSort.shellSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}

/*
=======================������=======================
�ܹ�����0��
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
�ܹ�����13��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

*/