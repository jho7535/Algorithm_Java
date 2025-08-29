package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1043 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] people = new boolean[n + 1];
        boolean[] party = new boolean[m];

        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            int k = Integer.parseInt(st.nextToken());
            people[k] = true;
            queue.add(k);
        }

        // List, Set
        List<Set<Integer>> list = new ArrayList<>();
        List<List<Integer>> participant = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new HashSet<>());
            participant.add(new ArrayList<>());

            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            for (int j = 0; j < length; j++) {
                int num = Integer.parseInt(st.nextToken());

                list.get(i).add(num);
                participant.get(i).add(num);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 모든 파티 돌면서 진실. 파티 참가자 진실. 바뀐 참가자 큐에 삽입
            for (int i = 0; i < m; i++) {
                if (party[i]) continue;
                if (!list.get(i).contains(current)) continue;

                party[i] = true;
                for (int p : participant.get(i)) {
                    if (people[p]) continue;

                    people[p] = true;
                    queue.add(p);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) if (!party[i]) cnt++;
        System.out.println(cnt);
    }
}
