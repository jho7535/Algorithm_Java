package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_13334 {
    //public class Main {
    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new int[]{Math.min(s, e), Math.max(s, e)});
        }
        int d = Integer.parseInt(br.readLine());

        list.sort(Comparator.comparingInt(a -> a[1]));

        Queue<Integer> pq = new PriorityQueue<>();
        int max = 0;
        for (int[] element : list) {
            int start = element[0];
            int end = element[1];

            pq.add(start);
            while (!pq.isEmpty() && pq.peek() < end - d) pq.poll();

            max = Math.max(max, pq.size());
        }

        System.out.println(max);
    }
}
