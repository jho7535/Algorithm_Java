package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2470 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        int start = 0, end = n - 1;
        int minStart = start, minEnd = end;
        while (start < end) {
            if (arr[start] + arr[end] == 0) {
                minStart = start;
                minEnd = end;
                break;
            }

            if (Math.abs(arr[start] + arr[end]) < Math.abs(arr[minStart] + arr[minEnd])) {
                minStart = start;
                minEnd = end;
            }

            // (양수, 양수). end--
            if (arr[start] > 0) end--;
            // (음수, 음수). start++
            else if (arr[end] < 0) start++;
            // 합이 음수. start++
            else if (arr[start] + arr[end] < 0) start++;
            // 합이 양수. end--
            else end--;
        }

        System.out.println(arr[minStart] + " " + arr[minEnd]);
    }
}
