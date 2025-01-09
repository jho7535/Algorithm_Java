package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 당구_연습 {
    class Solution {
        public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
            int[] answer = new int[balls.length];

            for (int i = 0; i < balls.length; i++) {
                int nx = 0;
                int ny = 0;
                int distance = 0;
                int min = Integer.MAX_VALUE;

                // y축에 대칭
                nx = 0;
                if (startY != balls[i][1] || startX < balls[i][0]) {
                    nx -= balls[i][0];
                    ny = balls[i][1];

                    distance = (int)Math.pow(nx - startX, 2) + (int)Math.pow(ny - startY, 2);
                    min = Math.min(min, distance);
                }
                // x축에 대칭
                ny = 0;
                if (startX != balls[i][0] || startY < balls[i][1]) {
                    nx = balls[i][0];
                    ny -= balls[i][1];

                    distance = (int)Math.pow(nx - startX, 2) + (int)Math.pow(ny - startY, 2);
                    min = Math.min(min, distance);
                }
                // x = m에 대칭
                nx = m;
                if (startY != balls[i][1] || startX > balls[i][0]) {
                    nx += (nx - balls[i][0]);
                    ny = balls[i][1];

                    distance = (int)Math.pow(nx - startX, 2) + (int)Math.pow(ny - startY, 2);
                    min = Math.min(min, distance);
                }
                // x축에 대칭
                ny = n;
                if (startX != balls[i][0] || startY > balls[i][1]) {
                    nx = balls[i][0];
                    ny += (ny - balls[i][1]);

                    distance = (int)Math.pow(nx - startX, 2) + (int)Math.pow(ny - startY, 2);
                    min = Math.min(min, distance);
                }

                answer[i] = min;
            }

            return answer;
        }
    }
}
