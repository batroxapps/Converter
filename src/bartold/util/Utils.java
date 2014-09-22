package bartold.util;


/*  
*  class that will have a lot of handy stuff in it
*/


public class Utils{
	// writes a string and line to the cmd
	public static void write(String s){
		System.out.println(s);
	}
	
	// prints a string to the cmd (no \n)
	public static void print(String s){
		System.out.print(s);
	}
	
	// writes a blank line, "!!!!! WARNING !!!!!", a string and another blank line to the screen
	public static void writeWarning(String s){
		write("");
		write("!!!!! WARNING !!!!!");
		write(s);
		write("");
	}
	
	public static void showErrorMsgBox(String s){
		// JOptionPane.showMessageDialog(null, s, s, JOptionPane.ERROR_MESSAGE);
		writeWarning(s);
	}
	
	// rounds a double on a given amount of numbers after the comma
	public static double roundDouble(double value, int places){
		if(places < 0){
			writeWarning("A double can't have a negative amount of numbers after the comma!!");
		}
		
		long factor = (long) Math.pow(10, places);
		value *= factor;
		long tmp = Math.round(value);
		return (double)tmp / factor;
	}
	
	// returns a random double within a range
	public static double randomDouble(double min, double max){
		/* 
		 * TODO
		*/
		return 0;
	}
	
	public static <T> boolean isElementPresentInArray(T[] array, T par){
		for(T t : array){
			if(t.equals(par)){
				return true;
			}
		}
		return false;
	}
	
	public static Character[] primitiveToBoxedCharArray(char[] beginArray){
		Character[] resultArray = new Character[beginArray.length];
		for(int i = 0; i < beginArray.length; i++){
			resultArray[i] = beginArray[i];
		}
		return resultArray;
	}
}