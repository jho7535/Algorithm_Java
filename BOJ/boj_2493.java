package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_2493 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        /*
        * 레이저 왼쪽으로 날림. 같거나 높아야 받을 수 있음.
        * 각 탑에서 발사한 레이저가 어느 탑에서 받는지.
        * 입     n : 1~50만
        *        탑 높이 : 1~1억
        * 출     수신한 탑 번호. 없으면 0
        * */

        // 입력 arr
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] high = new int[n];
        for (int i = 0; i < n; i++) high[i] = Integer.parseInt(st.nextToken());

        // arr 뒤에서 부터
        int[] output = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            int curHigh = high[i];

            // 인덱스로 높이 비교. stack 비우기. top 보다 작으면 넘김. 출력 배열 채우기
            while (!stack.isEmpty() && high[stack.peek()] <= curHigh) {
                output[stack.pop()] = i + 1;
            }

            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : output) sb.append(i).append(" ");
        System.out.println(sb);
    }
}
