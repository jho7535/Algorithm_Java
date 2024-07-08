package nestnet_algorithm_2023_2.JeongHanUl.elice_code_challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_Day_1 {
    static int output = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] cnt = new int[10];
        int num = n;
        int digitCnt = 0;

        while (num != 0) {
            cnt[num % 10]++;

            num /= 10;
            digitCnt++;
        }

        dfs(cnt, n, digitCnt, 0);

        System.out.println(output);
    }

    public static void dfs(int[] arr, int n, int digitCnt, int value) {
        if (digitCnt != 0) {
            for (int i = 0; i < 10; i++) {
                if (arr[i] == 0) continue;

                int k = 1;
                for (int j = 0; j < digitCnt - 1; j++) {
                    k *= 10;
                }
                value += i * k;
                arr[i]--;
                digitCnt--;
                dfs(arr, n, digitCnt, value);
                value -= i * k;
                arr[i]++;
                digitCnt++;
            }
        } else if (value > n && value < output) output = value;
    }
}
