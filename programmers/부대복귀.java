package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 부대복귀 {
    class Solution {
        static List<List<Integer>> graph;
        static int[] distance;

        public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            graph = new ArrayList<List<Integer>>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            distance = new int[n + 1];
            Arrays.fill(distance, -1);

            for (int[] road : roads){
                graph.get(road[0]).add(road[1]);
                graph.get(road[1]).add(road[0]);
            }

            bfs(destination, n);

            int[] answer = new int[sources.length];
            for (int i = 0; i < sources.length; i++) {
                answer[i] = distance[sources[i]];
            }

            return answer;
        }

        public void bfs(int start, int n) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            distance[start] = 0;

            while (!queue.isEmpty()) {
                int node = queue.poll();
                List<Integer> current = graph.get(node);

                for (int i = 0; i < current.size(); i++) {
                    if (distance[current.get(i)] == -1) {
                        queue.add(current.get(i));
                        distance[current.get(i)] = distance[node] + 1;
                    }
                }
            }
        }
    }
}
