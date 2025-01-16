package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 귤_고르기 {
    class Solution {
        public int solution(int k, int[] tangerine) {
            Integer[] arr = new Integer[10000001];
            Arrays.fill(arr, 0);
            for (int i : tangerine) {
                arr[i]++;
            }

            Arrays.sort(arr, (a, b) -> b - a);

            int index = 0, cnt = 1;
            while (k > 0 && index < 10000001) {
                if (arr[index] == 0) {
                    index++;
                    cnt++;
                    continue;
                }

                arr[index]--;
                k--;
            }

            return cnt;
        }
    }
}
