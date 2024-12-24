package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1780 {
    //public class Main {
    static int n;
    static int[][] map;
    static int[] results = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        recur(1, 1, n);

        for (int i = 0; i < 3; i++)
            sb.append(results[i]).append("\n");
        System.out.println(sb);
    }

    public static void recur(int row, int col, int length) {
        for (int i = -1; i <= 1; i++) {
            boolean flag = true; // 참이면 안 잘라도 됨

            for (int p = row; p < row + length; p++) {
                for (int q = col; q < col + length; q++) {
                    if (map[p][q] != i) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) break;
            }

            // 안 잘라도 될 때
            if (flag) {
                results[i + 1]++;
                return;
            }
        }

        // 9등분 해야됨
        length /= 3;
        if (length == 0) return; // 0일 때 예외
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                recur(row + length * i, col + length * j, length);
        }
    }
}
