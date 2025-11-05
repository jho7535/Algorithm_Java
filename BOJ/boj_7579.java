package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

/*
* m 바이트 이상을 확보할 건데, 비용을 최소로
*
* 입 |   n, m : 1-100, 1-1000만
*       n개 정수 : 사용 중인 바이트. 1-1000만
*       c : 0-100
* 출 |   최소 비용
*
* dp[i] = dp[i - c],
* */

public class boj_7579 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] dp = new int[10001];
        int[][] mem = new int[n][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            mem[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            mem[i][1] = Integer.parseInt(st.nextToken());
        }

        // 비용 오름차순 정렬
        Arrays.sort(mem, (a, b) -> a[1] - b[1]);

        // dp[0] 세팅
        int index = 0;
        while (index < n && mem[index][1] == 0) {
            dp[0] += mem[index++][0];
        }

        for (int i = index; i < n; i++) {
            int curMem = mem[i][0];
            int curCost = mem[i][1];

            for (int j = 10000; j >= curCost ; j--) {
                dp[j] = Math.max(dp[j], dp[j - curCost] + curMem);
            }
        }

        for (int i = 0; i <= 10000; i++) {
            if (dp[i] >= m) {
                System.out.println(i);
                return;
            }
        }
    }
}
