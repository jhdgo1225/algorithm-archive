class Solution {
    public int[] solution(int brown, int yellow) {
        int[] yellowRectangle = new int[2];
        int rootYellow = (int)Math.sqrt(yellow);
        for (int i=1; i<=rootYellow; i++) {
            int tmpYellowWidth = yellow / i;
            int tmpYellowHeight = i;
            if (yellow == tmpYellowWidth * tmpYellowHeight && brown == (tmpYellowWidth + tmpYellowHeight) * 2 + 4) {
                yellowRectangle[0] = tmpYellowWidth + 2;
                yellowRectangle[1] = tmpYellowHeight + 2;
            }
        }
        return yellowRectangle;
    }
}