package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_5430 {
    //public class Main {

    public static String p;
    public static int n;
    public static String a;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            p = br.readLine();
            n = Integer.parseInt(br.readLine());
            a = br.readLine().replaceAll("[\\[\\]]", "");

            sol();
        }
    }

    public static void sol() {
        String[] command = a.split(",");
        int start = 0, end = n;
        boolean order = true;

        for (int i = 0; i < p.length(); i++) {
            // 뒤집기
            if (p.charAt(i) == 'R') order = !order;
                // 버리기. 정방향
            else if (p.charAt(i) == 'D' && order) {
                if (start >= end) {
                    System.out.println("error");
                    return;
                }
                start++;
            }
            // 버리기. 역방향
            else {
                if (start >= end) {
                    System.out.println("error");
                    return;
                }
                end--;
            }
        }

        if (start >= end) {
            System.out.println("[]");
            return;
        }
        // 정방향 출력
        System.out.print("[");
        if (order) {
            int i;
            for (i = start; i < end - 1; i++) {
                System.out.print(command[i] + ",");
            }
            System.out.print(command[i]);
        }
        // 역방향 출력
        else {
            int i;
            for (i = end - 1; i >= start + 1; i--) {
                System.out.print(command[i] + ",");
            }
            System.out.print(command[i]);
        }
        System.out.println("]");
    }
}
