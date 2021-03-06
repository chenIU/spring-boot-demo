package com.ruida.springbootdemo.stream;

import com.google.common.collect.Lists;
import com.ruida.springbootdemo.entity.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: chenjy
 * @create: 2020-08-13 15:59
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("apple","orange","banana","apple","apple","banana");
        Map<String,Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result);

        // 排序
        Map<String,Long> finalMap = new HashMap<>();
        result.entrySet()
                .stream()
                .sorted(Map.Entry.<String,Long>comparingByValue())
                .forEachOrdered(e -> finalMap.put(e.getKey(),e.getValue()));
        System.out.println(finalMap);

        Stream<String> steam = Stream.of("I","love","you","too");
        Map<String,Integer> map = steam.collect(Collectors.toMap(Function.identity(),String::length));
        System.out.println(map);

        /*List转Map*/
        Student s1 = new Student(1,"chenjy",26);
        Student s2 = new Student(2,"lxy",22);
        List<Student> list = Lists.newArrayList(s1,s2);
        System.out.println(list);
        Map<Integer,Object> convertMap = list.stream().collect(Collectors.toMap(Student::getId,Student::getName));
        System.out.println(convertMap);

        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);
        nums.stream().forEach(n -> System.out.print(n));
        System.out.println();
        nums.parallelStream().forEach(n -> System.out.print(n));
        System.out.println();
        nums.parallelStream().forEach(n -> System.out.println(Thread.currentThread().getName()+"<<"+n));

        //stream的map操作：对流操作，返回一个新的流
        List<Integer> number = Arrays.asList(1,2,3,4,5);
        number.stream().map(x -> {
            System.out.println("没有返回新的流");
            return x;
        });

        number.stream().map(x -> {
            System.out.println("返回新的流");
            return x;
        }).collect(Collectors.toList());
    }

    @Test
    public void test(){
        List<String> list = Arrays.asList("1","2","3","4");
        String collect = list.stream().collect(Collectors.joining("|"));
        System.out.println(collect);
    }

    @Test
    public void testJoining(){
        List<String> list = Arrays.asList("A", "B", "C", "D");
        System.out.println(list.stream().collect(Collectors.joining()));
        System.out.println(list.stream().collect(Collectors.joining("-")));
        System.out.println(list.stream().collect(Collectors.joining(",","[","]")));
    }
}
