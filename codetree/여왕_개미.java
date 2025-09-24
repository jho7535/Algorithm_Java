package nestnet_algorithm_2023_2.JeongHanUl.codetree;

import java.io.*;
import java.util.*;

public class 여왕_개미 {
    public class Main {

        static List<int[]> town = new ArrayList<>();

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int q = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            int k = Integer.parseInt(st.nextToken());
            int index = 1;
            while (index <= k) {
                int x = Integer.parseInt(st.nextToken());
                town.add(new int[]{index, x});
                index++;
            }

            StringBuilder sb = new StringBuilder();
            for (int t = 1; t < q; t++) {
                st = new StringTokenizer(br.readLine());
                String op = st.nextToken();

                if (op.equals("200")) {
                    int x = Integer.parseInt(st.nextToken());
                    town.add(new int[]{index, x});
                    index++;
                } else if (op.equals("300")) {
                    int targetIndex = Integer.parseInt(st.nextToken());
                    town.removeIf(twn -> twn[0] == targetIndex);
                } else if (op.equals("400")) {
                    int n = Integer.parseInt(st.nextToken());
                    int low = 0;
                    int high = town.get(town.size() - 1)[1] - town.get(0)[1];
                    int answer = high;

                    while (low <= high) {
                        int mid = (low + high) / 2;

                        // mid 길이로 n개 그룹으로 분할 가능?
                        if (sol(n, mid)) {
                            answer = mid;
                            high = mid - 1;
                        } else {
                            low = mid + 1;
                        }
                    }

                    sb.append(answer).append("\n");
                }
            }

            System.out.print(sb);
        }

        public static boolean sol(int n, int maxLen) {
            int group = 1;
            int cur = town.get(0)[1];

            for (int[] t : town) {
                int len = t[1] - cur;

                if (len > maxLen) {
                    group++;
                    cur = t[1];
                }
            }

            return group <= n;
        }
    }
}
