package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 파괴되지_않은_건물 {
    class Solution {
        public int solution(int[][] board, int[][] skill) {
            int[][] psum = new int[board.length + 1][board[0].length + 1];

            for (int[] query : skill) {
                int type = query[0] == 2 ? 1 : -1;
                int r1 = query[1], c1 = query[2], r2 = query[3], c2 = query[4];
                int degree = query[5];

                psum[r1][c1] += type * degree;
                psum[r1][c2 + 1] -= type * degree;
                psum[r2 + 1][c1] -= type * degree;
                psum[r2 + 1][c2 + 1] += type * degree;
            }

            for (int i = 0; i < psum.length; i++) {
                for (int j = 1; j < psum[0].length; j++) {
                    psum[i][j] += psum[i][j - 1];
                }
            }
            for (int j = 0; j < psum[0].length; j++) {
                for (int i = 1; i < psum.length; i++) {
                    psum[i][j] += psum[i - 1][j];
                }
            }

            int cnt = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    board[i][j] += psum[i][j];

                    if (board[i][j] > 0) cnt++;
                }
            }

            return cnt;
        }
    }
}
