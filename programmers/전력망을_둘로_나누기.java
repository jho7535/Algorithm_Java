package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 전력망을_둘로_나누기 {
    class Solution {
        static int answer = Integer.MAX_VALUE;

        public int solution(int n, int[][] wires) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int[] wire : wires) {
                graph.get(wire[0]).add(wire[1]);
                graph.get(wire[1]).add(wire[0]);
            }

            for (int[] wire : wires) {
                graph.get(wire[0]).remove(Integer.valueOf(wire[1]));
                graph.get(wire[1]).remove(Integer.valueOf(wire[0]));
                bfs(graph, n);
                graph.get(wire[0]).add(wire[1]);
                graph.get(wire[1]).add(wire[0]);
            }

            return answer;
        }

        public void bfs(List<List<Integer>> graph, int n) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(1);
            boolean[] visited = new boolean[n + 1];
            visited[1] = true;
            int cnt = 1;

            while (!queue.isEmpty()) {
                List<Integer> list = graph.get(queue.poll());

                for (int node : list) {
                    if (visited[node]) continue;

                    queue.add(node);
                    cnt++;
                    visited[node] = true;
                }
            }

            answer = Math.min(answer, Math.abs((n - cnt) - cnt));
        }
    }
}
