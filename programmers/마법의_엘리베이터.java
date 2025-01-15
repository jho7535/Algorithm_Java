package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 마법의_엘리베이터 {
    class Solution {
        public int solution(int storey) {
            int answer = 0;

            while (storey > 0) {
                int digit = storey % 10;
                int nextDigit = storey / 10 % 10;

                if (digit > 5 || (digit == 5 && nextDigit >= 5)) {
                    answer += (10 - digit);
                    storey += 10;
                } else {
                    answer += digit;
                }

                storey /= 10;
            }

            return answer;
        }
    }
}
