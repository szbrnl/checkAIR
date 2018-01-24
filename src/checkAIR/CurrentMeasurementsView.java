package checkAIR;

import checkAIR.airly.MeasurementType;
import checkAIR.console.Color;
import checkAIR.console.Frame;
import checkAIR.console.IConsoleView;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CurrentMeasurementsView implements IConsoleView {

    private List<Frame> columns;

    private Frame namesFrame;
    private Frame valuesFrame;
    private Frame AsciiArtFrame;

    public CurrentMeasurementsView() {
        this.columns = new LinkedList<>();

        namesFrame = new Frame(true);
        valuesFrame = new Frame(false);
        AsciiArtFrame = new Frame(false);

        columns.add(namesFrame);
        columns.add(valuesFrame);
        columns.add(AsciiArtFrame);
    }

    @Override
    public List<Frame> getColumns() {
        return columns;
    }

    public void addMeasurement(MeasurementType type, Integer value, Color color) {
        namesFrame.add(type.getName());

        valuesFrame.add(
                //If null given, show 'No information' instead
                Optional.ofNullable(value)
                        .map(x -> x.toString() + " " + type.getUnit())
                        .orElse("No information"),
                color
        );

    }

    public void addMeasurement(MeasurementType type, Double value, Color color) {
        namesFrame.add(type.getName());

        valuesFrame.add(
                //If null given, show 'No information' instead
                Optional.ofNullable(value)
                        .map(x -> x.toString() + " " + type.getUnit())
                        .orElse("No information"),
                color
        );
    }
}
