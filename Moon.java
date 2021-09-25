public class Moon {

    private double orbitDuration; //In reference to months on Earth
    private int[] coord;
    private int[][] planetPopMap;
    private int centerRow;
    private int centerCol;
    private String trajectory;
    private String direction;
    private double durability;

    public Moon() {
        this.trajectory = "dr";
        this.direction = "lr";
        this.orbitDuration = 1;
        this.coord = new int[2];
        this.coord[0] = 4;
        this.coord[1] = 6;
        this.durability = 1;
    }

    public Moon(double orbitDuration, int[][] planetPopMap,
                String trajectory,
                String direction, int coordRow, int coordCol, int centerRow, int centerCol, double durability) {
        this.trajectory = trajectory;
        this.direction = direction;
        this.orbitDuration = orbitDuration;
        this.coord = new int[2];
        this.coord[0] = coordRow;
        this.coord[1] = coordCol;
        this.durability = durability;
    }

    public double getOrbitDuration() {
        return this.orbitDuration;
    }
    public int[] getCenterRow() {
        return this.coord;
    }
    public String getDirection() {
        return this.direction;
    }
    public String getTrajectory() {
        return this.trajectory;
    }
    public int[] getCoord() {
        return this.coord;
    }

    public void setTrajectory(String trajectory) {
        this.trajectory = trajectory;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void setCenter(int i, int j) {
        this.centerRow = i;
        this.centerCol = j;
    }


}
