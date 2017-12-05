# IndicatorSeekBar
[![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-IndicatorSeekBar-green.svg?style=flat )]( https://android-arsenal.com/details/1/6434 )


#### README: 中文 | [ English here](https://github.com/warkiz/IndicatorSeekBar/blob/master/README_EN.md)

自定义SeekBar, 能改变尺寸、颜色、滑块(thumb)图片、刻度(tick)图片、刻度文字(text)和气泡指示器(indicator)，当滑动时显示带有进度的指示器。
欢迎 `star` or `pull request`。

----------------
## 1.截图

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/continuous.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_1.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_2.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/custom.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/java_build.gif?raw=true" width = "264" height = "464"/>
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator.gif?raw=true" width = "264" height = "464"/>

## 2. 使用
###  1. 在app/build.gradle下:
最新版本: [![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
```groovy
dependencies {
    //推荐使用最新版本
    compile 'com.github.warkiz.widget:indicatorseekbar:1.1.7'
}
```
### 2. 在布局文件或java类中:
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

#### or Java

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

SeekBar以下部分的颜色或尺寸可以被自定义：

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/overview.png?raw=true" width = "392" height = "115"/>

- 背景条 track_background_bar
- 进度条 track_progress_bar
- 刻度 tick
- 刻度文字 text
- 滑块 thumb
- 指示器 indicator
- 指示器文字 indicator_text

### 3.3 隐藏刻度tick
SeekBar的两个端点的刻度或者滑块左边的刻度可以被隐藏。
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_both_end_hide="true"
    .../>

<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_on_thumb_left_hide="true"
    .../>
```	
### 3.4 给SeekBar选择圆角/方角
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

### 3.6 自定义SeekBar两端的文字text
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
注意: 可通过isb_thumb_width 属性设置滑块图片大小, 最大限制显示30dp, 默认14dp . 如果图片小于30dp, 会整张图片显示.

### 3.9 自定义刻度tick的图片
刻度可以使用图片去自定义.
```xml
<com.warkiz.widget.IndicatorSeekBar
    app:isb_tick_drawable="@mipmap/ic_launcher"
    .../>
```

注意: 可通过isb_tick_size 属性设置刻度图片大小, 最大限制显示30dp, 默认8dp . 如果图片小于30dp, 会整张图片显示.

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
onSectionChanged: 当SeekBar的类型为非连续 `discrete`系列 时, 这个回调会获得滑块的位置和滑块下的文字. 当为连续 `continuous`系列则不回调。
## 5. 混淆配置
```Java
-keep class com.warkiz.widget.** { *; }
```

## 6. 属性

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

## 8. 联系我
如果你对此项目有问题，欢迎通过以下方式联系我。

1. 提issue.
3. 发邮件, "warkiz".concat("4j").concat("@").concat("gmail.com")

