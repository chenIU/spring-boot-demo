package com.ruida.springbootdemo.test;

import java.util.UUID;

public class StringTest {
    public static void main(String[] args) {

        String s1 = "china";
        String s2 = "china";
        String s3 = new String("china");
        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false
        System.out.println(s1.equals(s2));//true
        System.out.println(s1.equals(s3));//true

        long t1 = System.currentTimeMillis();
        String str = null;
        for(int i=0;i<10000;i++){
            str += UUID.randomUUID();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("字符串拼接耗时："+(t2-t1)+"ms");

        long t3 = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<10000;i++){
            sb.append(UUID.randomUUID());
        }
        long t4 = System.currentTimeMillis();
        System.out.println("StringBuffer append方法耗时："+(t4-t3)+"ms");

        /*String str = "Aa";
        byte[] bytes = str.getBytes();
        for(byte b:bytes){
            System.out.println(b);
        }

        char[] chars = str.toCharArray();
        for(char c:chars){
            System.out.println(c);
        }*/

       /* String s1 = "123";
        String s2 = "123";

        String s3 = new String("123");
        String s4 = new String("123");

        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s3 == s4); // false

        String str = "abc";
        char[] c = str.toCharArray();
        System.out.println(c.length);
        String s = String.valueOf(c);
        System.out.println(s);*/
    }
}
