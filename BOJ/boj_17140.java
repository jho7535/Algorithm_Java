package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_17140 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] arr = new int[101][101];

        int rCnt = 3;
        int cCnt = 3;
        int time = 0;

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= 3; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        while (time <= 100) {
            if (arr[p][q] == k) {
                System.out.println(time);
                return;
            }

            // R 연산
            if (rCnt >= cCnt) {
                for (int i = 1; i <= rCnt; i++) {
                    Map<Integer, Integer> map = new HashMap<>();

                    for (int j = 1; j <= cCnt; j++) {
                        if (arr[i][j] == 0) continue;

                        map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
                    }

                    List<int[]> list = new ArrayList<>();
                    map.forEach((a, b) -> list.add(new int[]{a, b}));
                    list.sort((a, b) -> {
                        if (a[1] == b[1]) return a[0] - b[0];
                        return a[1] - b[1];
                    });

                    int index = 1;
                    for (int[] l : list) {
                        if (index > 100) break;

                        arr[i][index++] = l[0];
                        arr[i][index++] = l[1];
                    }

                    cCnt = Math.max(cCnt, index - 1);

                    for (int j = index; j <= 100; j++) arr[i][j] = 0;
                }
            }

            // C 연산
            else if (rCnt < cCnt) {
                for (int j = 1; j <= cCnt; j++) {
                    Map<Integer, Integer> map = new HashMap<>();

                    for (int i = 1; i <= rCnt; i++) {
                        if (arr[i][j] == 0) continue;

                        map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
                    }

                    List<int[]> list = new ArrayList<>();
                    map.forEach((a, b) -> list.add(new int[]{a, b}));
                    list.sort((a, b) -> {
                        if (a[1] == b[1]) return a[0] - b[0];
                        return a[1] - b[1];
                    });

                    int index = 1;
                    for (int[] l : list) {
                        if (index > 100) break;

                        arr[index++][j] = l[0];
                        arr[index++][j] = l[1];
                    }

                    rCnt = Math.max(rCnt, index - 1);

                    for (int i = index; i <= 100; i++) arr[i][j] = 0;
                }
            }

            time++;
        }

        System.out.println(-1);
    }
}
