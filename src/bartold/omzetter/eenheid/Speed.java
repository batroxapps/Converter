package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;

public class Speed extends Eenheid{

	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*"}, new Double[]{0.621371192});

	public Speed(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_SPEED, toHoofd);
	}
}