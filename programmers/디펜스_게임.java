package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 디펜스_게임 {
    class Solution {
        public int solution(int n, int k, int[] enemy) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            int cnt = 0;

            for (int i = 0; i < enemy.length; i++) {
                if (n < enemy[i] && k <= 0) {
                    break;
                }

                cnt++;
                pq.add(enemy[i]);
                n -= enemy[i];

                if (n < 0 && k > 0) {
                    n += pq.poll();
                    k--;
                }
            }

            return cnt;
        }
    }
}
