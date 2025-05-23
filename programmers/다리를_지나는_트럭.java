package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 다리를_지나는_트럭 {
    class Solution {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int time = 0;
            Queue<Integer> queue = new LinkedList<>();
            for (int w : truck_weights) queue.add(w);
            Queue<int[]> bridge = new LinkedList<>();
            int curWeight = 0;

            while (!queue.isEmpty() || !bridge.isEmpty()) {
                time++;

                // 다리 가장 앞 트럭 제거
                if (!bridge.isEmpty() && bridge.peek()[1] + bridge_length == time) {
                    curWeight -= bridge.poll()[0];
                }

                // 대기 트럭 다리 진입
                // 다리 최대 트럭 개수, 무게 확인
                if (queue.isEmpty()) continue;
                if (bridge.size() < bridge_length && curWeight + queue.peek() <= weight) {
                    int w = queue.poll();
                    bridge.add(new int[]{w, time});
                    curWeight += w;
                }

            }

            return time;
        }
    }
}
