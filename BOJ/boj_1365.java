package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

/*
* 입 |   n : 1-100_000
*       n개
* 출 |   자를 전선의 최소 개수
*
* 계속 증가해야됨
* 2 1 4 5 3
* 3 2 1 3 5 9 1 2 7 6 8
* */

public class boj_1365 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(input[i]);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (list.isEmpty() || list.get(list.size() - 1) < arr[i]) {
                list.add(arr[i]);
                continue;
            }

            int low = 0;
            int high = list.size() - 1;
            int index = 0;
            while (low <= high) {
                int mid = (low + high) / 2;

                if (list.get(mid) >= arr[i]) {
                    index = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            list.set(index, arr[i]);
        }

        System.out.println(n - list.size());
    }
}
