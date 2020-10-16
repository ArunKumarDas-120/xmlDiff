package org.xxx.diff;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.text.diff.EditScript;
import org.apache.commons.text.diff.StringsComparator;
import org.xxx.visitor.FileCommandsVisitor;

public class FindDiff {

	private FileCommandsVisitor visitor = new FileCommandsVisitor();

	public String generateDiffrence(File leftFile, File rightFile) throws IOException {
		Objects.requireNonNull(leftFile);
		Objects.requireNonNull(rightFile);
		LineIterator leftItr = FileUtils.lineIterator(leftFile, "utf-8");
		LineIterator rightItr = FileUtils.lineIterator(rightFile, "utf-8");
		while (leftItr.hasNext() || rightItr.hasNext()) {
			String left = (leftItr.hasNext() ? leftItr.nextLine() : "") + "\n";
			String right = (rightItr.hasNext() ? rightItr.nextLine() : "") + "\n";
			StringsComparator comparator = new StringsComparator(left, right);
			EditScript<Character> scr = comparator.getScript();
			System.out.println(String.format("Modification length %d and LCSLength %d", scr.getModifications(),
					scr.getLCSLength()));
			comparator.getScript().visit(visitor);
		}
		visitor.generateHTML();
		return null;
	}
}
