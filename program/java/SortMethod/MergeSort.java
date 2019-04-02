package SortMethod;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 插入排序
 */
public class MergeSort {

	static int changeNum = 0;
	public synchronized static  int[] mergeSort(int[] sourceArray)  {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

		changeNum++;
		System.out.println("第" + changeNum + "次迭代");
		
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
		System.out.println("=======================最快情况=======================");
		int order_arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] new_order_arr = MergeSort.mergeSort(order_arr);
		System.out.println(Arrays.toString(new_order_arr));
		changeNum = 0 ; // 对迭代次数清零
		System.out.println("=======================最慢情况=======================");
		int revert_arr[] = new int[]{10,9,8,7,6,5,4,3,2,1};
		int[] newrevert_arr = MergeSort.mergeSort(revert_arr);
		System.out.println(Arrays.toString(newrevert_arr));
		changeNum = 0 ; // 对迭代次数清零
		System.out.println("=======================随意情况=======================");
		int rand_arr[] = new int[]{6,3,7,8,1,2,4,9,5,10};
		int[] newrRand_arr = MergeSort.mergeSort(rand_arr);
		System.out.println(Arrays.toString(newrRand_arr));
		changeNum = 0 ; // 对迭代次数清零
	}
}
/*
=======================最快情况=======================
第1次迭代
第2次迭代
第3次迭代
第4次迭代
第5次迭代
第6次迭代
第7次迭代
第8次迭代
第9次迭代
第10次迭代
第11次迭代
第12次迭代
第13次迭代
第14次迭代
第15次迭代
第16次迭代
第17次迭代
第18次迭代
第19次迭代
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================最慢情况=======================
第1次迭代
第2次迭代
第3次迭代
第4次迭代
第5次迭代
第6次迭代
第7次迭代
第8次迭代
第9次迭代
第10次迭代
第11次迭代
第12次迭代
第13次迭代
第14次迭代
第15次迭代
第16次迭代
第17次迭代
第18次迭代
第19次迭代
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
=======================随意情况=======================
第1次迭代
第2次迭代
第3次迭代
第4次迭代
第5次迭代
第6次迭代
第7次迭代
第8次迭代
第9次迭代
第10次迭代
第11次迭代
第12次迭代
第13次迭代
第14次迭代
第15次迭代
第16次迭代
第17次迭代
第18次迭代
第19次迭代
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

*/

