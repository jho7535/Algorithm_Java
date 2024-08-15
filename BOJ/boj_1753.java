package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1753 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int vertex = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= vertex; i++) graph.add(new ArrayList<>());


        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        int[] distance = new int[vertex + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[vertex + 1];
        dijkstra(graph, distance, visited, start);

        for (int i = 1; i <= vertex; i++) {
            bw.write(distance[i] == Integer.MAX_VALUE ? "INF\n" : distance[i] + "\n");
        }
        bw.flush();
    }

    static void dijkstra(ArrayList<ArrayList<Node>> graph, int[] distance, boolean[] visited, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int current = node.destination;

            if (visited[current]) continue;
            visited[current] = true;

            ArrayList<Node> list = graph.get(current);
            for (int i = 0; i < list.size(); i++) {
                Node n = list.get(i);

                if (distance[current] + n.weight < distance[n.destination]) {
                    distance[n.destination] = distance[current] + n.weight;
                    pq.offer(new Node(n.destination, distance[n.destination]));
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
