package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class n1_카드게임 {
    class Solution {
        public int solution(int coin, int[] cards) {
            int n = cards.length;

            int low = 0;
            int high = (n - n / 3) / 2;
            int answer = 0;
            while (low <= high) {
                int mid = (low + high) / 2;
                // System.out.println("mid: " + mid);

                boolean flag = true;

                // 소유하고 있는 카드 set
                Set<Integer> having = new HashSet<>();
                int cnt = 0;
                for (int i = 0; i < n / 3; i++) {
                    if (having.contains((n + 1) - cards[i])) {
                        cnt++;
                        having.remove((n + 1) - cards[i]);
                    }
                    else having.add(cards[i]);
                }

                // 시뮬 돌리기
                int curCoin = coin;
                Set<Integer> wait = new HashSet<>();
                for (int i = n / 3; i < n / 3 + 2 * mid; i += 2) {
                    wait.add(cards[i]);
                    wait.add(cards[i + 1]);

                    if (cnt > 0) {
                        cnt--;
                        continue;
                    }

                    // 1원 쓰기
                    if (curCoin >= 1) {
                        boolean able = false;
                        int havingCard = -1;
                        int waitCard = -1;

                        for (int k : wait) {
                            if (having.contains((n + 1) - k)) {
                                able = true;
                                curCoin--;
                                havingCard = (n + 1) - k;
                                waitCard = k;
                                break;
                            }
                        }

                        if (able) {
                            having.remove(havingCard);
                            wait.remove(waitCard);
                            continue;
                        }
                    }

                    // 2원 쓰기
                    if (curCoin >= 2) {
                        boolean able = false;
                        int waitCard1 = -1;
                        int waitCard2 = -1;

                        for (int k : wait) {
                            if (wait.contains((n + 1) - k)) {
                                able = true;
                                curCoin -= 2;
                                waitCard1 = (n + 1) - k;
                                waitCard2 = k;
                                break;
                            }
                        }

                        if (able) {
                            wait.remove(waitCard1);
                            wait.remove(waitCard2);
                            continue;
                        }
                    }

                    flag = false;
                    break;
                }

                if (flag) {
                    answer = mid;
                    low = mid + 1;
                } else high = mid - 1;
            }

            return answer + 1;
        }
    }
}
