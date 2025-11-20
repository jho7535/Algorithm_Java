package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_8983 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int[] xs = new int[m];
        int[][] animals = new int[n][];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) xs[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            animals[i] = new int[]{x, y};
        }

        Arrays.sort(xs);

        int cnt = 0;
        for (int[] animal : animals) {
            int x = animal[0];
            int y = animal[1];

            if (y > l) continue;

            int low = 0;
            int high = m - 1;
            int index = 0;
            while (low <= high) {
                int mid = (low + high) / 2;

                if (xs[mid] > x) {
                    if (Math.abs(xs[mid] - x) < Math.abs(xs[index] - x)) index = mid;

                    high = mid - 1;
                } else if (xs[mid] < x) {
                    if (Math.abs(xs[mid] - x) < Math.abs(xs[index] - x)) index = mid;

                    low = mid + 1;
                } else {
                    index = mid;
                    break;
                }
            }

            if (Math.abs(xs[index] - x) + y <= l) cnt++;
        }

        System.out.println(cnt);
    }
}
