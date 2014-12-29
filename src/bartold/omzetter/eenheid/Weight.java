package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;
import bartold.omzetter.R;

public class Weight extends Eenheid{
	
	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*"}, new Double[]{2.20462262});
	public static final int IMAGE_INACTIVE = R.drawable.gewicht_bmp;
	public static final int IMAGE_ACTIVE = R.drawable.gewicht_actief_bmp;
	
	public Weight(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_WEIGHT, toHoofd);
	}
	
}