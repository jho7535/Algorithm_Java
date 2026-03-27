package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_16235 {
    //public class Main {

    static int n, m, k;
    static int[][] yangbun;
    static List<Integer>[][] tree;
    static int[][] winter;
    static int[] dr = {1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        yangbun = new int[n][n];
        tree = new List[n][n];
        winter = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                winter[i][j] = Integer.parseInt(st.nextToken());
                yangbun[i][j] = 5;
                tree[i][j] = new LinkedList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            tree[x - 1][y - 1].add(z);
        }

        while (k-- > 0) {
            // 봄 + 여름
            // 전체 순회하면서 나무 양분 먹이기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int sum = 0;
                    List<Integer> list = new LinkedList<>();

                    for (int index = 0; index < tree[i][j].size(); index++) {
                        int cur = tree[i][j].get(index);

                        if (cur > yangbun[i][j]) {
                            sum += cur / 2;
                        } else {
                            yangbun[i][j] -= cur;
                            list.add(cur + 1);
                        }
                    }

                    tree[i][j] = list;
                    yangbun[i][j] += sum;
                }
            }

            // 가을 + 겨울
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    yangbun[i][j] += winter[i][j];

                    for (int cur : tree[i][j]) {
                        if (cur % 5 != 0) continue;

                        for (int q = 0; q < 8; q++) {
                            int nr = i + dr[q];
                            int nc = j + dc[q];

                            if (isRange(nr, nc)) {
                                tree[nr][nc].add(0, 1);
                            }
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += tree[i][j].size();
            }
        }

        System.out.println(answer);
    }

    static boolean isRange(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}
