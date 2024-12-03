package day3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3_1 {

    private String input;

    private Day3_1() {

        try (BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream("src/day3/input")))) {
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = read.readLine()) != null) {
                sb.append(s);
            }
            input = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        String numRegex = "\\d{1,3}";
        Pattern numPattern = Pattern.compile(numRegex);


        int[] nums = new int[2];
        int i = 0;
        int sum = 0;

        while (matcher.find()) {
            String match = input.substring(matcher.start(), matcher.end());

            Matcher numMatcher = numPattern.matcher(match);
            while (numMatcher.find()) {
                int numMatch = Integer.parseInt(match.substring(numMatcher.start(), numMatcher.end()));
                nums[i] = numMatch;
                i++;
            }
            sum += nums[0] * nums[1];
            i = 0;
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {
        new Day3_1();
    }
}
