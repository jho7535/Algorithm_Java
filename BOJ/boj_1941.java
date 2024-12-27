package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.util.*;
import java.io.*;

public class boj_1941 {
    //public class Main {
    static char[][] map = new char[5][5];
    static int cnt = 0;
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();

            for (int j = 0; j < 5; j++)
                map[i][j] = str.charAt(j);
        }

        combination(0, 0, new int[7][2], new boolean[5][5]);

        System.out.println(cnt);
    }

    public static void combination(int start, int depth, int[][] coordinates, boolean[][] selected) {
        if (depth == 7) {
            if (bfs(coordinates)) cnt++;
            return;
        }

        int j = start % 5;
        for (int i = start / 5; i < 5; i++) {
            for (; j < 5; j++) {
                if (selected[i][j]) continue;

                coordinates[depth][0] = i;
                coordinates[depth][1] = j;
                selected[i][j] = true;
                combination(i * 5 + j, depth + 1, coordinates, selected);
                selected[i][j] = false;
            }

            j = 0;
        }
    }

    // bfs로 연결, S 4개 이상 검사
    public static boolean bfs(int[][] result) {
        boolean[][] noVisited = new boolean[5][5];
        for (int i = 0; i < 7; i++) {
            noVisited[result[i][0]][result[i][1]] = true;
        }
        int vCnt = 0, SCnt = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{result[0][0], result[0][1]});
        noVisited[result[0][0]][result[0][1]] = false;
        vCnt++;
        if (map[result[0][0]][result[0][1]] == 'S') SCnt++;

        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            int row = coordinate[0];
            int col = coordinate[1];

            for (int i = 0; i < 4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];

                if (isRange(nr, nc) && noVisited[nr][nc]) {
                    noVisited[nr][nc] = false;
                    vCnt++;
                    if (map[nr][nc] == 'S') SCnt++;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        return vCnt == 7 && SCnt >= 4;
    }

    public static boolean isRange(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }
}
