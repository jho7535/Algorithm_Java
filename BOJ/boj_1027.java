package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_1027 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] height = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) height[i] = Integer.parseInt(st.nextToken());

        int max = 0;
        for (int mid = 0; mid < n; mid++) {
            int cnt = 0;

            boolean flag = true;
            int left = mid - 1;
            while (left >= 0 && flag) {
                double gradient = (double) (height[mid] - height[left]) / (double) (mid - left);
                flag = false;
                cnt++;

                for (int i = left - 1; i >= 0; i--) {
                    if (gradient * (i - mid) + height[mid] < (double) height[i]) {
                        left = i;
                        flag = true;
                        break;
                    }
                }
            }

            flag = true;
            int right = mid + 1;
            while (right < n && flag) {
                double gradient = (double) (height[right] - height[mid]) / (double) (right - mid);
                flag = false;
                cnt++;

                for (int i = right + 1; i < n; i++) {
                    if (gradient * (i - mid) + height[mid] < (double) height[i]) {
                        right = i;
                        flag = true;
                        break;
                    }
                }
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}
