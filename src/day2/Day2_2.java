package day2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2_2 {

    ArrayList<Integer> levels;
    boolean isSafe;
    int safeReports = 0;

    public Day2_2() {

        try (Scanner scan = new Scanner(new FileInputStream("src/day2/input"))) {
            while (scan.hasNextLine()) {
                String report = scan.nextLine();
                Scanner s = new Scanner(report);
                levels = new ArrayList<>();
                while (s.hasNextInt()) {
                    int level = s.nextInt();
                    levels.add(level);
                }

                if (checkReportSafety(levels)) {
                    safeReports++;
                } else {
                    for (int j = 0; j < levels.size(); j++) {
                        if (checkDampenedReport(levels, j)) {
                            safeReports++;
                            break;
                        }
                    }
                }

            }
            System.out.println(safeReports);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkDirection(int a, int b) {
        return a < b;
    }

    public boolean checkSafety(int a, int b, boolean isIncreasing) {
        if (isIncreasing) {
            return a < b && b - a > 0 && b - a < 4;
        } else {
            return a > b && a - b > 0 && a - b < 4;
        }
    }

    public boolean checkReportSafety(ArrayList<Integer> report) {
        boolean isIncreasing = false;
        for (int i = 1; i <= report.size() - 1; i++) {
            int a = report.get(i - 1);
            int b = report.get(i);
            if (i == 1) {
                isIncreasing = checkDirection(a, b);
            }
            isSafe = checkSafety(a, b , isIncreasing);
            if (!isSafe) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDampenedReport(ArrayList<Integer> report, int levelIndex) {
        ArrayList<Integer> dampenedReport = new ArrayList<>(report);
        dampenedReport.remove(levelIndex);
        return checkReportSafety(dampenedReport);
    }


    public static void main(String[] args) {
        new Day2_2();
    }
}
