package org.visualds.arrays;

import java.util.Scanner;

public class LeftRotate {

        public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            Scanner scanner = new Scanner(System.in);
            int len = scanner.nextInt();
            int nrotations = scanner.nextInt();
            int arr[] = new int[len];
            for (int i=0; i<len; i++) {
                arr[i] = scanner.nextInt();
            }

            for (int k=0; k<nrotations; k++) {
                int val = arr[0];
                for (int index=1; index<len; index++) {
                    arr[index-1]=arr[index];
                }
                arr[len-1] = val;
            }

            for (int i=0; i<len; i++) {
                System.out.printf("%d ",arr[i]);
            }
        }
}

