package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1806 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int start = 0, end = 0;
        int sum = arr[0], length = Integer.MAX_VALUE;
        while (start <= end) {
            if (sum < target) {
                end++;
                if (end >= n) break;
                sum += arr[end];
            } else {
                length = Math.min(length, end - start + 1);
                sum -= arr[start];
                start++;
            }
        }

        if (length == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(length);
    }
}
