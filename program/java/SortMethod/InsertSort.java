package SortMethod;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * ��������
 */
public class InsertSort {
	
	
    public synchronized static int[]  insertSort(int[] sourceArray)  {
        // �� arr ���п��������ı��������
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // ��λ���ƶ��Ĵ���ͳ��
        int changeNum = 0 ;
        
        // ���±�Ϊ1��Ԫ�ؿ�ʼѡ����ʵ�λ�ò��룬��Ϊ�±�Ϊ0��ֻ��һ��Ԫ�أ�Ĭ���������
        for (int i = 1; i < arr.length; i++) {

            // ��¼Ҫ���������
            int tmp = arr[i];

            // ���Ѿ�������������ұߵĿ�ʼ�Ƚϣ��ҵ�����С����
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
                changeNum++;
                System.out.println("��"+changeNum+"�ν���λ��");  
            }

            // ���ڱ���С����������
            if (j != i) {
                arr[j] = tmp;
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
        int[] new_order_arr = InsertSort.insertSort(order_arr);
        System.out.println(Arrays.toString(new_order_arr));
    	System.out.println("=======================�������=======================");
        int revert_arr[] = new int[]{10,1,2,3,4,5,6,7,8,9};
        int[] newrevert_arr = InsertSort.insertSort(revert_arr);
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
��14�ν���λ��
��15�ν���λ��
��16�ν���λ��
��17�ν���λ��
��18�ν���λ��
�ܹ�����18��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
*/
