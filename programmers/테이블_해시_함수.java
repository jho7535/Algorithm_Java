package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;


public class 테이블_해시_함수 {
    class Solution {
        public int solution(int[][] data, int col, int row_begin, int row_end) {
            Arrays.sort(data, (o1, o2) -> {
                if (o1[col - 1] == o2[col - 1]) return o2[0] - o1[0];
                else return o1[col - 1] - o2[col - 1];
            });

            int prev = 0;
            for (int j = 0; j < data[row_begin - 1].length; j++) {
                prev += (data[row_begin - 1][j] % (row_begin));
            }

            for (int i = row_begin; i < row_end; i++) {
                int sum = 0;
                for (int j = 0; j < data[i].length; j++) {
                    sum += data[i][j] % (i + 1);
                }

                prev = prev ^ sum;
            }

            return prev;
        }
    }
}
