package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;

public class 멀쩡한_사각형 {
    class Solution {
        public long solution(int w, int h) {
            if (w > h) {
                int temp = w;
                w = h;
                h = temp;
            }

            long cnt = 0;
            for (int i = 1; i <= w; i++) {
                double a = (double) h * (double) (i - 1) / (double) w;
                double b = (double) h * (double) i / (double) w;

                cnt += (long) Math.ceil(b) - (long) Math.floor(a);
            }

            long width = w;
            long height = h;
            long answer = width * height - cnt;
            return answer;
        }
    }
}
