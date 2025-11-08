package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2655 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] input = new int[n][]; // 원래 번호, 넓이, 높이, 무게

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = i + 1;
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            input[i] = new int[]{num, a, b, c};
        }

        Arrays.sort(input, (a, b) -> b[1] - a[1]);

        int[] dp = new int[n];
        int[] path = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = input[i][2];
            path[i] = -1;

            for (int j = 0; j < i; j++) {
                if (input[i][3] > input[j][3]) continue;

                if (dp[i] < dp[j] + input[i][2]) {
                    dp[i] = dp[j] + input[i][2];
                    path[i] = j;
                }
            }
        }

        int index = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < dp[i]) {
                max = dp[i];
                index = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (index != -1) {
            sb.append(input[index][0]).append("\n");
            cnt++;
            index = path[index];
        }

        System.out.println(cnt + "\n" + sb.toString());
    }
}
