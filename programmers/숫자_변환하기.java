package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 숫자_변환하기 {
    class Solution {
        static int[] dp = new int[1000001];

        public int solution(int x, int y, int n) {
            Arrays.fill(dp, -1);
            bfs(x, y, n);
            return dp[y];
        }

        public void bfs (int start, int target, int n) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{start, 0});
            dp[start] = 0;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();

                for (int i = 0; i < 3; i++) {
                    int nx = 0;
                    if (i == 0) nx = current[0] + n;
                    if (i == 1) nx = current[0] * 2;
                    if (i == 2) nx = current[0] * 3;

                    if (nx >= 1 && nx <= 1000000 && dp[nx] == -1) {
                        queue.add(new int[]{nx, current[1] + 1});
                        dp[nx] = current[1] + 1;
                    }
                }
            }
        }
    }
}
