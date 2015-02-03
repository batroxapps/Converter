package bartold.omzetter.preset;

import static bartold.util.Utils.*;

import bartold.omzetter.MainActivity;
import bartold.omzetter.DataManager;
import bartold.omzetter.eenheid.Eenheid;
import bartold.omzetter.preset.Preset;
import bartold.omzetter.R;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class PresetAddActivity extends Activity{
	
	private static HashMap<String, Eenheid> eenhedenMap = new HashMap<String, Eenheid>();
	
	private String[] currentArray = null;
	private String[] grootheden;
	private Map<String, String[]> grootheidArrayMapImp = new HashMap<String, String[]>();
	private Map<String, String[]> grootheidArrayMapMetric = new HashMap<String, String[]>();	
	private String activeGrootheid = "lengte";
	private Map<String, Integer> grootheidImagesMap = new HashMap<String, Integer>();
	private Map<String, Integer> systeemImagesMap = new HashMap<String, Integer>();
	
	private String systeemLinks = "Metric";
	private String systeemRechts = "Imp";
	
	Spinner linksSpinner = null;
    Spinner rechtsSpinner = null;
	
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
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_add);
		
		DataManager.init(this);
		
		loadArrays();
		loadGrootheidArrayMaps();
		loadEenheidHashMap();
		loadSysteemImagesMap();
		
		initImageViews();
		initSpinners();
		
		loadImageViewEvents();
		
		setSpinnerArrays();
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
	
	private void initSpinners(){
		linksSpinner = (Spinner) findViewById(R.id.eenheid_spinner_links_preset);
        rechtsSpinner = (Spinner) findViewById(R.id.eenheid_spinner_rechts_preset);
        
        setSpinnerArrays();
	}
	
	private void initImageViews(){
		grootheidImageViews = DataManager.getGrootheidImageViewsPreset();
		
		deactivateImages();
		activateImage(LENGTE);
		
		this.imgSysteemLinksView = (ImageView) this.findViewById(R.id.img_systeem_l_preset);	
		this.imgSysteemRechtsView = (ImageView) this.findViewById(R.id.img_systeem_r_preset);
		
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
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    private void setSpinnerArrays(){
    	ArrayAdapter linksAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getCurrentArray(systeemLinks));
		ArrayAdapter rechtsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getCurrentArray(systeemRechts));

		linksSpinner.setAdapter(null);
		rechtsSpinner.setAdapter(null);
		linksSpinner.setAdapter(linksAdapter);
		rechtsSpinner.setAdapter(rechtsAdapter);
    }
	
	private String[] getCurrentArray(String systeem){
		if(systeem.equals("Metric"))
			return grootheidArrayMapMetric.get(activeGrootheid);
		else if (systeem.equals("Imp"))
			return grootheidArrayMapImp.get(activeGrootheid);
			
		return null;
    }
	
	/*
	 *
	 *
	 *	\\\\\\\\ EVENTS ////////	
	 *
	 *
	*/
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