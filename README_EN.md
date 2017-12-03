# IndicatorSeekBar
[![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-IndicatorSeekBar-green.svg?style=flat )]( https://android-arsenal.com/details/1/6434 )

#### README: [ 中文 ](https://github.com/warkiz/IndicatorSeekBar/blob/master/README.md)  |  English

A deeply customized SeekBar on Android, which can be changed the `size` , `color` , `thumbDrawable` , `tickDrawable` , `textsBelowTick` , `indicator`, also can show an indicator view with progress above SeekBar when seeking. Welcome `star` or `pull requests`.

----------------
## 1.Screenshot

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/continuous.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_1.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_2.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/custom.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/java_build.gif?raw=true" width = "264" height = "464"/>
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator.gif?raw=true" width = "264" height = "464"/>

## 2. How to use

### 1. build.gradle in module :
latest version : [![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
```groovy
dependencies {
  compile 'com.github.warkiz.widget:indicatorseekbar:1.1.5'//recommend to use latest version.
}
```
### 2. in xml or class file:
#### xml
```xml
<com.warkiz.widget.IndicatorSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    app:isb_max="78.8"
    app:isb_min="10.2"
    app:isb_progress="50.3"
    app:isb_progress_value_float="true"
    app:isb_show_indicator="true"/>
```
```xml
<com.warkiz.widget.IndicatorSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:isb_indicator_custom_layout="@layout/custom_indicator"
    app:isb_indicator_type="custom"
    app:isb_max="189"
    app:isb_min="23"
    app:isb_progress="67"
    app:isb_seek_bar_type="discrete_ticks_texts"
    app:isb_show_indicator="true"
    app:isb_text_array="@array/tick_below_text_length_5"
    app:isb_text_color="@color/color_blue"
    app:isb_thumb_width="18dp"
    app:isb_tick_drawable="@mipmap/ic_launcher"
    app:isb_tick_num="5"
    app:isb_tick_on_thumb_left_hide="true"
    app:isb_track_background_bar_color="#FF00"
    app:isb_track_background_bar_size="2dp"
    app:isb_track_progress_bar_color="#FF0000"
    app:isb_track_progress_bar_size="5dp"/>
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
```xml
//seekBar
<attr name="isb_max" format="float"/><!-- the max value of seekBar to seek, default 100-->
<attr name="isb_min" format="float"/><!-- the min value of seekBar to seek, default 0 -->
<attr name="isb_progress" format="float"/><!-- the current progress value of seekBar, default 0-->
<attr name="isb_clear_default_padding" format="boolean"/><!-- set seekBar's leftPadding&rightPadding to zero, default false, default padding is 16dp-->
<attr name="isb_progress_value_float" format="boolean"/><!--set the value of seekBar to float type, default false-->
<attr name="isb_seek_bar_type"><!-- the type for seekBar, default 0.-->
    <enum name="continuous" value="0"/>
    <enum name="continuous_texts_ends" value="1"/>
    <enum name="discrete_ticks" value="2"/>
    <enum name="discrete_ticks_texts" value="3"/><!--has below text-->
    <enum name="discrete_ticks_texts_ends" value="4"/><!--has below text of both ends of seekBar-->
</attr>
//indicator
<attr name="isb_indicator_type"><!-- the type for indicator, default square_corners/0.-->
    <enum name="square_corners" value="0"/>
    <enum name="rounded_corners" value="1"/>
    <!-- the attr:isb_indicator_custom_layout should be called to give a indicator view when you select custom indicator type -->
    <enum name="custom" value="2"/>
</attr>
<attr name="isb_show_indicator" format="boolean"/><!-- show indicator or not when seeking, default true-->
<attr name="isb_indicator_color" format="color|reference"/><!-- set indicator's color, default #FF4081-->
<attr name="isb_indicator_custom_layout" format="reference"/><!-- when you set indicator type to custom , you can set this layout for indicator view you want-->
<attr name="isb_indicator_custom_top_content_layout" format="reference"/> <!--you can set this layout for indicator top view you want, no effect arrow below indicator, effect on indicator type : square_corners or rounded_corners-->
<attr name="isb_indicator_text_color" format="color|reference"/><!-- set indicator's text color, default #FF4081 , work on indicator type : square_corners or rounded_corners-->
<attr name="isb_indicator_text_size" format="dimension|reference"/><!-- set indicator's text size, default 13sp,  work on indicator type : square_corners or rounded_corners-->
//track
<attr name="isb_track_background_bar_size" format="dimension|reference"/><!-- set indicatorSeekBar's track background bar size, default 2dp-->
<attr name="isb_track_background_bar_color" format="color|reference"/><!-- set indicatorSeekBar's track background bar color, default #D7D7D7-->
<attr name="isb_track_progress_bar_size" format="dimension|reference"/><!-- set indicatorSeekBar's track progress bar size, default 2dp-->
<attr name="isb_track_progress_bar_color" format="color|reference"/><!-- set indicatorSeekBar's track progress bar color, default #FF4081-->
<attr name="isb_track_rounded_corners" format="boolean"/><!-- set indicatorSeekBar's track's both ends's corners to rounded/square, default false-->
//thumb
<attr name="isb_thumb_progress_stay" format="boolean"/><!-- set thumb below text to stay after seek, default false, work on seekBar type : continuous / discrete_ticks-->
<attr name="isb_thumb_color" format="color|reference"/><!--set thumb's color, default #FF4081-->
<attr name="isb_thumb_width" format="dimension|reference"/><!--set thumb's size, default 18dp, when custom thumb-->
<attr name="isb_thumb_drawable" format="reference"/><!--set custom thumb's drawable you want, thumb size will be limited in 18dp no matter drawable size-->
//tick
<attr name="isb_tick_drawable" format="reference"/><!--set custom tick's drawable you want-->
<attr name="isb_tick_color" format="color|reference"/><!--set tick's color, default #FF4081-->
<attr name="isb_tick_num" format="integer"/><!--seekBar's tick count, default 5-->
<attr name="isb_tick_size" format="dimension|reference"/><!--set the tick width, default 13dp,  custom drawable will be limited in 18dp no matter drawable size-->
<attr name="isb_tick_both_end_hide" format="boolean"/><!--hide 2 ticks on the seekBar's both ends, default false-->
<attr name="isb_tick_on_thumb_left_hide" format="boolean"/><!--hide the ticks on the seekBar's thumb left, default false-->
<attr name="isb_tick_type"><!--select the tick shape type, default rectangle/1-->
    <enum name="none" value="0"/>
    <enum name="rec" value="1"/>
    <enum name="oval" value="2"/>
</attr>
//texts
<attr name="isb_text_color" format="color|reference"/><!--set the color of text below tick, default #FF4081-->
<attr name="isb_text_left_end" format="string|reference"/><!--set the text below seekBar left end, default min value string, work on seekBar type :CONTINUOUS_TEXTS_ENDS/DISCRETE_TICKS_TEXTS/DISCRETE_TICKS_TEXTS_ENDS-->
<attr name="isb_text_right_end" format="string|reference"/><!--set the text below seekBar right end, default max value string, work on seekBar type :CONTINUOUS_TEXTS_ENDS/DISCRETE_TICKS_TEXTS/DISCRETE_TICKS_TEXTS_ENDS-->
<attr name="isb_text_size" format="dimension|reference"/><!--set the text size of tick below text, default 13sp-->
<attr name="isb_text_array" format="reference"/><!--set the texts below tick to replace default progress text, default string of progress, work on seekBar type :DISCRETE_TICKS_TEXTS-->
```
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

