package checkAIR.console;

public class PrettyConsole {

    private Window window;

    public PrettyConsole(IConsoleView selectedView, String firstTitle, String secondTitle) {

        int width = 100;
        int height = 20;

        window = new Window(width, height, 40, firstTitle, secondTitle);
        setView(selectedView);
    }

    private void setView(IConsoleView view) {

        view.getColumns()
                .forEach(x-> window.addColumn(x));

    }

    @Override
    public String toString() {
        return window.toString();
    }
}
