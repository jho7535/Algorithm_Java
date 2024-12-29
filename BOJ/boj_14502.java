package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_14502 {
    //public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int max = 0;
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{1, -1, 0, 0};

    public static void main(String[] argv) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0, new int[3][2]);

        System.out.println(max);
    }

    public static void combination(int start, int depth, int[][] selected) {
        if (depth == 3) {
            bfs(selected, new int[n][m]);
            return;
        }

        for (int i = start; i < n * m; i++) {
            int row = i / m;
            int col = i % m;

            if (map[row][col] == 0) {
                selected[depth][0] = row;
                selected[depth][1] = col;
                combination(i + 1, depth + 1, selected);
            }
        }
    }

    public static void bfs(int[][] selected, int[][] newMap) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            newMap[selected[i][0]][selected[i][1]] = 1;
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) queue.add(new int[]{i, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int r = current[0] + dr[i];
                int c = current[1] + dc[i];

                if (isRange(r, c) && newMap[r][c] == 0) {
                    newMap[r][c] = 2;
                    queue.add(new int[]{r, c});
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (newMap[i][j] == 0) cnt++;
            }
        }
        max = Math.max(cnt, max);
    }

    public static boolean isRange(int row, int col) {
        return row < n && row >= 0 && col < m && col >= 0;
    }
}
