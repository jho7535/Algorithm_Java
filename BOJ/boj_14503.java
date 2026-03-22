package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_14503 {
    //public class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int n, m;
    static int[][] map;
    static boolean[][] isCleaned;
    static int dir;
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        isCleaned = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        while (true) {
            // 현재 칸 청소
            if (!isCleaned[r][c]) {
                isCleaned[r][c] = true;
                cnt++;
            }

            // 주변 청소 판단
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (!isRange(nr, nc) || map[nr][nc] == 1) continue;

                if (!isCleaned[nr][nc]) {
                    flag = true;
                    break;
                }
            }

            // 청소 안 해도 됨
            if (!flag) {
                int nr = r + dr[(dir + 2) % 4];
                int nc = c + dc[(dir + 2) % 4];

                if (isRange(nr, nc) && map[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                } else {
                    break;
                }
            }
            // 청소 해야 됨
            else {
                while (true) {
                    dir = (dir + 4 - 1) % 4;

                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if (isRange(nr, nc) && map[nr][nc] == 0 && !isCleaned[nr][nc]) {
                        r = nr;
                        c = nc;
                        break;
                    }
                }
            }
        }

        System.out.println(cnt);
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
