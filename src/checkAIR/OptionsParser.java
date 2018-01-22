package checkAIR;

import org.apache.commons.cli.*;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public final Options options = new Options();
    CommandLine cmd;

    private String apiKey;
    private double longitude;
    private double latitude;
    private int sensorId;

    private List<Mode> modes;

    public OptionsParser(String[] args) throws ParseException, IllegalArgumentException {
        addOptions();
        modes = new LinkedList<>();


        CommandLineParser parser = new DefaultParser();
        cmd = parser.parse(options, args);

        checkOptions();

        if (cmd.hasOption("h")) {
            modes.add(Mode.Help);
            return;
        }
        if (cmd.hasOption("apikey")) {
            apiKey = cmd.getOptionValue("apikey");
            modes.add(Mode.ApiKey);
        }
        if (cmd.hasOption("longitude")) {
            longitude = Double.parseDouble(cmd.getOptionValue("longitude"));
            latitude = Double.parseDouble(cmd.getOptionValue("latitude"));
            modes.add(Mode.Coordinates);
        }
        if (cmd.hasOption("sensorid")) {
            sensorId = Integer.parseInt(cmd.getOptionValue("sensorid"));
            modes.add(Mode.SensorID);
        }
        if(cmd.hasOption("history")) {
            modes.add(Mode.History);
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

    public List<Mode> getModes() {
        return modes;
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

        if (!cmd.hasOption("h")) {
            if (cmd.hasOption("latitude") && !cmd.hasOption("longitude")) {
                throw new IllegalArgumentException("Both coordinates must be specified");
            }

            else if (cmd.hasOption("longitude") && !cmd.hasOption("latitude")) {
                throw new IllegalArgumentException("Both coordinates must be specified");
            }

            else if ((cmd.hasOption("latitude") || cmd.hasOption("longitude")) && cmd.hasOption("sensor-id")) {
                throw new IllegalArgumentException("Cannot combine coordinates and sensor id");
            }
            else if(!cmd.hasOption("sensorid") && !cmd.hasOption("longitude"))
                throw new IllegalArgumentException("No mode selected");

        }
    }

    private void addOptions() {
        options.addOption(Option.builder("h").desc("Show help").build());
        options.addOption(Option.builder("apikey").argName("key").hasArg().desc("Airly API key").build());
        options.addOption(Option.builder("sensorid").argName("ID").hasArg().desc("sensor's ID number").build());
        options.addOption(Option.builder("longitude").argName("coordinate").hasArg().build());
        options.addOption(Option.builder("latitude").argName("coordinate").hasArg().build());
        options.addOption(Option.builder("history").desc("show history measurements").build());
     }

}
