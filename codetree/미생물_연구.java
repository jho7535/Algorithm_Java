package nestnet_algorithm_2023_2.JeongHanUl.codetree;

import java.io.*;
import java.util.*;

public class 미생물_연구 {
    public class Main {

        static int n;
        static int[][] map;
        static int[] dr = {-1, 1, 0, 0};
        static int[] dc = {0, 0, -1, 1};

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            StringBuilder sb = new StringBuilder();

            for (int t = 1; t <= T; t++) {
                st = new StringTokenizer(br.readLine());
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());

                /////////////////// 투입
                // 덮어 쓰기
                for (int i = r1; i < r2; i++) {
                    for (int j = c1; j < c2; j++) {
                        map[i][j] = t;
                    }
                }

                // 영역 2개인 것 체크
                Set<Integer> checkSet = new HashSet<>();
                Set<Integer> removeSet = new HashSet<>();
                boolean[][] visited = new boolean[n][n];
                List<int[]> list = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] != 0 && !visited[i][j]) {
                            int target = map[i][j];
                            int w = bfs(i, j, visited, target);

                            if (checkSet.contains(target)) {
                                list.removeIf(a -> a[0] == target);
                                removeSet.add(target);
                            }
                            else {
                                checkSet.add(target);
                                list.add(new int[]{target, w});
                            }
                        }
                    }
                }

                // 영역 2개인 것 삭제
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (removeSet.contains(map[i][j])) {
                            map[i][j] = 0;
                        }
                    }
                }

                /////////////////// 이동
                // 넓이 우선 순위 정렬
                list.sort((a, b) -> {
                    if (a[1] == b[1]) return a[0] - b[0];
                    else return b[1] - a[1];
                });

                // 좌하단 기준점 잡아서
                Map<Integer, List<int[]>> hashMap = new HashMap<>();
                for (int[] mi : list) {
                    int minR = n;
                    int minC = n;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (map[i][j] == mi[0]) {
                                minR = Math.min(minR, i);
                                minC = Math.min(minC, j);
                            }
                        }
                    }

                    // 기준으로 부터 얼마나 떨어짐?
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (map[i][j] == mi[0]) {
                                List<int[]> temp = hashMap.getOrDefault(mi[0], new ArrayList<>());
                                temp.add(new int[]{i - minR, j - minC});
                                hashMap.put(mi[0], temp);
                            }
                        }
                    }
                }

                // 새로운 곳으로 이동
                int[][] newMap = new int[n][n];
                for (int[] mi : list) {
                    // 모든 자리 돌면서 가능한지 확인
                    boolean flag = false;
                    for (int r = 0; r < n; r++) {
                        for (int c = 0; c < n; c++) {
                            // 좌표 확인
                            int cnt = 0;
                            for (int[] coor : hashMap.get(mi[0])) {
                                int nr = r + coor[0];
                                int nc = c + coor[1];

                                if (nr < 0 || nr >= n || nc < 0 || nc >= n) break;
                                if (newMap[nr][nc] != 0) break;

                                cnt++;
                            }

                            // 세팅 가능
                            if (cnt == hashMap.get(mi[0]).size()) {
                                for (int[] coor : hashMap.get(mi[0])) {
                                    int nr = r + coor[0];
                                    int nc = c + coor[1];

                                    newMap[nr][nc] = mi[0];
                                }

                                flag = true;
                            }

                            if (flag) break;
                        }

                        if (flag) break;
                    }
                }

                for (int i = 0; i < n; i++) map[i] = Arrays.copyOf(newMap[i], n);

                /////////////////// 기록
                int sum = 0;
                Set<Integer> set = new HashSet<>();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < 4; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];

                            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                            if (map[i][j] == 0 || map[nr][nc] == 0) continue;
                            if (map[i][j] == map[nr][nc]) continue;
                            if (set.contains(map[i][j] * 100 + map[nr][nc]) || set.contains(map[nr][nc] * 100 + map[i][j])) continue;

                            sum += hashMap.get(map[i][j]).size() * hashMap.get(map[nr][nc]).size();
                            set.add(map[i][j] * 100 + map[nr][nc]);
                            set.add(map[nr][nc] * 100 + map[i][j]);
                        }
                    }
                }

                sb.append(sum).append("\n");
            }

            System.out.println(sb);
        }

        static int bfs(int r, int c, boolean[][] visited, int target) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{r, c});
            visited[r][c] = true;
            int w = 0;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];

                w++;

                for (int i = 0; i < 4; i++) {
                    int nr = curRow + dr[i];
                    int nc = curCol + dc[i];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if (visited[nr][nc]) continue;
                    if (map[nr][nc] != target) continue;

                    queue.add(new int[]{nr ,nc});
                    visited[nr][nc] = true;
                }
            }

            return w;
        }
    }
}
