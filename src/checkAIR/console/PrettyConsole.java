package checkAIR.console;

public class PrettyConsole {

    private IConsoleView currentView;

    Window window;

    public PrettyConsole(IConsoleView selectedView, String firstTitle, String secondTitle) throws IllegalAccessException, InstantiationException{

        int width = 100;
        int height = 20;

        window = new Window(width, height, 40, firstTitle, secondTitle);
    }

    public void addFrame(Frame frame) {
        window.addColumn(frame);
    }


    @Override
    public String toString() {
        return window.toString();
    }
}
