package bartold.omzetter.preset;

import static bartold.util.Utils.*;

import bartold.omzetter.preset.Preset;
import bartold.omzetter.R;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.widget.Spinner;

public class PresetManagerActivity extends Activity{
	
	private Spinner spnGrootheid = null;
	private Spinner spnEenheidLinks = null;
	private Spinner spnEenheidRechts = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_manager);
		
		spnGrootheid = (Spinner) findViewById(R.id.spn_preset_grootheid);
		spnEenheidLinks = (Spinner) findViewById(R.id.spn_preset_links);
		spnEenheidRechts = (Spinner) findViewById(R.id.spn_preset_rechts);
	}
	
	public void savePreset(View button){
		String groot = (String) spnGrootheid.getSelectedItem();
		String links = (String) spnEenheidLinks.getSelectedItem();
		String rechts = (String) spnEenheidRechts.getSelectedItem();
		bartold.omzetter.MainActivity.addPreset(new Preset(groot, links, rechts));
	}
	
}