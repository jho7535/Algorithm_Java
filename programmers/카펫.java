package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 카펫 {
    class Solution {
        public int[] solution(int brown, int yellow) {
            int[] answer = {};

            for (int i = 1; i <= Math.sqrt(yellow); i++) {
                if (yellow % i != 0) continue;

                int w = yellow / i;
                int h = i;

                if ((w + h + 2) * 2 == brown) answer = new int[]{w + 2, h + 2};
            }

            return answer;
        }
    }
}
