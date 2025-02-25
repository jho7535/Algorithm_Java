package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 이진_변환_반복하기 {
    class Solution {
        public int[] solution(String s) {
            int cycle = 0, cnt = 0;

            while (!s.equals("1")) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '0') cnt++;
                }

                s = s.replace("0", "");
                s = Integer.toBinaryString(s.length());

                cycle++;
            }

            int[] answer = {cycle, cnt};

            return answer;
        }
    }
}
