package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2887 {
    //public class Main {

    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] planets = new int[n][4];

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        rank = new int[n];
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            planets[i][0] = i;
            planets[i][1] = Integer.parseInt(st.nextToken());
            planets[i][2] = Integer.parseInt(st.nextToken());
            planets[i][3] = Integer.parseInt(st.nextToken());
        }

        // x 기준 정렬
        Arrays.sort(planets, (a, b) -> a[1] - b[1]);
        for (int i = 0; i < n - 1; i++) {
            int s = planets[i][0];
            int e = planets[i + 1][0];
            int cost = Math.abs(planets[i][1] - planets[i + 1][1]);

            edges.add(new int[]{s, e, cost});
        }

        // y 기준 정렬
        Arrays.sort(planets, (a, b) -> a[2] - b[2]);
        for (int i = 0; i < n - 1; i++) {
            int s = planets[i][0];
            int e = planets[i + 1][0];
            int cost = Math.abs(planets[i][2] - planets[i + 1][2]);

            edges.add(new int[]{s, e, cost});
        }

        // z 기준 정렬
        Arrays.sort(planets, (a, b) -> a[3] - b[3]);
        for (int i = 0; i < n - 1; i++) {
            int s = planets[i][0];
            int e = planets[i + 1][0];
            int cost = Math.abs(planets[i][3] - planets[i + 1][3]);

            edges.add(new int[]{s, e, cost});
        }

        edges.sort((a, b) -> a[2] - b[2]);

        long sum = 0;
        int cnt = 0;
        for (int[] edge : edges) {
            if (find(edge[0]) == find(edge[1])) continue;

            union(edge[0], edge[1]);
            sum += edge[2];
            cnt++;

            if (cnt == n - 1) break;
        }

        System.out.println(sum);
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
            rank[rootY]++;
        }
    }
}
