package bartold.omzetter.preset;

import static bartold.util.Utils.*;

import bartold.omzetter.MainActivity;
import bartold.omzetter.preset.Preset;
import bartold.omzetter.R;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class PresetAddActivity extends Activity{
	
	// private Spinner spnGrootheid = null;
	// private Spinner spnEenheidLinks = null;
	// private Spinner spnEenheidRechts = null;
	
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
	private Map<String, String[]> grootheidArrayMapImp = new HashMap<String, String[]>();
	private Map<String, String[]> grootheidArrayMapMetric = new HashMap<String, String[]>();	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_add);
		
		// spnGrootheid = (Spinner) findViewById(R.id.spn_preset_grootheid);
		// spnEenheidLinks = (Spinner) findViewById(R.id.spn_preset_links);
		// spnEenheidRechts = (Spinner) findViewById(R.id.spn_preset_rechts);
		
		loadArrays();
		loadGrootheidArrayMaps();
		
		setSpinnerArrays();
		loadMiddenSpinnerEvents();
	}
	
	public void savePreset(View button){
		// String groot = (String) spnGrootheid.getSelectedItem();
		// String links = (String) spnEenheidLinks.getSelectedItem();
		// String rechts = (String) spnEenheidRechts.getSelectedItem();
		// int oldSize = MainActivity.getPresetsSize();
		// MainActivity.addPreset(new Preset(groot, links, rechts));
		// int newSize = MainActivity.getPresetsSize();
		// if(newSize > oldSize)
			// Toast.makeText(this, getResources().getString(R.string.preset_added), Toast.LENGTH_SHORT).show();
		// else
			// Toast.makeText(this, getResources().getString(R.string.preset_already_exists), Toast.LENGTH_SHORT).show();
	}
	
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
	
	 // loads the HashMap which holds the arrays and their "grootheid"
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
		
		// ArrayAdapter middenAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grootheden);
		// spnGrootheid.setAdapter(middenAdapter);
    }
	
	// private String[] getCurrentArray(){
    	// return grootheidArrayMap.get((String)spnGrootheid.getSelectedItem());
    // }
	
	 @SuppressWarnings({"rawtypes", "unchecked"})
    private void setSpinnerArrays(){
    	// ArrayAdapter tempAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getCurrentArray());
		
    	// spnEenheidLinks.setAdapter(null);
    	// spnEenheidRechts.setAdapter(null);
    	// spnEenheidLinks.setAdapter(tempAdapter);
    	// spnEenheidRechts.setAdapter(tempAdapter);
    }
	
	private void loadMiddenSpinnerEvents(){
		// spnGrootheid.setOnItemSelectedListener(new OnItemSelectedListener(){

				// @Override
				// public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// setSpinnerArrays();
					
				// }

				// @Override
				// public void onNothingSelected(AdapterView<?> arg0) {}
        		
        	// }
        // );
	}
}