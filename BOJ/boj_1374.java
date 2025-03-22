package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1374 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            list.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        list.sort(Comparator.comparingInt(a -> a[0]));

        int max = 0;
        Queue<Integer> pq = new PriorityQueue<>();
        for (int[] lecture : list) {
            int start = lecture[0];
            int end = lecture[1];

            while (!pq.isEmpty() && pq.peek() <= start) pq.poll();

            pq.add(end);
            max = Math.max(max, pq.size());
        }

        System.out.println(max);
    }
}
