package org.xxx.main;

import java.io.File;

import org.xxx.diff.FindDiff;

public class Test {

	public static void main(String[] args) {
		FindDiff diff = new FindDiff();
		try {
			diff.generateDiffrence(new File("/left.txt"), new File("/right.txt"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
