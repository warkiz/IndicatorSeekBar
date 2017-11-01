#IndicatorSeekBar<br>
#级标题  

A deeply customized SeekBar on Android, which can be changed the size / color / thumbDrawable / tickDrawable / textsBelowTick / indicator by user, can show an indicator view with progress above SeekBar when seeking.
###1. ScreenShot

###2. How to use
####2.1 xml

	<com.warkiz.widget.IndicatorSeekBar
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
			android:layout_marginTop="16dp"
            app:isb_max="78.8"
            app:isb_min="10.2"
            app:isb_progress="50.3"
            app:isb_progress_value_float="true"
            app:isb_show_indicator="true"/>


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

####2.2 Java

	IndicatorSeekBar indicatorSeekBar = new IndicatorSeekBar.Builder(this)
										.setMax(200)
										.setMin(0)
										.setProgress(35)
										.setSeekBarType(IndicatorSeekBarType.DISCRETE_TICKS)
										.setTickType(TickType.OVAL)
										.setTickColor(Color.parseColor("#0000FF"))
										.setTickSize(8)
										.setTickNum(8)
										.setBackgroundTrackSize(2)
										.setBackgroundTrackColor(Color.parseColor("#666666"))
										.setProgressTrackSize(3)
										.setProgressTrackColor(Color.parseColor("#0000FF"))
										.showIndicator(true)
										.setIndicatorType(IndicatorType.SQUARE_CORNERS)
										.setIndicatorColor(Color.parseColor("#0000FF"))
										.build();


####3. Ability
#####3.1 custom color/size
Below seekbar parts' color/size can be custom:

- background track
- progress track
- ticks
- text below tick
- thumb
- indicator
- indicator text

#####3.1 hide ticks
you can hide the ticks on the seekbar both end sides or on thumb left.

	<com.warkiz.widget.IndicatorSeekBar
		...
		app:isb_tick_both_end_hide="true"
		...
	/>

	<com.warkiz.widget.IndicatorSeekBar
		...
		app:isb_tick_on_thumb_left_hide="true"
		...
	/>

#####3.1 change the seekbar corners shape
 seekbar's track's is round corners default , could be set to square.

	<com.warkiz.widget.IndicatorSeekBar
		...
		app:isb_tick_both_end_hide="true"
		...
	/>

#####3.1 seeking to show progress text below thumb.
when the seekabr type is CONTINUOUS or DISCRETE_TICKS , you can set the progress text showing when seeking.

	<com.warkiz.widget.IndicatorSeekBar
		...
		app:isb_seek_bar_type="continuous"/discrete_ticks
        app:isb_thumb_progress_stay="true"
		...
	/>

	or

	indicatorSeekBar.setTextArray(R.array.tick_below_text_length_5);

#####3.1 custom 2 below texts on both ends of seekbar
when the seekabr type is CONTINUOUS_TEXTS_ENDS or DISCRETE_TICKS_TEXTS_ENDS , you can set the left & right text.

	<com.warkiz.widget.IndicatorSeekBar
		...
		app:isb_seek_bar_type="continuous_texts_ends"/discrete_ticks_texts_ends
        app:isb_text_left_end="last"
        app:isb_text_right_end="next"
		...
	/>

#####3.1 custom texts below tick
when the seekabr type is DISCRETE_TICKS_TEXTS , you can custom the texts below tick by an array, and the array's length should equals ticks num.

	<com.warkiz.widget.IndicatorSeekBar
		...
		app:isb_text_array="@array/tick_below_text_length_5"
        app:isb_tick_num="5"
		...
	/>

	or

	indicatorSeekBar.setTextArray(R.array.tick_below_text_length_5);

#####3.1 custom thumb drawable
Thumb can be replace by a drawable:

	<com.warkiz.widget.IndicatorSeekBar
		...
		app:isb_thumb_drawable="@mipmap/ic_launcher"
		...
	/>


#####3.1 custom tick drawable
Ticks can be replace by a drawable:

	<com.warkiz.widget.IndicatorSeekBar
		...
		app:isb_tick_drawable="@mipmap/ic_launcher"
		...
	/>

#####3.1 custom indicator
IndicatorSeekbar provided 3 kinds of indicator type ROUNDED_CORNERS / SQUARE_CORNERS / CUSTOM, when the indicator type is CUSTOM , you can set a custom indicator view.

	<com.warkiz.widget.IndicatorSeekBar
		...
		 app:isb_indicator_type="custom"
         app:isb_indicator_custom_layout="@layout/indicator"
		...
	/>

	or

	indicatorSeekBar.setCustomIndicator(R.layout.custom_indicator_blue);

#####3.1 custom indicator's top content view
when the indicator type is ROUNDED_CORNERS or SQUARE_CORNERS , you can set a custom indicator top content view.

	<com.warkiz.widget.IndicatorSeekBar
		...
		 app:isb_indicator_type="rounded_corners"/square_corners
		 app:isb_indicator_custom_top_content_layout="@layout/indicator"
		...
	/>

	or

	indicatorSeekBar.getIndicator().setIndicatorTopContentLayout(R.layout.top_content_view);

#####3.1 Listener
	indicatorSeekBar.setOnSeekChangeListener(new IndicatorSeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch) {

            }

            @Override
            public void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch) {
                //only callback on discrete serious seekbar type.
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick) {
            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

####3. Proguard
	-keep class com.warkiz.widget.** { *; }

####3. Attr
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
        <attr name="isb_show_indicator" format="boolean"/><!-- show indicator or not when seeking, default false-->
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



####3. License
Apache License 2.0


####3. Contact me
Feel free to contact me if you have any trouble on this project.

1. Create an issue.
2. Send mail to me, "warkiz".concat("4j").concat("@").concat("gmail.com")
