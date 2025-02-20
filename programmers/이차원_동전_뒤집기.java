package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 이차원_동전_뒤집기 {
    class Solution {
        static int n;
        static int m;
        static int answer = Integer.MAX_VALUE;;

        public int solution(int[][] beginning, int[][] target) {
            n = beginning.length;
            m = beginning[0].length;

            dfs(beginning, target, 0, 0);

            return answer == Integer.MAX_VALUE ? -1 : answer;
        }

        public void dfs(int[][] board, int[][] target, int row, int cnt) {
            // 행 뒤집기 끝
            if (row == n) {
                int[][] copy = new int[n][];
                for (int i = 0; i < n; i++) {
                    copy[i] = Arrays.copyOf(board[i], m);
                }

                for (int i = 0; i < m; i++) {
                    if (copy[0][i] == target[0][i]) continue;

                    filpCol(copy, i);
                    cnt++;
                }

                if (Arrays.deepEquals(copy, target)) {
                    answer = Math.min(answer, cnt);
                }

                return;
            }

            // 뒤집을 행 선택
            dfs(board, target, row + 1, cnt);
            filpRow(board, row);
            dfs(board, target, row + 1, cnt + 1);
            filpRow(board, row);
        }
        public int[][] filpRow(int[][] board, int row) {
            for (int i = 0; i < m; i++) {
                board[row][i] = (board[row][i] + 1) % 2;
            }

            return board;
        }
        public int[][] filpCol(int[][] board, int col) {
            for (int i = 0; i < n; i++) {
                board[i][col] = (board[i][col] + 1) % 2;
            }

            return board;
        }
    }
}
