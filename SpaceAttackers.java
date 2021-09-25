
public class SpaceAttackers {
    private int turn = 1;
    private final int size = 4;
    private Planet planet1, planet2;

    public SpaceAttackers() {
        planet1 = new Planet(size, 1);
        planet1.shuffle();
        planet2 = new Planet(size, 1);
        planet2.shuffle();
    }

    public String turn(int player, int x, int y) {
        String output = "@";
        int[][] tempPlanet;

        if (player == turn) {
            if (0 <= x && x < size && 0 <= y && y < size) {
                output += "You fired at " + x + ", " + y +".@@";


                if (player == 1) {
                    tempPlanet = planet2.getPopMap();
                    Missile m1 = new Missile(tempPlanet);
                    m1.destroy(tempPlanet, x, y);
                    planet2.setPopMap(tempPlanet);

                    output += "Your Planet: @";
                    output += planet1.mapToString();
                    turn = 2;
                }
                else if (player == 2) {
                    tempPlanet = planet1.getPopMap();
                    Missile m1 = new Missile(tempPlanet);
                    m1.destroy(tempPlanet, x, y);
                    planet1.setPopMap(tempPlanet);

                    output += "Your Planet: @";
                    output += planet2.mapToString();
                    turn = 1;
                }
            }
            else {
                if (player == 1) {
                    player = 2;
                }
                if (player == 2) {
                    player = 1;
                }
                return "You missed the whole planet dummy!@";
            }
        }
        else {
            return "Hey it's not your turn!@";
        }
        return output;
    }

}
