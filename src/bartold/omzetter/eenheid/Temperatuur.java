package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;

public class Temperatuur extends Eenheid{

	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*", "+"}, new Double[]{1.8, 32.0});

	public Temperatuur(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_TEMPERATUUR, toHoofd);
	}
}