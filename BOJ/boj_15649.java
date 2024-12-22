package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_15649 {
    //public class Main {
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boolean[] arr = new boolean[n + 1];
        int[] output = new int[n];

        for (int i = 1; i <= n; i++) {
            arr[i] = true;
            output[0] = i;
            recur(arr, 1, output);
            arr[i] = false;
        }
    }

    static void recur(boolean[] arr, int cnt, int[] output) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(output[i] + " ");
            }
            System.out.println();

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
