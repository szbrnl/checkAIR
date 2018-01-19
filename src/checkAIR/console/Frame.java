package checkAIR.console;

import java.util.LinkedList;
import java.util.List;

public class Frame {

    private List<StringBuilder> lines;

    private List<Integer> linesLengths;

    private int width;

    private boolean alignRight;

    public Frame(boolean alignRight) {

        lines = new LinkedList<>();
        linesLengths = new LinkedList<>();
        width = 0;

        this.alignRight = alignRight;
    }

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
            align();
        return lines;
    }

    public List<Integer> getLinesLengths() {
        return linesLengths;
    }

    private void align() {
        if(alignRight) {
            for(int i=0; i<lines.size(); i++) {

                lines.set(i, new StringBuilder(
                        fromChar(' ', width).replace(width - linesLengths.get(i),width, lines.get(i).toString())
                ));

                linesLengths.set(i, width);
            }
        }
        else {
            for(int i=0; i<lines.size(); i++) {

                lines.set(i, new StringBuilder(
                        fromChar(' ', width).replace(0 ,linesLengths.get(i), lines.get(i).toString())
                ));

                linesLengths.set(i, width);
            }
        }

    }


    private StringBuilder fromChar(char character, int count) {
        StringBuilder temp = new StringBuilder();
        for(int i=0; i<count; i++) {
            temp.append(character);
        }
        return temp;
    }

}
