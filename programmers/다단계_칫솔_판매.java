package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 다단계_칫솔_판매 {
    class Solution {
        public List<Integer> solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            Map<String, String> parent = new HashMap<>();
            Map<String, Integer> cnt = new HashMap<>();
            // 트리 구성
            for (int i = 0; i < enroll.length; i++) {
                parent.put(enroll[i], referral[i]);
                cnt.put(enroll[i], 0);
            }

            // 계산
            for (int i = 0; i < seller.length; i++) {
                String current = seller[i];
                int money = amount[i] * 100;

                while (parent.containsKey(current) && money != 0) {
                    int div = money / 10;
                    cnt.put(current, cnt.get(current) + money - div);

                    current = parent.get(current);
                    money = div;
                }
            }

            List<Integer> answer = new ArrayList<>();
            for (String s : enroll) {
                answer.add(cnt.get(s));
            }

            return answer;
        }
    }
}
