package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 지게차와_크레인 {
    class Solution {

        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, -1, 1};

        public int solution(String[] storage, String[] requests) {
            int n = storage.length;
            int m = storage[0].length();
            char[][] map = new char[n][];
            for (int i =  0; i < n; i++) map[i] = storage[i].toCharArray();
            boolean[][] used = new boolean[n][m];
            int answer = n * m;

            for (String request : requests) {
                char ch = request.charAt(0);

                if (request.length() == 1) {
                    // bfs. side가 true, 알파벳.
                    Queue<int[]> queue = new ArrayDeque<>();
                    boolean[][] visited = new boolean[n][m];
                    for (int i =  0; i < n; i++) {
                        for (int j =  0; j < m; j++) {
                            if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                                if (used[i][j] || map[i][j] == ch) {
                                    visited[i][j] = true;
                                    queue.add(new int[]{i, j});
                                }
                            }
                        }
                    }

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int curRow = current[0];
                        int curCol = current[1];

                        if (!used[curRow][curCol]) {
                            answer--;
                            used[curRow][curCol] = true;
                            continue;
                        }

                        for (int i = 0; i < 4; i++) {
                            int nr = curRow + dr[i];
                            int nc = curCol + dc[i];

                            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                            if (visited[nr][nc]) continue;

                            if (used[nr][nc] || (!used[nr][nc] && map[nr][nc] == ch)) {
                                visited[nr][nc] = true;
                                queue.add(new int[]{nr, nc});
                            }
                        }
                    }
                } else {
                    for (int i =  0; i < n; i++) {
                        for (int j =  0; j < m; j++) {
                            if (map[i][j] == ch && !used[i][j]) {
                                used[i][j] = true;
                                answer--;
                            }
                        }
                    }
                }
            }

            return answer;
        }
    }
}
