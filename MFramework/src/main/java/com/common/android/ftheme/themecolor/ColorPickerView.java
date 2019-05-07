/*
 * Copyright (C) 2015 Martin Stone
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.common.android.ftheme.themecolor;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.common.android.R;

public class ColorPickerView extends FrameLayout {

	private final AlphaView alphaView;
	private final TextView hexEdit,hexEditSecond,hexEditThird;
	private final ObservableColor observableColor = new ObservableColor(0);
	private final SwatchView swatchView;
	private int colorTyoe=0;
	private int  firstColor,secondColor,thridColor;

	public ColorPickerView(Context context) {
		this(context, null);
	}

	public ColorPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.picker, this);

		swatchView = (SwatchView)findViewById(R.id.swatchView);
		swatchView.observeColor(observableColor);

		HueSatView hueSatView = (HueSatView)findViewById(R.id.hueSatView);
		hueSatView.observeColor(observableColor);

		ValueView valueView = (ValueView)findViewById(R.id.valueView);
		valueView.observeColor(observableColor);

		alphaView = (AlphaView)findViewById(R.id.alphaView);
		alphaView.observeColor(observableColor);

		hexEdit = (TextView)findViewById(R.id.hexEditFirst);
		hexEditSecond = (TextView)findViewById(R.id.hexEditSecond);
		hexEditThird = (TextView)findViewById(R.id.hexEditThird);
		HexEdit.setUpListeners(hexEdit, observableColor);


		hexEdit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {


                initColor(0);
			}
		});


		hexEditSecond.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {


                initColor(1);
			}
		});

		hexEditThird.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

                initColor(2);

			}
		});



		applyAttributes(attrs);

		// We get all our state saved and restored for free,
		// thanks to the EditText and its listeners!
	}

	private void applyAttributes(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ColorPicker, 0, 0);
			showAlpha(a.getBoolean(R.styleable.ColorPicker_colorpicker_showAlpha, true));
			showHex(a.getBoolean(R.styleable.ColorPicker_colorpicker_showHex, true));
			showPreview(a.getBoolean(R.styleable.ColorPicker_colorpicker_showPreview, true));
		}
	}

	/** Returns the color selected by the user */
	public int [] getColor() {
		//return observableColor.getColor();
		return new int[]{Color.parseColor("#"+hexEdit.getText().toString()),
				  Color.parseColor("#"+hexEditSecond.getText().toString()),
				  Color.parseColor("#"+hexEditThird.getText().toString())};
	}

	/** Sets the original color swatch and the current color to the specified value. */
	public void setColor(int color,int secondColor,int thirdColor) {
		setOriginalColor(color);
		setCurrentColor(color);
        hexEditSecond.setText(formatColor(secondColor));
        hexEditThird.setText(formatColor(thirdColor));

	}

	/** Sets the original color swatch without changing the current color. */
	public void setOriginalColor(int color) {
		swatchView.setOriginalColor(color);
	}

	/** Updates the current color without changing the original color swatch. */
	public void setCurrentColor(int color) {
		observableColor.updateColor(color, null);
	}

	public void showAlpha(boolean showAlpha) {
		alphaView.setVisibility(showAlpha ? View.VISIBLE : View.GONE);
		if(hexEdit.isFocused())
		{
			HexEdit.setShowAlphaDigits(hexEdit, showAlpha);

		}else if(hexEditSecond.isFocused()){

			HexEdit.setShowAlphaDigits(hexEditSecond, showAlpha);


		}else if(hexEditThird.isFocused())
		{
			HexEdit.setShowAlphaDigits(hexEditThird, showAlpha);
		}

	}

	public void addColorObserver(ColorObserver observer) {
		observableColor.addObserver(observer);
	}

	public void showHex(boolean showHex) {
		hexEdit.setVisibility(showHex ? View.VISIBLE : View.GONE);
	}

	public void showPreview(boolean showPreview) {
		swatchView.setVisibility(showPreview ? View.VISIBLE : View.GONE);
	}


    private String formatColor(int color) {
        return String.format("%06x", color & 0x00ffffff);
    }


    private void initColor(int  type)
    {


        if(colorTyoe==type)
        {
            return;
        }
        switch (type)
        {
            case 0:
                HexEdit.setUpListeners(hexEdit, observableColor);
                setOriginalColor(Color.parseColor("#"+hexEdit.getText().toString()));
                setCurrentColor(Color.parseColor("#"+hexEdit.getText().toString()));
                break;
            case 1:

                HexEdit.setUpListeners(hexEditSecond, observableColor);
                setOriginalColor(Color.parseColor("#"+hexEditSecond.getText().toString()));
                setCurrentColor(Color.parseColor("#"+hexEditSecond.getText().toString()));


                break;
            case 2:

                HexEdit.setUpListeners(hexEditThird, observableColor);
                setOriginalColor(Color.parseColor("#"+hexEditThird.getText().toString()));
                setCurrentColor(Color.parseColor("#"+hexEditThird.getText().toString()));
                break;

        }
        colorTyoe = type;
    }

}
