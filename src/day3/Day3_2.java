package day3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3_2 {

    private String input;
    private boolean isEnabled;

    public Day3_2() {

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

        String dontRegex = "don't\\(\\)";
        Pattern dontPattern = Pattern.compile(dontRegex);
        Matcher dontMatcher = dontPattern.matcher(input);

        String doRegex = "do\\(\\)";
        Pattern doPattern = Pattern.compile(doRegex);
        Matcher doMatcher;

        StringBuilder enabledBuilder = new StringBuilder();
        String readFrom = input;

        int k = 0;
        String enabledSection = "";

        while (true) {
            if (dontMatcher.find()) {
                isEnabled = false;
                if (k == 0) {
                    enabledSection = readFrom.substring(0, dontMatcher.start());
                    enabledBuilder.append(enabledSection);
                    k++;
                }
                readFrom = readFrom.substring(dontMatcher.end());

                doMatcher = doPattern.matcher(readFrom);
                if (doMatcher.find()) {
                    readFrom = readFrom.substring(doMatcher.end());
                    dontMatcher = dontPattern.matcher(readFrom);
                    isEnabled = true;
                    k = 0;
                }
            } else {
                break;
            }
        }

        if (isEnabled) {
            enabledBuilder.append(readFrom);
        }

        String sortedInput = enabledBuilder.toString();

        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sortedInput);

        String numRegex = "\\d{1,3}";
        Pattern numPattern = Pattern.compile(numRegex);

        int[] nums = new int[2];
        int i = 0;
        int sum = 0;

        while (matcher.find()) {
            String match = sortedInput.substring(matcher.start(), matcher.end());

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
        new Day3_2();
    }
}
