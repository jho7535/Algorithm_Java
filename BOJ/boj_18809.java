package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

/*
 * 땅, 호수. 초록 배양액, 빨간 배양액.
 * 배양액은 땅으로 퍼짐.
 * 정해진 배양액은 모두 사용.
 * 동시에 초 빨이 겹쳐야 꽃.
 *
 * 입 |   n, m : 2-50. g, r : 1-5
 *       n개 줄에 m개 0, 1, 2 : 호수, 배양액 가능, 불가
 * 출 |   꽃 최대 개수
 *
 * 배양액 놓을 곳 고르고
 * 가능한 순서 고르고
 * 확산 시작
 * 겹치면 꽃 카운트
 * */

public class boj_18809 {
    //public class Main {

    static int n, m;
    static int green, red;
    static int[][] map;
    static int max = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        green = Integer.parseInt(st.nextToken());
        red = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        select(0, 0, new ArrayList<>());

        System.out.println(max);
    }

    public static void select(int row, int col, List<int[]> list) {
        // 고른 개수가 g + r
        if (list.size() == green + red) {
            // 모든 조합 구하기
            combination(0, 0, new boolean[green + red], list);
            return;
        }


        for (int c = col; row < n && c < m; c++) {
            if (map[row][c] != 2) continue;

            list.add(new int[]{row, c});
            if (c + 1 == m) select(row + 1, 0, list);
            else select(row, c + 1, list);
            list.remove(list.size() - 1);
        }

        for (int r = row + 1; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (map[r][c] != 2) continue;

                list.add(new int[]{r, c});
                if (c + 1 == m) select(r + 1, 0, list);
                else select(r, c + 1, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void combination(int cnt, int index, boolean[] cur, List<int[]> list) {
        if (cnt == green) {
            int[][][] garden = new int[n][m][2];
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < cur.length; i++) {
                int[] co = list.get(i);
                int r = co[0];
                int c = co[1];
                int color = cur[i] ? 1 : 2;

                garden[r][c][0] = color;
                queue.add(new int[]{r, c, 0, color});
            }

            int flower = 0;
            Set<Integer> set = new HashSet<>();
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int curRow = current[0];
                int curCol = current[1];
                int curDist = current[2];
                int curColor = current[3];

                if (set.contains(curRow * 100 + curCol)) continue;

                for (int i = 0; i < 4; i++) {
                    int nr = curRow + dr[i];
                    int nc = curCol + dc[i];

                    // 범위. 호수 인지.
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (map[nr][nc] == 0) continue;

                    // 배양액 있는데 반대 색깔이면서 동일 시간 도달인지
                    if (garden[nr][nc][0] != 0) {
                        if (curColor == 1 && garden[nr][nc][0] == 2 && garden[nr][nc][1] == curDist + 1) {
                            flower++;
                            garden[nr][nc][0] = 3;

                            set.add(nr * 100 + nc);
                        } else if (curColor == 2 && garden[nr][nc][0] == 1 && garden[nr][nc][1] == curDist + 1) {
                            flower++;
                            garden[nr][nc][0] = 3;

                            set.add(nr * 100 + nc);
                        }

                        continue;
                    }

                    // 배양액 전파
                    garden[nr][nc][0] = curColor;
                    garden[nr][nc][1] = curDist + 1;
                    queue.add(new int[]{nr, nc, curDist + 1, curColor});
                }
            }

            max = Math.max(max, flower);

            return;
        }

        for (int i = index; i < cur.length; i++) {
            cur[i] = true;
            combination(cnt + 1, i + 1, cur, list);
            cur[i] = false;
        }
    }
}
