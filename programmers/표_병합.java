package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 표_병합 {
    class Solution {

        static int[] parent = new int[5051];
        static String[][] status = new String[51][51];

        public List<String> solution(String[] commands) {
            for (int i = 0; i <= 50; i++) {
                for (int j = 0; j <= 50; j++) status[i][j] = "";
            }
            for (int i = 0; i <= 5050; i++) {
                parent[i] = i;
            }

            List<String> answer = new ArrayList<>();

            // 명령 시작
            for (String command : commands) {
                String[] com = command.split(" ");

                // UPDATE
                if (com[0].equals("UPDATE")) {
                    // 1번 케이스
                    if (com.length == 4) {
                        int r = Integer.parseInt(com[1]);
                        int c = Integer.parseInt(com[2]);

                        int val = find(r * 100 + c);
                        int row = val / 100;
                        int col = val % 100;

                        status[row][col] = com[3];
                    }

                    // 2번 케이스
                    else if (com.length == 3) {
                        for (int i = 0; i <= 50; i++) {
                            for (int j = 0; j <= 50; j++) {
                                if (status[i][j].equals(com[1])) status[i][j] = com[2];
                            }
                        }
                    }
                }

                // MERGE
                else if (com[0].equals("MERGE")) {
                    int r1 = Integer.parseInt(com[1]);
                    int c1 = Integer.parseInt(com[2]);
                    int r2 = Integer.parseInt(com[3]);
                    int c2 = Integer.parseInt(com[4]);

                    union(r1 * 100 + c1, r2 * 100 + c2);
                }

                // UNMERGE
                else if (com[0].equals("UNMERGE")) {
                    int r = Integer.parseInt(com[1]);
                    int c = Integer.parseInt(com[2]);

                    int rootX = find(r * 100 + c);
                    String str = status[rootX / 100][rootX % 100];

                    List<Integer> list = new ArrayList<>();

                    for (int i = 0; i <= 50; i++) {
                        for (int j = 0; j <= 50; j++) {
                            if (rootX == find(i * 100 + j)) {
                                list.add(i * 100 + j);
                            }
                        }
                    }

                    for (int k : list) {
                        int row = k / 100;
                        int col = k % 100;

                        parent[k] = k;
                        status[row][col] = "";
                    }

                    status[r][c] = str;
                }

                // PRINT
                else if (com[0].equals("PRINT")) {
                    int r = Integer.parseInt(com[1]);
                    int c = Integer.parseInt(com[2]);

                    int rootX = find(r * 100 + c);

                    if (status[rootX / 100][rootX % 100].equals("")) answer.add("EMPTY");
                    else answer.add(status[rootX / 100][rootX % 100]);
                }
            }


            return answer;
        }

        public static int find(int x) {
            if (x == parent[x]) return x;
            else return parent[x] = find(parent[x]);
        }
        public static void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) return;

            int rowA = rootA / 100;
            int colA = rootA % 100;
            int rowB = rootB / 100;
            int colB = rootB % 100;

            parent[rootB] = rootA;

            if (!status[rowA][colA].equals("")) {
                status[rowB][colB] = "";
            } else {
                status[rowA][colA] = status[rowB][colB];
                status[rowB][colB] = "";
            }
        }
    }
}
