package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_17835 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(e).add(new int[]{s, w});
        }

        long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        for (int i = 0; i < k; i++) {
            int z = Integer.parseInt(st.nextToken());
            pq.add(new long[]{z, 0});
            distance[z] = 0;
        }

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int curNode = (int) cur[0];
            long curWeight = cur[1];

            if (curWeight > distance[curNode]) continue;

            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextWeight = next[1];

                if (distance[curNode] + nextWeight < distance[nextNode]) {
                    distance[nextNode] = distance[curNode] + nextWeight;
                    pq.add(new long[]{nextNode, distance[nextNode]});
                }
            }
        }

        int maxNode = 0;
        long maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] > maxDist) {
                maxDist = distance[i];
                maxNode = i;
            }
        }

        System.out.println(maxNode + "\n" + maxDist);
    }
}
