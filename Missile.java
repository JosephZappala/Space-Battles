
public class Missile {
    public int sideLength;

    public Missile(int[][] popMap) {
        if (popMap.length < 5) {
            this.sideLength = 1;
        }
        else {
            this.sideLength = (popMap.length / 3);
        }
    }

    public void destroy(int[][] oppPopMap, int row, int column) {
        int len = oppPopMap.length;
        for (int i = row; i < row + this.sideLength; i++) {
            for (int j = column; j < column + this.sideLength; j++) {
                oppPopMap[i % len][j % len] = oppPopMap[i % len][j % len] > 0 ?
                        oppPopMap[i % len][j % len] - 1 : 0;
            }
        }

    }

}