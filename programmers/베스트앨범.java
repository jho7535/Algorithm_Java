package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 베스트앨범 {
    class Solution {
        public List<Integer> solution(String[] genres, int[] plays) {
            int n = genres.length;
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            }
            List<Song> list = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                list.add(new Song(entry.getKey(), entry.getValue()));
            }
            list.sort((a, b) -> b.play - a.play);

            Map<String, Integer> rank = new HashMap<>();
            int r = 0;
            for (Song s : list) {
                rank.put(s.genre, r++);
            }
            List<int[]> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add(new int[]{i, rank.get(genres[i]), plays[i]});
            }
            arr.sort((a, b) -> {
                if (a[1] == b[1]) {
                    if (a[2] == b[2]) return a[0] - b[0];
                    return b[2] - a[2];
                }
                return a[1]- b[1];
            });

            int cnt = 0;
            int gen = -1;
            List<Integer> answer = new ArrayList<>();
            for (int[] cur : arr) {
                if (gen != cur[1]) {
                    answer.add(cur[0]);
                    gen = cur[1];
                    cnt = 1;
                    continue;
                }

                if (cnt >= 2) continue;

                answer.add(cur[0]);
                cnt++;
            }

            return answer;
        }

        class Song {
            public String genre;
            public int play;

            Song(String s, int p) {
                this.genre = s;
                this.play = p;
            }
        }
    }
}
