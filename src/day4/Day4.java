package day4;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day4 {

    public Day4() {

        List<String> input = new ArrayList<>();

        try (Scanner scan = new Scanner(new FileInputStream("src/day4/input"))) {
            while (scan.hasNextLine()) {
                input.add(scan.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int numMatches = 0;

        for (int i = 0 ; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length() - 3; j++) {
                String temp = input.get(i).substring(j, j+4);
                if (temp.equals("XMAS") || temp.equals("SAMX")) {
                    numMatches++;
                }
            }
        }

        for (int i = 0 ; i < input.size(); i++) {
            for (int j = 0; j < input.size() - 3; j++) {
                String temp = "" + input.get(j).charAt(i) + (input.get(j+1).charAt(i)) + input.get(j + 2).charAt(i) + input.get(j + 3).charAt(i);
                if (temp.equals("XMAS") || temp.equals("SAMX")) {
                    numMatches++;
                }
            }
        }

        for (int i = 0 ; i < input.size() - 3; i++) {
            for (int j = 0; j < input.size() - 3; j++) {
                String temp = "" + input.get(j).charAt(i) + (input.get(j+1).charAt(i+1)) + input.get(j + 2).charAt(i + 2) + input.get(j + 3).charAt(i + 3);
                if (temp.equals("XMAS") || temp.equals("SAMX")) {
                    numMatches++;
                }
            }
        }

        for (int i = 0 ; i < input.size() - 3; i++) {
            for (int j = 3; j < input.size(); j++) {
                String temp = "" + input.get(j).charAt(i) + (input.get(j-1).charAt(i+1)) + input.get(j - 2).charAt(i + 2) + input.get(j - 3).charAt(i + 3);
                if (temp.equals("XMAS") || temp.equals("SAMX")) {
                    numMatches++;
                }
            }
        }

        System.out.println(numMatches);
        numMatches = 0;

        for (int i = 0 ; i < input.size() - 2; i++) {
            for (int j = 0; j < input.size() - 2; j++) {
                String temp1 = "" + input.get(j).charAt(i) + (input.get(j+1).charAt(i+1)) + input.get(j + 2).charAt(i + 2);
                String temp2 = "" + (input.get(j+2).charAt(i)) + input.get(j + 1).charAt(i + 1) + input.get(j).charAt(i + 2);
                if ((temp1.equals("MAS") || temp1.equals("SAM")) && (temp2.equals("MAS") || temp2.equals("SAM"))) {
                    numMatches++;
                }
            }
        }

        System.out.println(numMatches);

    }

    public static void main(String[] args) {
        new Day4();
    }
}
