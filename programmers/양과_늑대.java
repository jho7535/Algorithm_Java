package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 양과_늑대 {
    class Solution {
        static boolean[][] graph;
        static int max = 0;

        public int solution(int[] info, int[][] edges) {
            graph = new boolean[info.length][info.length];
            for (int[] edge : edges) {
                graph[edge[0]][edge[1]] = true;
            }

            boolean[] isVisted = new boolean[info.length];
            isVisted[0] = true;
            dfs(0, new boolean[info.length], isVisted, 1, 0, info);

            return max;
        }

        public void dfs(int start, boolean[] canMove, boolean[] isVisited, int cnt, int wol, int[] info) {
            max = Math.max(max, cnt);

            // 종료 조건
            if (cnt - wol <= 0) {
                return;
            }

            boolean[] copy = Arrays.copyOf(canMove, canMove.length);
            for (int i = 0; i < graph[start].length; i++) {
                if (graph[start][i]) copy[i] = true;
            }

            for (int i = 0; i < canMove.length; i++) {
                if (copy[i] && !isVisited[i]) {
                    isVisited[i] = true;
                    if (info[i] == 0) dfs(i, copy, isVisited, cnt + 1, wol, info);
                    else dfs(i, copy, isVisited, cnt, wol + 1, info);
                    isVisited[i] = false;
                }
            }
        }
    }
}
