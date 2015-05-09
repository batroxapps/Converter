package bartold.omzetter.eenheid;

import bartold.omzetter.eenheid.formule.Formule;
import bartold.util.Utils;

public abstract class Eenheid{
	
	public static final int SYSTEM_METRIC = 0;
	public static final int SYSTEM_IMPERIAL = 1;
	public static final int SYSTEM_US = 2;
	
	public static final int GROOTHEID_DISTANCE = 0;
	public static final int GROOTHEID_WEIGHT = 1;
	public static final int GROOTHEID_VOLUME = 2;
	public static final int GROOTHEID_SPEED = 3;
	public static final int GROOTHEID_TEMPERATUUR = 4;
	
	public static final int[] GROOTHEDEN = {GROOTHEID_DISTANCE, GROOTHEID_WEIGHT, GROOTHEID_VOLUME, GROOTHEID_SPEED, GROOTHEID_TEMPERATUUR};
	@SuppressWarnings("unused")
	private String eenheid = "";
	private int systeem = -1;
	private int grootheid = -1;
	private Formule toHoofd;
	
	public Eenheid(String e, int s, int g, Formule f){
		eenheid = e;
		if(s == SYSTEM_METRIC || s == SYSTEM_IMPERIAL || s == SYSTEM_US){
			systeem = s;
		}else{
			Utils.writeWarning("This system doesn't exist");
		}
		boolean grootheidBestaat = false;
		
		for(int i : GROOTHEDEN){
			if(g == i){
				grootheidBestaat = true;
			}
		}
		if(!grootheidBestaat){
			Utils.writeWarning("Deze grootheid bestaat niet");
		}else{
			grootheid = g;
		}
		toHoofd = f;
	}
	
	public int getSysteem(){
		return systeem;
	}
	
	public String getSysteemName(){
		if (systeem == 0){
			return "Metric";
		}else if (systeem == 1 || systeem ==2){
			return "Imp";
		}
		return null;
	}
	
	public int getGrootheid(){
		return grootheid;
	}
	
	public Formule getToHoofd(){
		return toHoofd;
	}
	
	public static double convert(Eenheid from, Eenheid to, double value){
		int syst1 = from.getSysteem();
		int syst2 = to.getSysteem();
		int gr1 = from.getGrootheid();
		int gr2 = to.getGrootheid();
		double result = 0;
		if(gr1 == gr2){
			if(syst1 == syst2){
				result = to.getToHoofd().useOmgekeerd(from.getToHoofd().use(value));
			}else if(gr1 == GROOTHEID_VOLUME){
				if(syst1 == SYSTEM_METRIC){
					if(syst2 == SYSTEM_IMPERIAL){
						result = to.getToHoofd().useOmgekeerd(Volume.METRIC_TO_IMPERIAL.use(from.getToHoofd().use(value)));
					}else{
						result = to.getToHoofd().useOmgekeerd(Volume.METRIC_TO_US.use(from.getToHoofd().use(value)));
					}
				}else if(syst1 == SYSTEM_IMPERIAL){
					if(syst2 == SYSTEM_METRIC){
						result = to.getToHoofd().useOmgekeerd(Volume.METRIC_TO_IMPERIAL.useOmgekeerd(from.getToHoofd().use(value)));
					}else{
						result = to.getToHoofd().useOmgekeerd(Volume.METRIC_TO_US.use(Volume.METRIC_TO_IMPERIAL.useOmgekeerd(from.getToHoofd().use(value))));
					}
				}else{
					if(syst2 == SYSTEM_METRIC){
						result = to.getToHoofd().useOmgekeerd(Volume.METRIC_TO_US.useOmgekeerd(from.getToHoofd().use(value)));
					}else{
						result = to.getToHoofd().useOmgekeerd(Volume.METRIC_TO_IMPERIAL.use(Volume.METRIC_TO_US.useOmgekeerd(from.getToHoofd().use(value))));
					}
				}
			}else if(gr1 == GROOTHEID_WEIGHT){
				if(syst1 == SYSTEM_METRIC){
					result = to.getToHoofd().useOmgekeerd(Weight.METRIC_TO_IMPERIAL.use(from.getToHoofd().use(value)));
				}else{
					result = to.getToHoofd().useOmgekeerd(Weight.METRIC_TO_IMPERIAL.useOmgekeerd(from.getToHoofd().use(value)));
				}
			}else if(gr1 == GROOTHEID_DISTANCE){
				if(syst1 == SYSTEM_METRIC){
					result = to.getToHoofd().useOmgekeerd(Distance.METRIC_TO_IMPERIAL.use(from.getToHoofd().use(value)));
				}else{
					result = to.getToHoofd().useOmgekeerd(Distance.METRIC_TO_IMPERIAL.useOmgekeerd(from.getToHoofd().use(value)));
				}
			}else if(gr1 == GROOTHEID_TEMPERATUUR){
				if(syst1 == SYSTEM_METRIC){
					result = to.getToHoofd().useOmgekeerd(Temperatuur.METRIC_TO_IMPERIAL.use(from.getToHoofd().use(value)));
				}else{
					result = to.getToHoofd().useOmgekeerd(Temperatuur.METRIC_TO_IMPERIAL.useOmgekeerd(from.getToHoofd().use(value)));
				}
			}else if(gr1 == GROOTHEID_SPEED){
				if(syst1 == SYSTEM_METRIC){
					result = to.getToHoofd().useOmgekeerd(Speed.METRIC_TO_IMPERIAL.use(from.getToHoofd().use(value)));
				}else{
					result = to.getToHoofd().useOmgekeerd(Speed.METRIC_TO_IMPERIAL.useOmgekeerd(from.getToHoofd().use(value)));
				}
			}
		}else{
			Utils.writeWarning("Niet dezelfde grootheden");
		}
		return result;
	}
	
}
