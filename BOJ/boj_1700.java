package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1700 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Queue<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= k; i++) list.add(new ArrayDeque<>());
        int[] input = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int electro = Integer.parseInt(st.nextToken());

            list.get(electro).add(i);
            input[i] = electro;
        }

        List<int[]> plug = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            int electro = input[i];

            // 이미 해당 기기가 꽂혀있음
            if (set.contains(electro)) {
                list.get(electro).poll();
                int next = list.get(electro).isEmpty() ? 1000 : list.get(electro).peek();

                for (int[] p : plug) {
                    if (p[0] != electro) continue;

                    p[1] = next;
                }

                continue;
            }

            // 플러그 빈자리가 있음
            if (set.size() < n) {
                list.get(electro).poll();
                int next = list.get(electro).isEmpty() ? 1000 : list.get(electro).peek();

                set.add(electro);
                plug.add(new int[]{electro, next});

                continue;
            }

            // 플러그 하나 빼야됨
            int max = 0;
            int index = 0;
            for (int j = 0; j < plug.size(); j++) {
                if (plug.get(j)[1] <= max) continue;

                max = plug.get(j)[1];
                index = j;
            }
            set.remove(plug.get(index)[0]);
            plug.remove(index);
            cnt++;

            list.get(electro).poll();
            int next = list.get(electro).isEmpty() ? 1000 : list.get(electro).peek();

            set.add(electro);
            plug.add(new int[]{electro, next});
        }

        System.out.println(cnt);
    }
}
