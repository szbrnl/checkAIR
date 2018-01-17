package checkAIR.console;

import java.util.LinkedList;
import java.util.List;

public class CurrentMeasurementsView implements IConsoleView {

    private int width;

    private List<StringBuilder> content;
    private List<Integer> contentLinesLengths;

    public CurrentMeasurementsView() {
        content = new LinkedList<>();
        contentLinesLengths = new LinkedList<>();
        width = 0;
    }

    @Override
    public void add(String name, String value, Color nameColor, Color valueColor) {
        StringBuilder line = new StringBuilder();
        line.append(nameColor.getCode());
        line.append(name);
        line.append(":  ");
        line.append(nameColor.Reset);

        line.append(valueColor.getCode());
        line.append(value);
        line.append(valueColor.Reset);

        content.add(line);

        int lengthWithoutEscapes = name.length() + value.length() + 3;

        contentLinesLengths.add(lengthWithoutEscapes);
        width = Math.max(width, lengthWithoutEscapes);
    }

    @Override
    public void add(String name, String value) {
        StringBuilder line = new StringBuilder();
        line.append(name);
        line.append(":  ");
        line.append(value);

        content.add(line);
        contentLinesLengths.add(name.length() + value.length() + 3);
    }

    @Override
    public List<StringBuilder> getContent() {
        return content;
    }

    @Override
    public List<Integer> getContentLinesLengths() {
        return contentLinesLengths;
    }


    //TODO sprawdzanie argumentow
    @Override
    public void addFrame(Frame frame, int posX, int posY) {
        List<StringBuilder> lines = frame.getLines();
        List<Integer> linesLengths = frame.getLinesLengths();

        for(int i=0; i<lines.size(); i++) {
            content.get(posY+i).replace(posX, posX+linesLengths.get(i), lines.get(i).toString());
            width = Math.max(width, posX + linesLengths.get(i));
        }


    }

    @Override
    public void addImage(StringBuilder image) {

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return content.size();
    }

}
