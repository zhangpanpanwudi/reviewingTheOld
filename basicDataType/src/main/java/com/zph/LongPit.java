package com.zph;

/**
 * @ClassName: LongPit
 * @description: long类型的坑
 * @author: zph
 * @create: 2019-03-19 15:41
 **/
public class LongPit {

    public static void main(String[] args) {
   /*     Long a = 50L;
        Long b = 50L;
        Long c = 5000L;
        Long d = 5000L;
        Long e = new Long(50);
        Long f = new Long(50);
        Long i = Long.valueOf(50);

        Short g =1;
        Short h =1;

        System.out.print("a==b?");
        System.out.println(a==b);
        System.out.print("c==d?");
        System.out.println(c==d);
        System.out.print("e==f?");
        System.out.println(e==f);
        System.out.print("e.equals(f)?");
        System.out.println(e.equals(f));
        System.out.print("g==h?");
        System.out.println(g==h);
        System.out.println(a==e);
        System.out.println(a==i);
        System.out.println(e==i);*/

        Integer a = 1;
        Integer b = Integer.valueOf(1);
        Integer c =2;
        b = 2;
        System.out.println(b==c);
    }

}
