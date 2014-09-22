package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;

public class Distance extends Eenheid{	
	
	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*"}, new Double[]{3.2808399});
	
	public Distance(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_DISTANCE, toHoofd);
	}

}