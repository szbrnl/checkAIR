package checkAIR;

import checkAIR.airly.MeasurementType;
import checkAIR.console.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CurrentMeasurementsView implements IConsoleView {

    private List<ConsoleElement> columns;

    private ConsoleElement namesFrame;
    private ConsoleElement valuesFrame;
    private ConsoleElement asciiArtFrame;

    public CurrentMeasurementsView() {
        this.columns = new LinkedList<>();

        namesFrame = new Frame(true);
        valuesFrame = new Frame(false);

        columns.add(namesFrame);
        columns.add(valuesFrame);
    }

    @Override
    public List<ConsoleElement> getColumns() {
        return columns;
    }

    public void setAsciiArtNumber(int number, Color color) {
       ConsoleElement asciiArtNumber = new AsciiArtNumber(number, color);

        asciiArtFrame = asciiArtNumber;
        asciiArtFrame.add("Air Quality Index");
        columns.add(asciiArtFrame);

    }


    public void addMeasurement(MeasurementType type, Integer value, Color color) {
        namesFrame.add(type.getName());

        valuesFrame.add(
                //If null given, show 'No data' instead
                Optional.ofNullable(value)
                        .map(x -> x.toString() + " " + type.getUnit())
                        .orElse("No data"),
                color
        );

    }

    public void addMeasurement(MeasurementType type, Double value, Color color) {
        namesFrame.add(type.getName());

        valuesFrame.add(
                //If null given, show 'No information' instead
                Optional.ofNullable(value)
                        .map(x -> x.toString() + " " + type.getUnit())
                        .orElse("No data"),
                color
        );
    }
}
