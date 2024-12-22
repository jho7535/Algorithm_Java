package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15649 {
    //public class Main {
    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean[] arr = new boolean[n + 1];
        int[] output = new int[n];

        recur(arr, 0, output);

        System.out.println(sb);
    }

    static void recur(boolean[] arr, int cnt, int[] output) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                sb.append(output[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = 1; i <= n; i++) {
            if (arr[i]) continue;

            arr[i] = true;
            output[cnt] = i;
            recur(arr, cnt + 1, output);
            arr[i] = false;
        }
    }
}
