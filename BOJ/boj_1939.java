package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1939 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new int[]{e, w});
            graph.get(e).add(new int[]{s, w});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        pq.add(new int[]{start, Integer.MAX_VALUE});
        int[] visited = new int[n + 1];
        visited[start] = Integer.MAX_VALUE;
        int max = 0;
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];
            int curWeight = current[1];

            if (curWeight < visited[curNode]) continue;
            if (curNode == end) {
                max = curWeight;
                continue;
            }

            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextWeight = Math.min(curWeight, next[1]);

                if (nextWeight <= visited[nextNode]) continue;

                visited[nextNode] = nextWeight;
                pq.add(new int[]{nextNode, nextWeight});
            }
        }

        System.out.println(max);
    }
}
