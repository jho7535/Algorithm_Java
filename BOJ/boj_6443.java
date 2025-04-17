package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_6443 {
    //public class Main {

    static String word;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            word = br.readLine();
            int[] alpha = new int[26];

            for (int j = 0; j < word.length(); j++) {
                alpha[word.charAt(j) - 'a']++;
            }

            dfs(sb, alpha, 0);
        }
    }

    static void dfs(StringBuilder sb, int[] alpha, int depth) {
        // 종료 조건 depth == word.length()
        if (depth == word.length()) {
            System.out.println(sb);
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (alpha[i] <= 0) continue;

            sb.append((char) (i + 'a'));
            alpha[i]--;
            dfs(sb, alpha, depth + 1);
            sb.setLength(depth);
            alpha[i]++;
        }
    }
}
