package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 디스크_컨트롤러 {
    class Solution {
        public int solution(int[][] jobs) {
            Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
            int answer = 0;

            int time = 0;
            int index = 0, end = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                if (a[2] == b[2]) {
                    if (a[1] == b[1]) return a[0] - b[0];
                    return a[1] - b[1];
                }
                return a[2] - b[2];
            });

            while (index < jobs.length || !pq.isEmpty()) {
                // time이 요청 시각이면 대기큐 삽입
                while (index < jobs.length && jobs[index][0] <= time) {
                    pq.add(new int[]{index, jobs[index][0], jobs[index][1]});
                    index++;
                }

                // time이 end보다 크거나 같으면 작업 할당
                // 할당하면서 반환시각 누적
                if (!pq.isEmpty() && time >= end) {
                    int[] current = pq.poll();
                    end = time + current[2];
                    answer += end - current[1];
                }

                time++;
            }

            return answer / jobs.length;
        }
    }
}
