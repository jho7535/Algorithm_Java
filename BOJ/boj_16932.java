package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16932 {
    //public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n;
    static int m;
    static int[][] map;
    static int[][][] size;
    static boolean[][] visited;
    static int sum = 0;
    static int number = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        size = new int[n][m][2];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) bfs(i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) continue;

                int cnt = 0;
                Set<Integer> set = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (set.contains(size[nr][nc][1])) continue;

                    cnt += size[nr][nc][0];
                    set.add(size[nr][nc][1]);
                }

                sum = Math.max(sum, cnt);
            }
        }

        System.out.println(sum + 1);
    }

    static void bfs(int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nr][nc] == 0) continue;
                if (visited[nr][nc]) continue;

                queue.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }

        fill(row, col, cnt);
        number++;
    }
    static void fill(int row, int col, int num) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        size[row][col][0] = num;
        size[row][col][1] = number;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (map[nr][nc] == 0) continue;
                if (size[nr][nc][0] != 0) continue;

                size[nr][nc][0] = num;
                size[nr][nc][1] = number;
                queue.add(new int[]{nr, nc});
            }
        }
    }
}
