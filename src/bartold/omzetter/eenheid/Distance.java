package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;
import bartold.omzetter.R;

public class Distance extends Eenheid{	
	
	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*"}, new Double[]{3.2808399});
	public static final int IMAGE_INACTIVE = R.drawable.lengte_bmp;
	public static final int IMAGE_ACTIVE = R.drawable.lengte_actief_bmp;
	
	public Distance(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_DISTANCE, toHoofd);
	}

}