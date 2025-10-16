package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

/*
* 도토리 D개. 1-N개 상자.
* A-B까지 C개 간격으로 도토리를 하나씩 더 넣음. K개 규칙.
* 앞에서부터 채움.
* 마지막 도토리 들어있는 상자 번호 출력.
*
* 입 |   N, K, D : 1 ~ 1_000_000, 1 ~ 10_000, 1 ~ 10억
*       K개 줄.  A B C
* 출 |   마지막 도토리 들어가는 상자 번호
*
* */

public class boj_15732 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 받기. 규칙 배열 int[][3]
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] rules = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            rules[i][0] =  Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] =  Integer.parseInt(st.nextToken());
        }

        // 상자 번호 기준 이분 탐색
        int low = 1;
        int high = n;
        int answer = 0;
        while (low <= high) {
            int mid = (low + high) / 2;

            long cnt = 0L;
            // mid까지 몇 개의 도토리를 사용하는지
            for (int[] rule : rules) {
                int a = rule[0];
                int b = Math.min(mid, rule[1]);
                int c = rule[2];

                if ((b - a) < 0) continue;
                cnt += (b - a) / c + 1;
            }

            if (cnt < d) low = mid + 1;
            else {
                answer = mid;
                high = mid - 1;
            }
        }

        System.out.println(answer);
    }
}