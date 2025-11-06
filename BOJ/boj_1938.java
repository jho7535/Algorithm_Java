package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

/*
* 상하좌우, 회전.
* 최소 횟수로 최종 위치로
*
* 입 |   n : 4-50
*       n*n : 0,1,B,E
* 출 |   최소 동작 횟수 출력. 불가능 0
*
* n*n 중심 기준으로 탐색
*
* */

public class boj_1938 {
    //public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] ddr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] ddc = {0, 1, 1, 1, 0, -1, -1, -1};

    static int n;
    static int[] s = new int[3];
    static int[] e = new int[3];
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        char[][] input = new char[n][n];
        for (int i = 0; i < n; i++) {
            input[i] = br.readLine().toCharArray();
        }

        int bCnt = 0;
        int eCnt = 0;
        map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (input[i][j] == '1') map[i][j] = true;
                else if (input[i][j] == 'B') bCnt++;
                else if (input[i][j] == 'E') eCnt++;

                if (input[i][j] == 'B' && bCnt == 2) {
                    s[0] = i;
                    s[1] = j;
                } else if (input[i][j] == 'E' && eCnt == 2) {
                    e[0] = i;
                    e[1] = j;
                }
            }
        }

        if (input[s[0] - 1][s[1]] == 'B') s[2] = 1;
        else s[2] = 0;

        if (input[e[0] - 1][e[1]] == 'E') e[2] = 1;
        else e[2] = 0;

        // bfs. r, c, 가로/세로, 횟수
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{s[0], s[1], s[2], 0});
        boolean[][][] visited = new boolean[n][n][2];
        visited[s[0]][s[1]][s[2]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curStatus = cur[2];
            int curCnt = cur[3];

            // 꺼낸 값이 e와 일치하면 아웃풋에 대입
            if (curRow == e[0] && curCol == e[1] && curStatus == e[2]) {
                System.out.println(curCnt);
                return;
            }

            // 상하좌우. 중심을 포함해서 3개 해야됨
            for (int i = 0; i < 4; i++) {
                int nr1 = curRow + dr[i];
                int nc1 = curCol + dc[i];
                int nr2 = curRow + dr[i];
                int nc2 = curCol + dc[i];
                int nr3 = curRow + dr[i];
                int nc3 = curCol + dc[i];

                if (curStatus == 0) {
                    nc1 -= 1;
                    nc3 += 1;
                } else {
                    nr1 -= 1;
                    nr3 += 1;
                }

                // 범위 넘어가는지
                if (!isRange(nr1, nc1) || !isRange(nr2, nc2) || !isRange(nr3, nc3)) continue;
                // 이동 가능한지
                if (map[nr1][nc1] || map[nr2][nc2] || map[nr3][nc3]) continue;
                // 중심과 가로/세로가 이미 방문했는지
                if (visited[nr2][nc2][curStatus]) continue;

                // 큐 넣기
                visited[nr2][nc2][curStatus] = true;
                queue.add(new int[]{nr2, nc2, curStatus, curCnt + 1});
            }

            // 회전

            // 회전 가능한지
            // 중심과 가로/세로가 이미 방문했는지
            if (isTurn(curRow, curCol) && !visited[curRow][curCol][(curStatus + 1) % 2]) {
                visited[curRow][curCol][(curStatus + 1) % 2] = true;
                queue.add(new int[]{curRow, curCol, (curStatus + 1) % 2, curCnt + 1});
            }
        }

        System.out.println(0);
    }

    public static boolean isRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
    public static boolean isTurn(int r, int c) {
        if (r >= 1 && r < n - 1 && c >= 1 && c < n - 1) {
            for (int i = 0; i < 8; i++) {
                int nr = r + ddr[i];
                int nc = c + ddc[i];

                if (map[nr][nc]) return false;
            }

            return true;
        }
        return false;
    }
}
