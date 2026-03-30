package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_17142 {
    //public class Main {

    static int n, m;
    static int[][] map;
    static List<int[]> virus = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }

        combination(0, new ArrayList<>());

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void combination(int index, List<int[]> list) {
        if (list.size() == m) {
            bfs(list);
            return;
        }

        for (int i = index; i < virus.size(); i++) {
            int r = virus.get(i)[0];
            int c = virus.get(i)[1];

            list.add(new int[]{r, c});
            combination(i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    public static void bfs(List<int[]> list) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        for (int[] l : list) {
            queue.add(new int[]{l[0], l[1], 0});
            visited[l[0]][l[1]] = true;
        }

        int max = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curTime = cur[2];

            if (map[curRow][curCol] != 2) {
                max = Math.max(max, curTime);
            }

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (!isRange(nr, nc) || map[nr][nc] == 1 || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc, curTime + 1});
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 && !visited[i][j]) return;
            }
        }

        answer = Math.min(answer, max);
    }

    static boolean isRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}
