package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 거리두기_확인하기 {
    class Solution {
        static int[] dr = new int[]{-1, 1, 0, 0};
        static int[] dc = new int[]{0, 0, -1, 1};

        public int[] solution(String[][] places) {
            int[] answer = new int[5];
            Arrays.fill(answer, 1);

            for (int p = 0; p < 5; p++) {
                boolean flag = true;

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (places[p][i].charAt(j) == 'P') {
                            flag = bfs(i, j, places[p]);
                        }

                        if (!flag) answer[p] = 0;
                    }
                }
            }

            return answer;
        }

        boolean bfs(int row, int col, String[] map) {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[5][5];
            queue.add(new int[]{row, col, 0});
            visited[row][col] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = current[0] + dr[i];
                    int nc = current[1] + dc[i];

                    if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                    if (current[2] > 2 || visited[nr][nc] || map[nr].charAt(nc) == 'X') continue;

                    if (map[nr].charAt(nc) == 'P' && current[2] < 2) {
                        return false;
                    }

                    queue.add(new int[]{nr, nc, current[2] + 1});
                    visited[nr][nc] = true;
                }
            }

            return true;
        }
    }
}
