package day5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day5 {

    List<int[]> instructions = new ArrayList<>();
    List<int[]> updates = new ArrayList<>();

    public Day5() {

        List<int[]> correctUpdates = new ArrayList<>();
        List<int[]> incorrectUpdates = new ArrayList<>();
        List<int[]> sortedUpdates = new ArrayList<>();

        try (Scanner scan = new Scanner(new FileInputStream("src/day5/input"))) {
            String s;

            while (!(s = scan.nextLine()).isEmpty() ) {
                String[] in = s.split("\\|");
                instructions.add(convertToIntArray(in));
            }
            while (scan.hasNextLine()) {
                String[] in = scan.nextLine().split(",");
                updates.add(convertToIntArray(in));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int[] update : updates) {
            if (isCorrect(update)) {
                correctUpdates.add(update);
            } else {
                incorrectUpdates.add(update);
            }
        }

        int sum = sumMiddlePages(correctUpdates);
        System.out.println(sum);

        for (int[] incorrectUpdate : incorrectUpdates) {
            sortedUpdates.add(sortIncorrectUpdate(incorrectUpdate));
        }

        sum = sumMiddlePages(sortedUpdates);
        System.out.println(sum);


    }

    public int[] convertToIntArray(String[] input) {
        int[] output = new int[input.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = Integer.parseInt(input[i]);
        }
        return output;
    }

    public boolean isCorrect(int[] update) {
        for (int[] instruction : instructions) {
            int x = instruction[0];
            int y = instruction[1];

            int xIndex = -1;
            int yIndex = -1;

            for (int i = 0; i < update.length; i++) {
                if (update[i] == x) {
                    xIndex = i;
                }
                if (update[i] == y) {
                    yIndex = i;
                }
            }
            if ((yIndex > -1 && xIndex > -1) && yIndex < xIndex) {
                return false;
            }
        }
        return true;
    }

    public int[] sortIncorrectUpdate(int[] update) {
        int[] output = new int[update.length];
        List<Integer> templist = new ArrayList<>();
        templist.add(update[0]);

        for (int i = 1; i < update.length; i++) {

            int index = -1;

            for (int j = 0; j < templist.size(); j++) {
                for (int[] instruction : instructions) {
                    if (update[i] == instruction[0] && templist.get(j) == instruction[1] && index == -1) {
                        index = j;
                        break;
                    } else if (update[i] == instruction[1] && templist.get(j) == instruction[0]) {
                        index = j + 1;
                        break;
                    }
                }
            }
            if (index == templist.size() || index == -1) {
                templist.add(update[i]);
            } else {
                templist.add(index, update[i]);
            }
        }
        for (int i = 0; i < templist.size(); i++) {
            output[i] = templist.get(i);
        }
        return output;

    }

    public int sumMiddlePages(List<int[]> updates) {
        int sum = 0;

        for (int[] update : updates) {
            int middle = update.length / 2;
            sum += update[middle];
        }

        return sum;
    }

    public static void main(String[] args) {
        new Day5();
    }
}
