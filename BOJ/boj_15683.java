package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_15683 {
    //public class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static int n, m;
    static int[][] map;
    static int[][] direction;
    static int min = Integer.MAX_VALUE;
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        direction = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) list.add(new int[]{i, j});
            }
        }

        // 모든 경우의 수
        dfs(0, list.size());

        System.out.println(min);
    }

    static void dfs(int index, int depth) {
        // 종료 조건
        if (index == depth) {
            cal();
            return;
        }

        for (int i = 0; i < 4; i++) {
            int row = list.get(index)[0];
            int col = list.get(index)[1];

            direction[row][col] = i;
            dfs(index + 1, depth);
        }
    }

    static void cal() {
        int cnt = 0;
        boolean[][] zone = new boolean[n][m];

        // 감시지역 밝히기
        for (int[] spot : list) {
            int i = spot[0];
            int j = spot[1];

            int dir = direction[i][j];

            if (map[i][j] == 1) {
                int nr = i + dr[dir];
                int nc = j + dc[dir];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[dir];
                    nc += dc[dir];
                }
            } else if (map[i][j] == 2) {
                int nr = i + dr[dir];
                int nc = j + dc[dir];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[dir];
                    nc += dc[dir];
                }

                nr = i + dr[(dir + 2) % 4];
                nc = j + dc[(dir + 2) % 4];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[(dir + 2) % 4];
                    nc += dc[(dir + 2) % 4];
                }
            } else if (map[i][j] == 3) {
                int nr = i + dr[dir];
                int nc = j + dc[dir];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[dir];
                    nc += dc[dir];
                }

                nr = i + dr[(dir + 1) % 4];
                nc = j + dc[(dir + 1) % 4];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[(dir + 1) % 4];
                    nc += dc[(dir + 1) % 4];
                }
            } else if (map[i][j] == 4) {
                int nr = i + dr[dir];
                int nc = j + dc[dir];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[dir];
                    nc += dc[dir];
                }

                nr = i + dr[(dir + 1) % 4];
                nc = j + dc[(dir + 1) % 4];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[(dir + 1) % 4];
                    nc += dc[(dir + 1) % 4];
                }

                nr = i + dr[(dir + 2) % 4];
                nc = j + dc[(dir + 2) % 4];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[(dir + 2) % 4];
                    nc += dc[(dir + 2) % 4];
                }
            } else if (map[i][j] == 5) {
                int nr = i + dr[dir];
                int nc = j + dc[dir];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[dir];
                    nc += dc[dir];
                }

                nr = i + dr[(dir + 1) % 4];
                nc = j + dc[(dir + 1) % 4];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[(dir + 1) % 4];
                    nc += dc[(dir + 1) % 4];
                }

                nr = i + dr[(dir + 2) % 4];
                nc = j + dc[(dir + 2) % 4];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[(dir + 2) % 4];
                    nc += dc[(dir + 2) % 4];
                }

                nr = i + dr[(dir + 3) % 4];
                nc = j + dc[(dir + 3) % 4];

                while (nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] != 6) {
                    zone[nr][nc] = true;
                    nr += dr[(dir + 3) % 4];
                    nc += dc[(dir + 3) % 4];
                }
            }
        }

        // 사작지대 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !zone[i][j]) cnt++;
            }
        }

        min = Math.min(min, cnt);
    }
}
