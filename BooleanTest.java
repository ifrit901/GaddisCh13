package ch13java6thedition;

/**
 *
 * @author craig
 */
public class BooleanTest {
	public static void main(String[]args) {
		/*
		boolean isTrue = false;
		System.out.println(isTrue);
		System.out.println(!isTrue);

		if(!isTrue) {
			System.out.println(isTrue + " is true.");
		} else {
			System.out.println(isTrue + " is false");
		}

		// Try to change the boolean value
		isTrue = true;

		if(!isTrue) {
			System.out.println("isTrue is " + isTrue);
		} else
			System.out.println("isTrue is " + !isTrue);
		*/
		int [][] array = new int [3][3];
		int counter = 1;
		for (int i = 0; i < 3; i++) {
			for (int p = 0; p < 3; p++) {
				array[i][p] = counter;
				counter++;
			}
		}
	}

}
