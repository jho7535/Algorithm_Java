package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 서버_증설_횟수 {
    class Solution {
        public int solution(int[] players, int m, int k) {
            int time = 0;
            int answer = 0;
            Queue<Integer> queue = new ArrayDeque<>();

            while (time < 24) {
                while (!queue.isEmpty() && queue.peek() <= time) {
                    queue.poll();
                }

                int need = players[time] / m;
                if (need > queue.size()) {
                    int p = need - queue.size();
                    answer += p;
                    for (int i = 0; i < p; i++) queue.add(time + k);
                }

                time++;
            }

            return answer;
        }
    }
}
