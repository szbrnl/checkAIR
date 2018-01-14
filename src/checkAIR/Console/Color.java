package checkAIR.Console;

public enum Color {
    TextBlack, TextRed, TextGreen, TextYellow, TextBlue, TextPurple, TextCyan, TextWhite,
    BoldTextBlack, BoldTextRed, BoldTextGreen, BoldTextYellow, BoldTextBlue, BoldTextPurple, BoldTextCyan, BoldTextWhite,
    UnderlineBlack, UnderlineRed, UnderlineGreen, UnderlineYellow, UnderlineBlue, UnderlinePurple, UnderlineCyan, UnderlineWhite,
    HighIntensityTextBlack, HighIntensityTextRed, HighIntensityTextGreen, HighIntensityTextYellow, HighIntensityTextBlue, HighIntensityTextPurple, HighIntensityTextCyan, HighIntensityTextWhite,
    BoldHighIntensityTextBlack, BoldHighIntensityTextRed, BoldHighIntensityTextGreen, BoldHighIntensityTextYellow, BoldHighIntensityTextBlue, BoldHighIntensityTextPurple, BoldHighIntensityTextCyan, BoldHighIntensityTextWhite,
    BackgroundBlack, BackgroundRed, BackgroundGreen, BackgroundYellow, BackgroundBlue, BackgroundPurple, BackgroundCyan, BackgroundWhite,
    HighIntensityBackgroundBlack, HighIntensityBackgroundRed, HighIntensityBackgroundGreen, HighIntensityBackgroundYellow, HighIntensityBackgroundBlue, HighIntensityBackgroundPurple, HighIntensityBackgroundCyan, HighIntensityBackgroundWhute;    ;


    public String getCode() {
        if(ordinal() <= TextWhite.ordinal()) {
            return regularTextColor();
        }
        else if(ordinal() <= BoldTextWhite.ordinal()) {
            return boldTextColor();
        }
        else if(ordinal() <= UnderlineWhite.ordinal()) {
            return underlineTextColor();
        }
        else if(ordinal() <= HighIntensityTextWhite.ordinal()) {
            return highIntensityTextColor();
        }
        else if(ordinal() <= BoldHighIntensityTextWhite.ordinal()) {
            return boldHighIntensityTextColor();
        }
        else if(ordinal() <= BackgroundWhite.ordinal()) {
            return regularBackgroundColor();
        }
        else {
            return highIntensityBackgroundColor();
        }
    }

    private int colorBase() {
        return 30 + ordinal()%8;
    }

    private String regularTextColor() {
        return "\033[0;"+colorBase()+"m";
    }

    private String boldTextColor() {
        return "\033[1;"+colorBase()+"m";
    }

    private String underlineTextColor() {
        return "\033[4;"+colorBase()+"m";
    }

    private String regularBackgroundColor() {
        return "\033["+(colorBase()+10)+"m";
    }

    private String highIntensityTextColor() {
        return "\033[0;"+(colorBase()+60)+"m";
    }

    private String boldHighIntensityTextColor() {
        return "\033[1;"+(colorBase()+60)+"m";
    }

    private String highIntensityBackgroundColor() {
        return "\033[0;"+(colorBase()+70)+"m";
    }

    public String reset() {
        return "\033[0m";
    }

}
