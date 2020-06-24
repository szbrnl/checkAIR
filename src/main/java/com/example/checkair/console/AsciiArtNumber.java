package com.example.checkair.console;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AsciiArtNumber extends ConsoleElement {

    public AsciiArtNumber(int number) {
        linesLengths = new LinkedList<>();
        generateNumber(number);
    }

    public AsciiArtNumber(int number, Color color) {
        linesLengths = new LinkedList<>();
        generateNumber(number);
        setColor(color);
    }

    private void generateNumber(int number) {
        List<Integer> digits = invertNumber(number);
        Collections.reverse(digits);

        String digit = getDigit(digits.get(0));

        digits.remove(0);

        lines = new LinkedList<>(
                Arrays.stream(
                        digit.split("\n"))
                        .map(x -> new StringBuilder(x))
                        .collect(Collectors.toList())
        );

        for(Integer integer : digits) {
            digit = getDigit(integer);
            List<String> digitLines = Arrays.asList(digit.split("\n"));

            for (int i = 0; i < digitLines.size(); i++) {
                StringBuilder currentLine = lines.get(i);
                currentLine.append(digitLines.get(i));
                currentLine.append(" ");
                linesLengths.add(currentLine.length());
            }

        }

    }

    @Override
    public int getWidth() {
        return lines.get(0).length();
    }

    private void setColor(Color color) {
        for (StringBuilder line : lines) {
            line.insert(0, color.getCode());
            line.append(Color.Reset);
        }
    }

    private List<Integer> invertNumber(int number) {
        List<Integer> digits = new LinkedList<>();
        while (number > 0) {
            digits.add(number%10);
            number /= 10;
        }
        return digits;
    }

    private String getDigit(int digit) {
        switch (digit) {
            case 1:
                return " ██╗\n" +
                        "███║\n" +
                        "╚██║\n" +
                        " ██║\n" +
                        " ██║\n" +
                        " ╚═╝";
            case 2:
                return "██████╗ \n" +
                        "╚════██╗\n" +
                        " █████╔╝\n" +
                        "██╔═══╝ \n" +
                        "███████╗\n" +
                        "╚══════╝";
            case 3:
                return "██████╗ \n" +
                        "╚════██╗\n" +
                        " █████╔╝\n" +
                        " ╚═══██╗\n" +
                        "██████╔╝\n" +
                        "╚═════╝ ";
            case 4:
                return "██╗  ██╗\n" +
                        "██║  ██║\n" +
                        "███████║\n" +
                        "╚════██║\n" +
                        "     ██║\n" +
                        "     ╚═╝";
            case 5:
                return "███████╗\n" +
                        "██╔════╝\n" +
                        "███████╗\n" +
                        "╚════██║\n" +
                        "███████║\n" +
                        "╚══════╝";
            case 6:
                return " ██████╗ \n" +
                        "██╔════╝ \n" +
                        "███████╗ \n" +
                        "██╔═══██╗\n" +
                        "╚██████╔╝\n" +
                        " ╚═════╝ ";

            case 7:
                return "███████╗\n" +
                        "╚════██║\n" +
                        "    ██╔╝\n" +
                        "   ██╔╝ \n" +
                        "   ██║  \n" +
                        "   ╚═╝  ";

            case 8:
                return " █████╗ \n" +
                        "██╔══██╗\n" +
                        "╚█████╔╝\n" +
                        "██╔══██╗\n" +
                        "╚█████╔╝\n" +
                        " ╚════╝ ";
            case 9:
                return " █████╗  \n" +
                        "██╔══██╗ \n" +
                        "╚██████║ \n" +
                        " ╚═══██║ \n" +
                        " █████╔╝ \n" +
                        " ╚════╝  ";
            case 0:
                return " ██████╗ \n" +
                        "██╔═████╗\n" +
                        "██║██╔██║\n" +
                        "████╔╝██║\n" +
                        "╚██████╔╝\n" +
                        " ╚═════╝ ";
            default:
                return "";
        }
    }
}
