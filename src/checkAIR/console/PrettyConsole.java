package checkAIR.console;

public class PrettyConsole {

    private IConsoleView currentView;

    Window window;

    public PrettyConsole(IConsoleView selectedView, String firstTitle, String secondTitle) throws IllegalAccessException, InstantiationException{

        int width = 100;
        int height = 20;

        Frame namesFrame = new Frame(true);
        namesFrame.add("pm25");
        namesFrame.add("2222222222222", Color.TextRed);
        namesFrame.add("2222222222222222");

        Frame valueFrames = new Frame(false);
        valueFrames.add("odczyt1");
        valueFrames.add("odczyt234234");
        valueFrames.add("odczytssdaadasasads234", Color.BackgroundBlue);


        window = new Window(width, height, 40, firstTitle, secondTitle);
        window.addColumn(namesFrame);
        window.addColumn(valueFrames);
        window.addColumn(valueFrames);
        window.addColumn(valueFrames);
        window.addColumn(valueFrames);
        window.addColumn(valueFrames);



    }


    @Override
    public String toString() {
        return window.toString();
    }
}
