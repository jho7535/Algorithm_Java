package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 시소_짝꿍 {
    class Solution {
        public long solution(int[] weights) {
            int[] cnt = new int[1001];
            long answer = 0;
            for (int i = 0; i < weights.length; i++) {
                cnt[weights[i]]++;
            }

            for (int i = 1; i < 1001; i++) {
                if (cnt[i] == 0) continue;

                if (cnt[i] > 1) {
                    answer += (long) cnt[i] * (cnt[i] - 1) / 2;
                }

                if (i % 2 == 0 && i / 2 <= 1000) answer += (long) cnt[i] * cnt[i / 2];
                if (i % 3 == 0 && i * 2 / 3 <= 1000) answer += (long) cnt[i] * cnt[i * 2 / 3];
                if (i % 4 == 0 && i * 3 / 4 <= 1000) answer += (long) cnt[i] * cnt[i * 3 / 4];
            }

            return answer;
        }
    }
}
