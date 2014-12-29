package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;
import bartold.omzetter.R;

public class Volume extends Eenheid{
	
	public static final Formule METRIC_TO_IMPERIAL = new Formule(new String[]{"*"}, new Double[]{1.75975326});
	public static final Formule METRIC_TO_US = new Formule(new String[]{"*"}, new Double[]{2.11337642});
	public static final int IMAGE_INACTIVE = R.drawable.volume_bmp;
	public static final int IMAGE_ACTIVE = R.drawable.volume_actief_bmp;
	
	public Volume(String e, int s, Formule toHoofd){
		super(e, s, Eenheid.GROOTHEID_VOLUME, toHoofd);
	}

}