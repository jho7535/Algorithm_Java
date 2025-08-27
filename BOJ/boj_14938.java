package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_14938 {
    //public class Main {

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) items[i] = Integer.parseInt(st.nextToken());

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new int[]{e, w});
            graph.get(e).add(new int[]{s, w});
        }

        for (int i = 1; i <= n; i++) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
            int[] distance = new int[n + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            pq.add(new int[]{i, 0});
            distance[i] = 0;
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int curNode = cur[0];
                int curDist = cur[1];

                if (curDist > distance[curNode]) continue;

                for (int[] next : graph.get(curNode)) {
                    int nextNode = next[0];
                    int nextDist = next[1];

                    if (distance[nextNode] > distance[curNode] + nextDist) {
                        distance[nextNode] = distance[curNode] + nextDist;
                        pq.add(new int[]{nextNode, distance[nextNode]});
                    }
                }
            }

            // 먹을 수 있는 아이템 계산
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (distance[j] <= m) {
                    cnt += items[j];
                }
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}
