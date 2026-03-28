package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_17144 {
    //public class Main {

    static int[][] map;
    static int upRow = 0, upCol = 0;
    static int downRow = 0, downCol = 0;
    static int r, c, t;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == -1 && upRow == 0) {
                    upRow = i;
                } else if (map[i][j] == -1) {
                    downRow = i;
                }
            }
        }

        while (t-- > 0) {
            // 확산
            int[][] sum = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (map[i][j] <= 0) continue;

                    int cur = map[i][j];

                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if (!isRange(nr, nc) || map[nr][nc] == -1) continue;

                        sum[nr][nc] += cur / 5;
                        map[i][j] -= cur / 5;
                    }
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    map[i][j] += sum[i][j];
                }
            }

            // 바람
            // 반시계
            // j == 0
            for (int i = upRow; i > 0; i--) {
                map[i][0] = map[i - 1][0];
            }
            // i == 0
            for (int j = upCol; j < c - 1; j++) {
                map[0][j] = map[0][j + 1];
            }
            // j == c - 1
            for (int i = 0; i < upRow; i++) {
                map[i][c - 1] = map[i + 1][c - 1];
            }
            // i == upRow
            for (int j = c - 1; j > 1; j--) {
                map[upRow][j] = map[upRow][j - 1];
            }
            map[upRow][1] = 0;
            map[upRow][upCol] = -1;


            // 시계
            // j == 0
            for (int i = downRow; i < r - 1; i++) {
                map[i][0] = map[i + 1][0];
            }
            // i == r - 1
            for (int j = downCol; j < c - 1; j++) {
                map[r - 1][j] = map[r - 1][j + 1];
            }
            // j == c - 1
            for (int i = r - 1; i > downRow; i--) {
                map[i][c - 1] = map[i - 1][c - 1];
            }
            // i == downRow
            for (int j = c - 1; j > 1; j--) {
                map[downRow][j] = map[downRow][j - 1];
            }
            map[downRow][1] = 0;
            map[downRow][downCol] = -1;
        }

        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == -1) continue;

                answer += map[i][j];
            }
        }
        System.out.println(answer);
    }

    static boolean isRange(int row, int col) {
        return row >= 0 && row < r && col >= 0 && col < c;
    }
}
