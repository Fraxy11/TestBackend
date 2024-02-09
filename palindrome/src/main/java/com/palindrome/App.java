package com.palindrome;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);


        do{
        System.out.print("Masukan Kata (bisa lebih dari satu kata gunakan \",\" ):  ");
        String input = scanner.nextLine();

        String[] Kata = input.split("\\,");

        for(String kata : Kata){
        if (iniPalindrome(kata)) {
            System.out.println("Kata" + " " + kata + " " + "merupakan kata Palindrome");

        } else {
            System.out.println("Kata" + " " + kata + " " + "bukan merupakan kata Palindrome");

        }
    }
        System.out.print("Ingin memasukkan kata-kata lagi? (ya/tidak): ");
    } while (scanner.nextLine().equalsIgnoreCase("ya"));
    }

    private static boolean iniPalindrome(String str) {
        str = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}