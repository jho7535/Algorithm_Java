package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_21608 {
    //public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n * n];
        List<Set<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n * n; i++) list.add(new HashSet<>());

        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());
            int n4 = Integer.parseInt(st.nextToken());

            arr[i] = num;
            list.get(num).add(n1);
            list.get(num).add(n2);
            list.get(num).add(n3);
            list.get(num).add(n4);
        }

        int[][] map = new int[n + 1][n + 1];
        for (int i = 0; i < n * n; i++) {
            int num = arr[i];
            Set<Integer> set = list.get(num);
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[2] == b[2]) {
                    if (a[3] == b[3]) {
                        if (a[0] == b[0]) return a[1] - b[1];
                        return a[0] - b[0];
                    }
                    return b[3] - a[3];
                }
                return b[2] - a[2];
            });

            for (int r = 1; r <= n; r++) {
                for (int c = 1; c <= n; c++) {
                    if (map[r][c] != 0) continue;

                    int cnt1 = 0;
                    int cnt2 = 0;

                    for (int j = 0; j < 4; j++) {
                        int nr = r + dr[j];
                        int nc = c + dc[j];

                        if (nr < 1 || nr > n || nc < 1 || nc > n) continue;

                        if (set.contains(map[nr][nc])) cnt1++;
                        if (map[nr][nc] == 0) cnt2++;
                    }

                    pq.add(new int[]{r, c, cnt1, cnt2});
                }
            }

            int[] pos = pq.poll();
            int row = pos[0];
            int col = pos[1];

            map[row][col] = num;
        }

        int answer = 0;
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                int num = map[r][c];
                Set<Integer> set = list.get(num);
                int cnt = 0;

                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];

                    if (nr < 1 || nr > n || nc < 1 || nc > n) continue;

                    if (set.contains(map[nr][nc])) cnt++;
                }

                if (cnt == 0) answer += 0;
                else if (cnt == 1) answer += 1;
                else if (cnt == 2) answer += 10;
                else if (cnt == 3) answer += 100;
                else answer += 1000;
            }
        }

        System.out.println(answer);
    }
}
