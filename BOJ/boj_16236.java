package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_16236 {
    //public class Main {

    static int time = 0;
    static int[] shark = {0, 0, 2, 2};
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark[0] = i;
                    shark[1] = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            // target 위치 초기화
            int[] target = {0, 0, 1000};

            // 먹을 수 있는 거 찾기
            boolean flag = false;
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][n];
            queue.add(new int[]{shark[0], shark[1], 0});
            visited[shark[0]][shark[1]] = true;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];
                int curDist = cur[2];

                // target 최신화. 0 아니고, 상어보다 작고, 거리가 이전보다 가깝고,
                if (map[curRow][curCol] != 0 && map[curRow][curCol] < shark[2] &&
                        (target[2] > curDist || (target[2] == curDist && target[0] > curRow) || (target[2] == curDist && target[0] == curRow && target[1] > curCol))) {
                    target[0] = curRow;
                    target[1] = curCol;
                    target[2] = curDist;
                    flag = true;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = curRow + dr[i];
                    int nc = curCol + dc[i];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] > shark[2]) continue;

                    queue.add(new int[]{nr, nc, curDist + 1});
                    visited[nr][nc] = true;
                }
            }

            // 못 찾으면 종료
            if (!flag) break;

            // target으로 이동. 빈칸으로 만듦. time에 누적
            shark[0] = target[0];
            shark[1] = target[1];
            map[shark[0]][shark[1]] = 0;
            time += target[2];

            // 상어 크기 up시킬지
            shark[3]--;
            if (shark[3] == 0) {
                shark[3] = ++shark[2];
            }
        }

        System.out.println(time);
    }
}
