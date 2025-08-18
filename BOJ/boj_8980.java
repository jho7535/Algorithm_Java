package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_8980 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            list.add(new int[]{s, e, amount});
        }

        list.sort((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int[] capacity = new int[n + 1];
        int max = 0;
        for (int i = 0; i < m; i++) {
            int start = list.get(i)[0];
            int end = list.get(i)[1];
            int box = list.get(i)[2];

            int now = box; // 싣을 수 있는 최대 박스
            for (int j = start; j < end; j++) {
                now = Math.min(now, c - capacity[j]);
            }

            if (now > 0) {
                for (int j = start; j < end; j++) {
                    capacity[j] += now;
                }

                max += now;
            }
        }

        System.out.println(max);
    }
}
