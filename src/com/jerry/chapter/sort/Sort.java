package com.jerry.chapter.sort;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author jerry
 * @Description 排序测试
 * @Date 2022-05-16 12:34
 * @Version 1.0
 **/
public class Sort {

    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 7, 2, 5, 8, 6};
        //insertSort(arr);
        //selectSort(arr);
        quickStart(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        LinkedList<Integer>[] arr1 = new LinkedList[100];
    }


    /**
     * 冒泡排序
     * <p>
     * 时间复杂度 O(n^2)
     * <p>
     * 空间复杂度O(1)
     * <p>
     * 稳定排序
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 插入排序：将数组分为已排序和未排序两组。初始已排序就是第一个元素。
     * 核心思想就是提取未排序中元素，在已排序中找到合适位置插入
     * <p>
     * 时间复杂度 O(n^2)
     * <p>
     * 空间复杂度O(1)
     * <p>
     * 稳定排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > val) {
                    //数据移动
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            //3 1 2
            arr[j + 1] = val;
        }
    }

    /**
     * 选择排序：分已排序和未排序取决，每次从未排序区间中找到最小的元素，
     * 将最小的元素放到已排序的末尾（交换）
     * <p>
     * 空间复杂度O(1)
     * <p>
     * 时间复杂度 O(n^2)
     */
    public static void selectSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int min;
        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * 快速排序，选择一个枢纽，将大于枢纽的放在右边，小于枢纽的放在左边，递归排序
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickStart(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //分区
        int pivotIndex = partition(arr, left, right);
        quickStart(arr, left, pivotIndex - 1);
        quickStart(arr, pivotIndex + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        //取一个枢纽，把数组分为左右两组（左小于枢纽，右大于等于枢纽）
        int pivot = arr[right];
        int i = left;
        //分区排序
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                //交换位置
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        //返回枢纽下标
        return i;
    }

    private static void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
