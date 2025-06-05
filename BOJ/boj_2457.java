package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2457 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m1 = Integer.parseInt(st.nextToken());
            int d1 = Integer.parseInt(st.nextToken());
            int m2 = Integer.parseInt(st.nextToken());
            int d2 = Integer.parseInt(st.nextToken());

            if (m2 < 3 || (m2 == 3 && d2 == 1) || m1 == 12) continue;

            list.add(new int[]{m1 * 100 + d1, m2 * 100 + d2});
        }

        list.sort((a, b) -> a[0] - b[0]);

        int cnt = 0;
        int current = 301;
        int index = 0;
        while (current < 1201) {
            int end = 0;

            while (index < list.size() && list.get(index)[0] <= current) {
                end = Math.max(end, list.get(index)[1]);
                index++;
            }

            if (end <= current) {
                cnt = 0;
                break;
            }

            cnt++;
            current = end;
        }

        System.out.println(cnt);
    }
}
