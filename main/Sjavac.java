package main;

import java.io.File;

public class Sjavac {
	
	private static final int IO_FAILURE_NUM = 2;
	private static final int ILLEGAL_CODE_NUM = 1;
	private static final int KOSHER_CODE_NUM = 0;
	private static final int SRC_FILE_INDEX = 0;

	public static void main(String[] args) {
		
		// Make sure we received the necessary arguments:
		if (args.length < 1) {
			System.out.println(IO_FAILURE_NUM);
			System.err.println("ERROR: no command line arg provided.");
			System.err.println("USAGE: please provide source file path as cmd line argument.");
			return;
		}
		
		// Verify that the file exists and can be read:
		File srcFile = new File(args[SRC_FILE_INDEX]);
		if (!srcFile.exists() || !srcFile.canRead()) {
			System.out.println(IO_FAILURE_NUM);
			System.err.println("ERROR: file doesn't exist or can't be read.");
			return;
		}
		
	}

}
