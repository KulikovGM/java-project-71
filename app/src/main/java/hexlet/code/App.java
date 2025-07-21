package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    String format = "stylish";

    @Parameters(paramLabel = "filepath1", defaultValue = "filepath1",
            description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", defaultValue = "filepath2",
            description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

    }

    @Override
    public Integer call() throws Exception {
        System.out.println(ReaderAndDiffer.readAndDiff(filepath1, filepath2));
        //System.out.println(ReaderFile.readAndDiff(filepath1, filepath2, format));
        return 0;
    }
}
