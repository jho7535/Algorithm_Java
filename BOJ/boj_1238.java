package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1238 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[][] distance = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++)
            Arrays.fill(distance[i], Integer.MAX_VALUE);

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Node(e, t));
        }

        for (int i = 1; i < n + 1; i++) {
            boolean[] visited = new boolean[n + 1];
            dijkstra(i, graph, distance[i], visited, n);
        }

        int output = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) {
            if (distance[i][x] + distance[x][i] > output) output = distance[i][x] + distance[x][i];
        }

        System.out.println(output);
    }

    static void dijkstra(int start, ArrayList<ArrayList<Node>> graph, int[] distance, boolean[] visited, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node k = pq.poll();
            int currentNode = k.destination;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            ArrayList<Node> list = graph.get(currentNode);
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);

                if (distance[currentNode] + node.weight < distance[node.destination]) {
                    distance[node.destination] = distance[currentNode] + node.weight;
                    pq.offer(new Node(node.destination, distance[node.destination]));
                }
            }
        }
    }

    static class Node {
        int destination;
        int weight;

        Node(int node, int weight) {
            this.destination = node;
            this.weight = weight;
        }
    }
}

