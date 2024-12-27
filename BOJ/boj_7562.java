package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.util.*;
import java.io.*;

// 36.11
public class boj_7562 {
    //public class Main {
    static int[] dr = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dc = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    static int n;
    static int[][] dp;
    static int[] current;
    static int[] target;

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int test = Integer.parseInt(st.nextToken());
        for (int t = 0; t < test; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            dp = new int[n][n];
            for (int i = 0; i < n; i++)
                Arrays.fill(dp[i], Integer.MAX_VALUE);

            st = new StringTokenizer(br.readLine());
            current = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            st = new StringTokenizer(br.readLine());
            target = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            bfs();

            System.out.println(dp[target[0]][target[1]]);
        }
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{current[0], current[1]});
        dp[current[0]][current[1]] = 0;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if (isRange(nr, nc) && dp[nr][nc] > dp[now[0]][now[1]] + 1) {
                    dp[nr][nc] = dp[now[0]][now[1]] + 1;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }
    public static boolean isRange(int row, int col) {
        return row < n && row >= 0 && col < n && col >= 0;
    }
}
