import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 총 재생 횟수를 저장하는 Map
        // key: 장르명
        // value: 해당 장르에 속한 노래들의 총 재생 횟수
        Map<String, Integer> genreTotalPlays = new HashMap<>();

        // 장르별 노래 인덱스 목록을 저장하는 Map
        // key: 장르명
        // value: 해당 장르에 속한 노래들의 인덱스 리스트
        Map<String, List<Integer>> genreSongs = new HashMap<>();

        // 모든 노래를 순회하면서 장르별 총 재생 횟수와 노래 인덱스를 저장한다.
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];

            // 현재 장르의 기존 총 재생 횟수에 현재 노래의 재생 횟수를 더한다.
            // 장르가 처음 등장한 경우 기본값 0을 사용한다.
            genreTotalPlays.put(
                genre,
                genreTotalPlays.getOrDefault(genre, 0) + plays[i]
            );

            // 현재 장르에 해당하는 노래 인덱스 리스트가 없으면 새로 생성한다.
            // 그 후 현재 노래의 인덱스 i를 리스트에 추가한다.
            genreSongs
                .computeIfAbsent(genre, key -> new ArrayList<>())
                .add(i);
        }

        // 장르 목록을 정렬하기 위해 genreTotalPlays의 key들을 List로 변환한다.
        List<String> sortedGenres = new ArrayList<>(genreTotalPlays.keySet());

        // 장르별 총 재생 횟수를 기준으로 내림차순 정렬한다.
        // 즉, 총 재생 횟수가 많은 장르가 먼저 오도록 한다.
        sortedGenres.sort((g1, g2) ->
            genreTotalPlays.get(g2) - genreTotalPlays.get(g1)
        );

        // 베스트 앨범에 들어갈 노래의 인덱스를 저장할 리스트
        List<Integer> bestAlbum = new ArrayList<>();

        // 총 재생 횟수가 많은 장르부터 순서대로 처리한다.
        for (String genre : sortedGenres) {
            // 현재 장르에 속한 노래 인덱스 목록을 가져온다.
            List<Integer> songs = genreSongs.get(genre);

            // 현재 장르의 노래들을 정렬한다.
            // 1순위: 재생 횟수가 많은 노래
            // 2순위: 재생 횟수가 같다면 고유 번호, 즉 인덱스가 낮은 노래
            songs.sort((s1, s2) -> {
                if (plays[s1] == plays[s2]) {
                    return s1 - s2;
                }

                return plays[s2] - plays[s1];
            });

            // 현재 장르에서 최대 2곡까지만 베스트 앨범에 추가한다.
            // 노래가 1곡뿐인 장르는 1곡만 추가한다.
            for (int i = 0; i < Math.min(2, songs.size()); i++) {
                bestAlbum.add(songs.get(i));
            }
        }

        // List<Integer> 형태의 결과를 int[] 배열로 변환하여 반환한다.
        return bestAlbum.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}