package SortMethod;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 冒泡排序
 */
public class BubbleSort {
	
    public synchronized static int[] bubbleSort(int[] sourceArray)  {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 对位置移动的次数统计
        int changeNum = 0 ;
        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = false;
                    changeNum++;
                    System.out.println("第"+changeNum+"次交换位置");    
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println("总共交换"+changeNum+"次");    
        return arr;
    }
    
    public static void main(String[] args) {
    	System.out.println("=======================最快情况=======================");
        int order_arr[] = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] new_order_arr = BubbleSort.bubbleSort(order_arr);
        System.out.println(Arrays.toString(new_order_arr));
    	System.out.println("=======================最慢情况=======================");
        int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
        int[] newrevert_arr = BubbleSort.bubbleSort(revert_arr);
        System.out.println(Arrays.toString(newrevert_arr));

    }
}


/**
=======================最快情况=======================
总共交换0次
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
第35次交换位置
第36次交换位置
第37次交换位置
第38次交换位置
第39次交换位置
第40次交换位置
第41次交换位置
第42次交换位置
第43次交换位置
第44次交换位置
第45次交换位置
总共交换45次
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


**/