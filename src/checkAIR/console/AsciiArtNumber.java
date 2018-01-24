package checkAIR.console;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AsciiArtNumber {
    private List<StringBuilder> lines;

    public AsciiArtNumber(int number) {
        generateNumber(number);
    }

    public AsciiArtNumber(int number, Color color) {
        generateNumber(number);
        setColor(color);
    }

    private void generateNumber(int number) {

        String digit = getDigit(number%10);
        number/=10;

        lines = new LinkedList<>(
                Arrays.stream(
                        digit.split("\n"))
                        .map(x -> new StringBuilder(x))
                        .collect(Collectors.toList())
        );

        while (number > 0) {
            digit = getDigit(number%10);
            List<String> digitLines = Arrays.asList(digit.split("\n"));

            for (int i=0; i<digitLines.size(); i++) {
                StringBuilder currentLine = lines.get(i);
                currentLine.append(digitLines.get(i));
            }

        }

    }

    public int getWidth() {
        return lines.get(0).length();
    }

    public List<StringBuilder> getLines() {
        return lines;
    }


    private void setColor(Color color) {
        for(StringBuilder line : lines) {
            line.insert(0, color.getCode());
            line.append(Color.Reset);
        }
    }

    private String getDigit(int digit) {
        switch (digit) {
            case 1:
                return " ██╗\n" +
                        "███║\n" +
                        "╚██║\n" +
                        " ██║\n" +
                        " ██║\n" +
                        " ╚═╝\n";
            case 2:
                return "██████╗ \n" +
                        "╚════██╗\n" +
                        " █████╔╝\n" +
                        "██╔═══╝ \n" +
                        "███████╗\n" +
                        "╚══════╝\n";
            case 3:
                return "██████╗ \n" +
                        "╚════██╗\n" +
                        " █████╔╝\n" +
                        " ╚═══██╗\n" +
                        "██████╔╝\n" +
                        "╚═════╝ \n";
            case 4:
                return "██╗  ██╗\n" +
                        "██║  ██║\n" +
                        "███████║\n" +
                        "╚════██║\n" +
                        "     ██║\n" +
                        "     ╚═╝\n";
            case 5:
                return "███████╗\n" +
                        "██╔════╝\n" +
                        "███████╗\n" +
                        "╚════██║\n" +
                        "███████║\n" +
                        "╚══════╝\n";
            case 6:
                return " ██████╗ \n" +
                        "██╔════╝ \n" +
                        "███████╗ \n" +
                        "██╔═══██╗\n" +
                        "╚██████╔╝\n" +
                        " ╚═════╝ \n";

            case 7:
                return "███████╗\n" +
                        "╚════██║\n" +
                        "    ██╔╝\n" +
                        "   ██╔╝ \n" +
                        "   ██║  \n" +
                        "   ╚═╝  \n";

            case 8:
                return " █████╗ \n" +
                        "██╔══██╗\n" +
                        "╚█████╔╝\n" +
                        "██╔══██╗\n" +
                        "╚█████╔╝\n" +
                        " ╚════╝ \n";
            case 9:
                return " █████╗  \n" +
                        "██╔══██╗ \n" +
                        "╚██████║ \n" +
                        " ╚═══██║ \n" +
                        " █████╔╝ \n" +
                        " ╚════╝  \n";
            case 0:
                return " ██████╗ \n" +
                        "██╔═████╗\n" +
                        "██║██╔██║\n" +
                        "████╔╝██║\n" +
                        "╚██████╔╝\n" +
                        " ╚═════╝ \n";
            default:
                return "";
        }
    }
}
