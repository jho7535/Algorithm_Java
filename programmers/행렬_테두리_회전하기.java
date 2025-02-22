package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 행렬_테두리_회전하기 {
    class Solution {
        static int[][] board;

        public int[] solution(int rows, int columns, int[][] queries) {
            board = new int[rows][columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    board[i][j] = i * columns + j + 1;
                }
            }

            int[] answer = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                answer[i] = rotation(queries[i]);
            }

            return answer;
        }

        public int rotation(int[] queries) {
            int[] start = new int[]{queries[0] - 1, queries[1] - 1};
            int[] end = new int[]{queries[2] - 1, queries[3] - 1};
            int temp = board[start[0]][start[1]];
            int min = board[start[0]][start[1]];

            for (int i = start[0]; i <= end[0] - 1; i++) {
                board[i][start[1]] = board[i + 1][start[1]];
                min = Math.min(min, board[i][start[1]]);
            }
            for (int i = start[1]; i <= end[1] - 1; i++) {
                board[end[0]][i] = board[end[0]][i + 1];
                min = Math.min(min, board[end[0]][i]);
            }
            for (int i = end[0]; i >= start[0] + 1; i--) {
                board[i][end[1]] = board[i - 1][end[1]];
                min = Math.min(min, board[i][end[1]]);
            }
            for (int i = end[1]; i > start[1] + 1; i--) {
                board[start[0]][i] = board[start[0]][i - 1];
                min = Math.min(min, board[start[0]][i]);
            }
            board[start[0]][start[1] + 1] = temp;

            return min;
        }
    }
}
