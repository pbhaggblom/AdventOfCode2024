package day7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day7 {

    public Day7() {

        List<Long> testValues = new ArrayList<>();
        List<long[]> operands = new ArrayList<>();

        try (Scanner scan = new Scanner(new FileInputStream("src/day7/input"))) {
            while (scan.hasNextLine()) {
                String[] row = scan.nextLine().split(": ");
                testValues.add(Long.parseLong(row[0]));
                String[] op = row[1].split(" ");
                long[] num = new long[op.length];
                for (int i = 0; i < op.length; i++) {
                    num[i] = Long.parseLong(op[i]);
                }
                operands.add(num);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



        long sum = 0;
        for (int i = 0; i < testValues.size(); i++) {
            if (checkValidity(testValues.get(i), operands.get(i))) {
                sum += testValues.get(i);
            }
        }

        System.out.println(sum);

    }

    public boolean checkValidity(long testValue, long[] operands) {
        List<Long> r = new ArrayList<>();
        r.add(operands[0]);

        int k = 1;
        for (int i = 1; i < operands.length; i++) {
            for (int j = 0; j < k; j++) {
                r.add(r.get(j) + operands[i]);
                r.add(r.get(j) * operands[i]);
            }
            k += k * 2;
        }

        int numRelevantResults = (int) Math.pow(2, (operands.length - 1));

        for (int i = r.size() - numRelevantResults - 1; i < r.size(); i++) {
            if (r.get(i) == testValue) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new Day7();
    }
}
