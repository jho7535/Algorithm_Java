package nestnet_algorithm_2023_2.JeongHanUl.programmers;

import java.io.*;
import java.util.*;

public class 오픈채팅방 {
    class Solution {
        public List<String> solution(String[] record) {
            // 입력 받으면서 map 업데이트
            Map<String, String> map = new HashMap<>();
            for (String str : record) {
                String[] sp = str.split(" ");

                if (sp[0].equals("Enter")) {
                    map.put(sp[1], sp[2]);
                } else if (sp[0].equals("Change")) {
                    map.put(sp[1], sp[2]);
                }
            }

            List<String> answer = new ArrayList<>();
            // 출력 모으기
            for (String str : record) {
                String[] sp = str.split(" ");
                StringBuilder sb = new StringBuilder();

                if (sp[0].equals("Enter")) {
                    sb.append(map.get(sp[1])).append("님이 들어왔습니다.");
                } else if (sp[0].equals("Leave")) {
                    sb.append(map.get(sp[1])).append("님이 나갔습니다.");
                } else continue;

                answer.add(sb.toString());
            }

            return answer;
        }
    }
}
