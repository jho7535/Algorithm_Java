package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 기둥과_보_설치 {
    class Solution {

        static boolean[][] isBo;
        static boolean[][] isKi;
        static int n;

        public List<int[]> solution(int n, int[][] build_frame) {
            this.n = n;
            isBo = new boolean[n + 1][n + 1];
            isKi = new boolean[n + 1][n + 1];

            // 시뮬
            for (int[] frame : build_frame) {
                int r = frame[1];
                int c = frame[0];

                // 기둥
                if (frame[2] == 0) {
                    // 설치
                    if (frame[3] == 1) {
                        if (ableKi(r, c)) isKi[r][c] = true;
                    }

                    // 삭제
                    else if (frame[3] == 0) {
                        isKi[r][c] = false;

                        if (r + 1 <= n && c - 1 >= 0 && isBo[r + 1][c - 1]) {
                            if (!ableBo(r + 1, c - 1)) isKi[r][c] = true;
                        }

                        if (r + 1 <= n && isBo[r + 1][c]) {
                            if (!ableBo(r + 1, c)) isKi[r][c] = true;
                        }

                        if (r + 1 <= n && isKi[r + 1][c]) {
                            if (!ableKi(r + 1, c)) isKi[r][c] = true;
                        }
                    }
                }

                // 보
                else if (frame[2] == 1) {
                    // 설치
                    if (frame[3] == 1) {
                        if (ableBo(r, c)) isBo[r][c] = true;
                    }

                    // 삭제
                    else if (frame[3] == 0) {
                        isBo[r][c] = false;

                        if (c - 1 >= 0 && isBo[r][c - 1]) {
                            if (!ableBo(r, c - 1)) isBo[r][c] = true;
                        }

                        if (c + 1 <= n && isBo[r][c + 1]) {
                            if (!ableBo(r, c + 1)) isBo[r][c] = true;
                        }

                        if (isKi[r][c]) {
                            if (!ableKi(r, c)) isBo[r][c] = true;
                        }

                        if (c + 1 <= n && isKi[r][c + 1]) {
                            if (!ableKi(r, c + 1)) isBo[r][c] = true;
                        }
                    }
                }
            }

            List<int[]> answer = new ArrayList<>();
            for (int c = 0; c <= n; c++) {
                for (int r = 0; r <= n; r++) {
                    if (isKi[r][c]) answer.add(new int[]{c, r, 0});
                    if (isBo[r][c]) answer.add(new int[]{c, r, 1});
                }
            }

            return answer;
        }

        public static boolean ableKi(int r, int c) {
            // 바닥
            if (r == 0) return true;

            // 보의 한쪽 끝 위
            if (isRange(r, c - 1) && isBo[r][c - 1]) return true;
            if (isRange(r, c) && isBo[r][c]) return true;

            // 다른 기둥 위
            if (isRange(r - 1, c) && isKi[r - 1][c]) return true;

            return false;
        }

        public static boolean ableBo(int r, int c) {
            // 한쪽 끝이 기둥 위
            if (isRange(r - 1, c) && isKi[r - 1][c]) return true;
            if (isRange(r - 1, c + 1) && isKi[r - 1][c + 1]) return true;

            // 왼, 오에 보
            if (isRange(r, c - 1) && isBo[r][c - 1] && isRange(r, c + 1) && isBo[r][c + 1]) return true;

            return false;
        }

        public static boolean isRange(int r, int c) {
            return r >= 0 && r <= n && c >= 0 && c <= n;
        }
    }
}
