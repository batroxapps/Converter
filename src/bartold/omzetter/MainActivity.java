package bartold.omzetter;

import static bartold.util.Utils.*;

import bartold.omzetter.DataManager;
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

	private static HashMap<String, Eenheid> eenhedenMap = new HashMap<String, Eenheid>();
	
	private String[] currentArray = null;
	private String[] grootheden;
	private static Set<String> presetNames = new HashSet<String>();
	private static HashMap<String, Preset> presets = new HashMap<String, Preset>();
	private Map<String, String[]> grootheidArrayMapImp = new HashMap<String, String[]>();
	private Map<String, String[]> grootheidArrayMapMetric = new HashMap<String, String[]>();
	private String activeGrootheid = "lengte";
	private Map<String, Integer> grootheidImagesMap = new HashMap<String, Integer>();
	private Map<String, Integer> systeemImagesMap = new HashMap<String, Integer>();
	
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
	
	private int metrischSysteem = R.drawable.lengte_bmp;
	private int impSysteem = R.drawable.imperiaal_bmp;
	
	private ImageView imgSysteemLinksView;
	private ImageView imgSysteemRechtsView;
	
	private ImageView imgLengthView;
	private ImageView imgGewichtView;
	private ImageView imgVolumeView;
	private ImageView imgSnelheidView;
	private ImageView imgTempView;
	
	private ImageView[] grootheidImageViews = new ImageView[5];
	
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
    }
	
	protected void onStart(){
		super.onStart();
		
		DataManager.init(this);
		
		loadArrays();
		loadGrootheidArrayMaps();
        loadEenheidHashMap();
		loadSysteemImagesMap();
		
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
		grootheden = DataManager.getGrootheden();
	}
	
	private void loadGrootheidArrayMaps(){
    	grootheidArrayMapImp = DataManager.getGrootheidArrayMapImp();
		grootheidArrayMapMetric = DataManager.getGrootheidArrayMapMetric();
    }
	
	private void loadEenheidHashMap(){
    	eenhedenMap = DataManager.getEenhedenHashMap();
    }
	
	private void loadSysteemImagesMap(){
		systeemImagesMap = DataManager.getSysteemImagesMap();
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
		grootheidImageViews = DataManager.getGrootheidImageViews();
		
		deactivateImages();
		activateImage(LENGTE);
		
		this.imgSysteemLinksView = (ImageView) this.findViewById(R.id.img_systeem_l);	
		this.imgSysteemRechtsView = (ImageView) this.findViewById(R.id.img_systeem_r);
		
		imgSysteemLinksView.setImageResource(systeemImagesMap.get("Metric"));
		imgSysteemRechtsView.setImageResource(systeemImagesMap.get("Imp"));
	}
	
	private void deactivateImages(){
		for(int i = 0; i < 5; i++){
			grootheidImageViews[i].setImageResource(eenheidInactiveImages[i]);
		}
	}
	
	private void activateImage(int i){
		grootheidImageViews[i].setImageResource(eenheidActiveImages[i]);
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
    
    
    /*
	*
	*	loads the HashMap which holds the arrays and their "grootheid"(measure)
	*
	*/
    
	
	/*
	*
	*	loads the HashMap which holds the measures and their images
	*
	*/
	// private void loadGrootheidImagesMap(){
		// grootheidImagesMap.put(grootheden[0], R.drawable.lengte_bmp);
		// grootheidImagesMap.put(grootheden[4], R.drawable.temperatuur_bmp);
	// }
	
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
    		uitkomst = roundDouble(Eenheid.convert(getEenheid((String)linksSpinner.getSelectedItem()), getEenheid((String)rechtsSpinner.getSelectedItem()), 0d), 7);
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
    	return eenhedenMap.get(s);
    }
	
	private void switchSystems(){
		
		String tempSysteem = systeemRechts;
		systeemRechts = systeemLinks;
		systeemLinks = tempSysteem;
		
		reloadSysteemImageViews();
		
		setSpinnerArrays();
	}
	
	private void reloadSysteemImageViews(){
		if(systeemLinks.equals("Metric")){
			imgSysteemLinksView.setImageResource(systeemImagesMap.get("Metric"));
		}else{
			imgSysteemLinksView.setImageResource(systeemImagesMap.get("Imp"));
		}
		
		if(systeemRechts.equals("Metric")){
			imgSysteemRechtsView.setImageResource(systeemImagesMap.get("Metric"));
		}else{
			imgSysteemRechtsView.setImageResource(systeemImagesMap.get("Imp"));
		}
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
		
		switchSystems();
		
		linksSpinner.setSelection(leftSpinnerPos);
		rechtsSpinner.setSelection(rightSpinnerPos);
		
		convert();
	}
	
	/*
	*
	*	opens the activity to add presets
	*
	*/
	public void openPresetAddActivity(MenuItem item){
		Intent intent = new Intent(this, PresetAddActivity.class);
		startActivity(intent);
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
			grootheidImageViews[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view){
					if (view == grootheidImageViews[i]){
						deactivateImages();
						activateImage(i);
						setSpinnerArrays();
					}
				}
			});
		}
		
		imgSysteemLinksView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view){
				if(systeemLinks.equals("Metric")){
					systeemLinks = "Imp";
					imgSysteemLinksView.setImageResource(systeemImagesMap.get("Imp"));
				}else{
					systeemLinks = "Metric";
					imgSysteemLinksView.setImageResource(systeemImagesMap.get("Metric"));
				}
				
				setSpinnerArrays();
			}
		});
		
		imgSysteemRechtsView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view){
				if(systeemRechts.equals("Metric")){
					systeemRechts = "Imp";
					imgSysteemRechtsView.setImageResource(systeemImagesMap.get("Imp"));
				}else{
					systeemRechts = "Metric";
					imgSysteemRechtsView.setImageResource(systeemImagesMap.get("Metric"));
				}
				
				setSpinnerArrays();
			}
		});
	}
}
