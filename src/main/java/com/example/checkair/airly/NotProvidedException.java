package com.example.checkair.airly;

public class NotProvidedException extends Exception {
    public NotProvidedException() {
        super();
    }

    public NotProvidedException(String message) {
        super(message);
    }
}
