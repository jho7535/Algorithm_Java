package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

/*
*
* */

public class boj_16719 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int n = input.length();

        boolean[] used = new boolean[n];
        StringBuilder output = new StringBuilder();
        for (int len = 1; len <= n; len++) {
            int index = 0;
            String str = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";

            for (int i = 0; i < n; i++) {
                if (used[i]) continue;

                used[i] = true;
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < n; j++) {
                    if (used[j]) sb.append(input.charAt(j));
                }

                if (str.compareTo(sb.toString()) > 0) {
                    str = sb.toString();
                    index = i;
                }

                used[i] = false;
            }

            used[index] = true;
            output.append(str).append("\n");
        }

        System.out.println(output.toString());

    }
}
