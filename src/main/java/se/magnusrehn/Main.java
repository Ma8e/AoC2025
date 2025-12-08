package se.magnusrehn;


import java.io.IOException;

public class Main {

    private static void day1() {
        PasswordFinder passwordFinder = new PasswordFinder("day1Instructions.txt");
        System.out.println(passwordFinder.password1a());
        System.out.println(passwordFinder.password1b());
    }

    private static void day2() throws IOException {
        ProductIdRanges productIdRanges = new ProductIdRanges("productIdRanges.txt");
        System.out.println(InvalidIds.invalidsSum(productIdRanges));
        System.out.println(InvalidIds.invalids2Sum(productIdRanges));
    }

    public static void main(String[] args) throws IOException {
        day2();
    }
}