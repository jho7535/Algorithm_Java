package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2229 {
    //public class Main {

    static int answer = 0;
    static int[] arr;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] dp = new int[n + 1];

        // 가장 높은 학생 - 낮은 학생. 잘 짜여진 정도
        // 정도의 합이 가장 높게

        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int max = 0;

            for (int j = i; j >= 1; j--) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                dp[i] = Math.max(dp[i] , dp[j - 1] + max - min);
            }
        }

        System.out.println(dp[n]);
    }
}
