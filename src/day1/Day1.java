package day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    public Day1() {
        try (Scanner scan = new Scanner(new FileInputStream("src/day1/input"))) {
            String s;
            while (scan.hasNextLine()) {
                s = scan.nextLine();

                Scanner sc = new Scanner(s);
                while (sc.hasNextInt()) {
                    left.add(sc.nextInt());
                    right.add(sc.nextInt());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(left);
        Collections.sort(right);

        int sum = 0;
        List<Integer> comp;
        for (int i = 0; i < left.size(); i++) {
            int diff = Math.abs(left.get(i) - right.get(i));
            sum += diff;
        }
        System.out.println(sum);

        int sim = 0;
        for (int i = 0; i < left.size(); i++) {
            int num = 0;
            for (int j = 0; j < left.size(); j++) {
                int l = left.get(i);
                int r = right.get(j);
                if (l == r) {
                    num++;
                }
            }
            sim += left.get(i) * num;
        }
        System.out.println(sim);
    }

    public static void main(String[] args) {
        new Day1();
    }
}
