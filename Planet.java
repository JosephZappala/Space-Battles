public class Planet {
    private int size;
    private int popCount;
    private int[][] popMap;
    private Moon[] moons;

    public Planet() {
        this.size = 5;
        this.popCount = this.size * 5;
        this.popMap = new int[5][5];
        this.moons = new Moon[1];
    }

    public Planet(int n, int moons) {
        this.size = n;
        this.popCount = this.size * this.size;
        this.popMap = new int[size][size];
        this.moons = new Moon[moons];
        for (int i = 0; i < moons; i++){
            this.moons[i] = new Moon();
        }
    }

    public int getSize() {
        return size;
    }
    public int getPopCount() {
        return popCount;
    }
    public int[][] getPopMap() {
        return popMap;
    }
    public Moon[] getMoons() {
        return moons;
    }

    public void setSize(int n) {
        this.size = n;
    }
    public void setPopCount(int n) {
        this.popCount = n;
    }

    public void setPopMap(int[][] popMap) {
        this.popMap = popMap;
    }

    public void printMap() {

        for (int[] ints : this.popMap) {
            System.out.print("| ");
            for (int j = 0; j < this.popMap.length; j++) {
                if (ints[j] == 69)
                    System.out.print("M | ");
                else if (ints[j] == 70)
                    System.out.print("C | ");
                else
                    System.out.print(ints[j] + " | ");
            }
            System.out.println();
        }
    }

    public String mapToString() {
        String output = "";
        for (int[] ints : this.popMap) {
            output += "| ";
            for (int j = 0; j < this.popMap.length; j++) {
                if (ints[j] == 69)
                    output+= "M | ";
                else if (ints[j] == 70)
                    output += "C | ";
                else
                    output += ints[j] + " | ";
            }
            output += "@";
        }
        return output;
    }

    public void shuffle() {
        for (int i = 0; i < this.size; i++) {
            int rowTotal = popCount / 4;
            int count = rowTotal;
            for (int j = 0; j < this.size; j++) {
                if (count == 0)
                    break;
                if (j == this.size - 1 && count > 0) {
                    this.popMap[i][j] = count;
                    break;
                }
                int big = (int) (Math.random() * 10000);

                if (big % 2 == 0) {
                    if (big % rowTotal > count)
                        continue;
                    else {
                        this.popMap[i][j] = (big % rowTotal);
                        count -= (big % rowTotal);
                    }
                } else if (big % 2 == 0) 
                    this.popMap[i][j] = 0;
            }
        }
    }

    public void moonPath(String direction, int i , int j) {
        int centerRow = i;
        int centerCol = j;
        this.popMap[i][j] = 70;
        switch (direction) {
            case "du":
                while (i > 0 && j > 0) {
                    this.popMap[i - 1][j - 1] = 69;
                    i--;
                    j--;
                }
                i = centerRow;
                j = centerCol;
                while (i < this.popMap.length - 1 && j < this.popMap.length - 1) {
                    this.popMap[i + 1][j + 1] = 69;
                    i++;
                    j++;
                }
                break;

            case "dd":
                while (i < this.popMap.length - 1 && j < this.popMap.length - 1) {
                    this.popMap[i + 1][j + 1] = 69;
                    i++;
                    j++;
                }
                i = centerRow;
                j = centerCol;
                while (j > 0 && i > 0) {
                    this.popMap[i - 1][j - 1] = 69;
                    i--;
                    j--;
                }
                break;

            case "v":
                while (i > 0) {
                    this.popMap[i - 1][j] = 69;
                    i--;
                }
                i = centerRow;
                j = centerCol;
                while (i < this.popMap.length - 1) {
                    this.popMap[i + 1][j] = 69;
                    i++;
                }
                break;

            case "h":
                while (j > 0) {
                    this.popMap[i][j - 1] = 69;
                    j--;
                }
                i = centerRow;
                j = centerCol;
                while (j < this.popMap.length - 1) {
                    this.popMap[i][j + 1] = 69;
                    j++;
                }
                break;
        }
    }

    public void moveDU() {
        if (this.getMoons()[0].getDirection() == "LR") {
            if ((this.getMoons()[0].getCoord()[0] < this.popMap.length - 1 && this.getMoons()[0].getCoord()[1]
                    < this.popMap.length - 1) &&
                    (this.getMoons()[0].getCoord()[0] > 0 && this.getMoons()[0].getCoord()[1 ] > 0)) {
                this.getMoons()[0].getCoord()[0]--;
                this.getMoons()[0].getCoord()[1]++;
            } else {
                int i = this.getMoons()[0].getCoord()[0];
                int j = this.getMoons()[0].getCoord()[1];
                while (i >= 0 && j >= 0) {
                    i++;
                    j--;
                }
                this.getMoons()[0].getCoord()[0] = i;
                this.getMoons()[0].getCoord()[1] = j;
            }
        } else if (this.getMoons()[0].getDirection() == "RL")  {
            if ((this.getMoons()[0].getCoord()[0] < this.popMap.length - 1 && this.getMoons()[0].getCoord()[1]
                    < this.popMap.length - 1) &&
                    (this.getMoons()[0].getCoord()[0] > 0 && this.getMoons()[0].getCoord()[1] > 0)) {
                this.getMoons()[0].getCoord()[0]++;
                this.getMoons()[0].getCoord()[1]--;
            } else {
                int i = this.getMoons()[0].getCoord()[0];
                int j = this.getMoons()[0].getCoord()[1];
                while (i < this.popMap.length && j < this.popMap.length) {
                    i--;
                    j++;
                }
                this.getMoons()[0].getCoord()[0] = i;
                this.getMoons()[0].getCoord()[1] = j;
            }
        }
        this.popMap[this.getMoons()[0].getCoord()[0]][this.getMoons()[0].getCoord()[1]] = 69;
    }
    public void moveDD(int n) {
        if (this.getMoons()[n].getDirection() == "LR") {
            if ((this.getMoons()[n].getCoord()[0] < this.popMap.length - 1 && this.getMoons()[n].getCoord()[1] <
                    this.popMap.length - 1) &&
                    (this.getMoons()[n].getCoord()[0] > 0 && this.getMoons()[n].getCoord()[1] > 0)) {
                this.getMoons()[n].getCoord()[0]++;
                this.getMoons()[n].getCoord()[0]++;
            } else {
                int i = this.getMoons()[n].getCoord()[0];
                int j = this.getMoons()[n].getCoord()[1];
                while (i > 0 && j > 0) {
                    i--;
                    j--;
                }
                this.getMoons()[n].getCoord()[0] = i;
                this.getMoons()[n].getCoord()[1] = j;
            }
        } else if (this.getMoons()[n].getDirection() == "RL")  {
            if ((this.getMoons()[n].getCoord()[0] < this.popMap.length - 1 && this.getMoons()[n].getCoord()[1] <
                    this.popMap.length - 1) &&
                    (this.getMoons()[n].getCoord()[0] > 0 && this.getMoons()[n].getCoord()[1] > 0)) {
                this.getMoons()[n].getCoord()[0]--;
                this.getMoons()[n].getCoord()[1]--;
            } else {
                int i = this.getMoons()[n].getCoord()[0];
                int j = this.getMoons()[n].getCoord()[1];
                while (i < this.popMap.length && j < this.popMap.length) {
                    i++;
                    j++;
                }
                this.getMoons()[n].getCoord()[0] = i;
                this.getMoons()[n].getCoord()[1] = j;
            }
        }
        this.popMap[this.getMoons()[0].getCoord()[0]][this.getMoons()[0].getCoord()[1]] = 69;
    }


}
