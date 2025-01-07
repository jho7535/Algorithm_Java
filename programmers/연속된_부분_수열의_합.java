package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 연속된_부분_수열의_합 {
    class Solution {
        public int[] solution(int[] sequence, int k) {
            int left = 0, right = 0;
            int minLength = Integer.MAX_VALUE;
            int sum = 0;
            int[] answer = new int[2];

            while (right < sequence.length) {
                sum += sequence[right];
                right++;

                while (sum >= k) {
                    if (sum == k && minLength > right - left - 1) {
                        minLength = right - left - 1;
                        answer[0] = left;
                        answer[1] = right - 1;
                    }

                    sum -= sequence[left];
                    left++;
                }
            }

            return answer;
        }
    }
}

