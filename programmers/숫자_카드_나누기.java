package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 숫자_카드_나누기 {
    class Solution {
        public int solution(int[] arrayA, int[] arrayB) {
            int[] answer = new int[2];
            Arrays.sort(arrayA);
            Arrays.sort(arrayB);

            // A
            int gcd = arrayA[0];
            for (int i = 1; i < arrayA.length; i++) {
                int b = arrayA[i];
                int k = 0;

                while (b != 0) {
                    k = gcd % b;
                    gcd = b;
                    b = k;
                }
            }

            for (int i = gcd; i > 0; i--) {
                if (gcd % i != 0) continue;

                boolean flag = true;
                for (int val : arrayB) {
                    if (val % i == 0) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    answer[0] = i;
                    break;
                }
            }

            // B
            gcd = arrayB[0];
            for (int i = 1; i < arrayB.length; i++) {
                int b = arrayB[i];
                int k = 0;

                while (b != 0) {
                    k = gcd % b;
                    gcd = b;
                    b = k;
                }
            }

            for (int i = gcd; i > 0; i--) {
                if (gcd % i != 0) continue;

                boolean flag = true;
                for (int val : arrayA) {
                    if (val % i == 0) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    answer[1] = i;
                    break;
                }
            }

            return Math.max(answer[0], answer[1]);
        }
    }
}
