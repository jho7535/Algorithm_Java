package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 아이템_줍기 {
    class Solution {
        public static int[][] board = new int[102][102];
        public static int[] dr = new int[]{-1, 1, 0, 0};
        public static int[] dc = new int[]{0, 0, -1, 1};
        public static int answer = 0;

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            for (int[] current : rectangle) {
                int r1 = current[0] * 2;
                int r2 = current[2] * 2;
                int c1 = current[1] * 2;
                int c2 = current[3] * 2;
                for (int i = r1; i <= r2; i++) {
                    for (int j = c1; j <= c2; j++) {
                        // 테두리 만들기
                        if ((i == r1 || i == r2 || j == c1 || j == c2) &&
                                board[i][j] != 2) board[i][j] = 1;
                        else board[i][j] = 2;
                    }
                }

            }

            bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);

            return answer;
        }

        public void bfs(int characterX, int characterY, int itemX, int itemY) {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[102][102];
            queue.add(new int[]{characterX, characterY, 0});
            visited[characterX][characterY] = true;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                if (current[0] == itemX && current[1] == itemY) {
                    answer = current[2] / 2;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = current[0] + dr[i];
                    int nc = current[1] + dc[i];

                    if (nr < 1 || nr > 100 || nc < 1 || nc > 100) continue;
                    if (visited[nr][nc] || board[nr][nc] != 1) continue;

                    queue.add(new int[]{nr, nc, current[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }
}
