package checkAIR;

import checkAIR.airly.MeasurementType;
import checkAIR.console.ConsoleElement;
import checkAIR.console.Color;
import checkAIR.console.Frame;
import checkAIR.console.IConsoleView;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class HistoryView implements IConsoleView {

    private List<ConsoleElement> columns;

    private ConsoleElement historyFrame;

    private int maxHeight;


    public HistoryView() {
        maxHeight = 12;
        columns = new LinkedList<>();

        addNewColumn();
    }

    public HistoryView(int maxHeight) {
        this.maxHeight = maxHeight;
        columns = new LinkedList<>();

        addNewColumn();
    }

    public void addMeasurement(String fromDateTime, String tillDateTime, List<MeasurementType> measurementTypes, List<Integer> values, List<Color> colors) {

        if (historyFrame.getHeight() + measurementTypes.size() > maxHeight) {
            addNewColumn();
        }

        historyFrame.add(formatTime(tillDateTime) + " - " + formatTime(fromDateTime), Color.UnderlineWhite);

        for (int i = 0; i < measurementTypes.size() && i < values.size() && i < colors.size(); i++) {
            String line = formatLine(measurementTypes.get(i), colors.get(i), values.get(i));
            historyFrame.add(line, colors.get(i));
        }

        historyFrame.add("");
    }

    private void addNewColumn() {
        historyFrame = new Frame(false);
        columns.add(historyFrame);
    }

    private String formatLine(MeasurementType type, Color color, Integer value) {

        StringBuilder line = new StringBuilder();
        line.append(type.getName());
        line.append(": ");
        line.append(Optional
                .ofNullable(value)
                .map(x-> x.toString())
                .orElse("No data"));

        line.append(" ");
        line.append(type.getUnit());

        return line.toString();
    }

    private String formatTime(String dateTime) {
        return dateTime.substring(11, 16);
    }

    @Override
    public List<ConsoleElement> getColumns() {
        return columns;
    }


}
