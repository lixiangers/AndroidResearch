package com.lixiangers.androidresearch.feature;

import com.android.internal.util.Predicate;

import java.util.Arrays;
import java.util.List;

/**
 * Lambda 使用的Main class
 */
public class LambdaMainClass {
    public static void main(String[] args) {
        execute(() -> System.out.println("WorkerInterfaceTest print"));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
//        evaluate(list, n -> n > 5);
        evaluate(list, (n) -> {
            return n < 4;
        });

//        list.stream().map((x) -> x*x).forEach(System.out::println);
    }

    private static void execute(WorkerInterfaceTest worker) {
        worker.doSomeWork();
    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.apply(n)) {
                System.out.println(n + " ");
            }
        }
    }
}
