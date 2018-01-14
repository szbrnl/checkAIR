package checkAIR.Console;

public class Console {

    private IConsoleView currentView;

    PrettyConsole console;

    public Console(ViewMode viewMode) throws IllegalAccessException, InstantiationException{

        Class selectedViewClass = CurrentMeasurementsView.class;

        console = new PrettyConsole();
        console.append(new StringBuilder("__________"), Color.TextBlue, Color.BackgroundGreen);


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
        return console.getContent().toString();
    }
}
