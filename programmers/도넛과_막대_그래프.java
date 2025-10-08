package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;


public class 도넛과_막대_그래프 {
    /*
    도넛, 막대, 8자

    도넛: n개 정점, n개 간선
        아무 정점. 이용한 적 없는 간선. n-1개 정점 방문하고 출발점

    막대: n개 정점, n-1개 간선
        모든 정점 한 번씩 방문하는 정점이 하나 있음.

    8자: 2n+1개의 정점, 2n+2개의 간선

    출력 : 생성 정점 번호, 각 그래프 수

    ---

    나가는 것만 2개 이상. 정점

    */
    class Solution {

        static int max = 0;

        public int[] solution(int[][] edges) {
            int[] in = new int[1_000_001];
            int[] out = new int[1_000_001];
            for (int[] edge : edges) {
                out[edge[0]]++;
                in[edge[1]]++;
            }

            int vertex = 0;
            for (int i = 1; i <= 1_000_000; i++) {
                if (in[i] == 0 && out[i] > 1) vertex = i;
            }

            // 2, 2 : 8자
            // 0, 1 : 1자
            int a = 0;
            int b = 0;
            int c = 0;
            for (int i = 1; i <= 1_000_000; i++) {
                if (out[i] == 2 && in[i] >= 2) c++;
                else if (out[i] == 0 && in[i] >= 1) b++;
            }
            a = out[vertex] - b - c;

            int[] answer = {vertex, a, b, c};

            return answer;
        }
    }
}
