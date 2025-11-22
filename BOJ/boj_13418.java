package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_13418 {
    //public class Main {

    static int[][] edges;
    static int[] parents;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        edges = new int[m + 1][];
        parents = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) parents[i] = i;

        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i] = new int[]{s, e, w == 0 ? 1 : 0};
        }

        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        int min = 0;
        int cnt = 0;
        for (int[] edge : edges) {
            int s = edge[0];
            int e = edge[1];
            int w = edge[2];

            if (find(s) == find(e)) continue;

            union(s, e);
            min += w;
            cnt++;

            if (cnt == n) break;
        }

        for (int i = 0; i <= n; i++) parents[i] = i;
        Arrays.fill(rank, 0);

        int max = 0;
        cnt = 0;
        for (int i = m; i >= 0; i--) {
            int s = edges[i][0];
            int e = edges[i][1];
            int w = edges[i][2];

            if (find(s) == find(e)) continue;

            union(s, e);
            max += w;
            cnt++;
            if (cnt == n) break;
        }

        System.out.println((int) Math.pow(max, 2) - (int) Math.pow(min, 2));
    }

    public static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        if (rank[rootA] < rank[rootB]) {
            parents[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parents[rootB] = rootA;
        } else {
            parents[rootA] = rootB;
            rank[rootB]++;
        }
    }
}
