package org.dsl.puzzles;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by rajesh on 9/10/16.
 */
public class Numbers {

    String values[];
    Scanner in;

    Numbers(int... numbers) {
        values = new String[numbers.length];
        int index = 0;
        for (int num: numbers) {
            values[index++] = "" + num;
        }
    }

    String getMaxNum() {
        Arrays.sort(values, new Comparator<String>() {
            public int compare(String first, String second) {
                Integer sum1 = Integer.parseInt(first + second);
                Integer sum2 = Integer.parseInt(second + first);
                return sum1.compareTo(sum2);
            }
        });

        Collections.reverse(Arrays.asList(values));
        String maxnum = "";
        for (String num: values) {
            maxnum+=num;
        }
        return maxnum;
    }

    public static void main(String[] args) {
        //Numbers numbers = new Numbers(9,998,97,88);
        Numbers numbers = new Numbers(9,988,77,66);
        String maxnum = numbers.getMaxNum();
        //if (maxnum.equals("99989788")) {
        if (maxnum.equals("99887766")) {
            System.out.println("right");
        } else {
            System.out.println("wrong");
        }
    }
}
