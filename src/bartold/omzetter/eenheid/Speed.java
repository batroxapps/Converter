package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;
import bartold.omzetter.R;

public class Speed extends Eenheid{

	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*"}, new Double[]{0.621371192});
	public static final int IMAGE_INACTIVE = R.drawable.snelheid_bmp;
	public static final int IMAGE_ACTIVE = R.drawable.snelheid_actief_bmp;

	public Speed(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_SPEED, toHoofd);
	}
}