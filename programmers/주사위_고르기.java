package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;


public class 주사위_고르기 {
    class Solution {

        static int n;
        static long max = 0;
        static boolean[] team;
        static List<Integer> answer;
        static int[][] di;

        public List<Integer> solution(int[][] dice) {
            di = dice;
            n = dice.length;
            team = new boolean[n];

            select(0, 0);

            return answer;
        }

        static void select(int start, int level) {
            if (level == n / 2) {
                // System.out.println(Arrays.toString(team));
                List<Integer> teamA = new ArrayList<>();
                List<Integer> teamB = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    if (team[i]) teamA.add(i);
                    else teamB.add(i);
                }

                // 모든 합 계산
                List<Integer> listA = new ArrayList<>();
                List<Integer> listB = new ArrayList<>();

                cal(0, 0, teamA, listA);
                cal(0, 0, teamB, listB);

                // B 정렬
                listB.sort((a, b) -> a - b);

                // 이기는 경우 계산
                long cnt = 0;
                for (int target : listA) {
                    int low = 0;
                    int high = listB.size() - 1;
                    int index = -1;

                    while (low <= high) {
                        int mid = (low + high) / 2;

                        if (listB.get(mid) < target) {
                            low = mid + 1;
                            index = mid;
                        } else {
                            high = mid - 1;
                        }
                    }

                    if (index != -1) cnt += index + 1;
                }

                // 최신화
                if (cnt > max) {
                    max = cnt;
                    answer = new ArrayList<>();
                    for (int i : teamA) answer.add(i + 1);
                }

                return;
            }

            for (int i = start; i < n; i++) {
                team[i] = true;
                select(i + 1, level + 1);
                team[i] = false;
            }
        }

        static void cal(int level, int cnt, List<Integer> teamA, List<Integer> listA) {
            if (level == n / 2) {
                listA.add(cnt);
                return;
            }

            for (int i = 0; i < 6; i++) {
                cal(level + 1, cnt + di[teamA.get(level)][i], teamA, listA);
            }
        }
    }
}
