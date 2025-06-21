package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 금과_은_운반하기 {
    class Solution {
        public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
            long low = 0;
            long high = 100_000_000_000_000L * 4L;
            long answer = 0;

            while (low <= high) {
                long mid = (low + high) / 2;

                if (decision(a, b, g, s, w, t, mid)) {
                    answer = mid;
                    high = mid - 1;
                }
                else low = mid + 1;
            }

            return answer;
        }

        public boolean decision(int a, int b, int[] g, int[] s, int[] w, int[] t, long target) {
            long sumGold = 0;
            long sumSilver = 0;
            long sumWeight = 0;

            int n = g.length;
            for (int i = 0; i < n; i++) {
                long move = target / (t[i] * 2);
                if (target % (t[i] * 2) >= t[i]) move++;

                long weight = Math.min((long) g[i] + (long) s[i], (long) w[i] * move);
                long gold = Math.min((long) g[i], weight);
                long silver = Math.min((long) s[i], weight);

                sumGold += gold;
                sumSilver += silver;
                sumWeight += weight;
            }

            return sumGold >= (long) a && sumSilver >= (long) b && sumWeight >= (long) a + (long) b;
        }
    }
}
