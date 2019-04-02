package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 插入排序
 */
public class BucketSort {

	public static final InsertSort insertSort = new InsertSort();

	public synchronized static int[] bucketSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
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

        // 利用映射函数将数据分配到各个桶中
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
			System.out.println("第" + changeNum + "次执行插入排序方法");
            // 对每个桶进行排序，这里使用了插入排序
            bucket = insertSort.insertSort(bucket);   // 这里引用了插入排序的类 insertSort

            for (int value : bucket) {
                arr[arrIndex++] = value;
            }
        }

        return arr;
    }
   static int changeNum = 0 ;

    /**
     * 自动扩容，并保存数据
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
		System.out.println("=======================最快情况=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = BucketSort.bucketSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum=0;
		System.out.println("=======================最慢情况=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = BucketSort.bucketSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));

	}
}


/*
 =======================最快情况=======================
第1次执行插入排序方法
总共交换0次
第2次执行插入排序方法
总共交换0次
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================最慢情况=======================
第1次执行插入排序方法
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
总共交换14次
第2次执行插入排序方法
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
总共交换14次
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 */

