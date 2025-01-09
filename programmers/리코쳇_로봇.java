package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;


public class 리코쳇_로봇 {
    class Solution {
        static int[] dr = new int[]{-1, 1, 0, 0};
        static int[] dc = new int[]{0, 0, -1, 1};
        static int n;
        static int m;
        static char[][] map;
        static int answer = -1;

        public int solution(String[] board) {
            n = board.length;
            m = board[0].length();
            map = new char[n][m];
            int p = 0, q = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = board[i].charAt(j);

                    if (map[i][j] == 'R') {
                        p = i;
                        q = j;
                    }
                }

            }

            bfs(p, q, new boolean[n][m]);

            return answer;
        }

        public void bfs(int p, int q, boolean[][] visited) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{p, q, 0});
            visited[p][q] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                if (map[current[0]][current[1]] == 'G') {
                    answer = current[2];
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = current[0] + dr[i];
                    int nc = current[1] + dc[i];

                    while (isRange(nr, nc) && map[nr][nc] != 'D') {
                        nr += dr[i];
                        nc += dc[i];
                    }
                    nr -= dr[i];
                    nc -= dc[i];

                    if (!visited[nr][nc]) {
                        queue.add(new int[]{nr, nc, current[2] + 1});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        public boolean isRange(int row, int col) {
            return row >= 0 && row < n && col >= 0 && col < m;
        }
    }
}
