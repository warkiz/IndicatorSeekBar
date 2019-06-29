# IndicatorSeekBar

[![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-IndicatorSeekBar-green.svg?style=flat )]( https://android-arsenal.com/details/1/6434 )

This is a customizable SeekBar library on Android. Also, If you don't need indicator and want to show tick texts to top of seek bar, please see [the other library](https://github.com/warkiz/TickSeekBar).

[ 中文.md ](https://github.com/warkiz/IndicatorSeekBar/blob/master/README_zh.md)


## Overview
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/overview.png?raw=true" width = "392" height = "115"/>

## Screenshot

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/continuous.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_1.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_2.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/custom.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/java_build.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator.gif?raw=true" width = "264" height = "464"/>

## Demo
[download apk](https://github.com/warkiz/IndicatorSeekBar/raw/master/apk/demo.apk)

 Scan QR code to download:
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/downloadQRcode.png?raw=true" width = "100" height = "100"/>


## Setup

```gradle
implementation 'com.github.warkiz.widget:indicatorseekbar:2.1.2'
```

## Usage
#### xml

```xml
<com.warkiz.widget.IndicatorSeekBar
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:isb_max="100"
    app:isb_min="-1.0"
    app:isb_progress="25"
    app:isb_seek_smoothly="true"
    app:isb_ticks_count="5"
    app:isb_show_tick_marks_type="oval"
    app:isb_tick_marks_size="13dp"
    app:isb_tick_marks_drawable="@mipmap/ic_launcher"
    app:isb_show_tick_texts="true"
    app:isb_tick_texts_size="15sp"
    app:isb_tick_texts_color="@color/color_blue"
    app:isb_thumb_color="@color/color_green"
    app:isb_thumb_size="20dp"
    app:isb_show_indicator="rounded_rectangle"
    app:isb_indicator_color="@color/color_gray"
    app:isb_indicator_text_color="@color/colorAccent"
    app:isb_indicator_text_size="18sp"
    app:isb_track_background_color="@color/color_gray"
    app:isb_track_background_size="2dp"
    app:isb_track_progress_color="@color/color_blue"
    app:isb_track_progress_size="4dp"
    app:isb_only_thumb_draggable="false"/>
```

#### Java

```Java

 IndicatorSeekBar seekBar = IndicatorSeekBar
                .with(getContext())
                .max(110)
                .min(10)
                .progress(53)
                .tickCount(7)
                .showTickMarksType(TickMarkType.OVAL)
                .tickMarksColor(getResources().getColor(R.color.color_blue, null))
                .tickMarksSize(13)//dp
                .showTickTexts(true)
                .tickTextsColor(getResources().getColor(R.color.color_pink))
                .tickTextsSize(13)//sp
                .tickTextsTypeFace(Typeface.MONOSPACE)
                .showIndicatorType(IndicatorType.ROUNDED_RECTANGLE)
                .indicatorColor(getResources().getColor(R.color.color_blue))
                .indicatorTextColor(Color.parseColor("#ffffff"))
                .indicatorTextSize(13)//sp
                .thumbColor(getResources().getColor(R.color.colorAccent, null))
                .thumbSize(14)
                .trackProgressColor(getResources().getColor(R.color.colorAccent,null))
                .trackProgressSize(4)
                .trackBackgroundColor(getResources().getColor(R.color.color_gray))
                .trackBackgroundSize(2)
		.onlyThumbDraggable(false)
                .build();

```

## Indicator stay always

Put IndicatorSeekBar into a IndicatorStayLayout can make the indicator stayed always.
By the way, make sure you had called the attr to show the indicator before.

#### Xml

```xml
<com.warkiz.widget.IndicatorStayLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <!--your layout-->
    <com.warkiz.widget.IndicatorSeekBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:isb_show_indicator="rectangle" <!--show indicator can not be NONE-->
        ....../>
    <!--your layout-->
</com.warkiz.widget.IndicatorStayLayout>
```

#### Java

```Java
IndicatorSeekBar seekBar = IndicatorSeekBar
                .with(getContext())
                .max(50)
                .min(10)
                .showIndicatorType(IndicatorType.RECTANGLE) //show indicator can not be NONE
                ...
                .build();

new IndicatorStayLayout(getContext()).attachTo(seekBar);
```

## Custom indicator's View

You can custom the indicator View by below way:

If you want to replace the indicator's View on top part, you can call:
```Java
 seekBar.getIndicator().setTopContentView(yourTopView);
```

or want to custom the indicator's View you want , you can call:
```Java
seekBar.getIndicator().setContentView(yourView);
```

## Custom indicator's text

Set a format string with placeholder `${PROGRESS}` or `${TICK_TEXT}` to IndicatorSeekBar, the indicator's text would change.
For example:
If you want to show the progress with suffix: `%` ，the code like：

```Java
seekBar.setIndicatorTextFormat("${PROGRESS} %")

Kotlin:
seekBar.setIndicatorTextFormat("\${PROGRESS} %")
```

or want to show the tick text with prefix: `I am` ，the code like：

```Java
seekBar.setIndicatorTextFormat("I am ${TICK_TEXT}")

Kotlin:
seekBar.setIndicatorTextFormat("I am \${TICK_TEXT}")
```

## Custom section tracks color
The color of every block of seek bar can also be custom.

```Java
seekBar.customSectionTrackColor(new ColorCollector() {
    @Override
    public boolean collectSectionTrackColor(int[] colorIntArr) {
        //the length of colorIntArray equals section count
        colorIntArr[0] = getResources().getColor(R.color.color_blue, null);
        colorIntArr[1] = getResources().getColor(R.color.color_gray, null);
        colorIntArr[2] = Color.parseColor("#FF4081");
        ...
        return true; //True if apply color , otherwise no change
    }
});
```

## Selector drawable&color were supported

You can set the StateListDrawable or ColorStateList for the thumb, tickMarks;
 also, ColorStateList for tickTexts is supported, too. Usage's format according to:

Thumb selector drawable:

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this drawable is for thumb when pressing-->
    <item android:drawable="@mipmap/ic_launcher_round" android:state_pressed="true" />
    <!--for thumb in normal-->
    <item android:drawable="@mipmap/ic_launcher" />
</selector>
```

Thumb selector color:

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this color is for thumb which is at pressing status-->
    <item android:color="@color/colorAccent" android:state_pressed="true" />
    <!--for thumb which is at normal status-->
    <item android:color="@color/color_blue" />
</selector>
```

TickMarks selector drawable：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this drawable is for tickMarks those are at start side of thumb-->
    <item android:drawable="@mipmap/ic_launcher_round" android:state_selected="true" />
    <!--for tickMarks in normal-->
    <item android:drawable="@mipmap/ic_launcher" />
</selector>
```

TickMarks selector color：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this color is for marks those are at start side of thumb-->
    <item android:color="@color/colorAccent" android:state_selected="true" />
    <!--for marks those are at right side of thumb-->
    <item android:color="@color/color_gray" />
</selector>
```

TickTexts selector color：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this color is for texts those are at start side of thumb-->
    <item android:color="@color/colorAccent" android:state_selected="true" />
    <!--for tick text which is stopped under thumb -->
    <item android:color="@color/color_blue" android:state_hovered="true" />
    <!--for texts those are at right side of thumb-->
    <item android:color="@color/color_gray" />
</selector>
```

## Listener
```Java
seekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.i(TAG, seekParams.seekBar);
                Log.i(TAG, seekParams.progress);
                Log.i(TAG, seekParams.progressFloat);
                Log.i(TAG, seekParams.fromUser);
                //when tick count > 0
                Log.i(TAG, seekParams.thumbPosition);
                Log.i(TAG, seekParams.tickText);
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
            }
        });
```

## Attributes

[ attr.xml ](https://github.com/warkiz/IndicatorSeekBar/blob/master/indicatorseekbar/src/main/res/values/attr.xml)

## Donation by [ Paypal ](https://www.paypal.me/BuyMeACupOfTeaThx), thanks

So happy to receive your donation or encouraging words , and I will post this on my [ thanks-lists ](https://github.com/warkiz/IndicatorSeekBar#Thanks-lists)
, thanks.

## 打赏([ 支付宝 ](https://github.com/warkiz/IndicatorSeekBar/blob/master/app/src/main/res/mipmap-xxhdpi/alipay.png?raw=true))([ 微信支付 ](https://github.com/warkiz/IndicatorSeekBar/blob/master/app/src/main/res/mipmap-xxhdpi/wechat_pay.png?raw=true))

自从文档里公布了二维码，我收到国内的朋友的一些打赏，虽然金额不大，但是一些支持和鼓励的话语还是让我感到开心，非常感谢。

感谢所有之前支持我的朋友。如果下次你要给我打赏，可以顺带写上你的github地址，我会在这里用[ 链接 ](https://github.com/warkiz/IndicatorSeekBar#Thanks-lists)贴出来，算是相互鼓励。

## Thanks-lists

非常感谢列表中的朋友，你们的肯定和支持是我前进的最大动力！

Thanks for all the friends on the lists!

|Name/昵称|Date/日期|Payment Tools/方式|Words/留言|Friendly link/友情连接|
|:---:|:---:|:---:|:---:|:---:|
|[E*e]|2019-06-27|微信| | |
|[*●]|2019-06-14|微信| | |
|[*宣]|2019-06-05|支付宝| | |
|[*圆]|2019-05-29|支付宝|加油|[GitHub/SaltedFish999](https://github.com/SaltedFish999)|
|[*兴明]|2019-02-12|支付宝| |[GitHub/jdpxiaoming](https://github.com/jdpxiaoming)|
|[*j!n]|2019-01-08|微信|666|[GitHub/jincom](https://github.com/jincom)|
|[*乐]|2018-12-19|支付宝| | |
|[*勇]|2018-12-05|微信| | |
|[*天佑]|2018-11-05|支付宝| | |
|[*勇]|2018-10-16|支付宝| | |
|[*利成]|2018-05-22|支付宝| | |

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/app/src/main/res/mipmap-xxhdpi/alipay.png?raw=true" /><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/app/src/main/res/mipmap-xxhdpi/wechat_pay.png?raw=true" />

## Contact me

Feel free to contact me if you have any trouble on this project:
1. Create an issue.
2. Send mail to me, "warkiz".concat("4j").concat("@").concat("gmail.com")


## License

	Copyright (C) 2017 zhuangguangquan
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	   http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
