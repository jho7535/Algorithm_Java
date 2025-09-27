package nestnet_algorithm_2023_2.JeongHanUl.codetree;

import java.io.*;
import java.util.*;

public class 메두사와_전사들 {
    public class Main {

        static int n, m;
        static Stack<int[]> path = new Stack<>();
        static int[] medusa = new int[2];
        static List<Junsa> junsa = new ArrayList<>();
        static int[][][] parent;
        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, -1, 1};
        static int[] dr2 = {0, 0, -1, 1};
        static int[] dc2 = {-1, 1, 0, 0};
        static int[][] wr = {
                {-1, -1, -1},
                {1, 1, 1},
                {-1, 0, 1},
                {-1, 0, 1}
        };
        static int[][] wc = {
                {-1, 0, 1},
                {-1, 0, 1},
                {-1, -1, -1},
                {1, 1, 1}
        };
        static int[][] rr = {
                {-1, -1},
                {-1, -1},
                {-1, -1},

                {1, 1},
                {1, 1},
                {1, 1},

                {-1, 0},
                {0, 0},
                {0, 1},

                {-1, 0},
                {0, 0},
                {0, 1}
        };
        static int[][] cc = {
                {-1, 0},
                {0, 0},
                {0, 1},

                {-1, 0},
                {0, 0},
                {0, 1},

                {-1, -1},
                {-1, -1},
                {-1, -1},

                {1, 1},
                {1, 1},
                {1, 1}
        };

        static int dist = 0;
        static int dolJunsa = 0;
        static int gongJunsa = 0;

        static class Junsa {
            int r;
            int c;
            boolean stone;

            Junsa(int r, int c, boolean stone) {
                this.r = r;
                this.c = c;
                this.stone = stone;
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            boolean[][] map = new boolean[n][n];
            parent = new int[n][n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    parent[i][j][0] = -1;
                    parent[i][j][1] = -1;
                }
            }

            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                junsa.add(new Junsa(r, c, false));
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    map[i][j] = st.nextToken().equals("0");
                }
            }

            ///////////////////////////// 최단 경로 찾기
            shortPath(sr, sc, er, ec, map);

            if (parent[er][ec][0] == -1 || parent[er][ec][1] == -1) {
                System.out.println(-1);
                return;
            }

            // 스택에 최단 경로 넣기
            int nr = er;
            int nc = ec;
            while (!(parent[nr][nc][0] == sr && parent[nr][nc][1] == sc)) {
                int pr = parent[nr][nc][0];
                int pc = parent[nr][nc][1];

                path.push(new int[]{pr, pc});
                nr = pr;
                nc = pc;
            }

            StringBuffer sb = new StringBuffer();

            ///////////////////////////// 시뮬레이션 시작
            while (!path.isEmpty()) {
                dist = 0;
                dolJunsa = 0;
                gongJunsa = 0;

                ///////////////////////////// 메두사 이동
                int[] cur = path.pop();
                medusa[0] = cur[0];
                medusa[1] = cur[1];

                if (medusa[0] == er && medusa[1] == ec) break;

                // 전사 세팅. 메두사 자리에 있는 건 죽임
                int[][] junsaMap = new int[n][n];
                List<Junsa> update = new ArrayList<>();
                for (int i = junsa.size() - 1; i >= 0; i--) {
                    Junsa j = junsa.get(i);
                    if (j.r == medusa[0] && j.c == medusa[1]) continue;

                    update.add(j);
                    junsaMap[j.r][j.c]++;
                }
                junsa = update;

                for (Junsa j : junsa) {
                    j.stone = false;
                }

                ///////////////////////////// 메두사 시선
                int[][][] zone = new int[n][n][4];
                int direction = 0;
                int dolMax = 0;

                // 상하좌우 돌전사 만들기. 영역 체크
                for (int i = 0; i < 4; i++) {
                    int dolCnt = 0;

                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{medusa[0], medusa[1]});
                    while (!queue.isEmpty()) {
                        int[] curN = queue.poll();
                        int curRow = curN[0];
                        int curCol = curN[1];

                        // 전사 존재하면 가리기
                        if (junsaMap[curRow][curCol] > 0 && zone[curRow][curCol][i] != 2) {
                            dolCnt += junsaMap[curRow][curCol];
                            safeZone(curRow, curCol, zone, i);
                            continue;
                        }

                        for (int j = 0; j < 3; j++) {
                            int nowRow = curRow + wr[i][j];
                            int nowCol = curCol + wc[i][j];

                            if (nowRow < 0 || nowRow >= n || nowCol < 0 || nowCol >= n) continue;
                            if (zone[nowRow][nowCol][i] != 0) continue;

                            zone[nowRow][nowCol][i] = 1;
                            queue.add(new int[]{nowRow, nowCol});
                        }
                    }

                    if (dolCnt > dolMax) {
                        dolMax = dolCnt;
                        direction = i;
                    }
                }

                // 방향 정해짐. 돌로 된 전사 누적.
                dolJunsa = dolMax;
                // 시선에 있는 전사 돌로 만들어
                for (Junsa j : junsa) {
                    if (zone[j.r][j.c][direction] == 1) j.stone = true;
                }

                ///////////////////////////// 전사 이동
                List<Junsa> update2 = new ArrayList<>();
                for (int i = junsa.size() - 1; i >= 0; i--) {
                    Junsa j = junsa.get(i);
                    if (j.stone) {
                        update2.add(j);
                        continue;
                    }

                    int junDir;

                    // 첫번째 이동. 상하좌우. 방향 정하기
                    for (junDir = 0; junDir < 4; junDir++) {
                        int junRow = j.r + dr[junDir];
                        int junCol = j.c + dc[junDir];

                        if (junRow < 0 || junRow >= n || junCol < 0 || junCol >= n) continue;
                        if (zone[junRow][junCol][direction] == 1) continue;
                        if (manDist(junRow, junCol) < manDist(j.r, j.c)) break;
                    }

                    // 방향으로 이동
                    if (junDir < 4) {
                        j.r += dr[junDir];
                        j.c += dc[junDir];
                        dist++;
                    }

                    // 메두사 공격 하냐?
                    if (medusa[0] == j.r && medusa[1] == j.c) {
                        gongJunsa++;
                        continue;
                    }

                    // 두번째 이동. 좌우상하. 방향 정하기
                    for (junDir = 0; junDir < 4; junDir++) {
                        int junRow = j.r + dr2[junDir];
                        int junCol = j.c + dc2[junDir];

                        if (junRow < 0 || junRow >= n || junCol < 0 || junCol >= n) continue;
                        if (zone[junRow][junCol][direction] == 1) continue;
                        if (manDist(junRow, junCol) < manDist(j.r, j.c)) break;
                    }

                    // 방향으로 이동
                    if (junDir < 4) {
                        j.r += dr2[junDir];
                        j.c += dc2[junDir];
                        dist++;
                    }

                    // 메두사 공격 하냐?
                    if (medusa[0] == j.r && medusa[1] == j.c) {
                        gongJunsa++;
                        continue;
                    }

                    update2.add(j);
                }
                junsa = update2;

                sb.append(dist).append(" ").append(dolJunsa).append(" ").append(gongJunsa).append("\n");
            }
            sb.append(0);

            System.out.println(sb);
        }

        public static void shortPath(int r1, int c1, int r2, int c2, boolean[][] map) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{r1, c1});
            boolean[][] visited = new boolean[n][n];
            visited[r1][c1] = true;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];

                for (int i = 0; i < 4; i++) {
                    int nr = curRow + dr[i];
                    int nc = curCol + dc[i];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (visited[nr][nc]) continue;
                    if (!map[nr][nc]) continue;

                    visited[nr][nc] = true;
                    parent[nr][nc][0] = curRow;
                    parent[nr][nc][1] = curCol;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        public static void safeZone(int row, int col, int[][][] zone, int direction) {
            int d = -1;

            if (direction == 0) {
                if (medusa[1] - col > 0) d = 0;
                else if (medusa[1] == col) d = 1;
                else if (medusa[1] - col < 0) d = 2;
            } else if (direction == 1) {
                if (medusa[1] - col > 0) d = 3;
                else if (medusa[1] == col) d = 4;
                else if (medusa[1] - col < 0) d = 5;
            } else if (direction == 2) {
                if (medusa[0] - row > 0) d = 6;
                else if (medusa[0] == row) d = 7;
                else if (medusa[0] - row < 0) d = 8;
            } else {
                if (medusa[0] - row > 0) d = 9;
                else if (medusa[0] == row) d = 10;
                else if (medusa[0] - row < 0) d = 11;
            }

            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{row, col});
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];

                // System.out.println(curRow + " " + curCol);

                for (int i = 0; i < 2; i++) {
                    int nr = curRow + rr[d][i];
                    int nc = curCol + cc[d][i];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (zone[nr][nc][direction] == 2) continue;

                    zone[nr][nc][direction] = 2;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        public static int manDist(int row, int col) {
            return Math.abs(row - medusa[0]) + Math.abs(col - medusa[1]);
        }
    }

}
