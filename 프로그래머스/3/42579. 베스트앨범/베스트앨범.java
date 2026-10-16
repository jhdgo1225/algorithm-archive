import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int n = plays.length;

        Map<String, Integer> totalByGenre = new HashMap<>();
        int[][] songs = new int[n][2];
        for (int i = 0; i < n; i++) {
            totalByGenre.put(genres[i], totalByGenre.getOrDefault(genres[i], 0) + plays[i]);
            songs[i][0] = plays[i];
            songs[i][1] = i;
        }

        Arrays.sort(songs, (a, b) -> {
            if (a[0] != b[0]) return Integer.compare(b[0], a[0]);
            return Integer.compare(a[1], b[1]);
        });

        Map<String, List<Integer>> songsByGenre = new HashMap<>();
        for (int[] song : songs) {
            String genre = genres[song[1]];
            List<Integer> picked = songsByGenre.get(genre);
            if (picked == null) {
                picked = new ArrayList<>();
                songsByGenre.put(genre, picked);
            }
            if (picked.size() < 2) picked.add(song[1]);
        }

        List<Map.Entry<String, Integer>> genreOrder = new ArrayList<>(totalByGenre.entrySet());
        genreOrder.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));

        int size = 0;
        for (Map.Entry<String, Integer> e : genreOrder) {
            size += songsByGenre.get(e.getKey()).size();
        }

        int[] answer = new int[size];
        int pos = 0;
        for (Map.Entry<String, Integer> e : genreOrder) {
            for (int idx : songsByGenre.get(e.getKey())) {
                answer[pos++] = idx;
            }
        }
        return answer;
    }
}