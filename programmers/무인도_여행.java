package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 무인도_여행 {
    class Solution {
        static int cnt = -1;
        static int n;
        static int m;
        static boolean[][] visited;
        static int[] dr = new int[]{-1, 1, 0, 0};
        static int[] dc = new int[]{0, 0, -1, 1};


        public int[] solution(String[] maps) {
            n = maps.length;
            m = maps[0].length();
            visited = new boolean[n][m];
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                        cnt = 0;
                        bfs(i, j, maps);
                        pq.add(cnt);
                    }
                }
            }

            if (pq.isEmpty()) return new int[]{-1};
            int size = pq.size();
            int[] answer = new int[size];
            for (int i = 0; i < size; i++) {
                answer[i] = pq.poll();
            }

            return answer;
        }

        public void bfs(int row, int col, String[] maps) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{row, col});
            visited[row][col] = true;
            cnt += (maps[row].charAt(col) - '0');

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = current[0] + dr[i];
                    int nc = current[1] + dc[i];

                    if (isRange(nr, nc) && !visited[nr][nc] && maps[nr].charAt(nc) != 'X') {
                        visited[nr][nc] = true;
                        cnt += (maps[nr].charAt(nc) - '0');
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
        public boolean isRange(int r, int c) {
            return r >= 0 && r < n && c >= 0 && c < m;
        }
    }
}
