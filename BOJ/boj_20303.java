package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_20303 {
    //public class Main {

    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] candy;
    static List<int[]> groups = new ArrayList<>();
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        candy = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) candy[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        // bfs
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) bfs(i);
        }

        groups.sort((a, b) -> {
           if (a[0] == b[0]) return b[1] - a[1];
           return a[0] - b[0];
        });

        int[] dp = new int[k];
        int max = 0;
        for (int[] group : groups) {
            int curKid = group[0];
            int curCandy = group[1];

            for (int i = k - 1; i > curKid; i--) {
                if (dp[i - curKid] != 0) {
                    dp[i] = Math.max(dp[i], dp[i - curKid] + curCandy);
                    max = Math.max(max, dp[i]);
                }
            }
            if (curKid < k) {
                dp[curKid] = Math.max(dp[curKid], curCandy);
                max = Math.max(max, dp[curKid]);
            }
        }

        System.out.println(max);
    }

    static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        int candyCnt = 0;
        int kidCnt = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            kidCnt++;
            candyCnt += candy[cur];

            for (int next : graph.get(cur)) {
                if (visited[next]) continue;

                queue.add(next);
                visited[next] = true;
            }
        }

        groups.add(new int[]{kidCnt, candyCnt});
    }
}
