package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 광물_캐기 {
    class Solution {
        static int n;
        static int min = Integer.MAX_VALUE;
        static int[] newPicks = new int[3];

        public int solution(int[] picks, String[] minerals) {
            for (int i = 0; i < minerals.length / 5 + 1; i++) {
                if (picks[0] > 0) {
                    newPicks[0]++;
                    picks[0]--;
                } else if (picks[1] > 0) {
                    newPicks[1]++;
                    picks[1]--;
                } else if (picks[2] > 0){
                    newPicks[2]++;
                    picks[2]--;
                }
            }
            n = newPicks[0] + newPicks[1]+ newPicks[2];
            combination(newPicks, 0, new int[n], minerals);

            int answer = min;
            return answer;
        }
        public void combination(int[] picks, int depth, int[] order, String[] minerals) {
            if (depth == n) {
                sol(order, minerals);
                return;
            }

            for (int i = 0; i < 3; i++) {
                if (picks[i] > 0) {
                    order[depth] = i;
                    picks[i]--;
                    combination(picks, depth + 1, order, minerals);
                    picks[i]++;
                }
            }
        }
        public void sol(int[] order, String[] minerals) {
            int cnt = 0;

            for (int i = 0; i < order.length; i++) {
                int pick = order[i];

                for (int j = i * 5; j < i * 5 + 5 && j < minerals.length; j++) {
                    if (pick == 0) cnt += 1;
                    else if (pick == 1 && minerals[j].equals("diamond")) cnt += 5;
                    else if (pick == 1) cnt += 1;
                    else if (pick == 2 && minerals[j].equals("diamond")) cnt += 25;
                    else if (pick == 2 && minerals[j].equals("iron")) cnt += 5;
                    else if (pick == 2) cnt += 1;
                }
            }

            min = Math.min(min, cnt);
        }
    }
}
