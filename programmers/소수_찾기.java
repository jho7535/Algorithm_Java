package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 소수_찾기 {
    class Solution {
        static int[] cnt = new int[10];
        static int answer = 0;

        public int solution(String numbers) {
            for (char ch : numbers.toCharArray()) cnt[ch -'0']++;

            for (int i = 1; i <= numbers.length(); i++) {
                dfs(0, i, 0);
            }

            return answer;
        }

        public void dfs(int level, int depth, int num) {
            // 종료 조건
            if (level == depth) {
                System.out.println(num);
                if (isPrime(num)) answer++;
                return;
            }

            // 첫째 자리는 0 빼고
            if (level == 0) {
                for (int i = 1; i < 10; i++) {
                    if (cnt[i] == 0) continue;

                    cnt[i]--;
                    dfs(level + 1, depth, num * 10 + i);
                    cnt[i]++;
                }
            }
            // 나머지 자리 완성
            else {
                for (int i = 0; i < 10; i++) {
                    if (cnt[i] == 0) continue;

                    cnt[i]--;
                    dfs(level + 1, depth, num * 10 + i);
                    cnt[i]++;
                }
            }
        }
        public boolean isPrime(int x) {
            if (x == 1) return false;
            for (int i = 2; i <= Math.sqrt(x); i++) {
                if (x % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
