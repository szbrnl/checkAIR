package com.example.checkair.console;

import java.util.LinkedList;
import java.util.List;

public class Frame extends ConsoleElement {
    private boolean alignRight;

    public Frame(boolean alignRight) {

        lines = new LinkedList<>();
        linesLengths = new LinkedList<>();
        width = 0;

        this.alignRight = alignRight;
    }

    @Override
    public List<StringBuilder> getLines() {
        align();
        return lines;
    }

    private void align() {
        if (alignRight) {
            for (int i = 0; i < lines.size(); i++) {

                lines.set(i, new StringBuilder(
                        fromChar(' ', width).replace(width - linesLengths.get(i), width, lines.get(i).toString())
                ));

                linesLengths.set(i, width);
            }
        } else {
            for (int i = 0; i < lines.size(); i++) {

                lines.set(i, new StringBuilder(
                        fromChar(' ', width).replace(0, linesLengths.get(i), lines.get(i).toString())
                ));

                linesLengths.set(i, width);
            }
        }

    }


}
