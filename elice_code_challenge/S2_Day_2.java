package nestnet_algorithm_2023_2.JeongHanUl.elice_code_challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_Day_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] selected = new int[q - p + 1];
            int index = 0;
            for (int j = p; j <= q; j++){
                selected[index] = arr[j - 1];
                index++;
            }

            Arrays.sort(selected);
            sb.append(selected[k - 1]).append("\n");
        }

        String output = sb.toString();
        System.out.println(output);
    }
}
