package org.github.dsauer.graalExample.smallCliApp;

import com.beust.jcommander.JCommander;

public class Application {

    public static void main(String[] args) {
        Args ar = new Args();

        JCommander jc = new JCommander(ar);

        if (args.length == 0) {
            jc.usage();
        }

        jc.parse(args);
    }
}
