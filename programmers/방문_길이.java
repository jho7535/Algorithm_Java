package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 방문_길이 {
    class Solution {
        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, 1, -1};

        public int solution(String dirs) {
            int pr = 5;
            int pc = 5;
            int cnt = 0;
            boolean[][][] visited = new boolean[11][11][4];

            for (int i = 0; i < dirs.length(); i++) {
                char ch = dirs.charAt(i);
                int nr = pr;
                int nc = pc;

                switch (ch) {
                    case 'U' :
                        nr += dr[0];
                        nc += dc[0];

                        if (nr < 0 || nr >= 11 || nc < 0 || nc >= 11) {
                            nr = pr;
                            nc = pc;
                            continue;
                        }

                        if (!visited[pr][pc][0] && !visited[nr][nc][1]) {
                            cnt++;
                            visited[pr][pc][0] = true;
                            visited[nr][nc][1] = true;
                        }

                        break;
                    case 'D' :
                        nr += dr[1];
                        nc += dc[1];

                        if (nr < 0 || nr >= 11 || nc < 0 || nc >= 11) {
                            nr = pr;
                            nc = pc;
                            continue;
                        }

                        if (!visited[pr][pc][1] && !visited[nr][nc][0]) {
                            cnt++;
                            visited[pr][pc][1] = true;
                            visited[nr][nc][0] = true;
                        }

                        break;
                    case 'R' :
                        nr += dr[2];
                        nc += dc[2];

                        if (nr < 0 || nr >= 11 || nc < 0 || nc >= 11) {
                            nr = pr;
                            nc = pc;
                            continue;
                        }

                        if (!visited[pr][pc][2] && !visited[nr][nc][3]) {
                            cnt++;
                            visited[pr][pc][2] = true;
                            visited[nr][nc][3] = true;
                        }

                        break;
                    case 'L' :
                        nr += dr[3];
                        nc += dc[3];

                        if (nr < 0 || nr >= 11 || nc < 0 || nc >= 11) {
                            nr = pr;
                            nc = pc;
                            continue;
                        }

                        if (!visited[pr][pc][3] && !visited[nr][nc][2]) {
                            cnt++;
                            visited[pr][pc][3] = true;
                            visited[nr][nc][2] = true;
                        }

                        break;
                }

                pr = nr;
                pc = nc;
            }

            return cnt;
        }
    }
}
