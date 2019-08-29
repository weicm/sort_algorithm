package com.weicm;

import java.util.Arrays;

/**
 * 快速排序是由东尼·霍尔所发展的一种排序算法。在平均状况下，排序n个元素要O(nlogn)次比较。在最坏状况下则需要O(n^2)次比较，但这种状况并不常见。事实上，快速排序通常明显比其他O(nlogn)算法更快，因为它的内部循环可以在大部分的架构上很有效率地被实现出来。
 *
 * 快速排序使用分治策略(Divide and Conquer)来把一个序列分为两个子序列。步骤为：
 * * 从序列中挑出一个元素，作为"基准"(pivot).
 * * 把所有比基准值小的元素放在基准前面，所有比基准值大的元素放在基准的后面（相同的数可以到任一边），这个称为分区(partition)操作。
 * * 对每个分区递归地进行步骤1~2，递归的结束条件是序列的大小是0或1，这时整体已经被排好序了。
 */

public class QuickSortDriver {

    public static void main(String[] args) {
        Integer[] arr = {2, 8, 1, 5, 10, 6, 3, 7, 11, 5};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.asList(arr));
    }


    /**
     * 快速排序递归核心逻辑
     * 假设从左到右升序排序
     * @param arr 待排数组
     * @param start 每次递归起始索引
     * @param end 每次递归截止索引
     */
    static void quickSort(Integer[] arr, int start, int end) {
        //递归最后只有一个元素, 此时递归结束
        if(start >= end)
            return;
        //每次递归分区排序, 小于等于当前值的放在当前值左边,否则放在右边
        int mid = part(arr, start, end);
        //递归当前值左侧分区
        quickSort(arr, start, mid - 1);
        //递归当前值右侧分区
        quickSort(arr, mid + 1, end);
    }

    /**
     * 分区排序, 小于等于当前值的放在当前值左边,否则放在右边
     * @param arr 待排序数组
     * @param start 分区开始索引
     * @param end 分区截止索引
     * @return 当前排序元素最终索引
     */
    static int part(Integer[] arr, int start, int end) {
        //当前排序元素
        int tmp = arr[start];
        //当前排序元素索引
        int p = start;
        //分区开始和结束索引
        int i = start, j = end;
        //分区左右索引碰撞后结束, 否则继续
        while(i < j) {
            //从分区右侧找一个比排序元素小的值, 大于等于当前元素&&分区索引未碰撞则跳过, 找到后跳出循环
            while(arr[j] >= tmp && i < j)
                j--;
            //分区索引未碰撞, 说明从右侧找到了小于等于排序元素的值
            if (i < j) {
                //用找到的元素覆盖坑位, 并将分区左侧索引+1, 且记录坑位为右侧索引位置
                arr[i++] = arr[j];
                p = j;
            }
            //从分区左侧找一个比排序元素大的值, 小于当前元素&&分区索引未碰撞则跳过, 找到后跳出循环
            while(arr[i] < tmp && i < j)
                i++;
            //分区索引未碰撞, 说明从左侧找到了大于排序元素的值
            if (i < j) {
                //用找到的元素覆盖坑位, 并将分区右侧索引-1, 且记录坑位为左侧索引位置
                arr[j--] = arr[i];
                p = i;
            }
        }
        //将排序元素从新填入最终坑位
        arr[p] = tmp;
        //返回排序元素最终位置(最终坑位)
        return p;
    }
}
