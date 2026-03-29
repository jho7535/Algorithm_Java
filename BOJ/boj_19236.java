package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_19236 {
    //public class Main {

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] shark = new int[]{0, 0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][][] space = new int[4][4][2];
        Map<Integer, int[]> map = new HashMap<>();
        int[] shark = new int[]{0, 0};

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                space[i][j][0] = Integer.parseInt(st.nextToken());
                space[i][j][1] = Integer.parseInt(st.nextToken()) - 1;

                map.put(space[i][j][0], new int[]{i, j});
            }
        }

        int sum = space[0][0][0];
        map.remove(space[0][0][0]);
        space[0][0][0] = 0;
        dfs(space, map, sum);

        System.out.println(answer);
    }

    static void dfs(int[][][] space, Map<Integer, int[]> map, int sum) {
        answer = Math.max(answer, sum);

        // 물고기 이동
        // 1 ~ 16
        for (int number = 1; number <= 16; number++) {
            if (!map.containsKey(number)) continue;

            int r = map.get(number)[0];
            int c = map.get(number)[1];

            // 이동 가능한 곳 찾기
            for (int i = 0; i < 8; i++) {
                int dir = (space[r][c][1] + i) % 8;
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (isRange(nr, nc) && !(nr == shark[0] && nc == shark[1])) {
                    // 스왑
                    space[r][c][1] = dir;
                    swap(r, c, nr, nc, space, map);

                    break;
                }
            }
        }

        // 상어 이동
        int r = shark[0];
        int c = shark[1];
        int dir = space[r][c][1];
        for (int i = 1; i < 4; i++) {
            int nr = r + dr[dir] * i;
            int nc = c + dc[dir] * i;

            if (isRange(nr, nc) && space[nr][nc][0] != 0) {
                int number = space[nr][nc][0];

                int[][][] copySpace = new int[4][4][2];
                for (int p = 0; p < 4; p++) {
                    for (int q = 0; q < 4; q++) {
                        copySpace[p][q][0] = space[p][q][0];
                        copySpace[p][q][1] = space[p][q][1];
                    }
                }

                Map<Integer, int[]> copyMap = new HashMap<>();
                for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
                    copyMap.put(entry.getKey(), entry.getValue().clone());
                }

                shark[0] = nr;
                shark[1] = nc;
                copySpace[nr][nc][0] = 0;
                copyMap.remove(number);
                dfs(copySpace, copyMap, sum + number);
                shark[0] = r;
                shark[1] = c;
            }
        }
    }

    static boolean isRange(int row, int col) {
        return row >= 0 && row < 4 && col >= 0 && col < 4;
    }

    static void swap(int r1, int c1, int r2, int c2, int[][][] space, Map<Integer, int[]> map) {
        int tempNumber = space[r1][c1][0];
        int tempDir = space[r1][c1][1];

        space[r1][c1][0] = space[r2][c2][0];
        space[r2][c2][0] = tempNumber;
        space[r1][c1][1] = space[r2][c2][1];
        space[r2][c2][1] = tempDir;

        if (space[r1][c1][0] != 0) map.put(space[r1][c1][0], new int[]{r1, c1});
        if (space[r2][c2][0] != 0) map.put(space[r2][c2][0], new int[]{r2, c2});
    }
}
