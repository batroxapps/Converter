package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;

public class Weight extends Eenheid{
	
	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*"}, new Double[]{2.20462262});
	
	public Weight(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_WEIGHT, toHoofd);
	}
	
}