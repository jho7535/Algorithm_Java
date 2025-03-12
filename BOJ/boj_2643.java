package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2643 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a > b) list.add(new int[]{b, a});
            else list.add(new int[]{a, b});
        }

        list.sort((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
        });

        int[] cnt = new int[n];
        Arrays.fill(cnt, 1);
        for (int i = 0; i < n - 1; i++) {
            int[] prev = list.get(i);

            for (int j = i + 1; j < n; j++) {
                int[] current = list.get(j);

                if (prev[0] <= current[0] && prev[1] <= current[1]) {
                    cnt[j] = Math.max(cnt[j], cnt[i] + 1);
                }
            }
//            System.out.println(i + " : " + Arrays.toString(cnt));
        }

        int max = 0;
        for (int i : cnt) max = Math.max(max, i);

        System.out.println(max);
    }
}
