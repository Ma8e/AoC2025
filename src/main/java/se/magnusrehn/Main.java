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

    private static void day3() {
        Batteries batteries = new Batteries("batteries.txt");
        System.out.println(batteries.maxTotalJoltage());
        System.out.println(batteries.maxTotalBigJoltage());

    }

    private static void day4() {
        PaperRolls paperRolls = new PaperRolls("rollsOfPaper.txt");
        System.out.println(paperRolls.numberOfAccessibleRolls());
        System.out.println(paperRolls.maxTotalRemoved());
    }

    public static void main(String[] args) {
        day4();
    }
}