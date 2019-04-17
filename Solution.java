import java.util.Scanner;
import java.util.Stack;

public class Solution {
	/**
	 * "Stack<Operation> undo" stores operations "append" and "delete" in the order
	 * of their occurrence so that they could be undone at a later stage.
	 * 
	 * If the operation is "append", the object "Operation" stores the length of the
	 * appended string.
	 * 
	 * If the operation is "delete", the object "Operation" stores the actual
	 * appended string.
	 */
	private static Stack<Operation> undo;
	private static StringBuilder currentString;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfOperations = scanner.nextInt();
		currentString = new StringBuilder();
		undo = new Stack<Operation>();

		for (int i = 0; i < numberOfOperations; i++) {
			switch (scanner.nextInt()) {
			case 1:
				append(scanner.next());
				break;
			case 2:
				delete(scanner.nextInt());
				break;
			case 3:
				print(scanner.nextInt());
				break;
			case 4:
				undo();
				break;
			default:
				break;
			}
		}
		scanner.close();
	}

	private static void append(String stringToAppend) {
		Operation op = new Operation(1);
		op.lengthOfAppendedString = stringToAppend.length();
		undo.push(op);
		currentString.append(stringToAppend);
	}

	private static void delete(int numberOfChar) {
		Operation op = new Operation(2);
		int fromIndex = currentString.length() - numberOfChar;
		int toIndex = currentString.length();
		op.deletedString = currentString.substring(fromIndex);
		undo.push(op);
		currentString.delete(fromIndex, toIndex);
	}

	private static void print(int positionOfChar) {
		System.out.println(currentString.charAt(positionOfChar - 1));
	}

	private static void undo() {
		Operation op = undo.pop();

		if (op.codeOfOperation == 1) {
			int fromIndex = currentString.length() - op.lengthOfAppendedString;
			int toIndex = currentString.length();
			currentString.delete(fromIndex, toIndex);
		} else if (op.codeOfOperation == 2) {
			currentString.append(op.deletedString);
		}
	}
}

class Operation {
	int codeOfOperation;
	String deletedString;
	int lengthOfAppendedString;

	public Operation(int codeOfOperation) {
		this.codeOfOperation = codeOfOperation;
	}
}
