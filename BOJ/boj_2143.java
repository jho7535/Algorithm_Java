package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2143 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr1[i] = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[] arr2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) arr2[i] = Integer.parseInt(st.nextToken());

        int[] preSum1 = new int[n + 1];
        int[] preSum2 = new int[m + 1];
        for (int i = 1; i <= n; i++) preSum1[i] = preSum1[i - 1] + arr1[i - 1];
        for (int i = 1; i <= m; i++) preSum2[i] = preSum2[i - 1] + arr2[i - 1];

        // b에서 나올 수 있는 수 map에 넣기
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= m - 1; i++) {
           for (int j = i + 1; j <= m; j++) {
               int sum = preSum2[j] - preSum2[i];

               map.put(sum, map.getOrDefault(sum, 0) + 1);
           }
        }

        // a에서 나올 수 있는 수 구해서 가능한 경우 누적
        long cnt = 0;
        for (int i = 0; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                int sum = preSum1[j] - preSum1[i];

                if (map.containsKey(target - sum)) {
                    cnt += map.get(target - sum);
                }
            }
        }

        System.out.println(cnt);
    }
}
