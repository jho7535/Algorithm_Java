package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 섬_연결하기 {
    class Solution {
        static int[] parent;
        static int[] rank;

        public int solution(int n, int[][] costs) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
            rank = new int[n];
            int answer = 0;

            Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));
            for (int i = 0; i < costs.length; i++) {
                int x = costs[i][0];
                int y = costs[i][1];
                int cost = costs[i][2];

                if (find(x) == find(y)) continue;

                answer += cost;
                union(x, y);
            }

            return answer;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }
        public void union(int x, int y) {
            int rootX = parent[x];
            int rootY = parent[y];

            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
                rank[rootX]++;
            }
        }
    }
}
