package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1202 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] gems = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            gems[i][0] = Integer.parseInt(st.nextToken());
            gems[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(gems, (a, b) -> b[1] - a[1]);

        TreeMap<Integer, Integer> bags = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            int capacity = Integer.parseInt(br.readLine());
            bags.put(capacity, bags.getOrDefault(capacity, 0) + 1);
        }

        long sum = 0L;
        for (int[] gem : gems) {
            int weight = gem[0];
            int value = gem[1];

            // 보석의 무게를 담을 수 있는 가장 작은 가방 찾기
            Integer bag = bags.ceilingKey(weight);

            if (bag != null) {
                sum += value;

                // 가방 개수 감소
                int count = bags.get(bag);
                if (count == 1) {
                    bags.remove(bag);
                } else {
                    bags.put(bag, count - 1);
                }
            }
        }

        System.out.println(sum);
    }
}
