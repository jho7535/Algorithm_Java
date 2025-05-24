package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 가장_먼_노드 {
    class Solution {
        public int solution(int n, int[][] edge) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] ed : edge) {
                graph.get(ed[0]).add(ed[1]);
                graph.get(ed[1]).add(ed[0]);
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{1, 0});
            boolean[] visited = new boolean[n + 1];
            visited[1] = true;
            List<int[]> list = new ArrayList<>();
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int node = cur[0];
                int dis = cur[1];
                list.add(new int[]{node, dis});

                for (int k : graph.get(node)) {
                    if (visited[k]) continue;

                    queue.add(new int[]{k, dis + 1});
                    visited[k] = true;
                }
            }

            list.sort((a, b) -> b[1] - a[1]);
            int max = list.get(0)[1];
            int answer = 0;
            for (int[] l : list) {
                if (l[1] < max) break;

                answer++;
            }

            return answer;
        }
    }
}
