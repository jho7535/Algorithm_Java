package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_15989 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[] target = new int[t];
        int max = 0;
        for (int i = 0; i < t; i++) {
            target[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, target[i]);
        }

        int[] dp = new int[max + 1];
        dp[0] = 1;
        int[] num = {1, 2, 3};
        for (int i : num) {
            for (int j = i; j <= max; j++) {
                dp[j] += dp[j - i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : target) sb.append(dp[i]).append("\n");
        System.out.println(sb);
    }
}
