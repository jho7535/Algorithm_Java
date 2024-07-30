package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1138 {
    //public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] answer = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int cnt = arr[i];

            for (int j = 0; j < n; j++) {
                if (answer[j] != 0) continue;

                if (cnt == 0) {
                    answer[j] = i + 1;
                    break;
                }

                cnt--;
            }
        }

        for (int i = 0; i < n; i++) System.out.print(answer[i] + " ");
    }
}
