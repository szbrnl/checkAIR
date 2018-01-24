package checkAIR;

import org.apache.commons.cli.*;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {

    private final Options options = new Options();
    private CommandLine commandLine;

    private String apiKey;
    private double longitude;
    private double latitude;
    private int sensorId;

    private List<Mode> modes;

    public OptionsParser(String[] args) throws ParseException, IllegalArgumentException {

        addOptions();
        modes = new LinkedList<>();

        CommandLineParser parser = new DefaultParser();
        commandLine = parser.parse(options, args);

        checkOptions();

        if (commandLine.hasOption("h")) {
            modes.add(Mode.Help);
            return;
        }
        if (commandLine.hasOption("apikey")) {
            apiKey = commandLine.getOptionValue("apikey");
            modes.add(Mode.ApiKey);
        }
        if (commandLine.hasOption("longitude")) {
            longitude = Double.parseDouble(commandLine.getOptionValue("longitude"));
            latitude = Double.parseDouble(commandLine.getOptionValue("latitude"));
            modes.add(Mode.Coordinates);
        }
        if (commandLine.hasOption("sensorid")) {
            sensorId = Integer.parseInt(commandLine.getOptionValue("sensorid"));
            modes.add(Mode.SensorID);
        }
        if (commandLine.hasOption("history")) {
            modes.add(Mode.History);
        }

        if (commandLine.hasOption("maxdistance")) {
            modes.add(Mode.MaxDistance);
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getSensorId() {
        return sensorId;
    }

    public boolean helpSelected() {
        return modes.get(0) == Mode.Help;
    }

    public boolean coordinatesModeSelected() {
        return modes.contains(Mode.Coordinates);
    }

    public boolean historyModeSelected() {
        return modes.contains(Mode.History);
    }

    public boolean apiKeyGiven() {
        return modes.contains(Mode.ApiKey);
    }


    public void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("checkair", options);
    }

    private void checkOptions() throws IllegalArgumentException {

        if (!commandLine.hasOption("h")) {
            if (commandLine.hasOption("latitude") && !commandLine.hasOption("longitude")) {
                throw new IllegalArgumentException("Both coordinates must be specified");
            } else if (commandLine.hasOption("longitude") && !commandLine.hasOption("latitude")) {
                throw new IllegalArgumentException("Both coordinates must be specified");
            } else if ((commandLine.hasOption("latitude") || commandLine.hasOption("longitude")) && commandLine.hasOption("sensor-id")) {
                throw new IllegalArgumentException("Cannot combine coordinates and sensor id");
            } else if (!commandLine.hasOption("sensorid") && !commandLine.hasOption("longitude"))
                throw new IllegalArgumentException("No mode selected");
            else if (commandLine.hasOption("sensorid") && commandLine.hasOption("maxdistance")) {
                throw new IllegalArgumentException("Cannot set max distance for sensor mode");
            }

        }
    }

    private void addOptions() {
        options.addOption(Option.builder("h").desc("Show help").build());
        options.addOption(Option.builder("apikey").argName("key").hasArg().desc("Airly API key").build());
        options.addOption(Option.builder("sensorid").argName("ID").hasArg().desc("sensor's ID number").build());
        options.addOption(Option.builder("longitude").argName("coordinate").hasArg().build());
        options.addOption(Option.builder("latitude").argName("coordinate").hasArg().build());
        options.addOption(Option.builder("history").desc("show history measurements").build());
        options.addOption(Option.builder("maxdistance").desc("set max distance (default 1000)").build());
    }

}
