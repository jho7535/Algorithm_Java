package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 두_원_사이의_정수_쌍 {
    class Solution {
        public long solution(int r1, int r2) {
            long x = 0, y = 0;
            long minY, maxY;
            long cnt = 0;

            for (x = 1; x <= r2; x++) {
                maxY = (long)Math.floor(Math.sqrt((long)r2 * r2 - x * x));
                if ((int)x < r1)
                    minY = (long)Math.ceil(Math.sqrt((long)r1 * r1 - (long)x * x));
                else
                    minY = 0;

                cnt += (maxY - minY + 1);
            }

            long answer = cnt * 4;
            return answer;
        }
    }
}
