package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 피로도 {
    class Solution {
        static int answer = 0;
        static boolean[] visited;

        public int solution(int k, int[][] dungeons) {
            visited = new boolean[dungeons.length];

            dfs(0, k, dungeons);

            return answer;
        }

        public void dfs(int level, int current, int[][] dungeons) {
            for (int i = 0; i < dungeons.length; i++) {
                if (visited[i] || current < dungeons[i][0]) continue;

                visited[i] = true;
                dfs(level + 1, current - dungeons[i][1], dungeons);
                visited[i] = false;
            }

            answer = Math.max(answer, level);
        }
    }
}
