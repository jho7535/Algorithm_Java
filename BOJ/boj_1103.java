package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1103 {
    //public class Main {
    static int max = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        boolean[][] visited = new boolean[n][m];
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visited[0][0] = true;
        dfs(0, 0, n, m, map, visited, dp, 0);

        if (max == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(max);
    }

    static void dfs(int row, int col, int n, int m, char[][] map, boolean[][] visited, int[][] dp, int cnt) {
        cnt++;
        dp[row][col] = cnt;

        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i] * (map[row][col] - '0');
            int nc = col + dc[i] * (map[row][col] - '0');

            if (!isRange(nr, nc, n, m) || map[nr][nc] == 'H') {
                if (cnt > max) max = cnt;
                continue;
            }

            if (visited[nr][nc]) {
                max = Integer.MAX_VALUE;
                break;
            }

            if (dp[nr][nc] > cnt) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, n, m, map, visited, dp, cnt);
            visited[nr][nc] = false;
        }
    }

    static boolean isRange(int r, int c, int n, int m) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }
}