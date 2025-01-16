package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 귤_고르기 {
    class Solution {
        public int solution(int k, int[] tangerine) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : tangerine) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }

            List<Integer> list = new ArrayList<>(map.values());
            list.sort((a, b) -> b - a);

            int cnt = 0;
            for (int num : list) {
                if (k <= 0) break;

                k -= num;
                cnt++;
            }

            return cnt;
        }
    }
}
