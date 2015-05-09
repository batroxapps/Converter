package bartold.omzetter.preset;

import static bartold.util.Utils.*;

import bartold.omzetter.R;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;

import android.view.View;

public class PresetView extends View{
	public PresetView(Context context, AttributeSet attrs){
		super(context, attrs);
		
		TypedArray a = context.getTheme().obtainStyledAttributes(
        attrs,
        R.styleable.PresetView,
        0, 0);

		try {
			String mShowText = a.getString(R.styleable.PresetView_presetName);
		} finally {
			a.recycle();
		}
	}
}
