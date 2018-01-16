package checkAIR.console;

public class PrettyConsole {

    private IConsoleView currentView;

    Window window;

    public PrettyConsole(ViewMode viewMode, String firstTitle, String secondTitle) throws IllegalAccessException, InstantiationException{

        int width = 100;
        int height = 20;

        Class selectedViewClass = CurrentMeasurementsView.class;

        window = new Window(width, height, 40, firstTitle, secondTitle);
        window.addContent("adsSDADS", 3, 4);


        try {
            IConsoleView selectedView = (IConsoleView) selectedViewClass.newInstance();
        }
        catch (IllegalAccessException ex) {
            throw new IllegalAccessException("Could not access the constructor of "+selectedViewClass.getName());
        }
        catch (InstantiationException ex) {
            throw new InstantiationException("There was an error during view instantiation");
        }
    }


    @Override
    public String toString() {
        return window.toString();
    }
}
