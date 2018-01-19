package checkAIR.console;

import javax.xml.stream.events.Characters;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Window {
    private List<StringBuilder> window;


    //Content for window, area inside the window's borders
    // x: 1 - width-2
    // y: 4 - height-1
    private List<StringBuilder> content;

    //Real length of the lines (escape characters used for colors are not visible on the screen
    //but they are elements in StringBuilder)
    private List<Integer> contentLinesEndPositions;

    private StringBuilder firstTitle;
    private StringBuilder secondTitle;



    private int width;
    private int height;
    private int bannerWidth;
    private int margin = 2;
    private int contentWidth = 2;

    //TODO jeśli coś wykracza poza ekran to nie powoduje błędu, jest po prostu ucinane


    public Window(int width, int height, int bannerWidth, String firstTitle, String secondTitle) throws IllegalArgumentException {

        //TODO parametryzacja szerokości titlebar
        // TODO sprawdzić czy się na pewno tak stopniuje
        if (height < 5)
            throw new IllegalArgumentException("Given height is too short");

        if (width < 10)
            throw new IllegalArgumentException("Given width is too small");

        if (width - 4 <= bannerWidth)
            throw new IllegalArgumentException("Banner width is too large");


        this.bannerWidth = bannerWidth;
        this.width = width;
        this.height = height;

        content = new LinkedList<>();
        contentLinesEndPositions = new LinkedList<>();
        window = new LinkedList<>();


        //If title is longer than the space for title, showing only partially
        this.firstTitle = new StringBuilder(fromChar(' ', this.bannerWidth - 2));
        this.firstTitle.replace(0, this.firstTitle.length(), firstTitle.substring(0, Math.min(this.firstTitle.length(), firstTitle.length())));

        this.secondTitle = new StringBuilder(fromChar(' ', this.bannerWidth - 2));
        this.secondTitle.replace(0, this.secondTitle.length() - 1, secondTitle.substring(0, Math.min(this.secondTitle.length(), secondTitle.length())));

        addBorders();
    }


    public void addColumn(Frame frame) {

        //Checking if there is enough space for a frame
        if( contentWidth + margin + frame.getWidth() > width - 2)
            return;

        List<StringBuilder> lines = frame.getLines();
        List<Integer> linesLengths = frame.getLinesLengths();

        //The position of last character in a content line
        int posX;
        int posY = margin;

        for (int i = 0; i < lines.size(); i++, posY++) {
            posX = contentLinesEndPositions.get(posY);

            content.get(posY).replace(posX, posX + linesLengths.get(i), lines.get(i).toString());

            contentLinesEndPositions.set(posY, posX + lines.get(i).length() + 4);
        }
        contentWidth = contentWidth + 4 + frame.getWidth();

        for(; posY<content.size(); posY++) {
            contentLinesEndPositions.set(posY, contentWidth);
        }

    }


    public StringBuilder getWindow() {

        StringBuilder filledWindow = new StringBuilder();

        //Appending title bar
        filledWindow.append(window.get(0));
        filledWindow.append('\n');


        filledWindow.append(new StringBuilder(window.get(1)).replace((width - bannerWidth) / 2, (width - bannerWidth) / 2 + firstTitle.length(), firstTitle.toString()));
        filledWindow.append('\n');

        filledWindow.append(new StringBuilder(window.get(2)).replace((width - bannerWidth) / 2, (width - bannerWidth) / 2 + secondTitle.length(), secondTitle.toString()));
        filledWindow.append('\n');

        for (int i = 3; i < 4; i++) {
            filledWindow.append(window.get(i));
            filledWindow.append('\n');
        }

        //Appending border frames and content
        for (int i = 4; i < height; i++) {
            filledWindow.append(window.get(i).replace(1, width  - 1, content.get(i - 4).toString()));
            filledWindow.append('\n');
        }


        //Appending bottom border
        filledWindow.append(window.get(window.size() - 1));

        return filledWindow;
    }



    private StringBuilder fromChar(char character, int count) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < count; i++) {
            temp.append(character);
        }
        return temp;
    }

    //TODO refaktoryzacja
    private void addBorders() {

        StringBuilder line = new StringBuilder();
        line.append(fromChar(' ', (width - bannerWidth) / 2));
        line.append(fromChar('─', bannerWidth - 2));
        line.append(fromChar(' ', (width - bannerWidth) / 2));

        window.add(line);

        line = new StringBuilder();
        line.append('┌');
        line.append(fromChar('─', (width - bannerWidth) / 2 - 3));
        line.append('/');
        line.append(fromChar(' ', bannerWidth));
        line.append('\\');
        line.append(fromChar('─', (width - bannerWidth) / 2 - 1));
        line.append('┐');

        window.add(line);

        line = new StringBuilder();
        line.append('├');
        line.append(fromChar('─', (width - bannerWidth) / 2 - 3));
        line.append('\\');
        line.append(fromChar(' ', bannerWidth));
        line.append('/');
        line.append(fromChar('─', (width - bannerWidth) / 2 - 1));
        line.append('┤');

        window.add(line);

        line = new StringBuilder();

        line.append('│');
        line.append(fromChar(' ', (width - bannerWidth) / 2 - 1));
        line.append(fromChar('─', bannerWidth - 2));
        line.append(fromChar(' ', (width - bannerWidth) / 2 + 1));
        line.append('│');

        window.add(line);

        line = new StringBuilder();
        line.append('│');
        line.append(fromChar(' ', width - 2));
        line.append('│');

        for (int i = 0; i < height - 4; i++) {
            window.add(new StringBuilder(line));
        }

        line = new StringBuilder();

        line.append('└');
        line.append(fromChar('─', width - 2));
        line.append('┘');

        window.add(line);

        //Filling content
        for (int i = 0; i < height - 4; i++) {
            content.add(new StringBuilder(fromChar(' ', width - 2)));
            contentLinesEndPositions.add(margin);
        }

    }

    //TODO no raczej tak nie będzie
    @Override
    public String toString() {
        return getWindow().toString();
    }

}
