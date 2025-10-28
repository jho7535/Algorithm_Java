package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_17135 {
    //public class Main {

    static int n, m, d;
    static boolean[][] map;
    static int max = 0;

    static int[] dr = {0, -1, 0}; // 좌 상 우
    static int[] dc = {-1, 0, 1}; // 좌 상 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new boolean[n + 1][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(st.nextToken()) == 0 ? false : true;
        }

        // 궁수 3명 배치
        dfs(0, 0);

        System.out.println(max);
    }

    static void dfs(int index, int level) {
        if (level == 3) {
            // 시뮬 돌리기
            simulation();
            return;
        }

        for (int i = index; i < m; i++) {
            map[n][i] = true;
            dfs(i + 1, level + 1);
            map[n][i] = false;
        }
    }

    static void simulation() {
        int cnt = 0;
        boolean[][] copy = new boolean[n + 1][m];
        for (int i = 0; i <= n; i++) copy[i] = Arrays.copyOf(map[i], m);

        while (true) {
            Set<Integer> set = new HashSet<>();

            // 성 돌면서 궁수 있는 곳에서 활 쏘기
            for (int i = 0; i < m; i++) {
                if (copy[n][i]) shoot(i, set, copy);
            }

            // set 죽이기
            for (int coordinate : set) {
                int row = coordinate / 100;
                int col = coordinate % 100;
                copy[row][col] = false;
                cnt++;
            }

            // 적 내리기
            boolean flag = false;
            for (int i = n - 1; i > 0; i--) {
                for (int j = 0; j < m; j++) {
                    if (copy[i][j]) flag = true;

                    copy[i][j] = copy[i - 1][j];
                }
            }
            for (int j = 0; j < m; j++) {
                if (copy[0][j]) flag = true;

                copy[0][j] = false;
            }

            if (!flag) break;
        }

        max = Math.max(max, cnt);
    }

    static void shoot(int col, Set<Integer> set, boolean[][] copy) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{n - 1, col, 1});
        boolean[][] visited = new boolean[n + 1][m];
        visited[n - 1][col] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curDist = cur[2];

            if (curDist > d) continue;
            if (copy[curRow][curCol]) {
                set.add(curRow * 100 + curCol);
                return;
            }

            for (int i = 0; i < 3; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (nr < 0 || nc < 0 || nc >= m) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc, curDist + 1});
            }
        }
    }
}
