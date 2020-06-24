package com.example.checkair;

import com.example.checkair.airly.MeasurementQualityIndex;
import com.example.checkair.console.Color;
import com.example.checkair.console.ColorConverter;

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
