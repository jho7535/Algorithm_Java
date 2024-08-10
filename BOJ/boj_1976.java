package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1976 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] plan = new int[m];

        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n + 1; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) plan[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m - 1; i++) {
            Arrays.fill(visited, false);
            bfs(plan[i], graph, visited, n);

            if (!visited[plan[i + 1]]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    static void bfs(int node, int[][] graph, boolean[] visited, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int start = queue.poll();

            for (int i = 1; i < n + 1; i++) {
                if (!visited[i] && graph[start][i] == 1) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
