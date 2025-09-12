package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_17143 {
    //public class Main {

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]> sharks = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharks.add(new int[]{p, q, s, d, z});
        }

        int sum = 0;
        // 1-c까지 낚시꾼 이동
        for (int fishing = 1; fishing <= c; fishing++) {
            int index = -1;
            int min = r + 1;
            for (int j = 0; j < sharks.size(); j++) {
                // 열 확인
                if (sharks.get(j)[1] == fishing && sharks.get(j)[0] < min) {
                    min = sharks.get(j)[0];
                    index = j;
                }
            }

            // 상어 잡기
            if (index != -1) {
                int[] shark = sharks.get(index);

                sum += shark[4];
                sharks.remove(index);
            }

            // 상어 순차 이동
            Map<String, Integer> map = new HashMap<>();
            List<int[]> newSharks = new ArrayList<>();
            for (int[] shark : sharks) {
                int[] newPos = moveShark(shark[0], shark[1], shark[2], shark[3], r, c);
                shark[0] = newPos[0];
                shark[1] = newPos[1];
                shark[3] = newPos[2];

                String pos = newPos[0] + "," + newPos[1];

                // 중복 상어 체크
                if (map.containsKey(pos)) {
                    int existingIndex = map.get(pos);
                    // 더 큰 상어만 남김
                    if (shark[4] > newSharks.get(existingIndex)[4]) {
                        newSharks.set(existingIndex, shark);
                    }
                } else {
                    map.put(pos, newSharks.size());
                    newSharks.add(shark);
                }
            }

            sharks = newSharks;
        }

        System.out.println(sum);
    }

    static int[] moveShark(int row, int col, int speed, int direction, int r, int c) {
        // 1. 주기성을 이용해 불필요한 이동을 제거합니다.
        if (direction <= 2) { // 상하 이동
            int cycle = (r - 1) * 2;
            speed %= cycle;
        } else { // 좌우 이동
            int cycle = (c - 1) * 2;
            speed %= cycle;
        }

        // 2. 줄어든 speed만큼만 이동을 시뮬레이션합니다.
        for (int i = 0; i < speed; i++) {
            // 다음 위치가 벽에 부딪히는지 확인
            int nextRow = row + dr[direction];
            int nextCol = col + dc[direction];

            if (nextRow < 1 || nextRow > r || nextCol < 1 || nextCol > c) {
                // 방향 전환
                if (direction == 1) direction = 2;
                else if (direction == 2) direction = 1;
                else if (direction == 3) direction = 4;
                else if (direction == 4) direction = 3;
            }

            // 한 칸 이동
            row += dr[direction];
            col += dc[direction];
        }

        return new int[]{row, col, direction};
    }
}
