package bartold.omzetter.preset;

public class Preset {
	private final String grootheid;
	private final String eenheidFrom;
	private final String eenheidTo;
	private final String name;
	
	public Preset(String grootheid, String eenheidFrom, String eenheidTo){
		this.grootheid = grootheid;
		this.eenheidFrom = eenheidFrom;
		this.eenheidTo = eenheidTo;
		
		name = eenheidFrom + " --> " + eenheidTo;
	}
	
	public String getGrootheid(){
		return grootheid;
	}
	
	public String getEenheidFrom(){
		return eenheidFrom;
	}
	
	public String getEenheidTo(){
		return eenheidTo;
	}
	
	public String getName(){
		return name;
	}
}