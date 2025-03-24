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

        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken()) - 1;
        int c1 = Integer.parseInt(st.nextToken()) - 1;
        int r2 = Integer.parseInt(st.nextToken()) - 1;
        int c2 = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < n; i++) map[i] = br.readLine().toCharArray();

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) System.out.print(map[i][j] + " ");
//            System.out.println();
//        }

        int cnt = 0;
        boolean flag = true;
        while (flag) {
            cnt++;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{r1, c1});
            boolean[][] visited = new boolean[n][m];
            visited[r1][c1] = true;
            Queue<int[]> deleteQueue = new LinkedList<>();

            while (!queue.isEmpty() && flag) {
                int[] current = queue.poll();
                int curRow = current[0];
                int curCol = current[1];

                for (int i = 0; i < 4; i++) {
                    int nr = curRow + dr[i];
                    int nc = curCol + dc[i];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] == '1') {
                        deleteQueue.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                        continue;
                    }

                    if (map[nr][nc] == '#') {
                        flag = false;
                        break;
                    }

                    queue.add(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }

            while (!deleteQueue.isEmpty() && flag) {
                int[] current = deleteQueue.poll();
                int curRow = current[0];
                int curCol = current[1];

                map[curRow][curCol] = '0';
            }
        }

        System.out.println(cnt);
    }
}
