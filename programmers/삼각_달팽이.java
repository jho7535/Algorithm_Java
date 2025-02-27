package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 삼각_달팽이 {
    class Solution {
        static int[] dr = new int[]{1, 0, -1};
        static int[] dc = new int[]{0, 1, -1};

        public List<Integer> solution(int n) {
            int num = 1;
            int[][] map = new int[n][n];
            int direction = 0;
            int nr = 0, nc = 0;
            int flag = 0;

            map[nr][nc] = num++;
            while (true) {
                nr += dr[direction];
                nc += dc[direction];

                if (flag > 1) break;

                if (nr < 0 || nr >= n || nc < 0 || nc >= n ||
                        map[nr][nc] != 0) {
                    nr -= dr[direction];
                    nc -= dc[direction];
                    direction = (direction + 1) % 3;
                    flag++;
                    continue;
                }

                flag = 0;
                map[nr][nc] = num++;
            }

            List<Integer> answer = new ArrayList<>();
            for (int[] row : map) {
                for (int i : row) {
                    if (i == 0) continue;

                    answer.add(i);
                }
            }

            return answer;
        }
    }
}
