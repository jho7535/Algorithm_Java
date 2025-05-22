package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 입국심사 {
    class Solution {
        public long solution(int n, int[] times) {
            long low = 1L;
            long high = 0;
            for (int time : times) high = Math.max(high, time);
            high *= n;
            long answer = high;

            while (low <= high) {
                long mid = (low + high) / 2;
                long sum = 0;
                for (int time : times) {
                    sum += mid / time;
                }

                if (sum >= n) {
                    answer = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return answer;
        }
    }
}
