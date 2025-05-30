package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2179 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = new String[n];
        for (int i = 0; i < n; i++) {
            input[i] = br.readLine();
        }

        String s = "";
        String e = "";
        int len = 0;
        for (int i = 0; i < n - 1; i++) {
            String a = input[i];
            if (a.length() < len) continue;

            for (int j = i + 1; j < n; j++) {
                String b = input[j];
                if (b.length() < len) continue;
                int cnt = 0;

                if (a.equals(b)) continue;

                for (int k = 0; k < a.length() && k < b.length(); k++) {
                    if (a.charAt(k) != b.charAt(k)) break;
                    cnt++;
                }

                if (cnt > len) {
                    s = a;
                    e = b;
                    len = cnt;
                }
            }
        }

        System.out.println(s + "\n" + e);
    }

}
