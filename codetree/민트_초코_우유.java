package nestnet_algorithm_2023_2.JeongHanUl.codetree;

import java.io.*;
import java.util.*;

public class 민트_초코_우유 {
    public class Main {

        static int[][] favor;
        static int[][] believe;
        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, -1, 1};
        static List<Captain> captain;
        static int n;

        // Captain 클래스 정의
        static class Captain {
            int row, col, belief, kind;

            Captain(int row, int col, int belief, int kind) {
                this.row = row;
                this.col = col;
                this.belief = belief;
                this.kind = kind;
            }
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int test = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();

            favor = new int[n][n];
            believe = new int[n][n];

            // 신봉 초기화
            for (int i = 0; i < n; i++) {
                String str = br.readLine();

                for (int j = 0; j < n; j++) {
                    if (str.charAt(j) == 'T') favor[i][j] = 4;
                    else if (str.charAt(j) == 'C') favor[i][j] = 2;
                    else if (str.charAt(j) == 'M') favor[i][j] = 1;
                }
            }

            // 신앙심 초기화
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) believe[i][j] = Integer.parseInt(st.nextToken());
            }


            for (int te = 0; te < test; te++) {
                //////////////////////// 하루 시작

                //////////////////////// 아침

                //////////////////////// 점심
                // 대표 찾기.
                captain = new ArrayList<>();
                boolean[][] visited = new boolean[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (visited[i][j]) continue;

                        bfs(i, j, favor[i][j], visited);
                    }
                }

                //////////////////////// 저녁
                // 대표자 정렬
                captain.sort((a, b) -> {
                    if (a.kind == b.kind && a.belief == b.belief && a.row == b.row) return a.col - b.col;
                    else if (a.kind == b.kind && a.belief == b.belief) return a.row - b.row;
                    else if (a.kind == b.kind) return b.belief - a.belief;
                    else return a.kind - b.kind;
                });

                // HashSet 대신 boolean 배열 사용 (1순위 최적화)
                boolean[][] isDefend = new boolean[n][n];

                // 대표자 순회
                for (Captain cap : captain) {
                    // 방어 상태 체크
                    if (isDefend[cap.row][cap.col]) continue;

                    // 방향 결정
                    int dir = cap.belief % 4;
                    // 간절함 초기화
                    int num = cap.belief - 1;
                    // nr, nc 세팅
                    int nr = cap.row;
                    int nc = cap.col;
                    believe[nr][nc] = 1;

                    // 타겟 음식 미리 저장 (5순위 최적화)
                    int targetFood = favor[cap.row][cap.col];

                    nr += dr[dir];
                    nc += dc[dir];

                    // 전파 시작
                    while (nr >= 0 && nr < n && nc >= 0 && nc < n && num > 0) {
                        if (favor[nr][nc] == targetFood) {
                            nr += dr[dir];
                            nc += dc[dir];
                            continue;
                        }

                        // 강한 전파
                        if (num > believe[nr][nc]) {
                            // 동일한 음식 신봉
                            favor[nr][nc] = targetFood;

                            // 간절함 감소
                            num -= (believe[nr][nc] + 1);

                            // 전파 대상 신앙심 1 증가
                            believe[nr][nc]++;
                        }

                        // 약한 전파
                        else {
                            // 기본 음식에 관심 (3순위 최적화 - 인라인)
                            favor[nr][nc] |= targetFood;

                            // 전파 대상 신앙심 x만큼 증가
                            believe[nr][nc] += num;

                            // 간절함 0
                            num = 0;
                        }

                        // 방어 상태로 설정
                        isDefend[nr][nc] = true;

                        nr += dr[dir];
                        nc += dc[dir];
                    }
                }

                //////////////////////// 신앙심 출력
                // 배열 선언
                int[] output = new int[7];

                // 7개 케이스 분류. 합 누적
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < n; col++) {
                        if (favor[row][col] == 4) output[6] += believe[row][col];
                        else if (favor[row][col] == 2) output[5] += believe[row][col];
                        else if (favor[row][col] == 1) output[4] += believe[row][col];
                        else if (favor[row][col] == 3) output[3] += believe[row][col];
                        else if (favor[row][col] == 5) output[2] += believe[row][col];
                        else if (favor[row][col] == 6) output[1] += believe[row][col];
                        else if (favor[row][col] == 7) output[0] += believe[row][col];
                    }
                }

                for (int i = 0; i < 7; i++) sb.append(output[i]).append(" ");
                sb.append("\n");
            }

            System.out.println(sb);
        }

        static void bfs(int r, int c, int target, boolean[][] visited) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{r, c});
            visited[r][c] = true;
            int cnt = 0;
            int max = 0;
            int row = r, col = c;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];

                cnt++;

                // 대표 후보
                if (believe[curRow][curCol] > max ||
                        (believe[curRow][curCol] == max && curRow < row) ||
                        (believe[curRow][curCol] == max && curRow == row && curCol < col)) {
                    row = curRow;
                    col = curCol;
                    max = believe[curRow][curCol];
                }

                for (int i = 0; i < 4; i++) {
                    int nr = curRow + dr[i];
                    int nc = curCol + dc[i];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (visited[nr][nc]) continue;
                    if (favor[nr][nc] == target) {
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }

            believe[row][col] += cnt;

            // 대표 추가
            // 종류 판단
            int kind = 0;
            if (favor[row][col] == 1 || favor[row][col] == 2 || favor[row][col] == 4) kind = 1;
            else if (favor[row][col] == 3 || favor[row][col] == 5 || favor[row][col] == 6) kind = 2;
            else if (favor[row][col] == 7) kind = 3;

            captain.add(new Captain(row, col, believe[row][col], kind));
        }
    }
}
