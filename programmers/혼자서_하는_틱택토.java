package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 혼자서_하는_틱택토 {
    class Solution {
        public int solution(String[] board) {
            int cntO = 0, cntX = 0;
            boolean winO = false, winX = false;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i].charAt(j) == 'O') cntO++;
                    else if (board[i].charAt(j) == 'X') cntX++;
                }
            }

            // O 승리 판단
            winO = win('O', board);

            // X 승리 판단
            winX = win('X', board);

            int answer = 1;
            // 불가능 판단
            if (!(cntO == cntX || cntO == cntX + 1)) answer = 0;
            if (winO && winX) answer = 0;
            if (winO && cntO != cntX + 1) answer = 0;
            if (winX && cntO != cntX) answer = 0;

            return answer;
        }

        public boolean win(char c, String[] board) {
            if (board[0].charAt(0) == c && board[0].charAt(1) == c && board[0].charAt(2) == c) return true;
            if (board[1].charAt(0) == c && board[1].charAt(1) == c && board[1].charAt(2) == c) return true;
            if (board[2].charAt(0) == c && board[2].charAt(1) == c && board[2].charAt(2) == c) return true;

            if (board[0].charAt(0) == c && board[1].charAt(0) == c && board[2].charAt(0) == c) return true;
            if (board[0].charAt(1) == c && board[1].charAt(1) == c && board[2].charAt(1) == c) return true;
            if (board[0].charAt(2) == c && board[1].charAt(2) == c && board[2].charAt(2) == c) return true;

            if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) return true;
            if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) return true;

            return false;
        }
    }
}
