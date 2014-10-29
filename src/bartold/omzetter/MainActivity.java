package bartold.omzetter;

import static bartold.util.Utils.*;

import bartold.omzetter.eenheid.*;
import bartold.omzetter.eenheid.formule.Formule;
import bartold.omzetter.preset.Preset;
import bartold.omzetter.preset.PresetManagerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private String[] weightUnits = {"gram", "kilogram", "grain", "dram", "ounce", "pound"};
	private String[] volumeUnits = {"milliliter", "metric cup", "liter", "fl oz (imp)", "cup (imp)",  "pint (imp)", "quart (imp)", "gallon (imp)", "fl oz (US)", "cup (US)", "pint (US)", "quart (US)", "gallon (US)"};
	private String[] speedUnits = {"kilometer/uur", "meter/s", "knopen", "mijl/uur"};
	private String[] temperatuurUnits = {"kelvin", "celsius", "fahrenheit"};
	private String[] currentArray = null;
	private String[] grootheden;
	private static ArrayList<String> presetNames = new ArrayList<String>();
	private static HashMap<String, Preset> presets = new HashMap<String, Preset>();
	private Map<String, String[]> grootheidArrayMap = new HashMap<String, String[]>();
	
	Spinner linksSpinner = null;
    Spinner middenSpinner = null;
    Spinner rechtsSpinner = null;
	
	Spinner presetSpinner = null;
	Button presetButton = null;
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		distanceUnits = getResources().getStringArray(R.array.length_units);
		grootheden = getResources().getStringArray(R.array.measures);
		
		loadGrootheidArrayMap();
        loadEenheidHashMap();
		
		initSpinners();
		
		initPresets();
		loadSpinnerEvents();
    }
	
	private void initSpinners(){
		linksSpinner = (Spinner) findViewById(R.id.eenheid_spinner_links);
        middenSpinner = (Spinner) findViewById(R.id.grootheid_spinner);
        rechtsSpinner = (Spinner) findViewById(R.id.eenheid_spinner_rechts);
		
		// adapter for the grootheid spinner
        ArrayAdapter middenAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grootheden);
        middenSpinner.setAdapter(middenAdapter);
        
        setSpinnerArrays();
	}

	private void initPresets(){
		presetSpinner = (Spinner) findViewById(R.id.spn_presets);
		presetButton = (Button) findViewById(R.id.btn_presets);
		
		loadPresetArrayLists();
		
		// adapter for the preset spinner
		ArrayAdapter presetAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, convertArrayListToArray(presetNames));
		presetSpinner.setAdapter(presetAdapter);
	}
	
	public static void addPreset(Preset preset){
		presetNames.add(preset.getName());
		presets.put(preset.getName(), preset);
	}
	
	private void loadSpinnerEvents(){
		loadMiddenSpinnerEvents();
        loadPresetSpinnerEvents();
	}
	
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
	
	private void loadPresetSpinnerEvents(){
		presetSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			
				@Override
				public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3){
					String preset = (String) parent.getItemAtPosition(pos);
					
					if(!preset.equals("test")){
						Preset p = presets.get(preset);
						System.out.println(p.getName() + p.getGrootheid() + p.getEenheidFrom() + p.getEenheidTo());
						
						String groot = p.getGrootheid();
						String links = p.getEenheidFrom();
						String rechts = p.getEenheidTo();
						
						ArrayAdapter grootheidAdapter = (ArrayAdapter) middenSpinner.getAdapter();
						ArrayAdapter linksAdapter = (ArrayAdapter) linksSpinner.getAdapter();
						ArrayAdapter rechtsAdapter = (ArrayAdapter) rechtsSpinner.getAdapter();
						
						int midSpinnerPos = grootheidAdapter.getPosition(groot);
						
						middenSpinner.setSelection(midSpinnerPos);
						
						int leftSpinnerPos = rechtsAdapter.getPosition(links);
						int rightSpinnerPos = linksAdapter.getPosition(rechts);
						
						linksSpinner.setSelection(leftSpinnerPos);
						rechtsSpinner.setSelection(rightSpinnerPos);
						System.out.println(midSpinnerPos + ", " + leftSpinnerPos + ", " + rightSpinnerPos);
					}
				}
			
				@Override
				public void onNothingSelected(AdapterView<?> arg0){}
			
			}
		);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
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
    			eenheden.put("gram", g);
    			eenheden.put("kilogram", kg);
    			eenheden.put("grain", gr);
    			eenheden.put("dram", dr);
    			eenheden.put("ounce", oz);
    			eenheden.put("pound", lb);
    			eenheden.put("milliliter", ml);
    			eenheden.put("liter", l);
    			eenheden.put("metric cup", mcup);
    			eenheden.put("fl oz (imp)", impfloz);
    			eenheden.put("cup (imp)", impcup);
    			eenheden.put("pint (imp)", imppt);
    			eenheden.put("quart (imp)", impqt);
    			eenheden.put("gallon (imp)", impgal);
    			eenheden.put("fl oz (US)", usfloz);
    			eenheden.put("cup (US)", uscup);
    			eenheden.put("pint (US)", uspt);
    			eenheden.put("quart (US)", usqt);
    			eenheden.put("gallon (US)", usgal);
    			eenheden.put("kilometer/uur", kph);
    			eenheden.put("meter/s", mps);
				eenheden.put("knopen", knoop);
    			eenheden.put("mijl/uur", mph);
    			eenheden.put("celsius", c);
    			eenheden.put("kelvin", k);
    			eenheden.put("fahrenheit", f);
    }
    
    // loads the HashMap which holds the arrays and their "grootheid"
    private void loadGrootheidArrayMap(){
    	grootheidArrayMap.put(grootheden[0], distanceUnits);
    	grootheidArrayMap.put(grootheden[1], weightUnits);
    	grootheidArrayMap.put(grootheden[2], volumeUnits);
    	grootheidArrayMap.put(grootheden[3], speedUnits);
    	grootheidArrayMap.put(grootheden[4], temperatuurUnits);
    }
	
	// loads the presets into the ArrayList
	private void loadPresetArrayLists(){
		presetNames.add("test");
	}
    
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
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void setSpinnerArrays(){
    	currentArray = getCurrentArray();
    	ArrayAdapter tempAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, currentArray);

    	linksSpinner.setAdapter(null);
    	rechtsSpinner.setAdapter(null);
    	linksSpinner.setAdapter(tempAdapter);
    	rechtsSpinner.setAdapter(tempAdapter);
    }
    
    private String[] getCurrentArray(){
    	return grootheidArrayMap.get((String)middenSpinner.getSelectedItem());
    }
    
    private Eenheid getEenheid(String s){
    	return eenheden.get(s);
    }
	
	// switches the units (left to right, right to left)
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
	
	//opens the activity to manage the presets
	public void openPresetManagerActivity(View Button){
		Intent intent = new Intent(this, PresetManagerActivity.class);
		startActivity(intent);
	}
}
