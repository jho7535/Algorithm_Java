package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1600 {
    //public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] dmr = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dmc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) map[i][j] = !st.nextToken().equals("0");
        }

        Queue<int[]> queue = new LinkedList<>();
        int[][][] visited = new int[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) Arrays.fill(visited[i][j], -1);
        }
        queue.add(new int[]{0, 0, 0, k});
        visited[0][0][k] = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];
            int curCnt = current[2];
            int curK = current[3];

            // 일반 이동
            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nr][nc]) continue;
                if (visited[nr][nc][curK] != -1) continue;

                visited[nr][nc][curK] = curCnt + 1;
                queue.add(new int[]{nr, nc, curCnt + 1, curK});
            }

            // 원숭이 이동
            if (curK <= 0) continue;
            for (int i = 0; i < 8; i++) {
                int nr = curRow + dmr[i];
                int nc = curCol + dmc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nr][nc]) continue;
                if (visited[nr][nc][curK - 1] != -1) continue;

                visited[nr][nc][curK - 1] = curCnt + 1;
                queue.add(new int[]{nr, nc, curCnt + 1, curK - 1});
            }
        }

        int out = Integer.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            if (visited[n - 1][m - 1][i] == -1) continue;

            out = Math.min(out, visited[n - 1][m - 1][i]);
        }

        System.out.println(out == Integer.MAX_VALUE ? -1 : out);
    }
}
