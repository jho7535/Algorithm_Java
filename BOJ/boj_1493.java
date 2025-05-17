package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1493 {
    //public class Main {

    static int[] cube = new int[20];
    static int x, y, z;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        z = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cube[k] = c;
        }

        long totalVolume = (long) x * y * z;
        long filled = 0;
        int answer = 0;

        for (int i = 19; i >= 0; i--) {
            int len = 1 << i;
            long fit = ((long) (x / len) * (y / len) * (z / len)) - filled;
            long use = Math.min(fit, cube[i]);
            filled += use;
            answer += (int) use;
            filled *= 8;
        }

        if (filled / 8 == totalVolume) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}