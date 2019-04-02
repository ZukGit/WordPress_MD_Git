package SortMethod;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * ð������
 */
public class BubbleSort {
	
    public synchronized static int[] bubbleSort(int[] sourceArray)  {
        // �� arr ���п��������ı��������
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // ��λ���ƶ��Ĵ���ͳ��
        int changeNum = 0 ;
        for (int i = 1; i < arr.length; i++) {
            // �趨һ����ǣ���Ϊtrue�����ʾ�˴�ѭ��û�н��н�����Ҳ���Ǵ��������Ѿ����������Ѿ���ɡ�
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                    changeNum++;
                    System.out.println("��"+changeNum+"�ν���λ��");    
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println("�ܹ�����"+changeNum+"��");    
        return arr;
    }
    
    public static void main(String[] args) {
    	System.out.println("=======================������=======================");
        int order_arr[] = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] new_order_arr = BubbleSort.bubbleSort(order_arr);
        System.out.println(Arrays.toString(new_order_arr));
    	System.out.println("=======================�������=======================");
        int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
        int[] newrevert_arr = BubbleSort.bubbleSort(revert_arr);
        System.out.println(Arrays.toString(newrevert_arr));

    }
}


/**
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
��35�ν���λ��
��36�ν���λ��
��37�ν���λ��
��38�ν���λ��
��39�ν���λ��
��40�ν���λ��
��41�ν���λ��
��42�ν���λ��
��43�ν���λ��
��44�ν���λ��
��45�ν���λ��
�ܹ�����45��
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


**/