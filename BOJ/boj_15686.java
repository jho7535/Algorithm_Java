package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_15686 {
    //public class Main {

    static int n, m;
    static int[][] map;
    static List<Node>[][] status;
    static List<int[]> list = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        status = new List[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                status[i][j] = new ArrayList<>();

                if (map[i][j] == 2) list.add(new int[]{i, j});
            }
        }

        // 모든 집의 치킨 거리 리스트 오름차순
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 1) continue;

                bfs(i, j);
            }
        }

        // 집 중에 M개 고르기
        combination(new HashSet<>(), 0);

        System.out.println(answer);
    }

    static void bfs(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row, col, 0});
        boolean[][] visited = new boolean[n][n];
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];
            int curDist = cur[2];

            if (map[curRow][curCol] == 2) status[row][col].add(new Node(curRow, curCol, curDist));

            for (int i = 0; i < 4; i++) {
                int nr = curRow + dr[i];
                int nc = curCol + dc[i];

                if (isRange(nr, nc) && !visited[nr][nc]) {
                    queue.add(new int[]{nr, nc, curDist + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static void combination(Set<Integer> set, int index) {
        // m개 뽑음. 도시의 치킨 거리 계산
        if (set.size() == m) {
            int sum = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (Node node : status[i][j]) {
                        if (!set.contains(node.row * 100 + node.col)) continue;

                        sum += node.dist;
                        break;
                    }
                }
            }

            answer = Math.min(answer, sum);
        }

        for (int i = index; i < list.size(); i++) {
            set.add(list.get(i)[0] * 100 + list.get(i)[1]);
            combination(set, i + 1);
            set.remove(list.get(i)[0] * 100 + list.get(i)[1]);
        }
    }

    static boolean isRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static class Node {
        public int row;
        public int col;
        public int dist;

        public Node(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}
