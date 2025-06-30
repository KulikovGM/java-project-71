package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Runnable {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    String format = "stylish";

    @Parameters(paramLabel = "filepath1", defaultValue = "filepath1",
            description = "path to first file")
    private String[] filepath1 = {"filepath1"};

    @Parameters(paramLabel = "filepath2", defaultValue = "filepath2",
            description = "path to second file")
    private String[] filepath2 = {"filepath2"};

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public void run() {
        try {
            ReaderFile.reading();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
