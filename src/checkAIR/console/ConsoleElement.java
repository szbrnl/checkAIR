package checkAIR.console;

import java.util.List;

public class ConsoleElement {
    protected List<StringBuilder> lines;

    protected List<Integer> linesLengths;

    protected int width;

    public void add(String line, Color color) {
        StringBuilder sLine = new StringBuilder();
        sLine.append(color.getCode());
        sLine.append(line);
        sLine.append(color.Reset);

        lines.add(sLine);
        linesLengths.add(line.length());
        width = Math.max(width, line.length());
    }

    public void add(String line) {
        StringBuilder sLine = new StringBuilder();
        sLine.append(line);

        lines.add(sLine);
        linesLengths.add(line.length());
        width = Math.max(width, line.length());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return lines.size();
    }

    public List<StringBuilder> getLines() {
        return lines;
    }

    public List<Integer> getLinesLengths() {
        return linesLengths;
    }

    protected StringBuilder fromChar(char character, int count) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < count; i++) {
            temp.append(character);
        }
        return temp;
    }
}
