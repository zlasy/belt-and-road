package com.example.demo.stream;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int[] a = {10, 30, 20, 10, 30, 40};
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                if (a[i] == a[j]) {
                    a[j] = 0;
                }
            }
        }
        Arrays.stream(a).forEach(x->System.out.print(x + ","));
    }
}
