package bartold.omzetter.eenheid.formule;

import bartold.util.Utils;

public class Formule{
	
	private String[] bewerkingen;
	private String[] bewerkingenOmgekeerd;
	private Double[] values;
	private Double[] valuesOmgekeerd;
	
	public Formule(String[] s, Double[] i){
		if(s.length == i.length){
			if(checkBewerkingenArray(s)){
				bewerkingen = s;
				values = i;
				loadOmgekeerdeArrays();
			}
		}else{
			Utils.writeWarning("Arrays don't have the same length");
		}
	}
	
	private boolean checkBewerkingenArray(String[] array){
		boolean temp = true;
		checking:
		if(array != null){
			for(String s : array){
				if(s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")){
					temp = true;
				}else{
					temp = false;
					break checking;
				}
			}
		}
		return temp;
	}
	
	private void loadOmgekeerdeArrays(){
		bewerkingenOmgekeerd = new String[bewerkingen.length];
		bewerkingenOmgekeerd = reverseArray(bewerkingen, bewerkingenOmgekeerd);
		valuesOmgekeerd = new Double[values.length];
		valuesOmgekeerd = reverseArray(values, valuesOmgekeerd);
	}
	
	private <T> T[] reverseArray(T[] arrayFrom, T[] arrayTo){
		if(arrayFrom != null && arrayTo != null){
			Integer size = arrayFrom.length;
			for(Integer i = 0; i < size; i++){
				arrayTo[(size - 1) - i] = arrayFrom[i];
			}
		}
		return arrayTo;
	}
	
	public double use(double d){
		double result = d;
		for(int i = 0; i < bewerkingen.length; i++){
			if(bewerkingen[i].equals("+")){
				result += values[i];
			}else if(bewerkingen[i].equals("-")){
				result -= values[i];
			}else if(bewerkingen[i].equals("*")){
				result *= values[i];
			}else if(bewerkingen[i].equals("/")){
				result /= values[i];
			}
		}
		return result;
	}
	
	public double useOmgekeerd(double d){
		double result = d;
		
		for(int i = 0; i < bewerkingenOmgekeerd.length; i++){
			if(bewerkingenOmgekeerd[i].equals("+")){
				result -= valuesOmgekeerd[i];
			}else if(bewerkingenOmgekeerd[i].equals("-")){
				result += valuesOmgekeerd[i];
			}else if(bewerkingenOmgekeerd[i].equals("*")){
				result /= valuesOmgekeerd[i];
			}else if(bewerkingenOmgekeerd[i].equals("/")){
				result *= valuesOmgekeerd[i];
			}
		}
		return result;
	}
}