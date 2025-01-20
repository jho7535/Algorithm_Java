package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 인사고과 {
    class Solution {
        public int solution(int[][] scores) {
            int[] start = new int[]{scores[0][0], scores[0][1]};
            Arrays.sort(scores, (o1, o2) -> {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o2[0] - o1[0];
            });

            List<Integer> sum = new ArrayList<>();
            int max = scores[0][1];
            for (int[] score : scores) {
                if (score[1] < max) {
                    if (start[0] == score[0] && start[1] == score[1]) return -1;
                    continue;
                }

                max = Math.max(max, score[1]);

                sum.add(score[0] + score[1]);
            }

            sum.sort((o1, o2) -> o2 - o1);

            int answer = 0;
            for (answer = 0; answer < sum.size(); answer++) {
                if (sum.get(answer) == start[0] + start[1]) break;
            }

            return answer + 1;
        }
    }
}
