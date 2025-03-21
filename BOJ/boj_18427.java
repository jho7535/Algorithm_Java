package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_18427 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) list.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
        int[][] dp = new int[n + 1][h + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= h; j++) {
                dp[i][j] = dp[i - 1][j];

                for (int val : list.get(i)) {
                    if (val > j) continue;

                    dp[i][j] += dp[i - 1][j - val];
                }

                dp[i][j] = dp[i][j] % 10007;
            }
        }

        System.out.println(dp[n][h]);
    }
}
