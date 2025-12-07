package se.magnusrehn;


public class Main {
    public static void main(String[] args) {

        PasswordFinder passwordFinder = new PasswordFinder("day1Instructions.txt");
        System.out.println(passwordFinder.password1a());
        System.out.println(passwordFinder.password1b());
    }
}