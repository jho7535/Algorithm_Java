package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_7576 {
    static int n, m;
    static int[][] map;
    static int[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new int[n][m];
        for (int[] row : visited) {
            Arrays.fill(row, -1);
        }
        List<int[]> starts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    starts.add(new int[]{i, j, 0});
                }
            }
        }

        bfs(starts);

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == -1 && map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                max = Math.max(max, visited[i][j]);
            }
        }

        System.out.println(max);
    }

    public static void bfs(List<int[]> starts) {
        Queue<int[]> queue = new LinkedList<>();
        for (int[] start : starts) {
            queue.add(new int[]{start[0], start[1], start[2]});
            visited[start[0]][start[1]] = start[2];
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];
            int curCnt = current[2];

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];
//                System.out.println(nr + " " + nc);

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (visited[nr][nc] != -1) continue;
                if (map[nr][nc] != 0) continue;

                queue.add(new int[]{nr, nc, curCnt + 1});
                visited[nr][nc] = curCnt + 1;
            }
        }
    }
}
