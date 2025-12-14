package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1726 {
    //public class Main {

    static boolean[][][] visited;
    static boolean[][] map;
    static int n, m;

    static int[] dr = {0, 0, 0, 1, -1};
    static int[] dc = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1][m + 1][5];
        map = new boolean[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= m; j++) {
                map[i][j] = st.nextToken().equals("0");
            }
        }

        int[] start = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) start[i] = Integer.parseInt(st.nextToken());

        int[] end = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) end[i] = Integer.parseInt(st.nextToken());

        // dp 배열 채우기
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start[0], start[1], start[2], 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curDir = cur[2];
            int curCnt = cur[3];

            if (curRow == end[0] && curCol == end[1] && curDir == end[2]) {
                System.out.println(curCnt);
                return;
            }

            // Go
            for (int i = 1; i <= 3; i++) {
                int nr = curRow + dr[curDir] * i;
                int nc = curCol + dc[curDir] * i;

                if (!isRange(nr, nc)) continue;
                if (visited[nr][nc][curDir]) continue;
                if (!map[nr][nc]) break;

                queue.add(new int[]{nr, nc, curDir, curCnt + 1});
                visited[nr][nc][curDir] = true;
            }

            // Turn
            if (curDir == 1 || curDir == 2) {
                if (!visited[curRow][curCol][4]) {
                    queue.add(new int[]{curRow, curCol, 4, curCnt + 1});
                    visited[curRow][curCol][4] = true;
                }

                if (!visited[curRow][curCol][3]) {
                    queue.add(new int[]{curRow, curCol, 3, curCnt + 1});
                    visited[curRow][curCol][3] = true;
                }
            } else if (curDir == 3 || curDir == 4) {
                if (!visited[curRow][curCol][2]) {
                    queue.add(new int[]{curRow, curCol, 2, curCnt + 1});
                    visited[curRow][curCol][2] = true;
                }

                if (!visited[curRow][curCol][1]) {
                    queue.add(new int[]{curRow, curCol, 1, curCnt + 1});
                    visited[curRow][curCol][1] = true;
                }
            }
        }
    }

    static boolean isRange(int r, int c) {
        return r >= 1 && r <= n && c >= 1 && c <= m;
    }
}
