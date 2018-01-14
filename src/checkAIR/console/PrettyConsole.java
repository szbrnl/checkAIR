package checkAIR.console;

class PrettyConsole {
    private StringBuilder content;

    int width;
    int height;

    public PrettyConsole(int width, int height) {
        content = new StringBuilder();
        this.width = width;
        this.height = height;

        fill();
    }

    public void append(StringBuilder text, Color... colors) {
        for (Color color : colors) {
            content.append(color.getCode());
        }
        content.append(text);
        content.append(Color.Reset);
    }

    public void add(StringBuilder content, int positionX, int positionY, Color... colors) throws IllegalArgumentException {
        if (positionX <= 0 || positionX >= width || positionY <= 0 || positionY >= height)
            throw new IllegalArgumentException("Illegal position");

    }


    public StringBuilder getContent() {

        return content;
    }


    private void fill() {
        content = new StringBuilder("                    ────────────────────────────────                     \n" +
                "┌─────────────────/                                  \\──────────────────┐\n" +
                "├─────────────────\\                                  /──────────────────┤\n" +
                "│                   ────────────────────────────────                    │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "│                                                                       │\n" +
                "└───────────────────────────────────────────────────────────────────────┘");
//
//        add(' ', (width-26)/2);
//        add('-', 26);
//        add(' ', (width-26)/2);
//
//        content.append("\n");
//
//        content.append("┌");
//        add('─', width+16);
//        content.append("┐");
//        content.append("\n");
//        for (int i = 1; i < height; i++) {
//            content.append("|");
//            add(' ', width-2);
//            content.append("|");
//            content.append("\n");
//        }
//        content.append("└");
//        add('─', width+16);
//        content.append("┘");
    }

    private void add(char c, int count) {
        for(int i=0; i<count; i++)
            content.append(c);
    }

}
