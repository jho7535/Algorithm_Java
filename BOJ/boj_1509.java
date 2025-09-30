package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1509 {
    //public class Main {
    static boolean[][] palindrome;
    static int[] dp;
    static String input;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        len = input.length();

        palindrome = new boolean[len + 1][len + 1];
        dp = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            palindrome[i][i] = true;
        }

        for (int i = 1; i < len; i++) {
            if (input.charAt(i - 1) == input.charAt(i)) palindrome[i][i + 1] = true;
        }

        for (int l = 3; l <= len; l++) {
            for (int i = 1; i <= len - l + 1; i++) {
                int j = i + l - 1;
                if (input.charAt(i - 1) == input.charAt(j - 1) && palindrome[i + 1][j - 1]) palindrome[i][j] = true;
            }
        }

        for (int i = 1; i <= len; i++) dp[i] = i;
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= i; j++) {
                if (palindrome[j][i]) dp[i] = Math.min(dp[i], dp[j - 1] + 1);
            }
        }

        System.out.println(dp[len]);
    }
}
