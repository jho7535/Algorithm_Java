package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1766 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        int[] indegree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph.get(s).add(e);
            indegree[e]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) pq.add(i);
        }

        List<Integer> output = new ArrayList<>();
        while (!pq.isEmpty()) {
            int cur = pq.poll();

            output.add(cur);

            for (int next : graph.get(cur)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    pq.add(next);
                }
            }
        }

        for (int k : output) System.out.print(k + " ");
    }
}
