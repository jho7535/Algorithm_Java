package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_4179 {
    //public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] fireMap = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(fireMap[i], Integer.MAX_VALUE);
        char[][] map = new char[n][m];
        int[] start = new int[2];
        List<int[]> fires = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'J') {
                    start[0] = i;
                    start[1] = j;
                }
                if (map[i][j] == 'F') {
                    fires.add(new int[]{i, j});
                }
            }
        }

        // 불 맵 채우기
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        for (int[] fire : fires) {
            queue.add(new int[]{fire[0], fire[1], 0});
            visited[fire[0]][fire[1]] = true;
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curDist = cur[2];

            fireMap[curRow][curCol] = curDist;

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == '#') continue;

                queue.add(new int[]{nr, nc, curDist + 1});
                visited[nr][nc] = true;
            }
        }

        // 최단 탈출 찾기
        queue = new ArrayDeque<>();
        queue.add(new int[]{start[0], start[1], 0});
        visited = new boolean[n][m];
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curDist = cur[2];

            if (curRow == 0 || curRow == n - 1 || curCol == 0 || curCol == m - 1) {
                System.out.println(curDist + 1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] != '.') continue;
                if (fireMap[nr][nc] <= curDist + 1) continue;

                queue.add(new int[]{nr, nc, curDist + 1});
                visited[nr][nc] = true;
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
