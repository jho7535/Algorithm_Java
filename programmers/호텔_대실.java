package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 호텔_대실 {
    class Solution {
        public int solution(String[][] book_time) {
            Arrays.sort(book_time, (t1, t2) -> t1[0].compareTo(t2[0]));
            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int i = 0; i < book_time.length; i++) {
                String[] time = book_time[i][0].split(":");
                int start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                time = book_time[i][1].split(":");
                int end = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]) + 10;

                if (pq.isEmpty() || start < pq.peek()) {
                    pq.add(end);
                    continue;
                }

                pq.poll();
                pq.add(end);
            }

            int answer = pq.size();
            return answer;
        }
    }
}
