package checkAIR.console;

import java.util.LinkedList;
import java.util.List;

class Window {
    private List<StringBuilder> window;


    //Content for window, area inside the window's borders
    // x: 1 - width-2
    // y: 4 - height-1
    private List<StringBuilder> content;

    private StringBuilder firstTitle;
    private StringBuilder secondTitle;


    int width;
    int height;
    int bannerWidth;

    public Window(int width, int height, int bannerWidth, String firstTitle, String secondTitle) throws IllegalArgumentException {

        //TODO parametryzacja szerokości titlebar
        // TODO sprawdzić czy się na pewno tak stopniuje
        if(height<5)
            throw new IllegalArgumentException("Given height is too short");

        if(width < 10)
            throw new IllegalArgumentException("Given width is too small");

        if(width - 4 <= bannerWidth)
            throw new IllegalArgumentException("Banner width is too large");


        this.bannerWidth = bannerWidth;
        this.width = width;
        this.height = height;

        content = new LinkedList<StringBuilder>();
        window = new LinkedList<StringBuilder>();


        //If title is longer than the space for title, showing only partially
        this.firstTitle = new StringBuilder(fromChar(' ', this.bannerWidth -2));
        this.firstTitle.replace(0, this.firstTitle.length(), firstTitle.substring(0, Math.min(this.firstTitle.length(), firstTitle.length())));

        this.secondTitle = new StringBuilder(fromChar(' ', this.bannerWidth -2));
        this.secondTitle.replace(0, this.secondTitle.length()-1, secondTitle.substring(0, Math.min(this.secondTitle.length(), secondTitle.length())));

        addBorders();
    }

    public StringBuilder getWindow() {

        StringBuilder filledWindow = new StringBuilder();

        //Appending title bar
        filledWindow.append(window.get(0));
        filledWindow.append('\n');


        filledWindow.append(new StringBuilder(window.get(1)).replace((width- bannerWidth)/2,(width- bannerWidth)/2 + firstTitle.length(),firstTitle.toString()));
        filledWindow.append('\n');

        filledWindow.append(new StringBuilder(window.get(2)).replace((width- bannerWidth)/2,(width- bannerWidth)/2 + secondTitle.length(),secondTitle.toString()));
        filledWindow.append('\n');

        for(int i=3; i<4; i++) {
            filledWindow.append(window.get(i));
            filledWindow.append('\n');
        }

        //Appending border frames and content
        for(int i=4; i<height; i++) {
            filledWindow.append(window.get(i).replace(1,width-1, content.get(i-4).toString()));
            filledWindow.append('\n');
        }

        //Appending bottom border
        filledWindow.append(window.get(window.size()-1));

        return filledWindow;
    }

//dodawanie z kolorem - prawdopodobnie do wywalenia
//    public void append(StringBuilder text, Color... colors) {
//        for (Color color : colors) {
//            content.append(color.getCode());
//        }
//        content.append(text);
//        content.append(Color.Reset);
//    }

    public void add(StringBuilder content, int positionX, int positionY, Color... colors) throws IllegalArgumentException {
        if (positionX <= 0 || positionX >= width || positionY <= 0 || positionY >= height)
            throw new IllegalArgumentException("Illegal position");

    }

    public void addContent(StringBuilder content, int x, int y) {
        String[] sLines = content.toString().split("\n");

        int i = 0;
        for(String sLine : sLines) {
            StringBuilder line = this.content.get(y+i);
            line.replace(x,x+sLine.length(), sLine);
            i++;
        }
    }

    public void addContent(String content, int x, int y) {
        addContent(new StringBuilder(content), x, y);
    }

    public void addHorizontalLine(int y) {

    }

    private StringBuilder fromChar(char character, int count) {
        StringBuilder temp = new StringBuilder();
        for(int i=0; i<count; i++) {
            temp.append(character);
        }
        return temp;
    }

    //TODO refaktoryzacja
    private void addBorders() {

        StringBuilder line = new StringBuilder();
        line.append(fromChar(' ',(width- bannerWidth)/2));
        line.append(fromChar('─', bannerWidth -2));
        line.append(fromChar(' ',(width- bannerWidth)/2));

        window.add(line);

        line = new StringBuilder();
        line.append('┌');
        line.append(fromChar('─', (width- bannerWidth)/2-3));
        line.append('/');
        line.append(fromChar(' ', bannerWidth));
        line.append('\\');
        line.append(fromChar('─', (width- bannerWidth)/2-1));
        line.append('┐');

        window.add(line);

        line = new StringBuilder();
        line.append('├');
        line.append(fromChar('─', (width- bannerWidth)/2 - 3));
        line.append('\\');
        line.append(fromChar(' ', bannerWidth));
        line.append('/');
        line.append(fromChar('─', (width- bannerWidth)/2 - 1));
        line.append('┤');

        window.add(line);

        line = new StringBuilder();

        line.append('│');
        line.append(fromChar(' ', (width- bannerWidth)/2-1));
        line.append(fromChar('─', bannerWidth -2));
        line.append(fromChar(' ', (width- bannerWidth)/2 +1));
        line.append('│');

        window.add(line);

        line = new StringBuilder();
        line.append('│');
        line.append(fromChar(' ', width-2));
        line.append('│');

        for(int i=0; i<height-4; i++) {
            window.add(new StringBuilder(line));
        }

        line = new StringBuilder();

        line.append('└');
        line.append(fromChar('─', width-2));
        line.append('┘');

        window.add(line);

        //Filling content
        for(int i=0; i<height-4; i++) {
            content.add(new StringBuilder(fromChar(' ',width-2)));
        }

    }

    //TODO no raczej tak nie będzie
    @Override
    public String toString() {
        return getWindow().toString();
    }
}
