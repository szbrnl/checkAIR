package checkAIR.console;


import java.util.List;

public interface IConsoleView {

    void add(String name, String value, Color nameColor, Color valueColor);
    void add(String name, String value);

    List<StringBuilder> getContent();
    List<Integer> getContentLinesLengths();

    void addFrame(Frame frame, int posX, int posY);

    void addImage(StringBuilder image);

    int getWidth();

    int getHeight();

}
