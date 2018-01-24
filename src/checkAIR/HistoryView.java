package checkAIR;

import checkAIR.airly.MeasurementType;
import checkAIR.console.Color;
import checkAIR.console.Frame;
import checkAIR.console.IConsoleView;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class HistoryView implements IConsoleView {

    private List<Frame> columns;

    private Frame historyFrame;

    private int maxHeight;


    public HistoryView() {
        maxHeight = 14;
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

        historyFrame.add(formatTime(fromDateTime) + " - " + formatTime(tillDateTime));

        for (int i = 0; i < measurementTypes.size() && i < values.size() && i < colors.size(); i++) {
            String line = formatLine(measurementTypes.get(i), colors.get(i), values.get(i));
            historyFrame.add(line);
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
        line.append(" ");
        line.append(color.getCode());
        line.append(Optional
                .ofNullable(value)
                .map(x-> x.toString())
                .orElse("No data"));

        line.append(" ");
        line.append(type.getUnit());
        line.append(Color.Reset);

        return line.toString();
    }

    private String formatTime(String dateTime) {
        return dateTime.substring(11, 16);
    }

    @Override
    public List<Frame> getColumns() {
        return columns;
    }


}
