自定义SeekBar, 能改变尺寸、颜色、滑块(thumb)图片、刻度(tick)图片、刻度文字(text)和气泡指示器(indicator)，当滑动时显示带有进度的指示器。
欢迎 `submit issue` or `pull request`。

[![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-IndicatorSeekBar-green.svg?style=flat )]( https://android-arsenal.com/details/1/6434 )

#### [ English readme.md here](https://github.com/warkiz/IndicatorSeekBar/blob/master/README.md)

## 截图

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/continuous.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_1.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/discrete_2.gif?raw=true" width = "264" height = "464"/>
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/custom.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/java_build.gif?raw=true" width = "264" height = "464"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator.gif?raw=true" width = "264" height = "464"/>

#####  下载 [demo.apk](https://github.com/warkiz/IndicatorSeekBar/blob/master/apk/demo.apk)查看更多演示.

## 特点

   本库兼容ConstraintLayout/AppbarLayout/NestedScrollview/RecyclerView/ViewPager/ListView/ScrollView/GridView/Dialog。

## 使用
###  1. app/build.gradle:
最新版本: [![DOWNLOAD](https://api.bintray.com/packages/warkiz/maven/indicatorseekbar/images/download.svg)](https://bintray.com/warkiz/maven/indicatorseekbar/_latestVersion)
```groovy
dependencies {
    //推荐使用最新版本
    //e.g. compile 'com.github.warkiz.widget:indicatorseekbar:1.2.6'
      compile 'com.github.warkiz.widget:indicatorseekbar:${LATEST_VERSION}'
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
        app:isb_indicator_type="circular_bubble"
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
				.setTickSize(8)//dp size
				.setTickNum(8)
				.setProgressTrackSize(3)//dp size
				.setIndicatorType(IndicatorType.CIRCULAR_BUBBLE)
				.setIndicatorColor(Color.parseColor("#0000FF"))
				.build();
//动态更新IndicatorSeekBar:
        indicatorSeekBar.getBuilder()
                        .setMax(232)
                        .setMin(43)
                        .setTickType(TickType.OVAL)
		        .setTickColor(Color.parseColor("#0000FF"))
                        .setIndicatorColor(Color.parseColor("#00ff00"))
                        .apply();

```
#####  更多使用方式请参考 [demo.apk](https://github.com/warkiz/IndicatorSeekBar/blob/master/apk/demo.apk).
-------------------------
## 进阶
- 自定义颜色、尺寸(刻度,滑块,滑条,刻度文字,指示器,指示器文字)
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/overview.png?raw=true" width = "392" height = "115"/>

- 可选 连续/分段 系类的SeekBar类型.

- 可选 矩形/圆角矩形/圆形气泡/自定义的指示器类型.
<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/indicator_type.png?raw=true" width = "392" height = "115"/>

- 自定义滑块图片

- 自定义刻度图片

- 自定义指示器布局

- 自定义指示器顶部布局

- 如果自定义指示器顶部的view需要显示进度, 那么这个view必须要有一个TextView，而且其id必须设置为`isb_progress`.

####  下载项目查看更多的特性.

## 滑动监听
```Java
indicatorSeekBar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {

	@Override
	public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {
	}

	@Override
	public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String textBelowTick, boolean fromUserTouch) {
	    //这个回调仅在分段系列`discrete`的SeekBar生效，当为连续系列`continuous`则不回调。
	}

	@Override
	public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
	}

	@Override
	public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
	}
});
```
## 属性

[ attr.xml ](https://github.com/warkiz/IndicatorSeekBar/blob/master/indicatorseekbar/src/main/res/values/attr.xml)


## 联系我
1. 提issue.
2. 发邮件, "warkiz".concat("4j").concat("@").concat("gmail.com")

## 支持我
1. star支持, 或：
2. 走VIP打赏通道，扫描二维码打赏，金额不限，支持IndicatorSeekBar。

<img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/AliPay.png?raw=true" width = "200" height = "222"/><img src="https://github.com/warkiz/IndicatorSeekBar/blob/master/gif/WechatPay.png?raw=true" width = "200" height = "222"/>
