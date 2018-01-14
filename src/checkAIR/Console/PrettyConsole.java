package checkAIR.Console;

//TODO colorizer?
class PrettyConsole {
    private StringBuilder content;

    public PrettyConsole() {

    }

    public void append(StringBuilder text, Color... colors) {
        for (Color color : colors) {
            content.append(color.getCode());
        }
        content.append(text);
        content.append(Color.Reset);
    }

    public StringBuilder getContent() {
        return content;
    }
}
