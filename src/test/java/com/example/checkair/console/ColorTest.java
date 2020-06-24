package com.example.checkair.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorTest {

    @Test
    void getCode() {
        assertEquals("\033[0;31m", Color.TextRed.getCode());
        assertEquals("\033[0;37m", Color.TextWhite.getCode());

        assertEquals("\033[1;30m", Color.BoldTextBlack.getCode());
        assertEquals("\033[1;37m", Color.BoldTextWhite.getCode());

        assertEquals("\033[4;32m", Color.UnderlineGreen.getCode());
        assertEquals("\033[4;36m", Color.UnderlineCyan.getCode());

        assertEquals("\033[46m", Color.BackgroundCyan.getCode());
        assertEquals("\033[40m", Color.BackgroundBlack.getCode());




        assertEquals("\033[0;101m", Color.HighIntensityBackgroundRed.getCode());
    }
}