package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 점_찍기 {
    class Solution {
        public long solution(int k, int d) {
            long answer = 0;

            for (int i = 0; i <= d; i += k) {
                long squareD = (long) d * d;
                long squareI = (long) i * i;
                int y = (int)Math.floor(Math.sqrt(squareD - squareI));

                answer += (long)(y / k + 1);

            }

            return answer;
        }
    }
}
