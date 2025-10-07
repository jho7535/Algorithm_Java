package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 미로_탈출_명령어 {
    class Solution {

        static int[] dr = {1, 0, 0, -1};
        static int[] dc = {0, -1, 1, 0};
        static char[] dch = {'d', 'l', 'r', 'u'};

        public String solution(int n, int m, int x, int y, int r, int c, int k) {

            int[][] dist = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) Arrays.fill(dist[i], -1);

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{r, c, 0});
            dist[r][c] = 0;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                int curDist = cur[2];

                for (int i = 0; i < 4; i++) {
                    int nr = curRow + dr[i];
                    int nc = curCol + dc[i];

                    if (!isRange(n, m, nr, nc)) continue;
                    if (dist[nr][nc] != -1) continue;

                    dist[nr][nc] = curDist + 1;
                    queue.add(new int[]{nr, nc, curDist + 1});
                }
            }

            // 불가능
            int shortcut = dist[x][y];
            if ((k - shortcut) % 2 == 1 || k < shortcut) return "impossible";

            // 가능
            StringBuilder sb = new StringBuilder();
            int row = x;
            int col = y;
            while (true) {
                boolean flag = false;

                for (int i = 0; i < 4; i++) {
                    int nr = row + dr[i];
                    int nc = col + dc[i];

                    int remain =  k - (sb.length() + 1);

                    if (!isRange(n, m, nr, nc)) continue;
                    if (dist[nr][nc] > remain) continue;
                    if ((dist[nr][nc] - remain) % 2 == 1) continue;

                    sb.append(dch[i]);
                    row = nr;
                    col = nc;
                    flag = true;
                    break;
                }

                if (!flag) break;
            }

            return sb.toString();
        }

        public static boolean isRange(int n, int m, int row, int col) {
            return row >= 1 && row <= n && col >= 1 && col <= m;
        }
    }
}
