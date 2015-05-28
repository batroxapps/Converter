package bartold.omzetter.preset;

import static bartold.util.Utils.*;

import bartold.omzetter.DataManager;
import bartold.omzetter.MainActivity;
import bartold.omzetter.preset.Preset;
import bartold.omzetter.R;

import android.app.Activity;

import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PresetManagerActivity extends Activity{
		
	private TableLayout presetsTableScrollView;
	
	private Set<String> presetNames = DataManager.getPresetNames();
	
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preset_manager);
	}
	
	protected void onStart(){
		super.onStart();
		
		DataManager.init(this);
		
		presetsTableScrollView = (TableLayout) findViewById(R.id.presetsTableScrollView);
		
		reloadPresets();
	}
	
	protected void onStop(){
		super.onStop();
		
		DataManager.save();
	}
	
	private void reloadPresets(){
		
		presetsTableScrollView.removeAllViews();
		
		ArrayList<Preset> presets = new ArrayList<Preset>();
		
		if (DataManager.getPresetsSize() > 0){
			presets = getPresets();
			
			write("Size: " + DataManager.getPresetsSize());
			
			for(int i = 0; i < DataManager.getPresetsSize(); i++){
				
				insertPresetInScrollView(presets.get(i), i);
				
			}
		}
	}
	
	private ArrayList<Preset> getPresets(){
		
		ArrayList<Preset> presets = new ArrayList<Preset>();
		HashMap<String, Preset> presetsMap = DataManager.getPresets();
		
		for (String name : DataManager.getPresetNames()){
			if(!name.equals(" ")){
				presets.add(presetsMap.get(name));
				
				for (Preset p : presets){
					write(p.getName());
				}
			}
		}
		
		return presets;
		
	}
	
	private void insertPresetInScrollView(Preset p, int arrayIndex){		 
		// Get the LayoutInflator service
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// Use the inflater to inflate a stock row from stock_quote_row.xml
		View newPresetRow = inflater.inflate(R.layout.preset_view, null);
		 
		// Create the TextView for the ScrollView Row
		TextView newPresetTextView = (TextView) newPresetRow.findViewById(R.id.txtv_preset);
		 
		// Add the stock symbol to the TextView
		newPresetTextView.setText(p.getName());
		 
		// Add the new components for the stock to the TableLayout
		presetsTableScrollView.addView(newPresetRow, arrayIndex);
		 
	}
	
	// deletes a preset
	public void deletePreset(View button){
		
		Button buttonClicked = (Button) button;
		ScrollView scrView = (ScrollView) findViewById(R.id.presetsScrollView);
		
		View presetRow = (View) button.getParent();
		
		for (int i = 0; i < scrView.getChildCount(); i++){
			
			TextView txtView = (TextView)((ViewGroup) presetRow).getChildAt(0);
			
			for (String s : presetNames){
				if (s.equals((String)txtView.getText())){

					DataManager.deletePreset(DataManager.getPresets().get(s));
					presetNames.remove(s);
					
					Toast.makeText(this, getResources().getString(R.string.preset_deleted), Toast.LENGTH_SHORT).show();
					
					reloadPresets();
					break;
				}
			}
		}
	}
		
}
