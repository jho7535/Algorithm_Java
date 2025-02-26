package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 쿼드압축_후_개수_세기 {
    class Solution {
        static int cnt0 = 0;
        static int cnt1 = 0;

        public int[] solution(int[][] arr) {
            divide(arr, 0, 0, arr.length - 1, arr.length - 1);

            int[] answer = {cnt0, cnt1};

            return answer;
        }

        public void divide(int[][] arr, int r1, int c1, int r2, int c2) {
            boolean flag = true;
            for (int i = r1; i <= r2; i++) {
                for (int j = c1; j <= c2; j++) {
                    if (arr[r1][c1] != arr[i][j]) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) break;
            }

            if (flag && arr[r1][c1] == 0) cnt0++;
            else if (flag && arr[r1][c1] == 1) cnt1++;
            else {
                divide(arr, r1, c1, (r1 + r2) / 2, (c1 + c2) / 2);
                divide(arr, r1, (c1 + c2) / 2 + 1, (r1 + r2) / 2, c2);
                divide(arr, (r1 + r2) / 2 + 1, c1, r2, (c1 + c2) / 2);
                divide(arr, (r1 + r2) / 2 + 1, (c1 + c2) / 2 + 1, r2, c2);
            }
        }
    }
}
