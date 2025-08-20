package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_17612 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 배열로 받기. 큐에 삽입
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            queue.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        int[][] counter = new int[k + 1][2];
        long cnt = 0;
        int index = 1;
        while (index <= n) {
            // 계산대 상태 보고 투입. 1-k. 회원 번호/남은 시간. 가장 작은 값 저장
            int min = 100;
            for (int i = 1; i <= k; i++) {
                if (counter[i][1] == 0 && !queue.isEmpty()) {
                    int[] cur = queue.poll();

                    counter[i][0] = cur[0];
                    counter[i][1] = cur[1];
                }

                if (counter[i][1] > 0) min = Math.min(min, counter[i][1]);
            }

            // k-1. 상태 보고 가장 작은 값 빼기. cnt에 누적.
            for (int i = k; i > 0; i--) {
                if (counter[i][1] != 0 && counter[i][0] != 0) counter[i][1] -= min;

                if (counter[i][1] == 0 && counter[i][0] != 0) {
                    cnt += (long) index * (long) counter[i][0];
                    counter[i][0] = 0;
                    index++;
                }
            }
        }

        System.out.println(cnt);
    }
}
