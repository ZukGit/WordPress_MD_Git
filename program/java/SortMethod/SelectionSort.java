package SortMethod;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * 选择排序
 */
public class SelectionSort {
	
        public synchronized static int[] selectionSort(int[] sourceArray)  {
        	
        	//  // 对 arr 进行拷贝，不改变参数内容
            int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

            // 对位置移动的次数统计
            int changeNum = 0 ;
            
            // 总共要经过 N-1 轮比较
            for (int i = 0; i < arr.length - 1; i++) {
                int min = i;

                // 每轮需要比较的次数 N-i
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < arr[min]) {
                        // 记录目前能找到的最小值元素的下标
                        min = j;
                    }
                }

                // 将找到的最小值和i位置所在的值进行交换
                if (i != min) {
                    int tmp = arr[i];
                    arr[i] = arr[min];
                    arr[min] = tmp;
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
        int[] new_order_arr = SelectionSort.selectionSort(order_arr);
        System.out.println(Arrays.toString(new_order_arr));
    	System.out.println("=======================最慢情况=======================");
        int revert_arr[] = new int[]{10,1,2,3,4,5,6,7,8,9};
        int[] newrevert_arr = SelectionSort.selectionSort(revert_arr);
        System.out.println(Arrays.toString(newrevert_arr));

    }
}

/*
=======================最快情况=======================
总共交换0次
[10]
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
总共交换9次
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
*/
