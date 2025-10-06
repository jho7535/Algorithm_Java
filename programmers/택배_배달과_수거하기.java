package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 택배_배달과_수거하기 {
    class Solution {
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            // 최대 들고 가서 배달
            // 오는 길에 최대로 들고 옴
            long answer = 0;

            // 스택에 넣기
            Stack<int[]> deliverStack = new Stack<>();
            for (int i = 0; i < n; i++) if (deliveries[i] != 0) deliverStack.push(new int[]{i + 1, deliveries[i]});
            Stack<int[]> pickupStack = new Stack<>();
            for (int i = 0; i < n; i++) if (pickups[i] != 0) pickupStack.push(new int[]{i + 1, pickups[i]});

            while (!deliverStack.isEmpty() || !pickupStack.isEmpty()) {
                // 거리 변수 하나. 배달 or 회수 최대 거리
                // 용량 변수
                int distance = 0;
                int curCap = 0;

                // 배달 스택에서 pop해서 용량 채우고 최대 거리 갱신
                while (!deliverStack.isEmpty() && curCap < cap) {
                    int[] cur = deliverStack.pop();
                    int curD = cur[0];
                    int curC = cur[1];

                    if (curCap + curC > cap) {
                        deliverStack.push(new int[]{curD, curC - (cap - curCap)});
                        curCap = cap;
                    } else {
                        curCap += curC;
                    }

                    distance = Math.max(distance, curD);
                }

                // 회수 스택에서 pop해서 용량 채우고 최대 거리 갱신
                curCap = 0;
                while (!pickupStack.isEmpty() && curCap < cap) {
                    int[] cur = pickupStack.pop();
                    int curD = cur[0];
                    int curC = cur[1];

                    if (curCap + curC > cap) {
                        pickupStack.push(new int[]{curD, curC - (cap - curCap)});
                        curCap = cap;
                    } else {
                        curCap += curC;
                    }

                    distance = Math.max(distance, curD);
                }

                // 거리 누적 합
                answer += (distance * 2);
            }

            return answer;
        }
    }
}
