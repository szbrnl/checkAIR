package com.example.checkair.console;

import java.util.List;

public class ConsoleElement {
    protected List<StringBuilder> lines;

    protected List<Integer> linesLengths;

    protected int width;

    public void add(String line, Color color) {
        StringBuilder sLine = new StringBuilder();
        sLine.append(color.getCode());
        sLine.append(line);
        sLine.append(Color.Reset);

        lines.add(sLine);



        linesLengths.add(line.length());
        width = Math.max(width, line.length());
    }

    public void add(String line) {
        StringBuilder sLine = new StringBuilder();
        sLine.append(line);

        lines.add(sLine);

        int specialCharactersCount = (int) line.chars().filter(x -> x == '\033').count();

        width = Math.max(width, line.length() - specialCharactersCount*11/2);
        linesLengths.add(line.length());
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return lines.size();
    }

    public List<StringBuilder> getLines() {

        for (int i=0; i<lines.size(); i++) {
            if(lines.get(i).length() < width) {
                lines.set(i, fromChar(' ', width).replace(0, lines.get(i).length(), lines.get(i).toString()));
            }
        }
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
