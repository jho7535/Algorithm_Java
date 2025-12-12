package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1516 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] time = new int[n + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        int[] indegree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int next = Integer.parseInt(st.nextToken());
            while (next != -1) {
                graph.get(next).add(i);
                indegree[i]++;

                next = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (indegree[i] != 0) continue;

            pq.add(new int[]{i, time[i]});
        }
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cur = current[0];
            int curTime = current[1];

            answer[cur] = curTime;

            for (int next : graph.get(cur)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    pq.add(new int[]{next, curTime + time[next]});
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(answer[i]);
        }
    }
}
