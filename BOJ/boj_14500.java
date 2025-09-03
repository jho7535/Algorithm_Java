package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_14500 {
    //public class Main {

    static int[][] dr = {
            {0, 0, 0, 0},
            {0, 1, 2, 3},

            {0, 1, 1, 0},

            {0, 0, -1, -2},
            {0, 0, 0, -1},
            {0, 1, 2, 2},
            {0, 0, 1, 2},
            {0, 0, 1, 2},
            {0, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 1, 0, 0},

            {0, 1, 1, 2},
            {0, 1, 1, 2},
            {0, 0, 1, 1},
            {0, 0, -1, -1},

            {0, 0, 0, 1},
            {0, 1, 1, 2},
            {0, 0, 0, -1},
            {0, 0, -1, 1}
    };
    static int[][] dc = {
            {0, 1, 2, 3},
            {0, 0, 0, 0},

            {0, 0, 1, 1},

            {0, 1, 1, 1},
            {0, 1, 2, 2},
            {0, 0, 0, 1},
            {0, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 0, 1, 2},
            {0, 1, 2, 2},
            {0, 0, 1, 2},

            {0, 0, -1, -1},
            {0, 0, 1, 1},
            {0, 1, 1, 2},
            {0, 1, 1, 2},

            {0, 1, 2, 1},
            {0, 0, 1, 0},
            {0, 1, 2, 1},
            {0, 1, 1, 1}
    };
    static int max = 0;
    static int n, m;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count(i, j);
            }
        }

        System.out.println(max);
    }

    static void count(int row, int col) {
        for (int i = 0; i < 19; i++) {
            int sum = 0;

            for (int j = 0; j < 4; j++) {
                int nr = row + dr[i][j];
                int nc = col + dc[i][j];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    sum = 0;
                    break;
                }

                sum += map[nr][nc];
            }

            max = Math.max(max, sum);
        }
    }
}
