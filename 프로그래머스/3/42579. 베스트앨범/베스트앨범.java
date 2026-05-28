import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        List<Integer> temp = new ArrayList<>();
        HashMap<String, Integer> genres_total = new HashMap<>();
        HashMap<String, HashMap<Integer, Integer>> genres_songs = new HashMap<>();
        for (int i=0; i<genres.length; i++){
            if (genres_total.containsKey(genres[i]))
                genres_total.put(genres[i], genres_total.get(genres[i]) + plays[i]);
            else
                genres_total.put(genres[i], plays[i]);
            if (!genres_songs.containsKey(genres[i]))
                genres_songs.put(genres[i], new HashMap<>());
            genres_songs.get(genres[i]).put(i, plays[i]);
        }
        String[] keys = genres_total.keySet().toArray(new String[0]);
        Arrays.sort(keys, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return genres_total.get(o2) - genres_total.get(o1);
            }
        });
        for (String key : keys) {
            Integer[] tmp = genres_songs.get(key).keySet().toArray(new Integer[0]);
            Arrays.sort(tmp, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (genres_songs.get(key).get(o2) == genres_songs.get(key).get(o1))
                        return o2 - o1;
                    return genres_songs.get(key).get(o2) - genres_songs.get(key).get(o1);
                }
            });
            for (int i=0; i<tmp.length; i++) {
                if (i >= 2) break;
                temp.add(tmp[i]);
            }
        }
        answer = temp.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}