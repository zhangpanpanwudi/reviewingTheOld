package com.zph;

/**
 * @ClassName: DoubleDemo
 * @description: double精度丢失问题
 * @author: zph
 * @create: 2019-03-21 12:57
 **/
public class DoubleDemo {


    public static void main(String[] args) {
        Long l = Long.MAX_VALUE;
        double d = l;
        System.out.println(d);

        double d1 = d;
        for(int i=0;i<100000000;i++){
            d1 += 10000;
        }
        System.out.println(d1);
        System.out.println(d1==d);


        double k =Math.pow(2, 60);
        double k1 =k;
        for(int i=0;i<100000000;i++){
            d1 += 1000;
        }
        System.out.println(k1==k);
    }
}
