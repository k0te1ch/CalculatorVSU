package org.example;

public class Main implements Operation<Integer> {
    @Override
    public Integer apply(Integer a, Integer b) {
        return a + b;
    }

}