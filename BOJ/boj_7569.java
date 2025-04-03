package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_7569 {
    //public class Main {

    static int[] dh = {1, -1, 0, 0, 0, 0};
    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][][] map = new int[h][n][m];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (map[i][j][k] == 1) queue.add(new int[]{i, j, k, 0});
                }
            }
        }
        int max = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curH = current[0];
            int curR = current[1];
            int curC = current[2];
            int curCnt = current[3];

            max = Math.max(max, curCnt);

            for (int i = 0; i < 6; i++) {
                int nh = curH + dh[i];
                int nr = curR + dr[i];
                int nc = curC + dc[i];

                if (nh < 0 || nh >= h || nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nh][nr][nc] != 0) continue;

                map[nh][nr][nc] = 1;
                queue.add(new int[]{nh, nr, nc, curCnt + 1});
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (map[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(max);
    }
}
