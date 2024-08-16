package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1916 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, w));
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n + 1];
        dijkstra(graph, distance, visited, a);

        System.out.println(distance[b]);
    }

    static void dijkstra(ArrayList<ArrayList<Node>> graph, int[] distance, boolean[] visited, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int current = pq.poll().destination;

            if (visited[current]) continue;
            visited[current] = true;

            ArrayList<Node> list = graph.get(current);
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);

                if (distance[current] + node.weight < distance[node.destination]) {
                    distance[node.destination] = distance[current] + node.weight;
                    pq.offer(new Node(node.destination, distance[node.destination]));
                }
            }
        }

    }

    static class Node {
        int destination;
        int weight;

        Node(int d, int w) {
            this.destination = d;
            this.weight = w;
        }
    }
}
