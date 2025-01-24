package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.util.*;
import java.io.*;

public class 혼자_놀기의_달인 {
    class Solution {

        boolean[] visited;

        public int solution(int[] cards) {
            for (int i = 0; i < cards.length; i++) {
                cards[i]--;
            }
            visited = new boolean[cards.length];
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < cards.length; i++){
                if (!visited[i]) {
                    list.add(sol(i, cards));
                }
            }

            list.sort((o1, o2) -> o2 - o1);

            if (list.size() == 1) return 0;
            else return list.get(0) * list.get(1);
        }

        public int sol(int start, int[] cards) {
            int cnt = 0;
            int index = start;

            while (cnt <= cards.length) {
                if (visited[index]) return cnt;

                visited[index] = true;
                cnt++;
                index = cards[index];
            }

            return cnt;
        }
    }
}
