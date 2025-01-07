package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;

public class 과제_진행하기 {
    class Solution {
        public String[] solution(String[][] plans) {
            PriorityQueue<Task> pq = new PriorityQueue<>();
            Stack<Task> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < plans.length; i++) {
                String[] time = plans[i][1].split(":");
                String name = plans[i][0];
                int start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                int playtime = Integer.parseInt(plans[i][2]);

                pq.add(new Task(name, start, playtime));
            }

            Task current = null;
            Task next = pq.poll();
            while (!pq.isEmpty()) {
                current = next;
                next = pq.poll();
                int rMinute = next.start - current.start;

                if (rMinute < current.playtime) {
                    current.playtime -= rMinute;
                    stack.push(current);
                } else if(rMinute == current.playtime) {
                    sb.append(current.name).append(" ");
                } else {
                    rMinute -= current.playtime;
                    sb.append(current.name).append(" ");

                    while (!stack.isEmpty() && rMinute > 0) {
                        current = stack.pop();

                        if (rMinute < current.playtime) {
                            current.playtime -= rMinute;
                            stack.push(current);
                            break;
                        } else {
                            rMinute -= current.playtime;
                            sb.append(current.name).append(" ");
                        }
                    }
                }
            }
            sb.append(next.name).append(" ");

            while (!stack.isEmpty()) {
                Task remain = stack.pop();
                sb.append(remain.name).append(" ");
            }

            String[] answer = sb.toString().split(" ");
            return answer;
        }

        public class Task implements Comparable<Task> {
            String name;
            int start;
            int playtime;

            Task(String n, int s, int p) {
                this.name = n;
                this.start = s;
                this.playtime = p;
            }

            @Override
            public int compareTo(Task o) {
                return this.start - o.start;
            }
        }
    }

}
