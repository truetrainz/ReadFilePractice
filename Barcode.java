package main;

public class Barcode {
    private char direction;
    private int number;
    private int productNumber;

    public Barcode(char directionIn, int numberIn, int productNumberIn) {
        if (directionIn == 'i' || directionIn == 'I') {
            direction = 'I';
        } else if (directionIn == 'o' || directionIn == 'O') {
            direction = 'O';
        } else {
            direction = 'X';
        }
        number = numberIn;
        productNumber = productNumberIn;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char directionIn) {
        direction = directionIn;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int numberIn) {
        number = numberIn;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumberIn) {
        productNumber = productNumberIn;
    }

    public String toString() {
        return "dir -> " + direction + " number -> " + number + " product number -> " + productNumber;
    }
}
