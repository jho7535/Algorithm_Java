package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 비밀_코드_해독 {
    class Solution {

        static Set<Integer> set = new HashSet<>();
        static int answer = 0;

        public int solution(int n, int[][] q, int[] ans) {
            dfs(1, 0, q, ans, n);

            return answer;
        }

        // dfs.
        public static void dfs(int index, int level, int[][] q, int[] ans, int n) {
            // 종료 조건
            if (level == 5) {
                if (sol(q, ans)) answer++;
                return;
            }

            // 가지치기
            if (!branch(q, ans)) return;

            // 선택
            for (int i = index; i <= n; i++) {
                set.add(i);
                dfs(i + 1, level + 1, q, ans, n);
                set.remove(i);
            }
        }

        // sol
        public static boolean sol(int[][] q, int[] ans) {
            boolean flag = true;

            for (int j = 0; j < q.length; j++) {
                int cnt = 0;

                for (int i = 0; i < 5; i++) {
                    if (set.contains(q[j][i])) cnt++;
                }

                if (cnt != ans[j]) {
                    flag = false;
                    break;
                }
            }

            return flag;
        }

        // branch
        public static boolean branch(int[][] q, int[] ans) {
            boolean flag = true;

            for (int j = 0; j < q.length; j++) {
                int cnt = 0;

                for (int i = 0; i < 5; i++) {
                    if (set.contains(q[j][i])) cnt++;
                }

                if (cnt > ans[j]) {
                    flag = false;
                    break;
                }
            }

            return flag;
        }
    }
}
