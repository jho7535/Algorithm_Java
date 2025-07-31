package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_14499 {
    //public class Main {

    static int[][] map;
    static int[] dice = new int[6];
    static int n, m;
    static int[] pos;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[] op;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pos = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        int k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        op = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) op[i] = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int dir : op) {
            dir--;

            int nr = pos[0] + dr[dir];
            int nc = pos[1] + dc[dir];

            // 맵 확인
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

            // 주사위 굴리기
            pos[0] = nr;
            pos[1] = nc;
            roll(dir);

            // 바닥 확인하고 값 처리
            if (map[nr][nc] == 0) {
                map[nr][nc] = dice[0];
            } else {
                dice[0] = map[nr][nc];
                map[nr][nc] = 0;
            }

            // sb에 출력 값 누적
            sb.append(dice[5]).append("\n");
        }

        System.out.println(sb);
    }

    static void roll(int dir) {
        int v0 = dice[0];
        int v1 = dice[1];
        int v2 = dice[2];
        int v3 = dice[3];
        int v4 = dice[4];
        int v5 = dice[5];

        // 우
        if (dir == 0) {
            dice[0] = v2;
            dice[3] = v0;
            dice[5] = v3;
            dice[2] = v5;
        }
        // 좌
        else if (dir == 1) {
            dice[0] = v3;
            dice[3] = v5;
            dice[5] = v2;
            dice[2] = v0;
        }
        // 상
        else if (dir == 2) {
            dice[0] = v1;
            dice[4] = v0;
            dice[5] = v4;
            dice[1] = v5;
        }
        // 하
        else if (dir == 3) {
            dice[0] = v4;
            dice[4] = v5;
            dice[5] = v1;
            dice[1] = v0;
        }
    }
}
