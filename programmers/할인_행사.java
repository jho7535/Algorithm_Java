package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 할인_행사 {
    class Solution {
        public int solution(String[] want, int[] number, String[] discount) {
            Map<String, Integer> wantMap = new HashMap<>();
            for (int i = 0; i < number.length; i++) {
                wantMap.put(want[i], number[i]);
            }
            System.out.println(wantMap.toString());

            Map<String, Integer> dp = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                dp.put(discount[i], dp.getOrDefault(discount[i], 0) + 1);
            }

            int cnt = 0;
            if (wantMap.equals(dp)) cnt++;

            for (int i = 10; i < discount.length; i++) {
                dp.put(discount[i - 10], dp.get(discount[i - 10]) - 1);
                dp.remove(discount[i - 10], 0);
                dp.put(discount[i], dp.getOrDefault(discount[i], 0) + 1);

                if (wantMap.equals(dp)) cnt++;
            }

            return cnt;
        }
    }
}
