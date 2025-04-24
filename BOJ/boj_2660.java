package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2660 {
    //public class Main {

    static List<List<Integer>> graph;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if (n1 == -1 && n2 == -1) break;

            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }

        List<int[]> answer = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int d = bfs(i);

            answer.add(new int[]{i, d});
        }

        answer.sort((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            else return a[1] - b[1];
        });

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int[] node : answer) {
            if (min < node[1]) break;

            min = node[1];
            sb.append(node[0]).append(" ");
            cnt++;
        }

        System.out.println(min + " " + cnt);
        System.out.println(sb);
    }

    static int bfs(int node) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{node, 0});
        boolean[] visited = new boolean[n + 1];
        visited[node] = true;

        int depth = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curNode = current[0];
            int curLevel = current[1];
            depth = curLevel;

            for (int i : graph.get(curNode)) {
                if (visited[i]) continue;

                visited[i] = true;
                queue.add(new int[]{i, curLevel + 1});
            }
        }

        return depth;
    }
}
