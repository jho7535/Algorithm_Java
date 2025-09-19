package nestnet_algorithm_2023_2.JeongHanUl.BOJ;

import java.io.*;
import java.util.*;

public class boj_5373 {
    //public class Main {

    static char[][] face;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            face = new char[][]{
//                    {'0', '1', '2', '3', '4', '5', '6', '7', '8'},
//                    {'0', '1', '2', '3', '4', '5', '6', '7', '8'},
//                    {'0', '1', '2', '3', '4', '5', '6', '7', '8'},
//                    {'0', '1', '2', '3', '4', '5', '6', '7', '8'},
//                    {'0', '1', '2', '3', '4', '5', '6', '7', '8'},
//                    {'0', '1', '2', '3', '4', '5', '6', '7', '8'}

                    {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
                    {'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y'},
                    {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'},
                    {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'},
                    {'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g'},
                    {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}
            };

            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                String str = st.nextToken();
                if (str.charAt(0) == 'U') u(str.charAt(1));
                else if (str.charAt(0) == 'D') d(str.charAt(1));
                else if (str.charAt(0) == 'F') f(str.charAt(1));
                else if (str.charAt(0) == 'B') b(str.charAt(1));
                else if (str.charAt(0) == 'L') l(str.charAt(1));
                else if (str.charAt(0) == 'R') r(str.charAt(1));
            }

            sb.append(face[0][0]).append(face[0][1]).append(face[0][2]).append("\n");
            sb.append(face[0][3]).append(face[0][4]).append(face[0][5]).append("\n");
            sb.append(face[0][6]).append(face[0][7]).append(face[0][8]).append("\n");
        }

        System.out.println(sb);
    }

    static void u(char dir) {
        turn(0, dir);

        char[] temp = {face[3][0], face[3][1], face[3][2]};

        if (dir == '+') {
            face[3][0] = face[4][0];
            face[3][1] = face[4][1];
            face[3][2] = face[4][2];

            face[4][0] = face[2][0];
            face[4][1] = face[2][1];
            face[4][2] = face[2][2];

            face[2][0] = face[5][0];
            face[2][1] = face[5][1];
            face[2][2] = face[5][2];

            face[5][0] = temp[0];
            face[5][1] = temp[1];
            face[5][2] = temp[2];
        } else {
            face[3][0] = face[5][0];
            face[3][1] = face[5][1];
            face[3][2] = face[5][2];

            face[5][0] = face[2][0];
            face[5][1] = face[2][1];
            face[5][2] = face[2][2];

            face[2][0] = face[4][0];
            face[2][1] = face[4][1];
            face[2][2] = face[4][2];

            face[4][0] = temp[0];
            face[4][1] = temp[1];
            face[4][2] = temp[2];
        }
    }

    static void d(char dir) {
        turn(1, dir);

        char[] temp = {face[3][6], face[3][7], face[3][8]};

        if (dir == '+') {
            face[3][6] = face[5][6];
            face[3][7] = face[5][7];
            face[3][8] = face[5][8];

            face[5][6] = face[2][6];
            face[5][7] = face[2][7];
            face[5][8] = face[2][8];

            face[2][6] = face[4][6];
            face[2][7] = face[4][7];
            face[2][8] = face[4][8];

            face[4][6] = temp[0];
            face[4][7] = temp[1];
            face[4][8] = temp[2];
        } else {
            face[3][6] = face[4][6];
            face[3][7] = face[4][7];
            face[3][8] = face[4][8];

            face[4][6] = face[2][6];
            face[4][7] = face[2][7];
            face[4][8] = face[2][8];

            face[2][6] = face[5][6];
            face[2][7] = face[5][7];
            face[2][8] = face[5][8];

            face[5][6] = temp[0];
            face[5][7] = temp[1];
            face[5][8] = temp[2];
        }
    }

    static void f(char dir) {
        turn(2, dir);

        char[] temp = {face[0][6], face[0][7], face[0][8]};

        if (dir == '+') {
            face[0][6] = face[4][8];
            face[0][7] = face[4][5];
            face[0][8] = face[4][2];

            face[4][8] = face[1][6];
            face[4][5] = face[1][7];
            face[4][2] = face[1][8];

            face[1][6] = face[5][0];
            face[1][7] = face[5][3];
            face[1][8] = face[5][6];

            face[5][0] = temp[0];
            face[5][3] = temp[1];
            face[5][6] = temp[2];
        } else {
            face[0][6] = face[5][0];
            face[0][7] = face[5][3];
            face[0][8] = face[5][6];

            face[5][0] = face[1][6];
            face[5][3] = face[1][7];
            face[5][6] = face[1][8];

            face[1][6] = face[4][8];
            face[1][7] = face[4][5];
            face[1][8] = face[4][2];

            face[4][8] = temp[0];
            face[4][5] = temp[1];
            face[4][2] = temp[2];
        }
    }

    static void b(char dir) {
        turn(3, dir);

        char[] temp = {face[0][0], face[0][1], face[0][2]};

        if (dir == '+') {
            face[0][0] = face[5][2];
            face[0][1] = face[5][5];
            face[0][2] = face[5][8];

            face[5][2] = face[1][0];
            face[5][5] = face[1][1];
            face[5][8] = face[1][2];

            face[1][0] = face[4][6];
            face[1][1] = face[4][3];
            face[1][2] = face[4][0];

            face[4][6] = temp[0];
            face[4][3] = temp[1];
            face[4][0] = temp[2];
        } else {
            face[0][0] = face[4][6];
            face[0][1] = face[4][3];
            face[0][2] = face[4][0];

            face[4][6] = face[1][0];
            face[4][3] = face[1][1];
            face[4][0] = face[1][2];

            face[1][0] = face[5][2];
            face[1][1] = face[5][5];
            face[1][2] = face[5][8];

            face[5][2] = temp[0];
            face[5][5] = temp[1];
            face[5][8] = temp[2];
        }
    }

    static void l(char dir) {
        turn(4, dir);

        char[] temp = {face[0][0], face[0][3], face[0][6]};

        if (dir == '+') {
            face[0][0] = face[3][8];
            face[0][3] = face[3][5];
            face[0][6] = face[3][2];

            face[3][8] = face[1][8];
            face[3][5] = face[1][5];
            face[3][2] = face[1][2];

            face[1][8] = face[2][0];
            face[1][5] = face[2][3];
            face[1][2] = face[2][6];

            face[2][0] = temp[0];
            face[2][3] = temp[1];
            face[2][6] = temp[2];
        } else {
            face[0][0] = face[2][0];
            face[0][3] = face[2][3];
            face[0][6] = face[2][6];

            face[2][0] = face[1][8];
            face[2][3] = face[1][5];
            face[2][6] = face[1][2];

            face[1][8] = face[3][8];
            face[1][5] = face[3][5];
            face[1][2] = face[3][2];

            face[3][8] = temp[0];
            face[3][5] = temp[1];
            face[3][2] = temp[2];
        }
    }

    static void r(char dir) {
        turn(5, dir);

        char[] temp = {face[0][2], face[0][5], face[0][8]};

        if (dir == '+') {
            face[0][2] = face[2][2];
            face[0][5] = face[2][5];
            face[0][8] = face[2][8];

            face[2][2] = face[1][6];
            face[2][5] = face[1][3];
            face[2][8] = face[1][0];

            face[1][6] = face[3][6];
            face[1][3] = face[3][3];
            face[1][0] = face[3][0];

            face[3][6] = temp[0];
            face[3][3] = temp[1];
            face[3][0] = temp[2];
        } else {
            face[0][2] = face[3][6];
            face[0][5] = face[3][3];
            face[0][8] = face[3][0];

            face[3][6] = face[1][6];
            face[3][3] = face[1][3];
            face[3][0] = face[1][0];

            face[1][6] = face[2][2];
            face[1][3] = face[2][5];
            face[1][0] = face[2][8];

            face[2][2] = temp[0];
            face[2][5] = temp[1];
            face[2][8] = temp[2];
        }
    }

    static void turn(int num, char dir) {
        char[] temp = Arrays.copyOf(face[num], 9);

        if (dir == '-') {
            face[num][0] = temp[2];
            face[num][1] = temp[5];
            face[num][2] = temp[8];
            face[num][3] = temp[1];
            face[num][5] = temp[7];
            face[num][6] = temp[0];
            face[num][7] = temp[3];
            face[num][8] = temp[6];
        } else {
            face[num][0] = temp[6];
            face[num][1] = temp[3];
            face[num][2] = temp[0];
            face[num][3] = temp[7];
            face[num][5] = temp[1];
            face[num][6] = temp[8];
            face[num][7] = temp[5];
            face[num][8] = temp[2];
        }
    }
}
