package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class H_Index {
    class Solution {
        public int solution(int[] citations) {
            int n = citations.length;
            Arrays.sort(citations);

            int answer = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (citations[i] >= n - i) {
                    answer = n - i;
                }
            }

            return answer;
        }
    }
}
