# IndicatorSeekBar

[![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-IndicatorSeekBar-green.svg?style=flat )]( https://android-arsenal.com/details/1/6434 )

这是一个安卓自定义SeekBar库. [ English.md ](https://github.com/warkiz/IndicatorSeekBar/blob/master/README.md)

## 预览

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/overview.png?raw=true" width = "392" height = "115"/>


## 截图


<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/continuous.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_1.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_2.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/custom.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/java_build.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator.gif?raw=true" width = "264" height = "464"/>

## 演示
[下载](https://github.com/warkiz/IndicatorSeekBar/blob/master/apk/demo.apk)

## 初始化

```gradle
    implementation 'com.github.warkiz.widget:indicatorseekbar:2.0.0'
```

## 使用
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
    app:isb_track_progress_size="4dp" />
```

#### Java

```Java

 IndicatorSeekBar seekbar = IndicatorSeekBar
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
                .build();

```

## 指示器总是停留

#### Xml

```xml
<com.warkiz.widget.IndicatorStayLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <!--your layout-->
    <com.warkiz.widget.IndicatorSeekBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:isb_show_indicator="rectangle" <!--显示指示器属性不能为none-->
        ....../>
    <!--your layout-->
</com.warkiz.widget.IndicatorStayLayout>
```

#### Java

```Java
IndicatorSeekBar seekbar = IndicatorSeekBar
                .with(getContext())
                .max(50)
                .min(10)
                .showIndicatorType(IndicatorType.RECTANGLE) //显示指示器属性不能为none
                ...
                .build();

new IndicatorStayLayout(getContext()).attachTo(seekbar);
```

## 自定义指示器文字

向IndicatorSeekBar设置一个带有占位符的格式字符串,`${PROGRESS}` 或者 `${TICK_TEXT}`,指示器的文字就会改变.

例如:
如果你想显示带百分号后缀的指示器进度文字: `%` ，代码如下：

```Java
seekbar.setIndicatorTextFormat("${PROGRESS} %")
```

如果你想显示带前缀 `I am`的指示器tick text文字 ，代码如下：

```Java
seekbar.setIndicatorTextFormat("I am ${TICK_TEXT}")
```

## 自定义每一节的track块颜色
seekbar上每一块track的颜色都能被设置：

```Java
sectionSeekBar.customSectionTrackColor(new ColorCollector() {
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

## 支持Selector类型的图片或颜色

你可以为滑块和tick标记设置StateListDrawable 或者 ColorStateList, 而且，tick下面的文字也支持ColorStateList,使用的格式如下:

滑块图片 selector:

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this drawable is for thumb when pressing-->
    <item android:drawable="@mipmap/ic_launcher_round" android:state_pressed="true" />
    <!--for thumb in normal-->
    <item android:drawable="@mipmap/ic_launcher" />
</selector>
```

滑块颜色 selectorr:

```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this drawable is for thumb when pressing-->
    <item android:drawable="@mipmap/ic_launcher_round" android:state_pressed="true" />
    <!--for thumb in normal-->
    <item android:drawable="@mipmap/ic_launcher" />
</selector>
```

tick标记图片 selector：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this drawable is for thumb when pressing-->
    <item android:drawable="@mipmap/ic_launcher_round" android:state_pressed="true" />
    <!--for thumb when normal-->
    <item android:drawable="@mipmap/ic_launcher" />
</selector>
```

tick标记颜色 selector：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this color is for marks those are at left side of thumb-->
    <item android:color="@color/colorAccent" android:state_selected="true" />
    <!--for marks those are at right side of thumb-->
    <item android:color="@color/color_gray" />
</selector>
```

tick文字颜色 selector：

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!--this color is for texts those are at left side of thumb-->
    <item android:color="@color/colorAccent" android:state_selected="true" />
    <!--for tick text which is stoped under thumb -->
    <item android:color="@color/color_blue" android:state_hovered="true" />
    <!--for texts those are at right side of thumb-->
    <item android:color="@color/color_gray" />
</selector>
```

## 监听器
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

## 属性

[ attr.xml ](https://github.com/warkiz/IndicatorSeekBar/blob/master/indicatorseekbar/src/main/res/values/attr.xml)

## 支持 & 联系我

Star to support me , many thanks!

Feel free to contact me if you have any trouble on this project:
1. Create an issue.
2. Send mail to me, "warkiz".concat("4j").concat("@").concat("gmail.com")

## 许可

	Copyright (C) 2017 zhuangguangquan warkiz

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
