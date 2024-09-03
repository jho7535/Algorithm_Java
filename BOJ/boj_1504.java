package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1504 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        final int INF = 200000000;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, w));
            graph.get(e).add(new Node(s, w));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[][] distance = new int[3][N + 1];
        boolean[][] visited = new boolean[3][N + 1];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(distance[i], INF);
        }
        dijkstra(graph, distance[0], visited[0], 1);
        dijkstra(graph, distance[1], visited[1], v1);
        dijkstra(graph, distance[2], visited[2], v2);

        if (distance[0][v1] + distance[1][v2] + distance[2][N] >= INF && distance[0][v2] + distance[2][v1] + distance[1][N] >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(distance[0][v1] + distance[1][v2] + distance[2][N], distance[0][v2] + distance[2][v1] + distance[1][N]));
        }

    }

    static void dijkstra(ArrayList<ArrayList<Node>> graph, int[] distance, boolean[] visited, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int currentNode = pq.poll().destination;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            ArrayList<Node> list = graph.get(currentNode);
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);

                if (distance[currentNode] + node.weight < distance[node.destination]) {
                    distance[node.destination] = distance[currentNode] + node.weight;
                    pq.add(new Node(node.destination, distance[node.destination]));
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
