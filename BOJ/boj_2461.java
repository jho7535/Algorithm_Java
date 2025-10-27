package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2461 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] students = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) Arrays.sort(students[i]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // i, j, 점수
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            maxVal = Math.max(maxVal, students[i][0]);
            pq.add(new int[]{i, 0, students[i][0]});
        }

        int answer = Integer.MAX_VALUE;
        while (true) {
            int[] cur = pq.poll();
            int curI = cur[0];
            int curJ = cur[1];
            int curS = cur[2];

            answer = Math.min(answer, maxVal - curS);

            if (curJ == m - 1) break;

            maxVal = Math.max(maxVal, students[curI][curJ + 1]);
            pq.add(new int[]{curI, curJ + 1, students[curI][curJ + 1]});
        }

        System.out.println(answer);
    }
}
