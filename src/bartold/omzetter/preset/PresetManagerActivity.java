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
		
		System.out.println("Test");
		
		presetsTableScrollView = (TableLayout) findViewById(R.id.presetsTableScrollView);
		
		ArrayList<Preset> presets = new ArrayList<Preset>();
		
		write("Size: " + MainActivity.getPresetsSize());
		
		reloadPresets();
	}
	
	private void reloadPresets(){
		
		presetsTableScrollView.removeAllViews();
		
		ArrayList<Preset> presets = new ArrayList<Preset>();
		
		if (MainActivity.getPresetsSize() > 0){
			presets = getPresets();
			
			for(int i = 0; i < MainActivity.getPresetsSize(); i++){
				
				insertPresetInScrollView(presets.get(i), i);
				
			}
		}
	}
	
	private ArrayList<Preset> getPresets(){
		
		ArrayList<Preset> presets = new ArrayList<Preset>();
		HashMap<String, Preset> presetsMap = MainActivity.getPresets();
		
		for (String name : MainActivity.getPresetNames()){
			if(!name.equals(" ")){
				presets.add(presetsMap.get(name));
				write(name);
				write(presetsMap.get(name).getName());
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
		
		//~ Button deleteButton = (Button) newPresetRow.findViewById(R.id.btn_delete_preset);
			         //~ 
		//~ Button editButton = (Button) newPresetRow.findViewById(R.id.btn_edit_preset);
		 
		// Add the new components for the stock to the TableLayout
		presetsTableScrollView.addView(newPresetRow, arrayIndex);
		
		write(p.getName());
		 
	}
	
	// deletes a preset
	public void deletePreset(View button){
		write("Test delete");
		
		Button buttonClicked = (Button) button;
		ScrollView scrView = (ScrollView) findViewById(R.id.presetsScrollView);
		
		View presetRow = (View) button.getParent();
		
		for (int i = 0; i < scrView.getChildCount(); i++){
			
			TextView txtView = (TextView)((ViewGroup) presetRow).getChildAt(0);
			write((String)txtView.getText());
			
			for (String s : presetNames){
				if (s.equals((String)txtView.getText())){
					write("Delete! Delete! Delete!");
					MainActivity.deletePreset(s);
					presetNames.remove(s);
					
					reloadPresets();
					break;
				}
			}
			
			
			//~ if (((View)scrView.getChildAt(i)).equals(presetRow)){
				//~ 
				//~ TextView txtView = (TextView)((ViewGroup) presetRow).getChildAt(2);
				//~ write((String)txtView.getText());
				//~ 
			//~ } else {
				//~ write("BUGDIBUG");
			//~ }
			
		}
	}
		
}
