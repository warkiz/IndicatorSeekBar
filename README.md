
**A custom SeekBar on Android, which can be changed the `size` , `color` , `thumb drawable` , `tick drawable` , `texts` , `indicator`, also can show an indicator view with progress above SeekBar when seeking. Welcome `submit issue` or `pull request`.**

#### [ 中文说明文档 ](https://github.com/warkiz/IndicatorSeekBar/blob/master/README_EN.md)

[![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-IndicatorSeekBar-green.svg?style=flat )]( https://android-arsenal.com/details/1/6434 )

## Screenshot

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/continuous.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_1.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_2.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/custom.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/java_build.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator.gif?raw=true" width = "264" height = "464"/>

####  Download the [demo.apk](https://github.com/warkiz/IndicatorSeekBar/blob/master/apk/demo.apk) for more details about usage.

## Advantage
IndicatorSeekBar is ok to use in ConstraintLayout/AppbarLayout/NestedScrollview/RecyclerView/ViewPager/ListView/ScrollView/GridView/Dialog scrollable situation.

## Usage

### 1. build.gradle in module :
latest version : [![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
```groovy
dependencies {
  //recommend using latest version.
  //e.g. compile 'com.github.warkiz.widget:indicatorseekbar:1.2.9'
  compile 'com.github.warkiz.widget:indicatorseekbar:${LATEST_VERSION}'
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
```

```xml
<com.warkiz.widget.IndicatorSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginTop="16dp"
    app:isb_indicator_type="circular_bubble"
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
				.setTickNum(8)
				.setBackgroundTrackSize(2)//dp size
				.setProgressTrackSize(3)//dp size
				.setIndicatorType(IndicatorType.SQUARE_CORNERS)
				.setIndicatorColor(Color.parseColor("#0000FF"))
				.build();

//change build params at the runtime.

 indicatorSeekBar.getBuilder()
                 .setMax(232)
                 .setMin(43)
                 .setTickType(TickType.OVAL)
                 .setTickColor(Color.parseColor("#0000FF"))
                 .apply();

```
-------------------------
## Advance Usage
- customized color/size (tick,thumb,track,text,indicator,indicator-text)
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/overview.png?raw=true" width = "392" height = "115"/>

- choose different series SeekBar type.(continuous,discrete series)

- choose different indicator type.(rectangle,rectangle_rounded_corner,circular_bubble,custom)
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator_type.png?raw=true" width = "392" height = "115"/>

- customized thumb drawable

- customized tick drawable

- customized indicator

- customized indicator's top content view

####  Check out the project for more details about advance usage.

## listener
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

## Attributes

[ attr.xml ](https://github.com/warkiz/IndicatorSeekBar/blob/master/indicatorseekbar/src/main/res/values/attr.xml)

## Support & Contact me

Star to support me , many thanks!

Feel free to contact me if you have any trouble on this project:
1. Create an issue.
2. Send mail to me, "warkiz".concat("4j").concat("@").concat("gmail.com")

## Thanks
[material.io-slider  ](https://material.io/guidelines/components/sliders.html#sliders-continuous-slider)[MaterialDesignLibrary  ](https://github.com/navasmdc/MaterialDesignLibrary)[PointerPopupWindow  ](https://github.com/okry1123/PointerPopupWindow)[SeekBarCompat  ](https://github.com/ahmedrizwan/SeekBarCompat)[BubbleSeekBar  ](https://github.com/woxingxiao/BubbleSeekBar)[android-slidr  ](https://github.com/florent37/android-slidr)
