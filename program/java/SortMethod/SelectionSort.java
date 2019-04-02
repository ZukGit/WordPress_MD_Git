package SortMethod;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * ѡ������
 */
public class SelectionSort {
	
        public synchronized static int[] selectionSort(int[] sourceArray)  {
        	
        	//  // �� arr ���п��������ı��������
            int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

            // ��λ���ƶ��Ĵ���ͳ��
            int changeNum = 0 ;
            
            // �ܹ�Ҫ���� N-1 �ֱȽ�
            for (int i = 0; i < arr.length - 1; i++) {
                int min = i;

                // ÿ����Ҫ�ȽϵĴ��� N-i
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < arr[min]) {
                        // ��¼Ŀǰ���ҵ�����СֵԪ�ص��±�
                        min = j;
                    }
                }

                // ���ҵ�����Сֵ��iλ�����ڵ�ֵ���н���
                if (i != min) {
                    int tmp = arr[i];
                    arr[i] = arr[min];
                    arr[min] = tmp;
                    changeNum++;
                    System.out.println("��"+changeNum+"�ν���λ��");    
                }

            }
            System.out.println("�ܹ�����"+changeNum+"��");    
            return arr;
        }
    
        
        
    public static void main(String[] args) {
    	System.out.println("=======================������=======================");
    	int order_arr[] = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] new_order_arr = SelectionSort.selectionSort(order_arr);
        System.out.println(Arrays.toString(new_order_arr));
    	System.out.println("=======================�������=======================");
        int revert_arr[] = new int[]{10,1,2,3,4,5,6,7,8,9};
        int[] newrevert_arr = SelectionSort.selectionSort(revert_arr);
        System.out.println(Arrays.toString(newrevert_arr));

    }
}

/*
=======================������=======================
�ܹ�����0��
[10]
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
�ܹ�����9��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
*/
