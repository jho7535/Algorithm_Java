package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_10775 {
    //public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());
        parent = new int[g + 1];
        for (int i = 0; i <= g; i++) parent[i] = i;

        int cnt = 0;
        for (int i = 0; i < p; i++) {
            int plane = Integer.parseInt(br.readLine());

            int able = find(plane);

            if (able == 0) break;

            union(able, able - 1);
            cnt++;
        }

        System.out.println(cnt);
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
}
