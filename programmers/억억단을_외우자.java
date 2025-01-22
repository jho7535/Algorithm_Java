package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 억억단을_외우자 {
    class Solution {
        public int[] solution(int e, int[] starts) {
            int[] cnt = new int[5000001];
            for (int i = 1; i <= 5000000; i++) {
                for (int j = 1; i * j <= 5000000; j++) {
                    cnt[i * j]++;
                }
            }

            int[] maxIndex = new int[e + 1];
            maxIndex[e] = e;
            for (int i = e - 1; i >= 1; i--) {
                if (cnt[i] >= cnt[maxIndex[i + 1]]) {
                    maxIndex[i] = i;
                } else {
                    maxIndex[i] = maxIndex[i + 1];
                }
            }

            int[] answer = new int[starts.length];
            for (int i = 0; i < starts.length; i++) {
                answer[i] = maxIndex[starts[i]];
            }

            return answer;
        }
    }
}
