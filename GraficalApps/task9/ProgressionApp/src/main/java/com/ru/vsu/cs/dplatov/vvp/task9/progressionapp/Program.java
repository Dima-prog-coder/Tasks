package com.ru.vsu.cs.dplatov.vvp.task9.progressionapp;

import com.ru.vsu.cs.dplatov.vvp.task9.progressionapp.utils.ListUtils;
import org.apache.commons.cli.*;
import com.ru.vsu.cs.dplatov.vvp.task9.progressionapp.utils.ArrayUtils;
import com.ru.vsu.cs.dplatov.vvp.task9.progressionapp.Solver;
import com.ru.vsu.cs.dplatov.vvp.task9.progressionapp.ProgressionApplication;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;


public class Program {
    public static final String PROGRAM_NAME_IN_HELP = "program (-h | -w | -i <in-file> [-o <out-file>])";

    public static void winMain() throws Exception {
        ProgressionApplication.main(new String[0]);
    }

    public static void main(String[] args) throws Exception {
        Options cmdLineOptions = new Options();
        cmdLineOptions.addOption("h", "help", false, "Show help");
        cmdLineOptions.addOption("w", "window", false, "Use window user interface");
        cmdLineOptions.addOption("i", "input-file", true, "Input file");
        cmdLineOptions.addOption("o", "output-file", true, "Output file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdLine = null;
        try {
            cmdLine = parser.parse(cmdLineOptions, args);
        } catch (Exception e) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }

        if (cmdLine.hasOption("h")) {
            new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
            System.exit(1);
        }
        if (cmdLine.hasOption("w")) {
            winMain();
        } else {
            if (!cmdLine.hasOption("i")) {
                new HelpFormatter().printHelp(PROGRAM_NAME_IN_HELP, cmdLineOptions);
                System.exit(1);
            }
            String inputFilename = cmdLine.getOptionValue("i");
            List<Integer> list = ListUtils.readIntListFromFile(inputFilename);
            if (list == null) {
                System.err.printf("Can't read array from \"%s\"%n", inputFilename);
                System.exit(2);
            }

            PrintStream out = (cmdLine.hasOption("o")) ? new PrintStream(cmdLine.getOptionValue("o")) : System.out;
            out.println(ListUtils.toString(Solver.process(list)));
            out.close();
        }
    }
}
