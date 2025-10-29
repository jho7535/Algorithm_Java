package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

/*
* 학생 1-N까지 정수
* 두 학생 중 누가 더 잘했는가. 동점인 경우 x
* m번 질문. x의 등수 범위
*
* 입 :   n, m, x : 2~100_000,
*       m개 줄. a, b : a가 b보다 잘했음
* 출 :   u, v : u <= v
*
* 1 3
* 2 3
* 3 4
* 3 5
* 4 5
* */

public class boj_17616 {
    //public class Main {

    static int n, m, x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph1 = new ArrayList<>();
        List<List<Integer>> graph2 = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph1.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph1.get(s).add(e);
            graph2.get(e).add(s);
        }

        int prev = bfs(graph2);
        int next = bfs(graph1);

        System.out.println((1 + prev) + " " + (n - next));
    }

    public static int bfs(List<List<Integer>> graph) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        boolean[] visited = new boolean[n + 1];
        visited[x] = true;
        int cnt = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            cnt++;

            for (int next : graph.get(cur)) {
                if (visited[next]) continue;

                visited[next] = true;
                queue.add(next);
            }
        }

        return cnt;
    }
}
