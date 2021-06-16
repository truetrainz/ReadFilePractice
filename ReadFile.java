package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    private String textFile;
    Barcode barcode;

    public ReadFile(String textFileIn, Barcode barcodeIn) {
        textFile = textFileIn;
        barcode = barcodeIn;
    }

    public void readFile(String textFile, Barcode barcode) {
        System.out.println("readFile -> barcode number -> " + barcode.getNumber());
        File file = new File(textFile);
        try {
            Scanner scanner = new Scanner(file);
            boolean found = false;
            long position = -1;
            while(scanner.hasNextLine() && !false) {
                String nextLine = scanner.nextLine();
                int length = nextLine.length();
                position += length;
                if (nextLine.contains(":")) {
                    System.out.println(nextLine.trim());
                    if (nextLine.contains(" ")) {
                        String working = nextLine;
                        while (working.contains(" ")) {
                            int index = working.indexOf(" ");
                            working = working.substring(index + 1);
                            System.out.println(working);
                        }
                        int fileProductNumber = getFileProductNumber(working);
                        System.out.println("readFile -> fileProductNumber -> " + fileProductNumber);
                        if (barcode.getProductNumber() == fileProductNumber) {
                            found = true;
                            int inventoryNumber = getInventoryNumber(working);
                            if (inventoryNumber != -1000000) {
                                inventoryNumber = updateInventoryNumber(inventoryNumber, barcode);
                            } else {
                                System.out.println("There was not an inventory number associated with the product number");
                            }
                        }
                    } else {
                        int fileProductNumber = getFileProductNumber(nextLine.trim());
                        System.out.println("readFile -> fileProductNumber -> " + fileProductNumber);
                        if (barcode.getProductNumber() == fileProductNumber) {
                            found = true;
                            int inventoryAmount = getInventoryNumber(nextLine.trim());
                            if (inventoryAmount != -1000000) {
                                inventoryAmount = updateInventoryNumber(inventoryAmount, barcode);
                            } else {
                                System.out.println("There was not an inventory number associated with the product number");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was an issue with the scanner");
        }
    }

    public boolean isDigit(char character) {
        if (Character.isDigit(character)) {
            return true;
        }
        return false;
    }

    public int getFileProductNumber(String string) {
        char working;
        String number = "";
        for (int i = 0; i < string.length(); i++) {
            working = string.charAt(i);
            if (Character.isDigit(working)) {
                number += new Character(working).toString();
            } else {
                break;
            }
        }
        if (number.length() <= 0) {
            return -1;
        } else {
            return new Integer(number).intValue();
        }
    }

    public int getInventoryNumber(String string) {
        String number = "";
        boolean colon = false;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ':') {
                colon = true;
            } else if (colon == true && Character.isDigit(string.charAt(i))) {
                Character myCharacter = new Character(string.charAt(i));
                number += myCharacter.toString();
            }
         }
        if (number.length() <= 0) {
            return -1000000;
        } else {
            return new Integer(number).intValue();
        }
    }

    public int updateInventoryNumber(int numberIn, Barcode barcode) {
        if (barcode.getDirection() == 'I') {
            return numberIn + barcode.getNumber();
        } else if (barcode.getDirection() == 'O') {
            return numberIn - barcode.getNumber();
        } else {
            System.out.println("The direction of the barcode is not in or out so the numberIn is going to be returned.");
            return numberIn;
        }
    }
}
