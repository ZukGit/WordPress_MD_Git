package SortMethod;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 插入排序
 */
public class InsertSort {
	
	
    public synchronized static int[]  insertSort(int[] sourceArray)  {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 对位置移动的次数统计
        int changeNum = 0 ;
        
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {

            // 记录要插入的数据
            int tmp = arr[i];

            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
                changeNum++;
                System.out.println("第"+changeNum+"次交换位置");  
            }

            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
                changeNum++;
                System.out.println("第"+changeNum+"次交换位置");  
            }

        }
        System.out.println("总共交换"+changeNum+"次");    
        return arr;
    }
    
        
    public static void main(String[] args) {
    	System.out.println("=======================最快情况=======================");
    	int order_arr[] = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] new_order_arr = InsertSort.insertSort(order_arr);
        System.out.println(Arrays.toString(new_order_arr));
    	System.out.println("=======================最慢情况=======================");
        int revert_arr[] = new int[]{10,1,2,3,4,5,6,7,8,9};
        int[] newrevert_arr = InsertSort.insertSort(revert_arr);
        System.out.println(Arrays.toString(newrevert_arr));

    }
}

/*
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
总共交换18次
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
*/
