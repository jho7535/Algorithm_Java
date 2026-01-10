package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 퍼즐_게임_챌린지 {
    class Solution {
        public int solution(int[] diffs, int[] times, long limit) {
            int n = diffs.length;

            int low = 1;
            int high = 100_000;
            int min = Integer.MAX_VALUE;
            while (low <= high) {
                int mid = (low + high) / 2;

                long sum = 0L;
                boolean flag = true;
                for (int i = 0; i < n; i++) {
                    if (mid >= diffs[i]) {
                        sum += times[i];
                    } else {
                        sum += (times[i] + times[i - 1]) * (diffs[i] - mid) + times[i];
                    }

                    if (sum > limit) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) low = mid + 1;
                else {
                    min = Math.min(min, mid);
                    high = mid - 1;
                }
            }

            return min;
        }
    }
}
