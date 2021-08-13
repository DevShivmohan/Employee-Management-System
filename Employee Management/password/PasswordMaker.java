package password;

public class PasswordMaker {
	public String getBinaryPassword(String inputString) {
		try {
			if (inputString.isEmpty())
				throw new BlankException();
			StringBuilder stringBuilder = new StringBuilder("");
			char[] password = inputString.toCharArray();
			for (char c : password) {
				stringBuilder.append(getBinaryCode(c));
			}
			if (stringBuilder.toString().length() >= 500)
				throw new OutputTooLongException();
			stringBuilder.append(inputString.charAt(0));
			stringBuilder.append((char) 64);
			return stringBuilder.toString();
		} catch (Exception e) {
			return null;
		}
	}

	private long getBinaryCode(long value) {
		StringBuilder str = new StringBuilder("");
		while (value > 0) {
			if (value % 2 == 0)
				str.append("0");
			else
				str.append("1");
			value = value / 2;
		}
		return Long.parseLong(str.reverse().toString());
	}

	public class BlankException extends Exception {
		public BlankException() {
			super("Input String cannot be blank");
		}
	}

	public class OutputTooLongException extends Exception {
		public OutputTooLongException() {
			super("Output too long");
		}
	}
}
