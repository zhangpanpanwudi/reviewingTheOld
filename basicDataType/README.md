basicDataType基础数据类型（基于jdk8）
===
- 基本信息

 | name        | 内存位数	    |	数据范围         |  包装类       |说明          |
 | :----------:| :-----------:  | :-----------:     |:-----------: |:-----------:|
 | byte        | 8              | -2^7~2^7-1        | Byte         |
 | short       | 16             | -2^15~2^15-1      | Short        |
 | int         | 32             | -2^31~2^31-1      | Integer      |
 | long        | 64             | -2^63~2^63        | Long         |
 | float       | 32             | -2^128 ~ +2^128   | Float        | 符号位1位，指数位8位，位数为23位
 | double      | 64             | -2^1024 ~ +2^1024 | Double       | 符号位1位，指数位11位，位数为52位
 | char | 16   |                | Character         | Unicode字符集
 | boolean     |                |                   | Boolean      | 

- 包装坑

  - java可以自动的将基本类型转换为包装类型，称为装箱，将包装类型转换为基本类型称为拆箱
  - byte、short、int、long的缓存
    - 以上包装类型都包含-128~127之间的值，所以在自动装箱的过程中会使用缓存，所以创建的对象一样，超过这个范围就不会使用缓存
       
    - ```java
        Integer a = 1;
        Integer b = 1;
        System.out.println(a==b);//true
      ```
    - Integer.valueOf(1)等价于Integer a= 1,也会使用缓存，所以这两个方法创建的对象是相同的。
    - ```java
        Integer a = 1;
        Integer b = Integer.valueOf(1);
        System.out.println(a==b);//true
       ```
    
    - 所以在实际项目中使用valueOf()或者装箱来声明对象，因为这样创建时读取缓存中的对象，更加快速，而且也节省了空间；
    - ```java
        Integer a = 1;
        Integer b = Integer.valueOf(1);
        Integer c = Integer.valueOf(2);
        b = 2;
        System.out.println(b==c);
      ```
- float、double在计算机中的表示
  - 小数在计算机中的表示
    - 小数位*2取整数位剩余的小数位*2再取整数位，循环下去，直到小数位为0
    
    — 0.1 计算
    
        > 0.1*2 = 0.2  ---> 0
        >
        > 0.2*2 = 0.4  ---> 0
        >
        > 0.4*2 = 0.8  ---> 0
        >
        > 0.8*2 = 1.6  ---> 1
        >
        > 0.6*2 = 1.2  ---> 1
        >
        > 0.2*2 = 0.4  ---> 0
        >
        >一直循环下去
        - 所以值为000110011.....
        - 但是计算机存储数字的位数是有限的，所存储时会截取至一定位数，这样就造成了计算机对0.1的表达不正确的问题。
        - 由于0.1的表达不正确，所以0.1与其他数字的运算都有可能造成不准确的情况。
        
    - 5.1表示
        - 整数位5二进制位101，小数位0.1表示为000110011....
        - 所以5.1二进制表示就是101.00011001100，科学计数法表示就是1.01000100110011*2^2
        - float表示
            - 符号位0表示正数，1表示负数，所以为0
            - float指数位为8位，但是区分正负，正负各一半。所以用1000 0000表示正数1，以此类推。5.1的指数为2.所以表示为1000 0001
            - float数位为23位，取0.1的二进制的23位，1010 0010 0110 0110 0110 011
                - 但是科学计数法的第一位都是1，所以不用记，所以又省出来一位，改为0100 0100 1100 1100 1100 110（最后一位0就是补上来的）
            - 到这里。就可以确定5.1在float中是如何存储的了
                0 1000 0001  0100 0100 1100 1100 1100 110
            - 这里的5.1就不是精确存储
        - double表示与float原理相同，只是存储的更加精确了
- 金额能用double或者float表示吗？
   - 首先说答案，不能。
   - 因为double和float位数有限，无法表示很大的数字，比如Long.MAX_VALUE.不能表示的部分会舍弃，所以即使+1也在表示的时候设计，所以数字不会有变化。
   — 举一个很现实的例子，假如一个人有2^60元钱，放到银行里每天产生利息1000元，用代码实现
   - ```java
        double k =Math.pow(2, 60);
        double k1 =k;
        for(int i=0;i<100000000;i++){
            d1 += 1000;
        }
        System.out.println(k1==k);//true
     ```
   - 结果是相等的，说明并没有吧这1000加到里面，也就是说不管多长时间，利息都加不上去，钱在银行不能生出利息
   - float精度更小，更加不能用于这个场景。
   - 所以在生产中金额使用bigdecimal
        - bigdecimal原理
            - bigdecimal采用十进制
            - ```java
                BigDecimal bd = new BigDecimal("100.001");
                //指数位
                System.out.println(bd.scale());//3
                //有效数字
                System.out.println(bd.unscaledValue()); //100001
              ``` 
            - 100.001=100001*0.1^3
            - 100001不封用char[]记录，
        - 由于十进制能准确的表达每一个数字，所以能够正确的加减
        - 这里要注意的是申明一个bigdecimal对象是最好是使用string类型构造器，而不是直接的使用数字，使用数字会导致精度丢失，
            - ```java
                BigDecimal a = new BigDecimal(1.01);
                BigDecimal b = new BigDecimal(1.02);
                BigDecimal c = new BigDecimal("1.01");
                BigDecimal d = new BigDecimal("1.02");
                System.out.println(a.add(b));
                System.out.println(c.add(d));
                
                输出：
                2.0300000000000000266453525910037569701671600341796875
                2.03
              ```
        
         
   - 其他说明
   double和float也不是一无是处，它主要是科学计算和工程计算，它的计算速度比较快，对于一些要求不是特别精确的计算场景十分有用
        
- 参考
   - [剖析金额不能用浮点数表示的原因]（https://blog.csdn.net/bruce128/article/details/52529734）
   - [Java之道系列：BigDecimal如何解决浮点数精度问题]（https://blog.csdn.net/kisimple/article/details/43773899）
              

    
    