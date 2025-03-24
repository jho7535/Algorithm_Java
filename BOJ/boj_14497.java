package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_14497 {
    //public class Main {

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][];
        int[][] cnt = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(cnt[i], Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken()) - 1;
        int c1 = Integer.parseInt(st.nextToken()) - 1;
        int r2 = Integer.parseInt(st.nextToken()) - 1;
        int c2 = Integer.parseInt(st.nextToken()) - 1;
        cnt[r1][c1] = 0;

        for (int i = 0; i < n; i++) map[i] = br.readLine().toCharArray();

        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{r1, c1});
        boolean[][] visited = new boolean[n][m];
        visited[r1][c1] = true;
        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int curRow = current[0];
            int curCol = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if (visited[nr][nc]) continue;

                int cost = map[nr][nc] == '0' ? 0 : 1;
                cnt[nr][nc] = Math.min(cnt[nr][nc], cnt[curRow][curCol] + cost);
                if (map[nr][nc] == '0') deque.addFirst(new int[]{nr, nc});
                else deque.addLast(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }

        System.out.println(cnt[r2][c2]);
    }
}
