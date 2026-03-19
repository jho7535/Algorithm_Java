package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_21921 {
    //public class Main {

    static int n, x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int s = 0, e;
        for (e = 0; e < x; e++) {
            sum += arr[e];
        }

        int answer = sum;
        int cnt = 0;
        for (int i = x; i < n; i++) {
            sum += arr[i];
            sum -= arr[i - x];

            if (answer < sum) {
                answer = sum;
                cnt = 1;
            } else if (answer == sum) {
                cnt++;
            }
        }

        if (answer == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(answer);
            System.out.println(cnt);
        }
    }
}
