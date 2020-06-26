package com.example.checkair.console;

public class PrettyConsole {

    private Window window;

    public PrettyConsole(ColumnarView selectedView, String firstTitle, String secondTitle) {

        int width = 94;
        int height = 18;

        window = new Window(width, height, 40, firstTitle, secondTitle);
        setView(selectedView);
    }

    private void setView(ColumnarView view) {

        view.getColumns()
                .forEach(x -> window.addColumn(x));

    }

    @Override
    public String toString() {
        return window.toString();
    }
}
