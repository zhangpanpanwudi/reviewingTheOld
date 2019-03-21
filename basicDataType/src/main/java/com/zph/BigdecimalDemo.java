package com.zph;

import java.math.BigDecimal;

/**
 * @ClassName: BigdecimalDemo
 * @description: bigdecimal的demo
 * @author: zph
 * @create: 2019-03-21 20:55
 **/
public class BigdecimalDemo {

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("100.001");
        //指数位
        System.out.println(bd.scale());//3
        //有效数字
        System.out.println(bd.unscaledValue()); //100001

        BigDecimal a = new BigDecimal(1.01);
        BigDecimal b = new BigDecimal(1.02);
        BigDecimal c = new BigDecimal("1.01");
        BigDecimal d = new BigDecimal("1.02");
        System.out.println(a.add(b));
        System.out.println(c.add(d));

//        输出：
//        2.0300000000000000266453525910037569701671600341796875
//        2.03
    }

}
