package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2931 {
    //public class Main {

    static int[][] dr = {
            {-1, 1, 0, 0}, // 상하좌우
            {0, 1, 0, 0}, // 우하
            {-1, 0, 0, 0}, // 상우
            {0, -1, 0, 0}, // 좌상
            {0, 1, 0, 0}, // 좌하
            {-1, 1, 0, 0}, // 상하
            {0, 0, 0, 0}  // 좌우
    };
    static int[][] dc = {
            {0, 0, -1, 1}, // 상하좌우
            {1, 0, 0, 0}, // 우하
            {0, 1, 0, 0}, // 상우
            {-1, 0, 0, 0}, // 좌상
            {-1, 0, 0, 0}, // 좌하
            {0, 0, 0, 0}, // 상하
            {-1, 1, 0, 0}  // 좌우
    };
    static int n, m;
    static int row, col;
    static boolean[] from = new boolean[4];
    static int[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            for (int j = 0; j < m; j++) {
                char ch = str.charAt(j);

                if (ch == '+') map[i][j] = 0;
                else if (ch == '1') map[i][j] = 1;
                else if (ch == '2') map[i][j] = 2;
                else if (ch == '3') map[i][j] = 3;
                else if (ch == '4') map[i][j] = 4;
                else if (ch == '|') map[i][j] = 5;
                else if (ch == '-') map[i][j] = 6;
                else if (ch == 'M') map[i][j] = 7;
                else if (ch == 'Z') map[i][j] = 8;
                else map[i][j] = 9;
            }
        }

        // 파이프 bfs
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 7 && map[i][j] != 8 && map[i][j] != 9 && !visited[i][j]) bfs(visited, i, j);
            }
        }


        char ch = ' ';
        if (from[0] && from[1] && from[2] && from[3]) ch = '+';
        else if (from[0] && from[1] && !from[2] && !from[3]) ch = '|';
        else if (!from[0] && !from[1] && from[2] && from[3]) ch = '-';
        else if (!from[0] && from[1] && !from[2] && from[3]) ch = '1';
        else if (from[0] && !from[1] && !from[2] && from[3]) ch = '2';
        else if (from[0] && !from[1] && from[2] && !from[3]) ch = '3';
        else if (!from[0] && from[1] && from[2] && !from[3]) ch = '4';
        System.out.println((row + 1) + " " + (col + 1) + " " + ch);
    }

    static void bfs(boolean[][] visited, int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];

            int dir = map[curRow][curCol];
            int len = dir == 0 ? 4 : 2;
            for (int i = 0; i < len; i++) {
                int nr = curRow + dr[dir][i];
                int nc = curCol + dc[dir][i];

                // 방문 판단. 7,8 판단. 9는 표시.
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 7 || map[nr][nc] == 8) continue;
                if (map[nr][nc] == 9) {
                    row = nr;
                    col = nc;

                    if (dr[dir][i] == -1 && dc[dir][i] == 0) from[1] = true;
                    else if (dr[dir][i] == 1 && dc[dir][i] == 0) from[0] = true;
                    else if (dr[dir][i] == 0 && dc[dir][i] == -1) from[3] = true;
                    else if (dr[dir][i] == 0 && dc[dir][i] == 1) from[2] = true;

                    continue;
                }

                queue.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
    }
}
