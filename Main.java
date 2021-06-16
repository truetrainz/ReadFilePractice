package main;

public class Main {
    public static void main(String[] args) {
        Barcode barcode = new Barcode('I', 3, 0);
        String filePath = "/Users/nickcliffel/Documents/InnovationCenter/Inventory.rtf";
        ReadFile read = new ReadFile(filePath, barcode);
        read.readFile(filePath, barcode);
    }
}
