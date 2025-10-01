package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_16724 {
    //public class Main {

    static int n, m;
    static int[][] board;
    static boolean[][] visited;
    static int[] parent;
    static int[] rank;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                if (str.charAt(j) == 'U') board[i][j] = 0;
                else if (str.charAt(j) == 'D') board[i][j] = 1;
                else if (str.charAt(j) == 'L') board[i][j] = 2;
                else board[i][j] = 3;
            }
        }

        parent = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            parent[i] = i;
        }
        rank = new int[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) continue;

                search(i, j);
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int root = find(i * m + j);

                if (set.contains(root)) continue;

                set.add(root);
            }
        }

        System.out.println(set.size());
    }

    static void search(int r, int c) {
        visited[r][c] = true;

        int pr = r;
        int pc = c;
        int nr = pr + dr[board[pr][pc]];
        int nc = pc + dc[board[pr][pc]];
        while (find(nr * m + nc) != find(pr * m + pc)) {
            visited[nr][nc] = true;

            union(nr * m + nc, pr * m + pc);

            pr = nr;
            pc = nc;
            nr = pr + dr[board[pr][pc]];
            nc = pc + dc[board[pr][pc]];
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
            rank[rootB]++;
        }
    }
}
