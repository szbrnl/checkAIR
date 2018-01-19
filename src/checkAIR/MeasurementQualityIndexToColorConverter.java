package checkAIR;

import checkAIR.airly.MeasurementQualityIndex;
import checkAIR.console.Color;
import checkAIR.console.ColorConverter;

public class MeasurementQualityIndexToColorConverter implements ColorConverter<MeasurementQualityIndex> {
    public Color convert(MeasurementQualityIndex index) {
        switch (index) {
            case Good:
                return Color.HighIntensityTextGreen;
            case Moderate:
                return Color.HighIntensityTextYellow;
            case Bad:
                return Color.HighIntensityTextRed;

            default:
                return Color.TextWhite;
        }
    }
}
