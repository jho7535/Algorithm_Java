package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_14719 {
    //public class Main{
    public static void main(String[] args) throws IOException {
        /*
         * 고이는 빗물의 총량 구하기.
         * 입 |   H, W    :   1~500. 세로 가로.
         *       블록이 쌓인 높이 : 0~H
         * 출 |   빗물 총량. 0 가능
         * */

        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] leftMax = new int[w];
        int[] rightMax = new int[w];

        leftMax[0] = arr[0];
        for (int i = 1; i < w; i++) leftMax[i] = Math.max(arr[i], leftMax[i - 1]);

        rightMax[w - 1] = arr[w - 1];
        for (int i = w - 2; i >= 0; i--) rightMax[i] = Math.max(arr[i], rightMax[i + 1]);

        int sum = 0;
        for (int i = 0; i < w; i++) {
            int high = Math.min(leftMax[i], rightMax[i]);
            if (high - arr[i] > 0) sum += high - arr[i];
        }

        System.out.println(sum);
    }
}
