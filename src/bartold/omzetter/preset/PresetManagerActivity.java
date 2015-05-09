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

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
		
		System.out.println("Test");
		
		presetsTableScrollView = (TableLayout) findViewById(R.id.presetsTableScrollView);
		
		ArrayList<Preset> presets = new ArrayList<Preset>();
		
		print("Size: " + MainActivity.getPresetsSize());
		
		if (MainActivity.getPresetsSize() > 0){
			print("test 5");
			
			for (Preset p: MainActivity.getPresets().values()){
				presets.add(p);
				write(p.getName());
			}
			
			insertPresetInScrollView(presets.get(0), 0);
		}
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
		
        Button stockQuoteButton = (Button) newPresetRow.findViewById(R.id.btn_delete_preset);
        
        Button btnEditPreset = (Button) newPresetRow.findViewById(R.id.btn_edit_preset);
		 
		 
		print("Test 10");
		// Add the new components for the stock to the TableLayout
		presetsTableScrollView.addView(newPresetRow, arrayIndex);
		 
	}
		
}
