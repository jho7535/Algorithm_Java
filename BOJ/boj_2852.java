package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2852 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String[] str = new String[n];   // 분:초 저장
        int[] team = new int[n];    // 골을 넣은 팀 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            team[i] = Integer.parseInt(st.nextToken());
            str[i] = st.nextToken();
        }

        int[] score = new int[3];   // 1팀 2팀 점수
        int[] winner = new int[n];  // 골이 들어갔을 때 이기고 있는 팀
        for (int i = 0; i < n; i++) {
            if (team[i] == 1) score[1]++;
            else score[2]++;

            if (score[1] > score[2]) winner[i] = 1;
            else if (score[1] < score[2]) winner[i] = 2;
            else winner[i] = 0;
        }

        int[] time = new int[3];
        for (int i = 0; i < n - 1; i++) {
            String[] timeString1 = str[i].split(":");
            String[] timeString2 = str[i + 1].split(":");

            int time1 = Integer.parseInt(timeString1[0]) * 60 + Integer.parseInt(timeString1[1]);
            int time2 = Integer.parseInt(timeString2[0]) * 60 + Integer.parseInt(timeString2[1]);

            if (winner[i] == 1) time[1] += time2 - time1;
            else if (winner[i] == 2) time[2] += time2 - time1;
        }

        String[] timeString = str[n - 1].split(":");
        int timeN = Integer.parseInt(timeString[0]) * 60 + Integer.parseInt(timeString[1]);
        if (winner[n - 1] == 1) time[1] += 48 * 60 - timeN;
        else if (winner[n - 1] == 2) time[2] += 48 * 60 - timeN;

        if (time[1] / 60 < 10) System.out.print("0" + time[1] / 60 + ":");
        else System.out.print(time[1] / 60 + ":");

        if (time[1] % 60 < 10) System.out.println("0" + time[1] % 60);
        else System.out.println(time[1] % 60);

        if (time[2] / 60 < 10) System.out.print("0" + time[2] / 60 + ":");
        else System.out.print(time[2] / 60 + ":");

        if (time[2] % 60 < 10) System.out.println("0" + time[2] % 60);
        else System.out.println(time[2] % 60);
    }
}
