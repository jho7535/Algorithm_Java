package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 충돌위험_찾기 {
    class Solution {
        public int solution(int[][] points, int[][] routes) {
            int[][] map = new int[101][101];
            int n = points.length;
            int x = routes.length;
            int m = routes[0].length;

            Robot[] robots = new Robot[x];
            for (int i = 0; i < x; i++) {
                robots[i] = new Robot(new int[m][2]);

                for (int j = 0; j < m; j++) {
                    robots[i].destination[j][0] = points[routes[i][j] - 1][0];
                    robots[i].destination[j][1] = points[routes[i][j] - 1][1];
                }
            }

            for (Robot ro : robots) {
                ro.cur[0] = ro.destination[0][0];
                ro.cur[1] = ro.destination[0][1];

                map[ro.destination[0][0]][ro.destination[0][1]]++;
                ro.destIndex++;
            }

            int answer = 0;
            for (int i = 1; i <= 100; i++) {
                for (int j = 1; j <= 100; j++) if (map[i][j] >= 2) answer++;
            }

            while (true) {
                // 종료 조건
                boolean flag = true;
                for (Robot ro : robots) {
                    if (!ro.done) {
                        flag = false;
                        break;
                    }
                }

                if (flag) break;

                // 각 로봇 위치 이동
                for (Robot ro : robots) {
                    if (ro.done) continue;

                    // 목적지 도착 확인. 목적지 설정
                    int destR = ro.destination[ro.destIndex][0];
                    int destC = ro.destination[ro.destIndex][1];

                    if (ro.cur[0] == destR && ro.cur[1] == destC) {
                        ro.destIndex++;

                        if (ro.destIndex == m) ro.done = true;
                        else {
                            destR = ro.destination[ro.destIndex][0];
                            destC = ro.destination[ro.destIndex][1];
                        }
                    }

                    // 맵에서 지우고
                    map[ro.cur[0]][ro.cur[1]]--;

                    // 끝났는지 확인
                    if (ro.done) continue;

                    // 한 칸 이동. r먼저
                    if (ro.cur[0] == destR) {
                        if (ro.cur[1] > destC) ro.cur[1]--;
                        else ro.cur[1]++;
                    } else {
                        if (ro.cur[0] > destR) ro.cur[0]--;
                        else ro.cur[0]++;
                    }

                    // 맵에 표시
                    map[ro.cur[0]][ro.cur[1]]++;
                }

                // map에서 충돌 확인
                for (int i = 1; i <= 100; i++) {
                    for (int j = 1; j <= 100; j++) if (map[i][j] >= 2) answer++;
                }
            }


            return answer;
        }

        public class Robot {
            public int[] cur = new int[]{0, 0};
            public int[][] destination;
            public int destIndex = 0;
            public boolean done = false;

            public Robot(int[][] d) {
                destination = d;
            }
        }
    }
}
