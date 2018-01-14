package checkAIR.console;

public enum ViewMode {
    CurrentMeasurements, History;

    public Class getViewClass() {
        switch(this) {
            case CurrentMeasurements:
                return CurrentMeasurementsView.class;
//            case History:
//                return
            default:
                return null;
        }
    }
}
