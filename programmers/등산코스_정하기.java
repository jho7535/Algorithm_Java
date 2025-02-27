package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 등산코스_정하기 {
    class Solution {
        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            // 그래프 생성
            List<List<int[]>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<int[]>());
            }
            for (int[] path : paths) {
                graph.get(path[0]).add(new int[]{path[1], path[2]});
                graph.get(path[1]).add(new int[]{path[0], path[2]});
            }

            // 산봉우리 판별 배열 생성
            boolean[] isSummit = new boolean[n + 1];
            for (int summit : summits) {
                isSummit[summit] = true;
            }

            // pq에 시작점 세팅
            int[] intensity = new int[n + 1];
            Arrays.fill(intensity, Integer.MAX_VALUE);
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            for (int gate : gates) {
                pq.add(new int[]{gate, 0});
                intensity[gate] = 0;
            }

            // 다익스트라
            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int currentNode = current[0];
                int currentIntensity = current[1];

                if (isSummit[currentNode]) continue;
                if (currentIntensity > intensity[currentNode]) continue;

                for (int[] node : graph.get(currentNode)) {
                    int nextNode = node[0];
                    int nextWeight = node[1];

                    int nextIntensity = Math.max(currentIntensity, nextWeight);

                    if (intensity[nextNode] > nextIntensity) {
                        intensity[nextNode] = nextIntensity;
                        pq.offer(new int[]{nextNode, nextIntensity});
                    }
                }
            }

            // 산봉우리 중 intensity 값이 가장 작은 것
            Arrays.sort(summits);
            int[] answer = new int[]{0, Integer.MAX_VALUE};
            for (int summit : summits) {
                if (answer[1] > intensity[summit]) {
                    answer[0] = summit;
                    answer[1] = intensity[summit];
                }
            }

            return answer;
        }
    }
}
