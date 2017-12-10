# IndicatorSeekBar
[![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-IndicatorSeekBar-green.svg?style=flat )]( https://android-arsenal.com/details/1/6434 )

#### README: [ 中文 ](https://github.com/warkiz/IndicatorSeekBar/blob/master/README.md)  |  English

A customized SeekBar on Android, which can be changed the `size` , `color` , `thumbDrawable` , `tickDrawable` , `textsBelowTick` , `indicator`, also can show an indicator view with progress above SeekBar when seeking. Welcome `star` or `pull request`.

----------------
## new feature logs

12/5

When custom tick or thumb drawable :
    
	if the drawable's width is less than 30 dp , will show the drawable in raw size.
	if large than 30dp , you can set the width by isb_tick_size/isb_thumb_width , default 14dp , max is 30dp when display.height auto adjust by ratio.

12/10

This new feature can set the indicator to show always.
    
	IndicatorSeekBar has provied the attribute isb_indicator_stay to do this , also can use setter in builder.
	this new feature is fine when use in below situation.
	ListView / ScrollView / GridView / RecyclerView / ViewPager / Dialog 

----------------
## 1.Screenshot

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/continuous.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_1.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_2.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/custom.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/java_build.gif?raw=true" width = "264" height = "464"/>
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator.gif?raw=true" width = "264" height = "464"/>

## 2. How to use

### 1. build.gradle in module :
latest version : [![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
```groovy
dependencies {
  //recommend using latest version.
  compile 'com.github.warkiz.widget:indicatorseekbar:1.1.7'
}
```
### 2. in xml or class file:
#### xml
```xml
<com.warkiz.widget.IndicatorSeekBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:isb_max="100"
        app:isb_min="10"
        app:isb_progress="34"
        app:isb_show_indicator="true" />

    <com.warkiz.widget.IndicatorSeekBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        app:isb_indicator_type="rounded_corners"
        app:isb_progress="50"
        app:isb_seek_bar_type="discrete_ticks_texts"
        app:isb_tick_num="6"
        app:isb_tick_type="oval" />
```

#### Java

```Java
IndicatorSeekBar indicatorSeekBar = new IndicatorSeekBar.Builder(this)
                                .setMax(200)
				.setMin(0)
				.setProgress(35)
				.setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS)
				.setTickType(TickType.OVAL)
				.setTickColor(Color.parseColor("#0000FF"))
				.setTickSize(8)//dp size
				.setTickNum(8)
				.setBackgroundTrackSize(2)//dp size
				.setBackgroundTrackColor(Color.parseColor("#666666"))
				.setProgressTrackSize(3)//dp size
				.setProgressTrackColor(Color.parseColor("#0000FF"))
				.showIndicator(true)
				.setIndicatorType(IndicatorType.SQUARE_CORNERS)
				.setIndicatorColor(Color.parseColor("#0000FF"))
				.build();
```
#####  Refer to [demo.apk](https://github.com/warkiz/IndicatorSeekBar/blob/master/apk/demo.apk) or demo for more details about usage.
-------------------------
## 3. Abilities
### 3.1 multiple SeekBar type
IndicatorSeekBar has provided 2 kinds of series seekbar type: 

1. continous series:  `CONTINUOUS`/  `CONTINUOUS_TEXTS_ENDS`.

2. discrete series:  `DISCRETE_TICKS`/  `DISCRETE_TICKS_TEXTS`/  `DISCRETE_TICKS_TEXTS_ENDS`.

```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_seek_bar_type="continuous"
    .../>
```

### 3.2 customized color/size

Below seekbar parts' color/size can be customized:

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/overview.png?raw=true" width = "392" height = "115"/>

- track background bar
- track progress bar
- ticks
- text
- thumb
- indicator
- indicator text

### 3.3 hide ticks
The ticks on the SeekBar both end sides or on thumb left can be hid.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_both_end_hide="true"
    .../>


<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_on_thumb_left_hide="true"
    .../>
```	
### 3.4 change the SeekBar corners shape
 Seekbar's track's is round corners default , could be set to square.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_track_rounded_corners="false"
    .../>
```	

### 3.5 seeking to show progress text below thumb.
When the seekabr type is `CONTINUOUS` or `DISCRETE_TICKS` , you can set the progress text to show when seeking.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_seek_bar_type="continuous"//discrete_ticks
    app:isb_thumb_progress_stay="true"
    .../>
```

### 3.6 customized 2 below texts on both ends of SeekBar
When the seekabr type is `CONTINUOUS_TEXTS_ENDS` or `DISCRETE_TICKS_TEXTS_ENDS` , you can set the left & right text.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_seek_bar_type="continuous_texts_ends"//discrete_ticks_texts_ends
    app:isb_text_left_end="last"
    app:isb_text_right_end="next"
    .../>
```

### 3.7 customized texts below tick
When the seekabr type is `DISCRETE_TICKS_TEXTS` , you can custom the texts below tick by an array, and the array's length should equals ticks num.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_text_array="@array/texts_below_tick_length_5"
    app:isb_tick_num="5" // normally , array length should equals tick num.
    .../>
```
Also, you can use the attr: isb_tick_size to change the drawable' size , limited in 30 dp, default 8dp. if the drawable less than 30dp, will show in raw size

```Java
or
indicatorSeekBar.setTextArray(R.array.texts_below_tick_length_5);
```

### 3.8 customized thumb drawable
Thumb can be replaced by a drawable:
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_thumb_drawable="@mipmap/ic_launcher"
    .../>
```
Also, you can use the attr: isb_thumb_width to change the drawable' size , limited in 30 dp , default 8dp . if the drawable less than 30dp, will show in raw size.

### 3.9 customized tick drawable
Ticks can be replaced by a drawable:
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_drawable="@mipmap/ic_launcher"
    .../>
```

### 3.10 customized indicator
IndicatorSeekbar provided 3 kinds of indicator type `ROUNDED_CORNERS` / `SQUARE_CORNERS` / `CUSTOM`, when the indicator type is `CUSTOM` , you can set a custom indicator view.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_indicator_type="custom"
    app:isb_indicator_custom_layout="@layout/indicator"
    .../>
```
```Java
or
indicatorSeekBar.setCustomIndicator(R.layout.indicator);
```
Attention: if want to show the custom indicator with a progress text when seeking , the indicator view should have a TextView which id is `isb_progress`. 

### 3.11 customized indicator's top content view
When the indicator type is `ROUNDED_CORNERS` or `SQUARE_CORNERS` , you can set a custom indicator top content view.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_indicator_type="rounded_corners"/square_corners
    app:isb_indicator_custom_top_content_layout="@layout/top_content_view"
    .../>
```
```Java
or
indicatorSeekBar.getIndicator().setIndicatorTopContentLayout(R.layout.top_content_view);
```
Attention: if want to show the custom indicator top content view with a progress text when seeking , the content view should have a TextView which id is `isb_progress`. 
## 4. Support listener
```Java
indicatorSeekBar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {

	@Override
	public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {

	}

	@Override
	public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {
	    //only callback on discrete series SeekBar type.
	}

	@Override
	public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
	}

	@Override
	public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

	}
});
```
onSectionChanged: when the SeekBar type is `discrete series`, this callback work to get the tick position and text. `continuous series` will not work.
## 5. Proguard
```Java
-keep class com.warkiz.widget.** { *; }
```

## 6. Attributes

[ attr.xml ](https://github.com/warkiz/IndicatorSeekBar/blob/master/indicatorseekbar/src/main/res/values/attr.xml)

## 7. License

Copyright 2017 Chuang Guangquan (warkiz)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.


## 8. Contact me
Feel free to contact me if you have any trouble on this project.

1. Create an issue.
2. Send mail to me, "warkiz".concat("4j").concat("@").concat("gmail.com")

## 9. Thanks
[material.io-slider](https://material.io/guidelines/components/sliders.html#sliders-continuous-slider). 

[MaterialDesignLibrary](https://github.com/navasmdc/MaterialDesignLibrary). 

[PointerPopupWindow](https://github.com/okry1123/PointerPopupWindow).

[SeekBarCompat](https://github.com/ahmedrizwan/SeekBarCompat). 

[BubbleSeekBar](https://github.com/woxingxiao/BubbleSeekBar). 

[NumberProgressBar](https://github.com/daimajia/NumberProgressBar). 

[android-slidr](https://github.com/florent37/android-slidr). 

[RangeSeekBar](https://github.com/Jay-Goo/RangeSeekBar). 

[BubblePopupWindow](https://github.com/smuyyh/BubblePopupWindow). 
