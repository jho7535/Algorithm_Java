package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 네트워크 {
    class Solution {
        public int solution(int n, int[][] computers) {
            boolean[] visited = new boolean[n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;

                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int current = queue.poll();

                    for (int j = 0; j < n; j++) {
                        if (computers[current][j] != 1) continue;
                        if (visited[j]) continue;

                        queue.add(j);
                        visited[j] = true;
                    }
                }

                cnt++;
            }

            return cnt;
        }
    }
}
