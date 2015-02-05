package bartold.omzetter;

import static bartold.util.Utils.*;

import bartold.omzetter.eenheid.*;
import bartold.omzetter.eenheid.formule.Formule;
import bartold.omzetter.preset.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import android.app.Activity;

import android.widget.ImageView;

public class DataManager{

	//Distance metric units
	private static Distance mm = new Distance("mm", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"/"}, new Double[]{1000d}));
	private static Distance cm = new Distance("cm", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"/"}, new Double[]{100d}));
	private static Distance dm = new Distance("dm", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"/"}, new Double[]{10d}));
	private static Distance m = new Distance("m", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"/"}, new Double[]{1d}));
	private static Distance dam = new Distance("dam", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"*"}, new Double[]{10d}));
	private static Distance hm = new Distance("hm", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"*"}, new Double[]{100d}));
	private static Distance km = new Distance("km", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"*"}, new Double[]{1000d}));
	//Distance imperial units
	private static Distance in = new Distance("in", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"/"}, new Double[]{12d}));
	private static Distance ft = new Distance("ft", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"*"}, new Double[]{1d}));
	private static Distance yd = new Distance("yd", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"*"}, new Double[]{3d}));
	private static Distance mi = new Distance("mi", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"*"}, new Double[]{5280d}));
	// Weight metric units
	private static Weight g = new Weight("g", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"/"}, new Double[]{1000d}));
	private static Weight kg = new Weight("kg", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"*"}, new Double[]{1d}));
	// Weight imperial units
	private static Weight gr = new Weight("gr", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"/"}, new Double[]{7000d}));
	private static Weight dr = new Weight("dr", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"/"}, new Double[]{256d}));
	private static Weight oz = new Weight("oz", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"/"}, new Double[]{16d}));
	private static Weight lb = new Weight("lb", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"*"}, new Double[]{1d}));
	// Volume metric units
	private static Volume ml = new Volume("ml", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"/"}, new Double[]{1000d}));
	private static Volume mcup = new Volume("metric cup", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"/"}, new Double[]{4d}));
	private static Volume l = new Volume("l", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"/"}, new Double[]{1d}));
	// Volume imperial units
	private static Volume impfloz = new Volume("imp fl oz", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"/"}, new Double[]{20d}));
	private static Volume impcup = new Volume("imp cup", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"/"}, new Double[]{2d}));
	private static Volume imppt = new Volume("imp pt", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"/"}, new Double[]{1d}));
	private static Volume impqt = new Volume("imp qt", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"*"}, new Double[]{2d}));
	private static Volume impgal = new Volume("imp gal", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"*"}, new Double[]{8d}));
	// Volume US units
	private static Volume usfloz = new Volume("us fl oz", Eenheid.SYSTEM_US, new Formule(new String[]{"/"}, new Double[]{16d}));
	private static Volume uscup = new Volume("us cup", Eenheid.SYSTEM_US, new Formule(new String[]{"/"}, new Double[]{2d}));
	private static Volume uspt = new Volume("us pt", Eenheid.SYSTEM_US, new Formule(new String[]{"/"}, new Double[]{1d}));
	private static Volume usqt = new Volume("us qt", Eenheid.SYSTEM_US, new Formule(new String[]{"*"}, new Double[]{2d}));
	private static Volume usgal = new Volume("us gal", Eenheid.SYSTEM_US, new Formule(new String[]{"*"}, new Double[]{8d}));
	// Speed metric units
	private static Speed kph = new Speed("kph", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"*"}, new Double[]{1d}));
	private static Speed mps = new Speed("mps", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"*"}, new Double[]{3.6d}));
	private static Speed knoop = new Speed("knopen", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"*"}, new Double[]{1.852d}));
	// Speed imperial units
	private static Speed mph = new Speed("mph", Eenheid.SYSTEM_IMPERIAL, new Formule(new String[]{"*"}, new Double[]{1d}));
	// metric temperatuur units (Kelvin is initizialized as imperial, but is metric
	private static Temperatuur c = new Temperatuur("c", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"*"}, new Double[]{1d}));
	private static Temperatuur k = new Temperatuur("k", Eenheid.SYSTEM_METRIC, new Formule(new String[]{"-"}, new Double[]{273.15}));
	// us temperature units
	private static Temperatuur f = new Temperatuur("f", Eenheid.SYSTEM_US, new Formule(new String[]{"*"}, new Double[]{1d}));

	private static String[] grootheden;
	
	private static String[] distanceUnitsImp; 
	private static String[] distanceUnitsMetric; 
	private static String[] weightUnitsImp;
	private static String[] weightUnitsMetric;
	private static String[] volumeUnitsImp;
	private static String[] volumeUnitsMetric;
	private static String[] speedUnitsImp;
	private static String[] speedUnitsMetric;
	private static String[] temperatuurUnitsImp;
	private static String[] temperatuurUnitsMetric;
	
	private static HashMap<String, Eenheid> eenhedenMap = new HashMap<String, Eenheid>();
	
	private static Set<String> presetNames = new HashSet<String>();
	private static HashMap<String, Preset> presets = new HashMap<String, Preset>();
	
	private static Map<String, String[]> grootheidArrayMapImp = new HashMap<String, String[]>();
	private static Map<String, String[]> grootheidArrayMapMetric = new HashMap<String, String[]>();

	private static Map<String, Integer> systeemImagesMap = new HashMap<String, Integer>();
	private static ImageView[] grootheidImageViews = new ImageView[5];
	private static ImageView[] grootheidImageViewsPreset = new ImageView[5];
	
	private static Activity activity;
	
	public static void init(Activity a){
		activity = a;
		
		loadArrays();
		loadEenheidHashMap();
		loadGrootheidArrayMaps();
		loadSysteemImagesMap();
		loadUnitImageviews();
		loadPresetUnitImageviews();
	}
	
	/*
	 *
	 * METHODS FOR LOADING THE ARRAYS/MAPS/SETS
	 *
	*/
	
	// loads the arrays
	private static void loadArrays(){
		distanceUnitsImp = activity.getResources().getStringArray(R.array.length_units_imp);
		distanceUnitsMetric = activity.getResources().getStringArray(R.array.length_units_metric);
		weightUnitsImp = activity.getResources().getStringArray(R.array.weight_units_imp);
		weightUnitsMetric = activity.getResources().getStringArray(R.array.weight_units_metric);
		volumeUnitsImp = activity.getResources().getStringArray(R.array.volume_units_imp);
		volumeUnitsMetric = activity.getResources().getStringArray(R.array.volume_units_metric);
		speedUnitsImp = activity.getResources().getStringArray(R.array.speed_units_imp);
		speedUnitsMetric = activity.getResources().getStringArray(R.array.speed_units_metric);
		temperatuurUnitsImp = activity.getResources().getStringArray(R.array.temperature_units_imp);
		temperatuurUnitsMetric = activity.getResources().getStringArray(R.array.temperature_units_metric);
		
		grootheden = activity.getResources().getStringArray(R.array.measures);
	}
	
	// populates the String in combobox - Eenheid objects Map
	private static void loadEenheidHashMap(){
		eenhedenMap.put(distanceUnitsMetric[0], mm);
		eenhedenMap.put(distanceUnitsMetric[1], cm);
		eenhedenMap.put(distanceUnitsMetric[2], dm);
		eenhedenMap.put(distanceUnitsMetric[3], m);
		eenhedenMap.put(distanceUnitsMetric[4], dam);
		eenhedenMap.put(distanceUnitsMetric[5], hm);
		eenhedenMap.put(distanceUnitsMetric[6], km);
		eenhedenMap.put(distanceUnitsImp[0], in);
		eenhedenMap.put(distanceUnitsImp[1], ft);
		eenhedenMap.put(distanceUnitsImp[2], yd);
		eenhedenMap.put(distanceUnitsImp[3], mi);
		
		eenhedenMap.put(weightUnitsMetric[0], g);
		eenhedenMap.put(weightUnitsMetric[1], kg);
		eenhedenMap.put(weightUnitsImp[0], gr);
		eenhedenMap.put(weightUnitsImp[1], dr);
		eenhedenMap.put(weightUnitsImp[2], oz);
		eenhedenMap.put(weightUnitsImp[3], lb);
		
		eenhedenMap.put(volumeUnitsMetric[0], ml);
		eenhedenMap.put(volumeUnitsMetric[1], mcup);
		eenhedenMap.put(volumeUnitsMetric[2], l);
		eenhedenMap.put(volumeUnitsImp[0], impfloz);
		eenhedenMap.put(volumeUnitsImp[1], impcup);
		eenhedenMap.put(volumeUnitsImp[2], imppt);
		eenhedenMap.put(volumeUnitsImp[3], impqt);
		eenhedenMap.put(volumeUnitsImp[4], impgal);
		eenhedenMap.put(volumeUnitsImp[5], usfloz);
		eenhedenMap.put(volumeUnitsImp[6], uscup);
		eenhedenMap.put(volumeUnitsImp[7], uspt);
		eenhedenMap.put(volumeUnitsImp[8], usqt);
		eenhedenMap.put(volumeUnitsImp[9], usgal);
		
		eenhedenMap.put(speedUnitsMetric[0], kph);
		eenhedenMap.put(speedUnitsMetric[1], mps);
		eenhedenMap.put(speedUnitsMetric[2], knoop);
		eenhedenMap.put(speedUnitsImp[0], mph);
		
		eenhedenMap.put(temperatuurUnitsMetric[0], k);
		eenhedenMap.put(temperatuurUnitsMetric[1], c);
		eenhedenMap.put(temperatuurUnitsImp[0], f);
    }
	
	// loads the measures with the unit arrays - filtered by system
	private static void loadGrootheidArrayMaps(){
    	grootheidArrayMapImp.put(grootheden[0], distanceUnitsImp);
    	grootheidArrayMapImp.put(grootheden[1], weightUnitsImp);
    	grootheidArrayMapImp.put(grootheden[2], volumeUnitsImp);
    	grootheidArrayMapImp.put(grootheden[3], speedUnitsImp);
    	grootheidArrayMapImp.put(grootheden[4], temperatuurUnitsImp);
		
		grootheidArrayMapMetric.put(grootheden[0], distanceUnitsMetric);
    	grootheidArrayMapMetric.put(grootheden[1], weightUnitsMetric);
    	grootheidArrayMapMetric.put(grootheden[2], volumeUnitsMetric);
    	grootheidArrayMapMetric.put(grootheden[3], speedUnitsMetric);
    	grootheidArrayMapMetric.put(grootheden[4], temperatuurUnitsMetric);
    }
	
	// loads the ImageViews for system selection
	private static void loadSysteemImagesMap(){
		systeemImagesMap.put("Metric", R.drawable.lengte_bmp);
		systeemImagesMap.put("Imp", R.drawable.imperiaal_bmp);
	}
	
	// loads the ImageViews for measure selection
	private static void loadUnitImageviews(){
		grootheidImageViews[0] = (ImageView) activity.findViewById(R.id.img_length);
		grootheidImageViews[1] = (ImageView) activity.findViewById(R.id.img_gewicht);
		grootheidImageViews[2] = (ImageView) activity.findViewById(R.id.img_volume);
		grootheidImageViews[3] = (ImageView) activity.findViewById(R.id.img_snelheid);
		grootheidImageViews[4] = (ImageView) activity.findViewById(R.id.img_temp);
	}
	
	// loads the ImageViews for measure selection for PresetAddActivity
	private static void loadPresetUnitImageviews(){
		grootheidImageViewsPreset[0] = (ImageView) activity.findViewById(R.id.img_length_preset);
		grootheidImageViewsPreset[1] = (ImageView) activity.findViewById(R.id.img_gewicht_preset);
		grootheidImageViewsPreset[2] = (ImageView) activity.findViewById(R.id.img_volume_preset);
		grootheidImageViewsPreset[3] = (ImageView) activity.findViewById(R.id.img_snelheid_preset);
		grootheidImageViewsPreset[4] = (ImageView) activity.findViewById(R.id.img_temp_preset);
	}
	
	// adds presets
	public static void addPreset(Preset p){
		presetNames.add(p.getName());
		presets.put(p.getName(), p);
	}
	
	/*
	 *
	 *	returns all the data
	 *
	*/
	// returns the array with the measures
	public static String[] getGrootheden(){
		return grootheden;
	}
	
	// returns the String in combobox - Eenheid objects Map
	public static HashMap<String, Eenheid> getEenhedenHashMap(){
		return eenhedenMap;
	} 
	
	// returns the Set with the Preset names
	public static Set<String> getPresetNames(){
		return presetNames;
	}
	
	// returns the Map with the presets
	public static HashMap<String, Preset> getPresets(){
		return presets;
	}
	
	// returns the amount of presets saved
	public static int getPresetsSize(){
		return presets.size();
	}
	
	// returns the system image - array 
	public static Map<String, Integer> getSysteemImagesMap(){
		return systeemImagesMap;
	}
	
	// returns the array with measure images
	public static ImageView[] getGrootheidImageViews(){
		return grootheidImageViews;
	}
	
	// returns the array with measure images
	public static ImageView[] getGrootheidImageViewsPreset(){
		return grootheidImageViewsPreset;
	}
	
	// returns the array with the metric unit arrays
	public static Map<String, String[]> getGrootheidArrayMapMetric(){
		return grootheidArrayMapMetric;
	}
	
	// returns the array with the imperial unit arrays
	public static Map<String, String[]> getGrootheidArrayMapImp(){
		return grootheidArrayMapImp;
	}
	
	
}