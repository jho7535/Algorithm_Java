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

        for (int i = 0; i < q; i++) {
            int k = Integer.parseInt(br.readLine());
            System.out.println(dp[k]);
        }
    }

    public static void dfs(int node) {
        visited[node] = true;
        List<Integer> current = graph.get(node);
        Set<Integer> set = new HashSet<>();

        for (int i : current) {
            if (visited[i]) continue;

            set.add(i);
            visited[i] = true;
            dfs(i);
            visited[i] = false;
        }

        for (int i : set) {
            dp[node] += dp[i];
        }
        dp[node]++;
    }
}
