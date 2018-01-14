package checkAIR.Console;

class PrettyConsole {
    private StringBuilder content;

    public PrettyConsole() {
        content = new StringBuilder();
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
