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

public class PresetManagerActivity extends Activity{
	
	private Spinner spnGrootheid = null;
	private Spinner spnEenheidLinks = null;
	private Spinner spnEenheidRechts = null;
	
	private String[] distanceUnits; 
	private String[] weightUnits;
	private String[] volumeUnits;
	private String[] speedUnits;
	private String[] temperatuurUnits;
	private String[] currentArray = null;
	private String[] grootheden;
	private Map<String, String[]> grootheidArrayMap = new HashMap<String, String[]>();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_manager);
		
		spnGrootheid = (Spinner) findViewById(R.id.spn_preset_grootheid);
		spnEenheidLinks = (Spinner) findViewById(R.id.spn_preset_links);
		spnEenheidRechts = (Spinner) findViewById(R.id.spn_preset_rechts);
		
		loadArrays();
		loadGrootheidArrayMap();
		
		setSpinnerArrays();
		loadMiddenSpinnerEvents();
	}
	
	public void savePreset(View button){
		String groot = (String) spnGrootheid.getSelectedItem();
		String links = (String) spnEenheidLinks.getSelectedItem();
		String rechts = (String) spnEenheidRechts.getSelectedItem();
		int oldSize = MainActivity.getPresetsSize();
		MainActivity.addPreset(new Preset(groot, links, rechts));
		int newSize = MainActivity.getPresetsSize();
		if(newSize > oldSize)
			Toast.makeText(this, getResources().getString(R.string.preset_added), Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, getResources().getString(R.string.preset_already_exists), Toast.LENGTH_SHORT).show();
	}
	
	private void loadArrays(){
		distanceUnits = getResources().getStringArray(R.array.length_units);
		weightUnits = getResources().getStringArray(R.array.weight_units);
		volumeUnits = getResources().getStringArray(R.array.volume_units);
		speedUnits = getResources().getStringArray(R.array.speed_units);
		temperatuurUnits = getResources().getStringArray(R.array.temperature_units);
		
		grootheden = getResources().getStringArray(R.array.measures);
	}
	
	 // loads the HashMap which holds the arrays and their "grootheid"
    private void loadGrootheidArrayMap(){
    	grootheidArrayMap.put(grootheden[0], distanceUnits);
    	grootheidArrayMap.put(grootheden[1], weightUnits);
    	grootheidArrayMap.put(grootheden[2], volumeUnits);
    	grootheidArrayMap.put(grootheden[3], speedUnits);
    	grootheidArrayMap.put(grootheden[4], temperatuurUnits);
    }
	
	private String[] getCurrentArray(){
    	return grootheidArrayMap.get((String)spnGrootheid.getSelectedItem());
    }
	
	 @SuppressWarnings({"rawtypes", "unchecked"})
    private void setSpinnerArrays(){
    	currentArray = getCurrentArray();
    	ArrayAdapter tempAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, currentArray);

    	spnEenheidLinks.setAdapter(null);
    	spnEenheidRechts.setAdapter(null);
    	spnEenheidLinks.setAdapter(tempAdapter);
    	spnEenheidRechts.setAdapter(tempAdapter);
    }
	
	private void loadMiddenSpinnerEvents(){
		spnGrootheid.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					setSpinnerArrays();
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {}
        		
        	}
        );
	}
}