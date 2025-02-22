package nestnet_algorithm_2023_2.JeongHanUl.programmers;

public class 카운트_다운 {
    class Solution {
        public int[] solution(int target) {
            Element[] dp = new Element[100001];
            for (int i = 1; i < 100001; i++) {
                dp[i] = new Element(Integer.MAX_VALUE, 0);
            }

            for (int j = 1; j <= 20; j++) {
                dp[j].cnt = 1;
                dp[j].sum = 1;
            }
            for (int j = 11; j <= 20; j++) {
                int index = j * 2;

                dp[index].cnt = 1;
            }
            for (int j = 7; j <= 20; j++) {
                int index = j * 3;

                dp[index].cnt = 1;
            }
            dp[50].cnt = 1;
            dp[50].sum = 1;

            for (int i = 1; i < 100001; i++) {
                for (int j = 1; j <= 20; j++) {
                    if (i + j > 100000) continue;

                    if (dp[i + j].cnt > dp[i].cnt + 1 || (dp[i + j].cnt == dp[i].cnt + 1 && dp[i + j].sum < dp[i].sum + 1)) {
                        dp[i + j].cnt = dp[i].cnt + 1;
                        dp[i + j].sum = dp[i].sum + 1;
                    }
                }

                for (int j = 1; j <= 20; j++) {
                    int index = j * 2;

                    if (i + index > 100000) continue;

                    if (dp[i + index].cnt > dp[i].cnt + 1 || (dp[i + index].cnt == dp[i].cnt + 1 && dp[i + index].sum < dp[i].sum)) {
                        dp[i + index].cnt = dp[i].cnt + 1;
                        dp[i + index].sum = dp[i].sum;
                    }
                }

                for (int j = 1; j <= 20; j++) {
                    int index = j * 3;

                    if (i + index > 100000) continue;

                    if (dp[i + index].cnt > dp[i].cnt + 1 || (dp[i + index].cnt == dp[i].cnt + 1 && dp[i + index].sum < dp[i].sum)) {
                        dp[i + index].cnt = dp[i].cnt + 1;
                        dp[i + index].sum = dp[i].sum;
                    }
                }

                if (i + 50 > 100000) continue;

                if (dp[i + 50].cnt > dp[i].cnt + 1 || (dp[i + 50].cnt == dp[i].cnt + 1 && dp[i + 50].sum < dp[i].sum + 1)) {
                    dp[i + 50].cnt = dp[i].cnt + 1;
                    dp[i + 50].sum = dp[i].sum + 1;
                }
            }

            int[] answer = {dp[target].cnt, dp[target].sum};

            return answer;
        }

        class Element {
            public int cnt;
            public int sum;

            Element(int c, int s) {
                this.cnt = c;
                this.sum = s;
            }
        }
    }
}
