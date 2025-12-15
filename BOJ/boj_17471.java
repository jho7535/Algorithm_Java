package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_17471 {
    //public class Main {

    static int n;
    static int[] pop;
    static int sum = 0;
    static int min = Integer.MAX_VALUE;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pop = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            pop[i] = Integer.parseInt(st.nextToken());
            sum += pop[i];
        }
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            for (int j = 0; j < k; j++) {
                int e = Integer.parseInt(st.nextToken());

                graph.get(i).add(e);
                graph.get(e).add(i);
            }
        }

        // 조합 만들기
        for (int i = 1; i <= n - 1; i++) {
            combination(new ArrayList<>(), i, 0);
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    public static void combination(List<Integer> list, int depth, int start) {
        if (list.size() == depth) {
            // 가능, 불가능 판단
            List<Integer> newList = new ArrayList<>(list);
            if (check(newList)) {
                int s = 0;
                for (int i : newList) s += pop[i];

                min = Math.min(min, Math.abs((sum - s) - s));
            }
            return;
        }

        for (int i = start; i <= n; i++) {
            list.add(i);
            combination(list, depth, i + 1);
            list.remove(list.size() - 1);
        }
    }

    static boolean check(List<Integer> list) {
        boolean[] team = new boolean[n + 1];
        for (int i : list) team[i] = true;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(list.get(0));
        boolean[] visited = new boolean[n + 1];
        visited[list.get(0)] = true;
        boolean t = team[list.get(0)];
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (visited[next]) continue;
                if (team[next] != t) continue;

                queue.add(next);
                visited[next] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (team[i] != t) continue;

            if (!visited[i]) return false;
        }

        for (int i = 1; i <= n; i++) {
            if (team[i] != t) {
                queue.add(i);
                visited[i] = true;
                break;
            }
        }
        t = !t;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (visited[next]) continue;
                if (team[next] != t) continue;

                queue.add(next);
                visited[next] = true;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (team[i] != t) continue;

            if (!visited[i]) return false;
        }

        return true;
    }
}
