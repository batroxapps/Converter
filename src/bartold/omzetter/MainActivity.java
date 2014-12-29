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

import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.os.Bundle;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static HashMap<String, Eenheid> eenheden = new HashMap<String, Eenheid>();
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
	
	private String[] distanceUnitsImp; 
	private String[] distanceUnitsMetric; 
	private String[] weightUnitsImp;
	private String[] weightUnitsMetric;
	private String[] volumeUnitsImp;
	private String[] volumeUnitsMetric;
	private String[] speedUnitsImp;
	private String[] speedUnitsMetric;
	private String[] temperatuurUnitsImp;
	private String[] temperatuurUnitsMetric;
	private String[] currentArray = null;
	private String[] grootheden;
	private static Set<String> presetNames = new HashSet<String>();
	private static HashMap<String, Preset> presets = new HashMap<String, Preset>();
	private Map<String, String[]> grootheidArrayMapImp = new HashMap<String, String[]>();
	private Map<String, String[]> grootheidArrayMapMetric = new HashMap<String, String[]>();
	private String activeGrootheid = "lengte";
	private Map<String, Integer> grootheidImagesMap = new HashMap<String, Integer>();
	
	private String systeemLinks = "Metric";
	private String systeemRechts = "Imp";
	
	Spinner linksSpinner = null;
    Spinner rechtsSpinner = null;
	Spinner presetSpinner = null;
	EditText txtFrom = null;
	
	private int[] eenheidInactiveImages = {	R.drawable.lengte_bmp, R.drawable.gewicht_bmp, 
											R.drawable.volume_bmp, R.drawable.snelheid_bmp,
											R.drawable.temperatuur_bmp};
											
	private int[] eenheidActiveImages = {	R.drawable.lengte_actief_bmp, R.drawable.gewicht_actief_bmp, 
											R.drawable.volume_actief_bmp, R.drawable.snelheid_actief_bmp,
											R.drawable.temperatuur_actief_bmp};
	
	private ImageView imgLengthView;
	private ImageView imgGewichtView;
	private ImageView imgVolumeView;
	private ImageView imgSnelheidView;
	private ImageView imgTempView;
	
	private ImageView[] eenheidImageViews = new ImageView[5];
	
	private final int LENGTE = 0;
	private final int GEWICHT = 1;
	private final int VOLUME = 2;
	private final int SNELHEID = 3;
	private final int TEMPERATUUR = 4;
	
	/*
	*
	*	MAIN METHOD
	*
	*/
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		// activeGrootheid = 
		
		loadArrays();
		loadGrootheidArrayMaps();
        loadEenheidHashMap();
		loadGrootheidImagesMap();
		
		initImageViews();
		initSpinners();
		initPresets();
		
		loadImageViewEvents();
		loadSpinnerEvents();
		
		txtFrom = (EditText) findViewById(R.id.txt_from);
		txtFrom.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event){
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					convert();
					return true;
				}
				return false;
			}
		});
    }
	
	/*
	*
	*	loads the units and the measures into their arrays
	*
	*/
	private void loadArrays(){
		distanceUnitsImp = getResources().getStringArray(R.array.length_units_imp);
		distanceUnitsMetric = getResources().getStringArray(R.array.length_units_metric);
		weightUnitsImp = getResources().getStringArray(R.array.weight_units_imp);
		weightUnitsMetric = getResources().getStringArray(R.array.weight_units_metric);
		volumeUnitsImp = getResources().getStringArray(R.array.volume_units_imp);
		volumeUnitsMetric = getResources().getStringArray(R.array.volume_units_metric);
		speedUnitsImp = getResources().getStringArray(R.array.speed_units_imp);
		speedUnitsMetric = getResources().getStringArray(R.array.speed_units_metric);
		temperatuurUnitsImp = getResources().getStringArray(R.array.temperature_units_imp);
		temperatuurUnitsMetric = getResources().getStringArray(R.array.temperature_units_metric);
		
		grootheden = getResources().getStringArray(R.array.measures);
	}
	
	/*
	*
	*	initializes the spinners (sets the adapter for the middle spinner)
	*
	*/
	private void initSpinners(){
		linksSpinner = (Spinner) findViewById(R.id.eenheid_spinner_links);
        rechtsSpinner = (Spinner) findViewById(R.id.eenheid_spinner_rechts);
        
        setSpinnerArrays();
	}

	/*
	*
	*	initializes the preset spinner, the preset names set and the preset map
	*
	*/
	private void initPresets(){
		presetSpinner = (Spinner) findViewById(R.id.spn_presets);
		// presetButton = (Button) findViewById(R.id.btn_presets);
		
		loadPresetArrayLists();
		
		// adapter for the preset spinner
		ArrayAdapter presetAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, presetNames.toArray(new String[presetNames.size()]));
		presetSpinner.setAdapter(presetAdapter);
	}
	
	private void initImageViews(){
		this.imgLengthView = (ImageView) this.findViewById(R.id.img_length);		
		this.imgGewichtView = (ImageView) this.findViewById(R.id.img_gewicht);
		this.imgVolumeView = (ImageView) this.findViewById(R.id.img_volume);
		this.imgSnelheidView = (ImageView) this.findViewById(R.id.img_snelheid);
		this.imgTempView = (ImageView) this.findViewById(R.id.img_temp);
		
		eenheidImageViews[0] = imgLengthView;
		eenheidImageViews[1] = imgGewichtView;
		eenheidImageViews[2] = imgVolumeView;
		eenheidImageViews[3] = imgSnelheidView;
		eenheidImageViews[4] = imgTempView;
		
		deactivateImages();
		activateImage(LENGTE);
	}
	
	private void deactivateImages(){
		for(int i = 0; i < 5; i++){
			eenheidImageViews[i].setImageResource(eenheidInactiveImages[i]);
		}
	}
	
	private void activateImage(int i){
		eenheidImageViews[i].setImageResource(eenheidActiveImages[i]);
		activeGrootheid = grootheden[i];
	}
	
	/*
	*
	*	adds the name of a preset to the set and the preset itself to the map
	*
	*/
	public static void addPreset(Preset preset){
		presetNames.add(preset.getName());
		presets.put(preset.getName(), preset);
	}
	
	/*
	*
	*	returns the size of the presets map
	*
	*/
	public static int getPresetsSize(){
		return presets.size();
	}
	
	/*
	*
	*	calls the methods to load the events on the preset and middle spinner
	*
	*/
	private void loadSpinnerEvents(){
		loadUnitSpinnerEvents();
        loadPresetSpinnerEvents();
	}
	
	/*
	*
	*	loads the events on the units spinners
	*
	*/
	private void loadUnitSpinnerEvents(){
	
		linksSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
				
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Preset currentPreset = null;
					if(!presetSpinner.getSelectedItem().equals(" ")){
						currentPreset = presets.get(presetSpinner.getSelectedItem());
					}
					
					if(!presetSpinner.getSelectedItem().equals(" ") && !(linksSpinner.getSelectedItem().equals(currentPreset.getEenheidFrom()) && rechtsSpinner.getSelectedItem().equals(currentPreset.getEenheidTo()))){
						ArrayAdapter tmpAdapt = (ArrayAdapter) presetSpinner.getAdapter();
						
						int presetSpinnerPos = tmpAdapt.getPosition(" ");
						
						presetSpinner.setSelection(presetSpinnerPos);
					}
					convert();
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {}
				
			}
		);
		
		rechtsSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
				
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Preset currentPreset = null;
					if(!presetSpinner.getSelectedItem().equals(" ")){
						currentPreset = presets.get(presetSpinner.getSelectedItem());
					}
				
					if(!presetSpinner.getSelectedItem().equals(" ") && !(linksSpinner.getSelectedItem().equals(currentPreset.getEenheidFrom()) && rechtsSpinner.getSelectedItem().equals(currentPreset.getEenheidTo()))){
						ArrayAdapter tmpAdapt = (ArrayAdapter) presetSpinner.getAdapter();
						
						int presetSpinnerPos = tmpAdapt.getPosition(" ");
						
						presetSpinner.setSelection(presetSpinnerPos);
					}
					convert();
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> arg0) {}
				
			}
		);
	}
	
	/*
	*
	*	loads the events on the preset spinner
	*
	*/
	private void loadPresetSpinnerEvents(){
		presetSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			
				@Override
				public void onNothingSelected(AdapterView<?> arg0){}
			
				@Override
				public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){
					String preset = (String) parent.getItemAtPosition(pos);
					
					if(!preset.equals(" ")){
						Preset p = presets.get(preset);
						System.out.println(p.getName() + p.getGrootheid() + p.getEenheidFrom() + p.getEenheidTo());
						
						activeGrootheid = p.getGrootheid();
						String links = p.getEenheidFrom();
						String rechts = p.getEenheidTo();
						
						setSpinnerArrays();
						
						ArrayAdapter linksAdapter = (ArrayAdapter) linksSpinner.getAdapter();
						ArrayAdapter rechtsAdapter = (ArrayAdapter) rechtsSpinner.getAdapter();
						
						int leftSpinnerPos = rechtsAdapter.getPosition(links);
						int rightSpinnerPos = linksAdapter.getPosition(rechts);
						
						linksSpinner.setSelection(leftSpinnerPos);
						rechtsSpinner.setSelection(rightSpinnerPos);
						
						convert();
					}
				}
			
				
			
			}
		);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	/*
	*
	*	loads the map with the units
	*
	*/
    private void loadEenheidHashMap(){
    	//load hashmap : tekst in combobox - eenheden objecten
				eenheden.put(distanceUnitsMetric[0], mm);
    			eenheden.put(distanceUnitsMetric[1], cm);
    			eenheden.put(distanceUnitsMetric[2], dm);
    			eenheden.put(distanceUnitsMetric[3], m);
    			eenheden.put(distanceUnitsMetric[4], dam);
    			eenheden.put(distanceUnitsMetric[5], hm);
    			eenheden.put(distanceUnitsMetric[6], km);
    			eenheden.put(distanceUnitsImp[0], in);
    			eenheden.put(distanceUnitsImp[1], ft);
    			eenheden.put(distanceUnitsImp[2], yd);
    			eenheden.put(distanceUnitsImp[3], mi);
				
    			eenheden.put(weightUnitsMetric[0], g);
    			eenheden.put(weightUnitsMetric[1], kg);
    			eenheden.put(weightUnitsImp[0], gr);
    			eenheden.put(weightUnitsImp[1], dr);
    			eenheden.put(weightUnitsImp[2], oz);
    			eenheden.put(weightUnitsImp[3], lb);
				
    			eenheden.put(volumeUnitsMetric[0], ml);
    			eenheden.put(volumeUnitsMetric[1], mcup);
    			eenheden.put(volumeUnitsMetric[2], l);
    			eenheden.put(volumeUnitsImp[0], impfloz);
    			eenheden.put(volumeUnitsImp[1], impcup);
    			eenheden.put(volumeUnitsImp[2], imppt);
    			eenheden.put(volumeUnitsImp[3], impqt);
    			eenheden.put(volumeUnitsImp[4], impgal);
    			eenheden.put(volumeUnitsImp[5], usfloz);
    			eenheden.put(volumeUnitsImp[6], uscup);
    			eenheden.put(volumeUnitsImp[7], uspt);
    			eenheden.put(volumeUnitsImp[8], usqt);
    			eenheden.put(volumeUnitsImp[9], usgal);
				
    			eenheden.put(speedUnitsMetric[0], kph);
    			eenheden.put(speedUnitsMetric[1], mps);
				eenheden.put(speedUnitsMetric[2], knoop);
    			eenheden.put(speedUnitsImp[0], mph);
				
    			eenheden.put(temperatuurUnitsMetric[0], k);
    			eenheden.put(temperatuurUnitsMetric[1], c);
    			eenheden.put(temperatuurUnitsImp[0], f);
    }
    
    /*
	*
	*	loads the HashMap which holds the arrays and their "grootheid"(measure)
	*
	*/
    private void loadGrootheidArrayMaps(){
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
	
	/*
	*
	*	loads the HashMap which holds the measures and their images
	*
	*/
	private void loadGrootheidImagesMap(){
		grootheidImagesMap.put(grootheden[0], R.drawable.lengte_bmp);
		grootheidImagesMap.put(grootheden[4], R.drawable.temperatuur_bmp);
	}
	
	/*
	*
	*	loads the presets into the ArrayList
	*
	*/
	private void loadPresetArrayLists(){
		presetNames.add(" ");
		// try{
			// if(presetSpinner.getAdapter().getItem(0) != null)
				// presetNames.add("test");
		// }catch(Exception e){}
	}
    
	/*
	*
	*	converts the units	
	*
	*/
    public void convert(){
    	EditText txtFrom = (EditText) findViewById(R.id.txt_from);
    	TextView tvUitkomst = (TextView) findViewById(R.id.txtv_uitkomst);
    	double uitkomst = 0;
    	try{
    		uitkomst = roundDouble(Eenheid.convert(getEenheid((String)linksSpinner.getSelectedItem()), getEenheid((String)rechtsSpinner.getSelectedItem()), Double.parseDouble(txtFrom.getText().toString())), 7);
    	}catch(NumberFormatException e){
    		showErrorMsgBox("Please enter a number!!!");
    	}
    	tvUitkomst.setText(String.valueOf(uitkomst));
	}
    
	/*
	*
	*	sets the contents of the spinner according to the measure selected	
	*
	*/
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void setSpinnerArrays(){
    	ArrayAdapter linksAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getCurrentArray(systeemLinks));
		ArrayAdapter rechtsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getCurrentArray(systeemRechts));

		// if(!isArrayActive()){
			linksSpinner.setAdapter(null);
			rechtsSpinner.setAdapter(null);
			linksSpinner.setAdapter(linksAdapter);
			rechtsSpinner.setAdapter(rechtsAdapter);
		// }
    }
	
	/*
	*
	*	checks if the new array it has to be is already active	
	*	(if this isn't checked, the presets behave weird)
	*	
	*/
	private boolean isArrayActive(){
		for(String s : getCurrentArray(systeemLinks)){
			if(s.equals(linksSpinner.getSelectedItem()))
				return true;
		}
		return false;
	}
    
	/*
	*	
	*	returns the array that has to be in the spinners
	*
	*/
    private String[] getCurrentArray(String systeem){
		if(systeem.equals("Metric"))
			return grootheidArrayMapMetric.get(activeGrootheid);
		else if (systeem.equals("Imp"))
			return grootheidArrayMapImp.get(activeGrootheid);
			
		return null;
    }
    
	/*
	*
	*	return the unit that matches the string	
	*
	*/
    private Eenheid getEenheid(String s){
    	return eenheden.get(s);
    }
	
	/*
	*
	*	switches the units (left to right, right to left)
	*
	*/
	public void switchUnits(View Button){
		String oldLinksEenheid = (String)linksSpinner.getSelectedItem();
		String oldRechtsEenheid = (String)rechtsSpinner.getSelectedItem();
		
		ArrayAdapter linksAdapter = (ArrayAdapter) linksSpinner.getAdapter();
		ArrayAdapter rechtsAdapter = (ArrayAdapter) rechtsSpinner.getAdapter();
		
		int leftSpinnerPos = rechtsAdapter.getPosition(oldRechtsEenheid);
		int rightSpinnerPos = linksAdapter.getPosition(oldLinksEenheid);
		
		linksSpinner.setSelection(leftSpinnerPos);
		rechtsSpinner.setSelection(rightSpinnerPos);
		
		convert();
	}
	
	public void switchSystem(View button){
		if(button == (Button)findViewById(R.id.btn_systeem_l)){
			if(systeemLinks.equals("Metric")){
				systeemLinks = "Imp";
				((Button)button).setText("I");
			}else if(systeemLinks.equals("Imp")){
				systeemLinks = "Metric";
				((Button)button).setText("M");
			}
		} 
		if(button == (Button)findViewById(R.id.btn_systeem_r)){
			if(systeemRechts.equals("Metric")){
				systeemRechts = "Imp";
				((Button)button).setText("I");
			}else if(systeemRechts.equals("Imp")){
				systeemRechts = "Metric";
				((Button)button).setText("M");
			}
		}
		
		setSpinnerArrays();
	}
	
	/*
	*
	*	opens the activity to manage the presets
	*
	*/
	public void openPresetManagerActivity(MenuItem item){
		Intent intent = new Intent(this, PresetManagerActivity.class);
		startActivity(intent);
	}
	
	private void loadImageViewEvents(){
		for(int j = 0; j < 5; j++){
			final int i = j;
			eenheidImageViews[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view){
					if (view == eenheidImageViews[i]){
						deactivateImages();
						activateImage(i);
						setSpinnerArrays();
					}
				}
			});
		}
	}
}
