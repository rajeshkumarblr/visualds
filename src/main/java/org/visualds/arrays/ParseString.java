package org.visualds.arrays;

import java.util.Scanner;

/**
 * Created by rajesh on 15/8/16.
 */
public class ParseString {
        public static void main(String[] args) {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            Scanner scanner = new Scanner(System.in);
            int len = scanner.nextInt();
            String text[] = new  String[len];
            for (int i=0; i<len;i++) {
                text[i] = scanner.next();
            }

            int qlen = scanner.nextInt();
            String queries[] = new  String[qlen];
            int occurances[] = new int[qlen];
            for (int j=0; j<qlen;j++) {
                queries[j] = scanner.next();
            }

            for (int i=0; i<qlen;i++) {
                for (int k=0; k<len; k++) {
                    if (queries[i].equals(text[k])) {
                        occurances[i]++;
                    }
                }
            }
            for (int j=0; j<qlen;j++) {
                System.out.println(occurances[j]);
            }
        }
}
