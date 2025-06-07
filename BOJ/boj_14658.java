package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_14658 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        list.sort((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int max = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int x = list.get(i)[0];
                int y = list.get(j)[1];
                int cnt = 0;
                for (int[] star : list) {
                    int nx = star[0];
                    int ny = star[1];

                    if (nx >= x && nx <= x + l && ny >= y && ny <= y + l) cnt++;
                }
                max = Math.max(max, cnt);
            }
        }

        System.out.println(k - max);
    }
}
