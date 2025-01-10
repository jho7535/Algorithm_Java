package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 미로_탈출 {
    class Solution {
        static int toL = 0;
        static int toE = 0;
        static int[] dr = new int[]{-1, 1, 0, 0};
        static int[] dc = new int[]{0, 0, -1, 1};

        public int solution(String[] maps) {
            int[] s = new int[2];
            int[] l = new int[2];
            for (int i = 0; i < maps.length; i++) {
                for (int j = 0; j < maps[i].length(); j++) {
                    if (maps[i].charAt(j) == 'S') {
                        s[0] = i;
                        s[1] = j;
                    }
                    if (maps[i].charAt(j) == 'L') {
                        l[0] = i;
                        l[1] = j;
                    }
                }
            }

            toL = bfs(s[0], s[1], maps, 'L');
            toE = bfs(l[0], l[1], maps, 'E');

            if (toL == -1 || toE == -1) return -1;
            else return toL + toE;
        }

        public int bfs(int row, int col, String[] maps, char target) {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[maps.length][maps[0].length()];
            queue.add(new int[]{row, col, 0});
            visited[row][col] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                if (maps[current[0]].charAt(current[1]) == target) return current[2];

                for (int i = 0; i < 4; i++) {
                    int nr = current[0] + dr[i];
                    int nc = current[1] + dc[i];

                    if (isRange(nr, nc, maps) && !visited[nr][nc] && maps[nr].charAt(nc) != 'X') {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc, current[2] + 1});
                    }
                }
            }

            return -1;
        }
        public boolean isRange(int r, int c, String[] maps) {
            return r >= 0 && r < maps.length && c >= 0 && c < maps[0].length();
        }
    }
}
