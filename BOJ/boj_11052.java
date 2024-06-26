package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11052 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            int j = 1;
            int max = arr[i];

            while (j <= i / 2) {
                if (dp[j] + dp[i - j] > max) max = dp[j] + dp[i - j];
                j++;
            }

            dp[i] = max;
        }

        System.out.println(dp[n]);
    }
}
