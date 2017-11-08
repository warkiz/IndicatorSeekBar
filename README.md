# IndicatorSeekBar
[![API](https://img.shields.io/badge/API-9%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=9)

README: 中文 | [ English ](https://github.com/warkiz/IndicatorSeekBar/blob/master/README_EN.md)

自定义SeekBar, 能改变尺寸、颜色、滑块(thumb)图片、刻度(tick)图片、刻度文字(text)和气泡指示器(indicator)，当滑动时显示带有进度的指示器。

----------------
## 1.截图

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/continuous.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_1.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_2.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/custom.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/java_build.gif?raw=true" width = "264" height = "464"/>
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator.gif?raw=true" width = "264" height = "464"/>

## 2. 使用
###  1. 在app/build.gradle下:
```groovy
dependencies {
    compile 'com.github.warkiz.widget:indicatorseekbar:1.0.9'
}
```
### 2. 在布局文件或java类中:
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
    app:isb_text_array="@array/tick_below_text_length_9"
    app:isb_text_color="@color/color_blue"
    app:isb_thumb_width="18dp"
    app:isb_tick_drawable="@mipmap/ic_launcher"
    app:isb_tick_num="9"
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
#####  更多使用方式请参考 [demo.apk](https://github.com/warkiz/IndicatorSeekBar/blob/master/apk/demo.apk).
-------------------------
## 3. 功能
### 3.1 多种的SeekBar类型
IndicatorSeekBar 提供了两种系列的类型: 

1. 连续（continuous）的滑动:  `CONTINUOUS`/`CONTINUOUS_TEXTS_ENDS`.

2. 非连续（discrete）的滑动:  `DISCRETE_TICKS`/  `DISCRETE_TICKS_TEXTS`/  `DISCRETE_TICKS_TEXTS_ENDS`.

```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_seek_bar_type="continuous"
    .../>
```

### 3.2 自定义 颜色、尺寸

seekbar以下部分的颜色或尺寸可以被自定义：

- 背景条 track_background_bar
- 进度条 track_progress_bar
- 刻度 tick
- 刻度文字 text
- 滑块 thumb
- 指示器 indicator
- 指示器文字 indicator_text

### 3.3 隐藏刻度tick
seekbar的两个端点的刻度或者滑块左边的刻度可以被隐藏。
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_both_end_hide="true"
    .../>

<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_on_thumb_left_hide="true"
    .../>
```	
### 3.4 给seekbar选择圆角/方角
 默认两端是圆角，可以设置为方形。
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_track_rounded_corners="true"
    .../>
```	

### 3.5 滑块thumb下显示进度
当 seekabr 的类型为 `CONTINUOUS` or `DISCRETE_TICKS`时 , 可以设置滑动后滑块下显示保留进度。
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_seek_bar_type="continuous"//discrete_ticks
    app:isb_thumb_progress_stay="true"
    .../>
```

### 3.6 自定义seekbar两端的文字text
当 seekabr 的类型是 `CONTINUOUS_TEXTS_ENDS` 或 `DISCRETE_TICKS_TEXTS_ENDS` 时, 可以设置两端的文字.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_seek_bar_type="continuous_texts_ends"//discrete_ticks_texts_ends
    app:isb_text_left_end="last"
    app:isb_text_right_end="next"
    .../>
```

### 3.7 自定义刻度下的文字text
当 seekabr 的类型是 `DISCRETE_TICKS_TEXTS` , 可以通过设置数组的方式自定义刻度下的文字, 数组的长度应当和刻度相等。
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_text_array="@array/texts_below_tick_length_5"
    app:isb_tick_num="5" //一般来说 , 文字数组的长度应该和刻度的数量相等
    .../>
```
```Java
or
indicatorSeekBar.setTextArray(R.array.texts_below_tick_length_5);
```

### 3.8 自定义滑块thumb的图片
滑块可以使用图片去自定义：
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_thumb_drawable="@mipmap/ic_launcher"
    .../>
```
### 3.9 自定义刻度tick的图片
刻度可以使用图片去自定义.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_drawable="@mipmap/ic_launcher"
    .../>
```

### 3.10 自定义指示器indicator
IndicatorSeekbar提供了3种指示器的类型： `ROUNDED_CORNERS` / `SQUARE_CORNERS` / `CUSTOM`,当指示器的类型为 `CUSTOM` 时, 可以自定义指示器的view.
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
注意：如果自定义指示器需要显示进度, 那么指示器必须要有一个TextView，而且其id必须设置为`isb_progress`.
### 3.11 自定义指示器顶部的内容
当 indicator 的类型为 `ROUNDED_CORNERS` 或者 `SQUARE_CORNERS` , 可以设置指示器顶部的view.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_indicator_type="rounded_corners"//square_corners
    app:isb_indicator_custom_top_content_layout="@layout/top_content_view"
    .../>
```
```Java
or
indicatorSeekBar.getIndicator().setIndicatorTopContentLayout(R.layout.top_content_view);
```
注意：如果自定义指示器顶部的view需要显示进度, 那么这个view必须要有一个TextView，而且其id必须设置为`isb_progress`.

## 4. 支持进度监听
```Java
indicatorSeekBar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {

	@Override
	public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {

	}

	@Override
	public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowText, boolean fromUserTouch) {
	    //only callback on discrete series seekbar type.
	}

	@Override
	public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
	}

	@Override
	public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

	}
});
```
onSectionChanged: 当seekbar的类型为非连续 `discrete`系列 时, 这个回调会获得滑块的位置和滑块下的文字. 当为连续 `continuous`系列则不回调。
## 5. 混淆配置
```Java
-keep class com.warkiz.widget.** { *; }
```

## 6. 属性
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
Apache License 2.0


## 8. 联系我
如果你对此项目有问题，欢迎通过以下方式联系我。

1. 提issue.
2. 新浪微博：http://weibo.com/warkiz
3. 发邮件, "warkiz".concat("4j").concat("@").concat("gmail.com")

