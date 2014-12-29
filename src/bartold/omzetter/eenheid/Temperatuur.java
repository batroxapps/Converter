package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;
import bartold.omzetter.R;

public class Temperatuur extends Eenheid{

	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*", "+"}, new Double[]{1.8, 32.0});
	public static final int IMAGE_INACTIVE = R.drawable.temperatuur_bmp;
	public static final int IMAGE_ACTIVE = R.drawable.temperatuur_actief_bmp;
	
	public Temperatuur(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_TEMPERATUUR, toHoofd);
	}
}