package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1799 {
    //public class Main {

    static int[][] map;
    static int n;
    static int whiteMax = 0;
    static int blackMax = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0, false);
        dfs(0, 1, 0, true);

        System.out.println(whiteMax + blackMax);
    }

    static void dfs(int row, int col, int cnt, boolean isWhite) {
        if (col >= n) {
            row++;
            if (isWhite) {
                col = (row % 2 == 0) ? 1 : 0;
            } else {
                col = (row % 2 == 0) ? 0 : 1;
            }
        }

        if (row >= n) {
            if (isWhite) whiteMax = Math.max(whiteMax, cnt);
            else blackMax = Math.max(blackMax, cnt);
            return;
        }

        if (map[row][col] == 1) {
            check(row, col, row * n + col + 2);
            dfs(row, col + 2, cnt + 1, isWhite);
            uncheck(row, col, row * n + col + 2);
        }

        dfs(row, col + 2, cnt, isWhite);

    }

    static void check(int row, int col, int num) {
        int nr, nc;

        // 좌상
        nr = row;
        nc = col;
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (map[nr][nc] == 1) map[nr][nc] = num;
            nr--;
            nc--;
        }

        // 우상
        nr = row;
        nc = col;
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (map[nr][nc] == 1) map[nr][nc] = num;
            nr--;
            nc++;
        }

        // 좌하
        nr = row;
        nc = col;
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (map[nr][nc] == 1) map[nr][nc] = num;
            nr++;
            nc--;
        }

        // 우하
        nr = row;
        nc = col;
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (map[nr][nc] == 1) map[nr][nc] = num;
            nr++;
            nc++;
        }
    }

    static void uncheck(int row, int col, int num) {
        int nr, nc;

        // 좌상
        nr = row;
        nc = col;
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (map[nr][nc] == num) map[nr][nc] = 1;
            nr--;
            nc--;
        }

        // 우상
        nr = row;
        nc = col;
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (map[nr][nc] == num) map[nr][nc] = 1;
            nr--;
            nc++;
        }

        // 좌하
        nr = row;
        nc = col;
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (map[nr][nc] == num) map[nr][nc] = 1;
            nr++;
            nc--;
        }

        // 우하
        nr = row;
        nc = col;
        while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
            if (map[nr][nc] == num) map[nr][nc] = 1;
            nr++;
            nc++;
        }
    }
}
