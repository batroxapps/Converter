package bartold.omzetter;

import static bartold.util.Utils.*;

import bartold.omzetter.eenheid.*;
import bartold.omzetter.eenheid.formule.Formule;
import bartold.omzetter.preset.Preset;
import bartold.omzetter.preset.PresetManagerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import android.app.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
	
	private String[] distanceUnits; 
	private String[] weightUnits;
	private String[] volumeUnits;
	private String[] speedUnits;
	private String[] temperatuurUnits;
	private String[] currentArray = null;
	private String[] grootheden;
	private static Set<String> presetNames = new HashSet<String>();
	private static HashMap<String, Preset> presets = new HashMap<String, Preset>();
	private Map<String, String[]> grootheidArrayMap = new HashMap<String, String[]>();
	
	Spinner linksSpinner = null;
    Spinner middenSpinner = null;
    Spinner rechtsSpinner = null;
	
	Spinner presetSpinner = null;
	Button presetButton = null;
	
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
        
		loadArrays();
		
		
		loadGrootheidArrayMap();
        loadEenheidHashMap();
		
		initSpinners();
		
		initPresets();
		loadSpinnerEvents();
    }
	
	/*
	*
	*	loads the units and the measures into their arrays
	*
	*/
	private void loadArrays(){
		distanceUnits = getResources().getStringArray(R.array.length_units);
		weightUnits = getResources().getStringArray(R.array.weight_units);
		volumeUnits = getResources().getStringArray(R.array.volume_units);
		speedUnits = getResources().getStringArray(R.array.speed_units);
		temperatuurUnits = getResources().getStringArray(R.array.temperature_units);
		
		grootheden = getResources().getStringArray(R.array.measures);
	}
	
	/*
	*
	*	initializes the spinners (sets the adapter for the middle spinner)
	*
	*/
	private void initSpinners(){
		linksSpinner = (Spinner) findViewById(R.id.eenheid_spinner_links);
        middenSpinner = (Spinner) findViewById(R.id.grootheid_spinner);
        rechtsSpinner = (Spinner) findViewById(R.id.eenheid_spinner_rechts);
		
		// adapter for the grootheid spinner
        ArrayAdapter middenAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grootheden);
        middenSpinner.setAdapter(middenAdapter);
        
        setSpinnerArrays();
	}

	/*
	*
	*	initializes the preset spinner, the preset names set and the preset map
	*
	*/
	private void initPresets(){
		presetSpinner = (Spinner) findViewById(R.id.spn_presets);
		presetButton = (Button) findViewById(R.id.btn_presets);
		
		loadPresetArrayLists();
		
		// adapter for the preset spinner
		ArrayAdapter presetAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, presetNames.toArray(new String[presetNames.size()]));
		presetSpinner.setAdapter(presetAdapter);
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
		loadMiddenSpinnerEvents();
		loadUnitSpinnerEvents();
        loadPresetSpinnerEvents();
	}
	
	/*
	*
	*	loads the events on the middle spinner
	*
	*/
	private void loadMiddenSpinnerEvents(){
		middenSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					setSpinnerArrays();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {}
        		
        	}
        );
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
						
						String groot = p.getGrootheid();
						String links = p.getEenheidFrom();
						String rechts = p.getEenheidTo();
						
						ArrayAdapter grootheidAdapter = (ArrayAdapter) middenSpinner.getAdapter();
						
						int midSpinnerPos = grootheidAdapter.getPosition(groot);
						
						middenSpinner.setSelection(midSpinnerPos);
						System.out.println((String)middenSpinner.getSelectedItem());
						
						setSpinnerArrays();
						
						ArrayAdapter linksAdapter = (ArrayAdapter) linksSpinner.getAdapter();
						ArrayAdapter rechtsAdapter = (ArrayAdapter) rechtsSpinner.getAdapter();
						
						int leftSpinnerPos = rechtsAdapter.getPosition(links);
						int rightSpinnerPos = linksAdapter.getPosition(rechts);
						
						linksSpinner.setSelection(leftSpinnerPos);
						rechtsSpinner.setSelection(rightSpinnerPos);
						
						convert((Button) findViewById(R.id.btn_convert));
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
				eenheden.put(distanceUnits[0], mm);
    			eenheden.put(distanceUnits[1], cm);
    			eenheden.put(distanceUnits[2], dm);
    			eenheden.put(distanceUnits[3], m);
    			eenheden.put(distanceUnits[4], dam);
    			eenheden.put(distanceUnits[5], hm);
    			eenheden.put(distanceUnits[6], km);
    			eenheden.put(distanceUnits[7], in);
    			eenheden.put(distanceUnits[8], ft);
    			eenheden.put(distanceUnits[9], yd);
    			eenheden.put(distanceUnits[10], mi);
				
    			eenheden.put(weightUnits[0], g);
    			eenheden.put(weightUnits[1], kg);
    			eenheden.put(weightUnits[2], gr);
    			eenheden.put(weightUnits[3], dr);
    			eenheden.put(weightUnits[4], oz);
    			eenheden.put(weightUnits[5], lb);
				
    			eenheden.put(volumeUnits[0], ml);
    			eenheden.put(volumeUnits[1], mcup);
    			eenheden.put(volumeUnits[2], l);
    			eenheden.put(volumeUnits[3], impfloz);
    			eenheden.put(volumeUnits[4], impcup);
    			eenheden.put(volumeUnits[5], imppt);
    			eenheden.put(volumeUnits[6], impqt);
    			eenheden.put(volumeUnits[7], impgal);
    			eenheden.put(volumeUnits[8], usfloz);
    			eenheden.put(volumeUnits[9], uscup);
    			eenheden.put(volumeUnits[10], uspt);
    			eenheden.put(volumeUnits[11], usqt);
    			eenheden.put(volumeUnits[12], usgal);
				
    			eenheden.put(speedUnits[0], kph);
    			eenheden.put(speedUnits[1], mps);
				eenheden.put(speedUnits[2], knoop);
    			eenheden.put(speedUnits[3], mph);
				
    			eenheden.put(temperatuurUnits[0], k);
    			eenheden.put(temperatuurUnits[1], c);
    			eenheden.put(temperatuurUnits[2], f);
    }
    
    /*
	*
	*	loads the HashMap which holds the arrays and their "grootheid"(measure)
	*
	*/
    private void loadGrootheidArrayMap(){
    	grootheidArrayMap.put(grootheden[0], distanceUnits);
    	grootheidArrayMap.put(grootheden[1], weightUnits);
    	grootheidArrayMap.put(grootheden[2], volumeUnits);
    	grootheidArrayMap.put(grootheden[3], speedUnits);
    	grootheidArrayMap.put(grootheden[4], temperatuurUnits);
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
    public void convert(View button){
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
    	currentArray = getCurrentArray();
    	ArrayAdapter tempAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, currentArray);

		if(!isArrayActive()){
			linksSpinner.setAdapter(null);
			rechtsSpinner.setAdapter(null);
			linksSpinner.setAdapter(tempAdapter);
			rechtsSpinner.setAdapter(tempAdapter);
		}
    }
	
	/*
	*
	*	checks if the new array it has to be is already active	
	*	(if this isn't checked, the presets behave weird)
	*	
	*/
	private boolean isArrayActive(){
		for(String s : currentArray){
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
    private String[] getCurrentArray(){
    	return grootheidArrayMap.get((String) middenSpinner.getSelectedItem());
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
		
		convert((Button) findViewById(R.id.btn_convert));
	}
	
	/*
	*
	*	opens the activity to manage the presets
	*
	*/
	public void openPresetManagerActivity(View Button){
		Intent intent = new Intent(this, PresetManagerActivity.class);
		startActivity(intent);
	}
}
