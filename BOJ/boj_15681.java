package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_15681 {
    //public class Main {

    public static boolean[] visited;
    public static List<List<Integer>> graph;
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        visited = new boolean[n + 1];
        dp = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        dfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int k = Integer.parseInt(br.readLine());
            sb.append(dp[k]).append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int node) {
        visited[node] = true;
        dp[node] = 1;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next);
                dp[node] += dp[next];
            }
        }
    }
}
