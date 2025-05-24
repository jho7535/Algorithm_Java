package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 여행경로 {
    class Solution {
        static List<String> count = new ArrayList<>();
        static String[] answer;
        static boolean flag = true;
        static Map<String, ArrayList<String>> graph = new HashMap<>();
        static Map<String, Integer> visited = new HashMap<>();

        public String[] solution(String[][] tickets) {
            Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));

            for (String[] ticket : tickets) {
                String start = ticket[0];
                String end = ticket[1];

                ArrayList<String> list = graph.getOrDefault(start, new ArrayList<>());
                list.add(end);
                graph.put(start, list);
                visited.put(start + end, visited.getOrDefault(start + end, 0) + 1);

            }

            count.add("ICN");
            dfs("ICN", tickets);

            return answer;
        }

        public void dfs(String start, String[][] tickets) {
            if (!flag) return;

            if (count.size() - 1 == tickets.length) {
                answer = count.toArray(new String[0]);
                flag = false;
                return;
            }

            if (!graph.containsKey(start)) return;

            ArrayList<String> list = graph.get(start);
            for (String end : list) {
                String str = start + end;

                if (visited.get(str) == 0) continue;

                count.add(end);
                visited.put(str, visited.get(str) - 1);
                dfs(end, tickets);
                count.remove(count.size() - 1);
                visited.put(str, visited.get(str) + 1);
            }
        }
    }
}
