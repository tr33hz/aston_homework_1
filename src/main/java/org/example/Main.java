package org.example;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(5);
        list.add(2);
        list.add(7);
        list.add(1);
        list.add(9);

        Comparator<Integer> comparator = Comparator.naturalOrder();

        list.quickSort(comparator);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}