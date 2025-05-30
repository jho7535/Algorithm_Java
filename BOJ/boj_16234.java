package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_16234 {
    //public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] population;
    static int n, l, r;

    public static void main(String[] args) throws IOException {
        /*
         * 땅에는 하나씩 나라. A[r][c]명이 살고 있. 국경선 정사각형
         * 인구 이동이 없을 때까지 반복
         * 이동하는 나라 연합. 인구 반으로 나눔 소수점 버림
         * 며칠 동안 발생하는지
         *
         * 입 :   N, L, R   |   1~50, 1~100
         *       인구수
         * 출 :   며칠
         * */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        population = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) population[i][j] = Integer.parseInt(st.nextToken());
        }

        // while 시작. flag로 종료 판단. cnt 해야됨
        int cnt = 0;
        while (true) {
            boolean flag = false;

            // 국경 열기. 방향 주고 boolean
            boolean[][][] edge = new boolean[n][n][4];
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    for (int k = 0; k < 4; k++) {
                        int nr = row + dr[k];
                        int nc = col + dc[k];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                        if (Math.abs(population[nr][nc] - population[row][col]) < l ||
                                Math.abs(population[nr][nc] - population[row][col]) > r) continue;

                        flag = true;
                        edge[row][col][k] = true;
                    }
                }
            }

            if (!flag) break;
            cnt++;

            // 인구 이동 시작. 이중 for문 BFS, 방문 체크
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) bfs(i, j, visited, edge);
                }
            }
        }

        System.out.println(cnt);
    }

    // bfs. 방문 했던 나라 list.add. 마지막에 인구 갱신
    public static void bfs(int row, int col, boolean[][] visited, boolean[][][] edge) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{row, col});
        int sum = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];

            sum += population[curRow][curCol];

            for (int i = 0; i < 4; i++) {
                if (!edge[curRow][curCol][i]) continue;

                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (visited[nr][nc]) continue;

                queue.add(new int[]{nr, nc});
                list.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }

        int in = sum / list.size();
        for (int[] co : list) population[co[0]][co[1]] = in;
    }
}
