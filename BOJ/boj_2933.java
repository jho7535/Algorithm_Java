package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2933 {
    //public class Main {

    static int r, c;
    static char[][] cave;
    static List<List<int[]>> cluster;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cave = new char[r][];
        for (int i = r - 1; i >= 0; i--) {
            cave[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());
        int[] spears = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) spears[i] = Integer.parseInt(st.nextToken()) - 1;

        // 시뮬 시작
        for (int i = 0; i < n; i++) {
            // 창 던져서 미네랄 삭제
            if (!move(spears[i], i % 2 == 0)) continue;

            // 모든 클러스터의 미네랄이 하나라도 바닥에 붙었는지 확인
            cluster = new ArrayList<>();
            boolean[][] visited = new boolean[r][c];
            int clusterCnt = 0;
            int moveCluster = 0;
            for (int row = 0; row < r; row++) {
                for (int col = 0; col < c; col++) {
                    if (visited[row][col] || cave[row][col] == '.') continue;

                    cluster.add(new ArrayList<>());
                    if (!bfs(row, col, visited, clusterCnt)) moveCluster = clusterCnt;
                    clusterCnt++;
                }
            }

            // 바닥에 안 붙어있는 클러스터 바닥 좌표 확인
            int[] minRow = new int[c];
            Arrays.fill(minRow, Integer.MAX_VALUE);
            for (int[] coordinate : cluster.get(moveCluster)) {
                int nr = coordinate[0];
                int nc = coordinate[1];

                minRow[nc] = Math.min(minRow[nc], nr);
            }

            // 해당 클러스터가 내려갈 수 있는 거리 최솟값
            int dist = Integer.MAX_VALUE;
            for (int col = 0; col < c; col++) {
                if (minRow[col] == Integer.MAX_VALUE) continue;

                int row = minRow[col];
                while (row - 1 >= 0 && cave[row - 1][col] == '.') row--;

                dist = Math.min(dist, minRow[col] - row);
            }

            // 옮기기
            for (int[] coordinate : cluster.get(moveCluster)) {
                int nr = coordinate[0];
                int nc = coordinate[1];

                cave[nr][nc] = '.';
            }

            for (int[] coordinate : cluster.get(moveCluster)) {
                int nr = coordinate[0] - dist;
                int nc = coordinate[1];

                cave[nr][nc] = 'x';
            }
        }

        for (int i = r - 1; i >= 0; i--) System.out.println(cave[i]);
    }

    static boolean move(int h, boolean isLeft) {
        if (isLeft) {
            for (int i = 0; i < c; i++) {
                if (cave[h][i] == '.') continue;

                cave[h][i] = '.';
                return true;
            }
        } else {
            for (int i = c - 1; i >= 0; i--) {
                if (cave[h][i] == '.') continue;

                cave[h][i] = '.';
                return true;
            }
        }

        return false;
    }

    static boolean bfs(int row, int col, boolean[][] visited, int clusterCnt) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col});
        visited[row][col] = true;
        cluster.get(clusterCnt).add(new int[]{row, col});
        boolean flag = false;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curRow = current[0];
            int curCol = current[1];

            if (curRow == 0) flag = true;

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
                if (visited[nr][nc]) continue;
                if (cave[nr][nc] == '.') continue;

                visited[nr][nc] = true;
                cluster.get(clusterCnt).add(new int[]{nr, nc});
                queue.add(new int[]{nr, nc});
            }
        }

        return flag;
    }
}
