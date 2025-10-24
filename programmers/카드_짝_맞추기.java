package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 카드_짝_맞추기 {
    class Solution {

        static int[][][] target = new int[4][4][2];
        static int answer = 100000000;
        static int depth = 0;

        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, -1, 1};

        public int solution(int[][] board, int r, int c) {
            // 타겟 카드 위치 찾기
            for (int t = 1; t <= 6; t++) {
                int[] prev = {-1, -1};

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (board[i][j] == t) {
                            depth++;
                            if (prev[0] == -1) {
                                prev[0] = i;
                                prev[1] = j;
                            } else {
                                target[i][j][0] = prev[0];
                                target[i][j][1] = prev[1];

                                target[prev[0]][prev[1]][0] = i;
                                target[prev[0]][prev[1]][1] = j;
                            }
                        }
                    }
                }
            }

            // 재귀 시작. 보드 상태, 현위치, 뒤집은 카드 개수, 누적된 횟수
            sol(board, r, c, 0, 0);

            return answer;
        }

        public static void sol(int[][] board, int r, int c, int level, int cnt) {
            // 종료 조건
            if (level == depth) {
                // System.out.println(cnt);
                answer = Math.min(answer, cnt);
                return;
            }

            // 전체 순회하면서 하나 찾아
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == 0) continue;

                    int t = board[i][j];

                    int sum = 0;
                    sum += bfs(board, r, c, i, j);
                    sum += bfs(board, i, j, target[i][j][0], target[i][j][1]);
                    sum += 2;

                    board[i][j] = 0;
                    board[target[i][j][0]][target[i][j][1]] = 0;
                    sol(board, target[i][j][0], target[i][j][1], level + 2, cnt + sum);
                    board[i][j] = t;
                    board[target[i][j][0]][target[i][j][1]] = t;
                }
            }
        }
        public static int bfs(int[][] board, int r1, int c1, int r2, int c2) {
            int dist = 100000000;

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{r1, c1, 0});
            boolean[][] visited = new boolean[4][4];
            visited[r1][c1] = true;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                int curDist = cur[2];

                if (curDist > dist) continue;
                if (curRow == r2 && curCol == c2) {
                    dist = Math.min(dist, curDist);
                    continue;
                }

                // 한 칸만 이동
                for (int i = 0; i < 4; i++) {
                    int nr = curRow + dr[i];
                    int nc = curCol + dc[i];

                    if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
                    if (visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, curDist + 1});
                }

                // 컨트롤 누르고 이동
                for (int i = 0; i < 4; i++) {
                    int nr = curRow;
                    int nc = curCol;
                    while (nr + dr[i] >= 0 && nr + dr[i] < 4 && nc + dc[i] >= 0 && nc + dc[i] < 4) {
                        nr += dr[i];
                        nc += dc[i];

                        if (board[nr][nc] != 0) break;
                    }

                    if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
                    if (visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, curDist + 1});
                }
            }

            return dist;
        }
    }
}
