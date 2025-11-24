package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_19238 {
    //public class Main {

    static int n, m, gas;
    static int[][] map;
    static int[] cur;
    static Map<Integer, int[]> destination = new HashMap<>();

    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        cur = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            map[r1][c1] = 2;
            destination.put(r1 * 100 + c1, new int[]{r2, c2});
        }

        // 처음 승객 위치로 세팅
        int dist = bfs2();
        if (dist == -1 || gas - dist < 0) {
            System.out.println(-1);
            return;
        }
        gas -= dist;
        map[cur[0]][cur[1]] = 0;

        while (!destination.isEmpty()) {
            // 현위치 -> 목적지
            int[] end = destination.get(cur[0] * 100 + cur[1]);
            int dist1 = bfs1(end[0], end[1]);

            if (dist1 == -1 || gas - dist1 < 0) {
                System.out.println(-1);
                return;
            }
            destination.remove(cur[0] * 100 + cur[1]);
            cur[0] = end[0];
            cur[1] = end[1];
            gas += dist1;

            if (destination.isEmpty()) {
                break;
            }

            // 현위치 -> 가장 가까운 승객
            int dist2 = bfs2();

            if (dist2 == -1 || gas - dist2 < 0) {
                System.out.println(-1);
                return;
            }
            gas -= dist2;
            map[cur[0]][cur[1]] = 0;
        }

        System.out.println(gas);
    }

    public static boolean isRange(int r, int c) {
        return r >= 1 && r <= n && c >= 1 && c <= n;
    }

    public static int bfs1(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{cur[0], cur[1], 0});
        boolean[][] visited = new boolean[n + 1][n + 1];
        visited[cur[0]][cur[1]] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];
            int curDist = current[2];

            if (curRow == r && curCol == c) {
                return curDist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (!isRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 1) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc, curDist + 1});
            }
        }

        return -1;
    }

    public static int bfs2() {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[2] == b[2]) {
                if (a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            }
            return a[2] - b[2];
        });
        queue.add(new int[]{cur[0], cur[1], 0});
        boolean[][] visited = new boolean[n + 1][n + 1];
        visited[cur[0]][cur[1]] = true;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];
            int curDist = current[2];

            if (map[curRow][curCol] == 2) {
                cur[0] = curRow;
                cur[1] = curCol;
                return curDist;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (!isRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 1) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc, curDist + 1});
            }
        }

        return -1;
    }
}
