package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1456 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        boolean[] isPrime = new boolean[(int) Math.sqrt(b) + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) continue;

            int j = 2;
            while (i * j < isPrime.length) {
                isPrime[i * j] = false;
                j++;
            }
        }

        int cnt = 0;
        for (long i = 2; i < isPrime.length; i++) {
            if (!isPrime[(int) i]) continue;

            long k = i;
            while (k <= b / i) {
                k *= i;
                if (k >= a) cnt++;
            }
        }

        System.out.println(cnt);
    }
}
