package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 타겟_넘버 {
    class Solution {
        public int solution(int[] numbers, int target) {
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{numbers[0], 1});
            queue.add(new int[]{-numbers[0], 1});
            int answer = 0;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int curCnt = current[0];
                int curLevel = current[1];

                if (curLevel == numbers.length) {
                    if (curCnt == target) answer++;
                    continue;
                }

                queue.add(new int[]{(curCnt + numbers[curLevel]), (curLevel + 1)});
                queue.add(new int[]{(curCnt - numbers[curLevel]), (curLevel + 1)});
            }

            return answer;
        }
    }
}
