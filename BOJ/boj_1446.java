package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1446 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 지름길 개수
        int d = Integer.parseInt(st.nextToken()); // 고속도로 길이

        ArrayList<Road> arrayList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if (end > d) continue;
            if (end - start <= distance) continue;

            arrayList.add(new Road(start, end, distance));
        }

        Collections.sort(arrayList, new Comparator<Road>() {
            @Override
            public int compare(Road o1, Road o2) {
                return o1.start - o2.start;
            }
        });
        int[] dp = new int[10001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int index = 0;
        int roadIndex = 0;
        while (index <= d) {
            if (roadIndex < arrayList.size()) {
                Road current = arrayList.get(roadIndex);
                if (index == current.start) { // 지름길을 탐
                    if (dp[current.end] > dp[index] + current.distance) {
                        dp[current.end] = dp[index] + current.distance;
                    }

                    roadIndex++;
                    continue;
                }
            }

            if (dp[index + 1] > dp[index] + 1) {
                dp[index + 1] = dp[index] + 1;
            }

            index++;
        }

        System.out.println(dp[d]);
    }
}

class Road {
    public int start;
    public int end;
    public int distance;

    Road(int s, int e, int d) {
        this.start = s;
        this.end = e;
        this.distance = d;
    }
}
