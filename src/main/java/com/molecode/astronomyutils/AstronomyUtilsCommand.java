package com.molecode.astronomyutils;

import picocli.CommandLine;

import java.util.Arrays;
import java.util.concurrent.Callable;

@CommandLine.Command(
		name = "hello",
		description = "Says hello"
)
public class AstronomyUtilsCommand implements Callable<Integer> {
	@CommandLine.Option(
			names = {"-L", "--observing-location"},
			arity = "2",
			required = true
	)
	private String[] observingLocation;

	@Override
	public Integer call() {
		System.out.println(Arrays.toString(observingLocation));
		return 0;
	}

	public static void main(String[] args) {
		int exitCode = new CommandLine(new AstronomyUtilsCommand()).execute(args);
		System.exit(exitCode);
	}

}
