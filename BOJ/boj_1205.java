package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1205 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int score = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        if (n == 0) {
            System.out.println("1");
            return;
        } else {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) pq.add(arr[i]);

        if (pq.size() < size) { // 랭킹 리스트에 여유가 있음
            pq.add(score);
        } else { // 랭킹 리스트가 꽉 참
            if (score <= pq.element()) { // 새 점수가 리스트에 못 들어감
                System.out.println("-1");
                return;
            } else { // 새 점수가 리스트에 들어감
                pq.poll();
                pq.add(score);
            }
        }

        int cnt = pq.size();
        while (!pq.isEmpty() && score >= pq.remove()) {
            cnt--;
        }
        System.out.println(cnt + 1);
    }
}
