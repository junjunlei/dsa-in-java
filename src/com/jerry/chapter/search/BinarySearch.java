package com.jerry.chapter.search;

/**
 * @Author jerry
 * 二分查找算法
 * <p>
 * 思路：while循环或者递归
 * <p>
 * 效率 log(n)
 * @Date 2022-06-21 23:37
 * @Version 1.0
 **/
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 6, 7, 8};
        int i = binarySearch(arr, 9);
        int i1 = binarySearchByRecursion(arr, 0, arr.length - 1, 9);
        System.out.println(i);
        System.out.println(i1);
    }

    /**
     * while循环二分查找
     *
     * @param arr    源数组
     * @param target 查找目标值
     * @return
     */
    public static int binarySearch(int[] arr, int target) {
        int mid;
        int low = 0;
        int high = arr.length - 1;
        while (true) {
            mid = (low + high) / 2;
            if (arr[mid] == target) {
                return target;
            } else if (low > high) {
                return -1;
            } else {
                if (arr[mid] > target) {
                    high = mid - 1;
                } else if (arr[mid] < target) {
                    low = mid + 1;
                }
            }
        }
    }

    /**
     * 递归二分查找
     *
     * @param arr    源数组
     * @param low    最小坐标
     * @param high   最大坐标
     * @param target 目标值
     * @return
     */
    public static int binarySearchByRecursion(int[] arr, int low, int high, int target) {
        int mid = (low + high) / 2;
        if (arr[mid] == target) {
            return target;
        } else if (low > high) {
            return -1;
        } else {
            if (arr[mid] > target) {
                return binarySearchByRecursion(arr, low, mid - 1, target);
            } else {
                return binarySearchByRecursion(arr, mid + 1, high, target);
            }
        }
    }
}
