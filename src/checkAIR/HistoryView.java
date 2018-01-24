package checkAIR;

import checkAIR.airly.MeasurementType;
import checkAIR.console.Color;
import checkAIR.console.Frame;
import checkAIR.console.IConsoleView;

import java.util.LinkedList;
import java.util.List;


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

//TODO null check jak w current
    public void addMeasurement(String fromDateTime, String tillDateTime, List<MeasurementType> measurementTypes, List<Integer> values, List<Color> colors) {

        if (historyFrame.getHeight() + measurementTypes.size() > maxHeight) {
            addNewColumn();
        }

//TODO zrobić z tym coś ładnego
        historyFrame.add(formatDateTime(fromDateTime) + " " + measurementTypes.get(0).getName() + " "
                + colors.get(0).getCode() + values.get(0).toString() + " " + measurementTypes.get(0).getUnit() + colors.get(0).Reset);


        historyFrame.add(formatDateTime(tillDateTime) + " " + measurementTypes.get(1).getName() + " "
                + colors.get(1).getCode() + values.get(1).toString() + " " + measurementTypes.get(1).getUnit() + colors.get(1).Reset);

        historyFrame.add("");
    }

    private void addNewColumn() {
        historyFrame = new Frame(false);
        columns.add(historyFrame);
    }

    private String formatDateTime(String dateTime) {
        return dateTime.substring(0, 10) + " "
                + dateTime.substring(11, 16);
    }

    @Override
    public List<Frame> getColumns() {
        return columns;
    }


}
