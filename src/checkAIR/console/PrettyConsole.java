package checkAIR.console;

import java.util.LinkedList;
import java.util.List;

class PrettyConsole {
    private List<StringBuilder> window;


    //Content for console, area inside the window's borders
    // x: 1 - width-2
    // y: 4 - height-1
    private List<StringBuilder> content;

    private StringBuilder firstTitle;
    private StringBuilder secondTitle;



    int width;
    int height;
    int banner_width = 40;

    public PrettyConsole(int width, int height, String firstTitle, String secondTitle) throws IllegalArgumentException {

        //TODO parametryzacja szerokości titlebar
        //TODO wyjątki na niewłaściwe argumenty w konstruktorze
        if(height<5) throw new IllegalArgumentException("Given height is to small");

        //TODO za długa nazwa nie powoduje błędu, tylko wyświetlenie części


        content = new LinkedList<StringBuilder>();
        window = new LinkedList<StringBuilder>();



        this.firstTitle = new StringBuilder(fromChar(' ', banner_width-2));
        this.firstTitle.replace(0,firstTitle.length(), firstTitle);

        this.secondTitle = new StringBuilder(fromChar(' ', banner_width-2));
        this.secondTitle.replace(this.secondTitle.length()-secondTitle.length(), this.secondTitle.length()-1, secondTitle);

        this.width = width;
        this.height = height;

        fill();
    }

    public StringBuilder getWindow() {

        StringBuilder filledWindow = new StringBuilder();

        //Appending title bar
        filledWindow.append(window.get(0));
        filledWindow.append('\n');


        filledWindow.append(new StringBuilder(window.get(1)).replace((width-banner_width)/2,(width-banner_width)/2 + firstTitle.length(),firstTitle.toString()));
        filledWindow.append('\n');

        filledWindow.append(new StringBuilder(window.get(2)).replace((width-banner_width)/2,(width-banner_width)/2 + secondTitle.length(),secondTitle.toString()));
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
    private void fill() {

        StringBuilder line = new StringBuilder();
        line.append(fromChar(' ',(width-banner_width)/2));
        line.append(fromChar('─', banner_width-2));
        line.append(fromChar(' ',(width-banner_width)/2));

        window.add(line);

        line = new StringBuilder();
        line.append('┌');
        line.append(fromChar('─', (width-banner_width)/2-3));
        line.append('/');
        line.append(fromChar(' ', banner_width));
        line.append('\\');
        line.append(fromChar('─', (width-banner_width)/2-1));
        line.append('┐');

        window.add(line);

        line = new StringBuilder();
        line.append('├');
        line.append(fromChar('─', (width-banner_width)/2 - 3));
        line.append('\\');
        line.append(fromChar(' ', banner_width));
        line.append('/');
        line.append(fromChar('─', (width-banner_width)/2 - 1));
        line.append('┤');

        window.add(line);

        line = new StringBuilder();

        line.append('│');
        line.append(fromChar(' ', (width-banner_width)/2-1));
        line.append(fromChar('─', banner_width-2));
        line.append(fromChar(' ', (width-banner_width)/2 +1));
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

}
