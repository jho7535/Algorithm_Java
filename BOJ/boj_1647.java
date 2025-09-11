package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1647 {
    //public class Main {

    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges.add(new int[]{s, e, w});
        }

        edges.sort((a, b) -> a[2] - b[2]);
        int max = 0;
        int cnt = 0;
        long sum = 0L;
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            int w = edge[2];

            if (find(n1) != find(n2)) {
                max = Math.max(max, w);
                sum += w;
                union(n1, n2);
                cnt++;
            }

            if (cnt == n - 1) break;
        }

        System.out.println(sum - (long) max);
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
        else if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
        else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }
}
