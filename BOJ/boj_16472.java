package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_16472 {
    //public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int max = 0;

        int start = 0, end = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (end < s.length()) {
            char ch = s.charAt(end);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (map.size() > n) {
                char startChar = s.charAt(start);
                map.put(startChar, map.get(startChar) - 1);

                if (map.get(startChar) == 0) map.remove(startChar);

                start++;
            }

            max = Math.max(max, end - start + 1);
            end++;
        }

        System.out.println(max);
    }
}
