package checkAIR.console;

public class Console {

    private IConsoleView currentView;

    PrettyConsole console;

    public Console(ViewMode viewMode) throws IllegalAccessException, InstantiationException{

        int width = 80;
        int height = 17;

        Class selectedViewClass = CurrentMeasurementsView.class;

        console = new PrettyConsole(width, height);
        console.addContent("adsSDADS", 3, 4);
        //console.append(new StringBuilder("________________________________________________________________________________"), Color.TextBlue, Color.BackgroundGreen);

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
        return console.getWindow().toString();
    }
}
