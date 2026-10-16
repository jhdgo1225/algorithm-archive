import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> totalPlayPerGenre = new HashMap<>();
        HashMap<String, List<Integer>> songsPerGenre = new HashMap<>();
        int[][] playsWithIndex = new int[plays.length][2];
        for (int i=0; i<genres.length; i++) {
            playsWithIndex[i][0] = plays[i];
            playsWithIndex[i][1] = i;
            totalPlayPerGenre.put(genres[i], totalPlayPerGenre.getOrDefault(genres[i], 0) + plays[i]);
            if (!songsPerGenre.containsKey(genres[i])) songsPerGenre.put(genres[i], new LinkedList<>());
        }
        Arrays.sort(playsWithIndex, (a, b) -> b[0] - a[0]);
        int cnt = 0;
        for (int[] index : playsWithIndex) {
            String genre = genres[index[1]];
            List<Integer> pointer = songsPerGenre.get(genre);
            if (pointer.size() < 2) {
                pointer.add(index[1]);
                cnt++;
            }
        }
        List<Map.Entry<String, Integer>> tmp = new LinkedList<>(totalPlayPerGenre.entrySet());
        Collections.sort(tmp, (a, b) -> b.getValue().compareTo(a.getValue()));
        int[] answer = new int[cnt];
        int i=0;
        for (Map.Entry<String, Integer> entry : tmp) {
            List<Integer> good = songsPerGenre.get(entry.getKey());
            for (int j=0; j<good.size(); j++) {
                answer[i++] = good.get(j);
            }
        }
        return answer;
    }
}