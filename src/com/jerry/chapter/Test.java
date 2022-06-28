package com.jerry.chapter;

/**
 * @Author jerry
 * @Description TODO
 * @Date 2022-05-08 23:53
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        int n=29,k=10;
        int sum=0;
        while(n!=0){
            sum+=n%k;
            n=n/k;
        }
        System.out.println(sum);
    }
}
