package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2211 {
    //public class Main {

    static int n, m;
    static int[][] distance;
    static List<List<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        distance = new int[n + 1][];
        for (int i = 0; i <= n; i++) distance[i] = new int[]{0, 0, Integer.MAX_VALUE};
        distance[1][2] = 0;
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new int[]{e, w});
            graph.get(e).add(new int[]{s, w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];
            int curWeight = current[1];

            if (distance[curNode][2] < curWeight) continue;

            for (int[] next : graph.get(curNode)) {
                int nextNode = next[0];
                int nextWeight = next[1];

                if (distance[nextNode][2] > distance[curNode][2] + nextWeight) {
                    distance[nextNode][2] = distance[curNode][2] + nextWeight;
                    distance[nextNode][0] = curNode;
                    distance[nextNode][1] = nextNode;
                    pq.add(new int[]{nextNode, distance[nextNode][2]});
                }
            }
        }

        List<int[]> output = new ArrayList<>();
        for (int[] dist : distance) {
            if (dist[2] == 0 || dist[2] == Integer.MAX_VALUE) continue;

            output.add(new int[]{dist[0], dist[1]});
        }

        System.out.println(output.size());
        for (int[] o : output) {
            System.out.println(o[0] + " " + o[1]);
        }
    }
}
