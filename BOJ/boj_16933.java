package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_16933 {
    //public class Main {
    static int n, m, k;
    static boolean[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) != '0';
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{0, 0, 1, k});
        boolean[][][] visited = new boolean[n][m][k + 1];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int curCnt = current[2];
            int curK = current[3];

            if (r == n - 1 && c == m - 1) return curCnt;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= n || nr < 0 || nc >= m || nc < 0) continue;

                if (map[nr][nc]) {
                    if (curK == 0) continue;
                    if (visited[nr][nc][curK - 1]) continue;

                    if (curCnt % 2 == 0) {
                        queue.add(new int[]{nr, nc, curCnt + 2, curK - 1});
                    } else {
                        queue.add(new int[]{nr, nc, curCnt + 1, curK - 1});
                    }
                    visited[nr][nc][curK - 1] = true;
                } else {
                    if (visited[nr][nc][curK]) continue;

                    queue.add(new int[]{nr, nc, curCnt + 1, curK});
                    visited[nr][nc][curK] = true;
                }
            }
        }

        return -1;
    }
}
