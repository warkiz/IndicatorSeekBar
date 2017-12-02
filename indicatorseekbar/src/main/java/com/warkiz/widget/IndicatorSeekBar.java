package com.warkiz.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * created by ZhuangGuangquan on 2017/9/1
 */

public class IndicatorSeekBar extends View
{
    private static final int GAP_BETWEEN_SEEK_BAR_AND_BELOW_TEXT = 3;
    private static final int CUSTOM_DRAWABLE_MAX_LIMITED_WIDTH = 30;
    private static final String INSTANCE_STATE_KEY = "isb_instance_state";
    private BuilderParams p;
    private float mTickRadius;
    private Indicator mIndicator;
    private List<Float> mTextLocationList;
    private ArrayList<String> mTextList;
    private Context mContext;
    private Paint mStockPaint;
    private TextPaint mTextPaint;
    private float mTouchX;
    private float mTrackY;
    private float mSeekLength;
    private float mSeekStart;
    private float mSeekEnd;
    private Rect mRect;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mMeasuredWidth;
    private float mSeekBlockLength;
    private int mPaddingTop;
    private Bitmap mTickDraw;
    private Bitmap mThumbDraw;
    private boolean mFirstDraw = true;
    private boolean mIsTouching;
    private float mThumbRadius;
    private float mThumbTouchRadius;
    private OnSeekBarChangeListener mListener;
    private float lastProgress;
    private float mFaultTolerance = -1;
    private int mTextHeight;
    private float mThumbTouchHeight;
    private boolean hasMeasured;

    public IndicatorSeekBar(Context context)
    {
        this(context, null);
    }

    public IndicatorSeekBar(Builder builder)
    {
        super(builder.getContext(), null, 0);
        this.mContext = builder.getContext();
        this.p = builder.p;
        initData();
    }

    public IndicatorSeekBar(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public IndicatorSeekBar(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttrs(mContext, attrs);
        initData();
    }

    private void initAttrs(Context context, AttributeSet attrs)
    {
        p = new BuilderParams(context);
        if (attrs == null)
        {
            return;
        }
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IndicatorSeekBar);
        //seekBar
        p.mSeekBarType = ta.getInt(R.styleable.IndicatorSeekBar_isb_seek_bar_type, p.mSeekBarType);
        p.mMax = ta.getFloat(R.styleable.IndicatorSeekBar_isb_max, p.mMax);
        p.mMin = ta.getFloat(R.styleable.IndicatorSeekBar_isb_min, p.mMin);
        p.mProgress = ta.getFloat(R.styleable.IndicatorSeekBar_isb_progress, p.mProgress);
        p.mClearPadding = ta.getBoolean(R.styleable.IndicatorSeekBar_isb_clear_default_padding, p.mClearPadding);
        p.mIsFloatProgress = ta.getBoolean(R.styleable.IndicatorSeekBar_isb_progress_value_float, p.mIsFloatProgress);
        //track
        p.mBackgroundTrackSize = ta.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_track_background_bar_size, p.mBackgroundTrackSize);
        p.mProgressTrackSize = ta.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_track_progress_bar_size, p.mProgressTrackSize);
        p.mBackgroundTrackColor = ta.getColor(R.styleable.IndicatorSeekBar_isb_track_background_bar_color, p.mBackgroundTrackColor);
        p.mProgressTrackColor = ta.getColor(R.styleable.IndicatorSeekBar_isb_track_progress_bar_color, p.mProgressTrackColor);
        p.mTrackRoundedCorners = ta.getBoolean(R.styleable.IndicatorSeekBar_isb_track_rounded_corners, p.mTrackRoundedCorners);
        //thumb
        p.mThumbColor = ta.getColor(R.styleable.IndicatorSeekBar_isb_thumb_color, p.mThumbColor);
        p.mThumbSize = ta.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_thumb_width, p.mThumbSize);
        p.mThumbProgressStay = ta.getBoolean(R.styleable.IndicatorSeekBar_isb_thumb_progress_stay, p.mThumbProgressStay);
        p.mThumbDrawable = ta.getDrawable(R.styleable.IndicatorSeekBar_isb_thumb_drawable);
        //indicator
        p.mIndicatorType = ta.getInt(R.styleable.IndicatorSeekBar_isb_indicator_type, p.mIndicatorType);
        p.mIndicatorColor = ta.getColor(R.styleable.IndicatorSeekBar_isb_indicator_color, p.mIndicatorColor);
        p.mIndicatorTextColor = ta.getColor(R.styleable.IndicatorSeekBar_isb_indicator_text_color, p.mIndicatorTextColor);
        p.mShowIndicator = ta.getBoolean(R.styleable.IndicatorSeekBar_isb_show_indicator, p.mShowIndicator);
        p.mIndicatorTextSize = ta.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_indicator_text_size, p.mIndicatorTextSize);
        int indicatorCustomViewId = ta.getResourceId(R.styleable.IndicatorSeekBar_isb_indicator_custom_layout, 0);
        if (indicatorCustomViewId > 0)
        {
            p.mIndicatorCustomView = View.inflate(mContext, indicatorCustomViewId, null);
        }
        int indicatorCustomTopContentLayoutId = ta.getResourceId(R.styleable.IndicatorSeekBar_isb_indicator_custom_top_content_layout, 0);
        if (indicatorCustomTopContentLayoutId > 0)
        {
            p.mIndicatorCustomTopContentView = View.inflate(mContext, indicatorCustomTopContentLayoutId, null);
        }
        //tick
        p.mTickDrawable = ta.getDrawable(R.styleable.IndicatorSeekBar_isb_tick_drawable);
        p.mTickNum = ta.getInt(R.styleable.IndicatorSeekBar_isb_tick_num, p.mTickNum);
        p.mTickColor = ta.getColor(R.styleable.IndicatorSeekBar_isb_tick_color, p.mTickColor);
        p.mTickType = ta.getInt(R.styleable.IndicatorSeekBar_isb_tick_type, p.mTickType);
        p.mTickHideBothEnds = ta.getBoolean(R.styleable.IndicatorSeekBar_isb_tick_both_end_hide, p.mTickHideBothEnds);
        p.mTickOnThumbLeftHide = ta.getBoolean(R.styleable.IndicatorSeekBar_isb_tick_on_thumb_left_hide, p.mTickOnThumbLeftHide);
        p.mTickSize = ta.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_tick_size, p.mTickSize);
        //text
        p.mTextArray = ta.getTextArray(R.styleable.IndicatorSeekBar_isb_text_array);
        p.mLeftEndText = ta.getString(R.styleable.IndicatorSeekBar_isb_text_left_end);
        p.mRightEndText = ta.getString(R.styleable.IndicatorSeekBar_isb_text_right_end);
        p.mTextSize = ta.getDimensionPixelSize(R.styleable.IndicatorSeekBar_isb_text_size, p.mTextSize);
        p.mTextColor = ta.getColor(R.styleable.IndicatorSeekBar_isb_text_color, p.mTextColor);
        ta.recycle();
    }

    private void initData()
    {
        mTextLocationList = new ArrayList<>();
        mTextList = new ArrayList<>();
        if (p.mMax < p.mMin)
        {
            p.mMax = p.mMin;
        }
        if (p.mProgress < p.mMin)
        {
            p.mProgress = p.mMin;
        }
        if (p.mProgress > p.mMax)
        {
            p.mProgress = p.mMax;
        }
        if (p.mBackgroundTrackSize > p.mProgressTrackSize)
        {
            p.mBackgroundTrackSize = p.mProgressTrackSize;
        }
        if (2 * p.mThumbSize / 3f < p.mProgressTrackSize)
        {
            p.mThumbSize = Math.round(p.mProgressTrackSize * 3 / 2f);
        }
        if (p.mTickNum < 0)
        {
            p.mTickNum = 0;
        }
        if (p.mTickNum > 100)
        {
            p.mTickNum = 100;
        }
        if (p.mLeftEndText == null)
        {
            if (p.mIsFloatProgress)
            {
                p.mLeftEndText = p.mMin + "";
            } else
            {
                p.mLeftEndText = Math.round(p.mMin) + "";
            }
        }
        if (p.mRightEndText == null)
        {
            if (p.mIsFloatProgress)
            {
                p.mRightEndText = p.mMax + "";
            } else
            {
                p.mRightEndText = Math.round(p.mMax) + "";
            }
        }

        if (p.mTickDrawable != null)
        {
            p.mTickType = TickType.REC;//set a not none type
        }
        if (p.mThumbDrawable == null)
        {
            mThumbRadius = p.mThumbSize / 2.0f;
            mThumbTouchRadius = mThumbRadius * 1.2f;
            mThumbTouchHeight = mThumbTouchRadius * 2.0f;
        } else
        {
            float thumbIntrinsicWidth = p.mThumbDrawable.getIntrinsicWidth();
            int maxThumbWidth = IndicatorUtils.dp2px(mContext, CUSTOM_DRAWABLE_MAX_LIMITED_WIDTH);
            mThumbRadius = (maxThumbWidth < thumbIntrinsicWidth ? maxThumbWidth : thumbIntrinsicWidth) / 2.0f;
            mThumbTouchRadius = p.mThumbSize;
            mThumbTouchHeight =  mThumbTouchRadius * 2.0f;
        }
        mTickRadius = p.mTickSize / 2.0f;

        initStrokePaint();

        initDefaultPadding();
        if (p.mShowIndicator)
        {
            mIndicator = new Indicator(mContext, this, p);
        }

        mTickRadius = mTickRadius > mThumbRadius ? mThumbRadius : mTickRadius;

        if (noMarks())
        {
            if (p.mMax - p.mMin > 100)
            {
                p.mTickNum = Math.round(p.mMax - p.mMin);
            } else
            {
                p.mTickNum = 100;
            }
            if (p.mIsFloatProgress)
            {
                p.mTickNum = p.mTickNum * 10;
            }
        } else
        {
            p.mTickNum = p.mTickNum < 2 ? 2 : (p.mTickNum - 1);
        }
        if (needDrawText())
        {
            if (mTextPaint == null)
            {
                initTextPaint();
            }
            mTextPaint.getTextBounds("jf1", 0, 3, mRect);
            mTextHeight += mRect.height() + IndicatorUtils.dp2px(mContext, 2 * GAP_BETWEEN_SEEK_BAR_AND_BELOW_TEXT);
        }

        lastProgress = p.mProgress;
    }

    private boolean noMarks()
    {
        return p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS || p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS;
    }

    private void initEndTexts()
    {
        if (mTextList.size() == 0)
        {
            if (p.mLeftEndText != null)
            {
                mTextList.add(p.mLeftEndText);
                mTextLocationList.add((float) mPaddingLeft);
            }
            if (p.mRightEndText != null)
            {
                mTextList.add(p.mRightEndText);
                mTextLocationList.add((float) (mMeasuredWidth - mPaddingRight));
            }
        } else if (mTextList.size() == 1)
        {
            if (p.mLeftEndText != null)
            {
                mTextList.set(0, p.mLeftEndText);
            }
            if (p.mRightEndText != null)
            {
                mTextList.add(p.mRightEndText);
                mTextLocationList.add((float) (mMeasuredWidth - mPaddingRight));
            }
        } else
        {
            if (p.mLeftEndText != null)
            {
                mTextList.set(0, p.mLeftEndText);
            }
            if (p.mLeftEndText != null)
            {
                mTextList.set(mTextList.size() - 1, p.mRightEndText);
            }
        }
    }

    private void initDefaultPadding()
    {
        if (mPaddingLeft == 0 && mPaddingRight == 0 && !p.mClearPadding)
        {
            int normalPadding = IndicatorUtils.dp2px(mContext, 16);
            setPadding(normalPadding, getPaddingTop(), normalPadding, getPaddingBottom());
        }
    }

    private void initStrokePaint()
    {
        mStockPaint = new Paint();
        if (p.mTrackRoundedCorners)
        {
            mStockPaint.setStrokeCap(Paint.Cap.ROUND);
        }
        mStockPaint.setAntiAlias(true);
        if (p.mBackgroundTrackSize > p.mProgressTrackSize)
        {
            p.mProgressTrackSize = p.mBackgroundTrackSize;
        }
    }

    private void initTextPaint()
    {
        if (needDrawText())
        {
            mTextPaint = new TextPaint();
            mTextPaint.setAntiAlias(true);
            mTextPaint.setTextAlign(Paint.Align.CENTER);
            mTextPaint.setTextSize(p.mTextSize);
            mTextPaint.setColor(p.mTextColor);
            mRect = new Rect();
        }
    }

    private boolean needDrawText()
    {
        return p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS || p.mSeekBarType == IndicatorSeekBarType.DISCRETE_TICKS_TEXTS || p.mSeekBarType == IndicatorSeekBarType.DISCRETE_TICKS_TEXTS_ENDS || p.mThumbProgressStay;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = Math.round(mThumbTouchHeight + .5f + getPaddingTop() + getPaddingBottom());
        setMeasuredDimension(width, height + mTextHeight);
        initSeekBarInfo();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //draw 2th track
        mStockPaint.setColor(p.mProgressTrackColor);
        if (mFirstDraw)
        {
            //progress
            float touchX = (p.mProgress - p.mMin) * mSeekLength / (p.mMax - p.mMin) + mPaddingLeft;
            calculateTouchX(touchX);
            mFirstDraw = false;
        }
        float thumbX = getThumbX();
        mStockPaint.setStrokeWidth(p.mProgressTrackSize);
        canvas.drawLine(mSeekStart, mTrackY, thumbX, mTrackY, mStockPaint);
        //draw BG track
        mStockPaint.setStrokeWidth(p.mBackgroundTrackSize);
        mStockPaint.setColor(p.mBackgroundTrackColor);
        canvas.drawLine(thumbX, mTrackY, mSeekEnd, mTrackY, mStockPaint);
        //draw tick
        drawTicks(canvas, thumbX);
        //draw text below tick
        drawText(canvas);
        //drawThumbText
        drawThumbText(canvas, thumbX);
        //drawThumb
        drawThumb(canvas, thumbX);

    }

    private void drawThumb(Canvas canvas, float thumbX)
    {
        mStockPaint.setColor(p.mThumbColor);
        if (p.mThumbDrawable != null)
        {
            if (mThumbDraw == null)
            {
                mThumbDraw = getBitmapDraw(p.mThumbDrawable);
            }
            canvas.drawBitmap(mThumbDraw, thumbX - mThumbDraw.getWidth() / 2.0f, mTrackY - mThumbDraw.getHeight() / 2.0f, mStockPaint);
        } else
        {
            canvas.drawCircle(thumbX + p.mBackgroundTrackSize / 2.0f, mTrackY, mIsTouching ? mThumbTouchRadius : mThumbRadius, mStockPaint);
        }
    }

    private void drawThumbText(Canvas canvas, float thumbX)
    {
        if (p.mSeekBarType != IndicatorSeekBarType.CONTINUOUS && p.mSeekBarType != IndicatorSeekBarType.DISCRETE_TICKS)
        {
            return;
        }
        if (p.mThumbProgressStay)
        {
            canvas.drawText(getProgressString(p.mProgress), thumbX + p.mBackgroundTrackSize / 2.0f, mPaddingTop + mThumbTouchHeight + mRect.height() + IndicatorUtils.dp2px(mContext, 2), mTextPaint);
        }
    }

    private void initSeekBarInfo()
    {
        mMeasuredWidth = getMeasuredWidth();
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mPaddingTop = getPaddingTop();
        mSeekLength = mMeasuredWidth - mPaddingLeft - mPaddingRight;
        mSeekBlockLength = mSeekLength / p.mTickNum;
        mTrackY = mPaddingTop + mThumbTouchRadius;
        mSeekStart = p.mTrackRoundedCorners ? mPaddingLeft + p.mBackgroundTrackSize / 2.0f : mPaddingLeft;
        mSeekEnd = mMeasuredWidth - mPaddingRight - p.mBackgroundTrackSize / 2.0f;
        if (!hasMeasured)
        {
            initLocationListData();
            hasMeasured = true;
        }
    }

    private void drawTicks(Canvas canvas, float thumbX)
    {
        if (p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS || p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS || p.mTickType == TickType.NONE)
        {
            return;
        }
        if (mTextLocationList.size() == 0)
        {
            return;
        }
        mStockPaint.setColor(p.mTickColor);
        for (int i = 0; i < mTextLocationList.size(); i++)
        {
            float locationX = mTextLocationList.get(i);
            if (getThumbPosOnTick() == i)
            {
                continue;
            }
            if (p.mTickOnThumbLeftHide)
            {
                if (thumbX >= locationX)
                {
                    continue;
                }
            }
            if (p.mTickHideBothEnds)
            {
                if (i == 0 || i == mTextLocationList.size() - 1)
                {
                    continue;
                }
            }
            int rectWidth = IndicatorUtils.dp2px(mContext, 1);
            if (p.mTickDrawable != null)
            {
                if (mTickDraw == null)
                {
                    mTickDraw = getBitmapDraw(p.mTickDrawable);
                }
                if (p.mTickType == TickType.REC)
                {
                    canvas.drawBitmap(mTickDraw, locationX - mTickDraw.getWidth() / 2.0f + rectWidth, mTrackY - mTickDraw.getHeight() / 2.0f, mStockPaint);
                } else
                {
                    canvas.drawBitmap(mTickDraw, locationX - mTickDraw.getWidth() / 2.0f, mTrackY - mTickDraw.getHeight() / 2.0f, mStockPaint);
                }
            } else
            {
                if (p.mTickType == TickType.OVAL)
                {
                    canvas.drawCircle(locationX, mTrackY, mTickRadius, mStockPaint);
                } else if (p.mTickType == TickType.REC)
                {
                    float rectTickHeightRange;
                    if (thumbX >= locationX)
                    {
                        rectTickHeightRange = p.mProgressTrackSize;
                    } else
                    {
                        rectTickHeightRange = p.mBackgroundTrackSize;
                    }
                    canvas.drawRect(locationX - rectWidth, mTrackY - rectTickHeightRange / 2.0f, locationX + rectWidth, mTrackY + rectTickHeightRange / 2.0f + .5f, mStockPaint);
                }
            }
        }

    }

    private Bitmap getBitmapDraw(Drawable drawable)
    {
        if (drawable == null)
        {
            return null;
        }
        int tickWidth;
        int tickHeight;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int maxRange = IndicatorUtils.dp2px(mContext, CUSTOM_DRAWABLE_MAX_LIMITED_WIDTH);
        if (intrinsicWidth > maxRange)
        {
            tickWidth = maxRange;
        } else
        {
            tickWidth = intrinsicWidth;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > maxRange)
        {
            tickHeight = maxRange;
        } else
        {
            tickHeight = intrinsicHeight;
        }
        Bitmap bitmap = Bitmap.createBitmap(tickWidth, tickHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void drawText(Canvas canvas)
    {
        if (p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS || p.mSeekBarType == IndicatorSeekBarType.DISCRETE_TICKS)
        {
            return;
        }
        if (mTextList.size() == 0)
        {
            return;
        }
        mStockPaint.setColor(p.mTickColor);
        String allText = getAllText();
        mTextPaint.getTextBounds(allText, 0, allText.length(), mRect);

        int textHeight = Math.round(mRect.height() - mTextPaint.descent());
        int gap = IndicatorUtils.dp2px(mContext, 3);
        for (int i = 0; i < mTextList.size(); i++)
        {
            String text = getStringText(i);
            mTextPaint.getTextBounds(text, 0, text.length(), mRect);
            if (i == 0)
            {
                canvas.drawText(text, mTextLocationList.get(i) + mRect.width() / 2.0f, mPaddingTop + mThumbTouchHeight + textHeight + gap, mTextPaint);
            } else if (i == mTextList.size() - 1)
            {
                canvas.drawText(text, mTextLocationList.get(i) - mRect.width() / 2.0f, mPaddingTop + mThumbTouchHeight + textHeight + gap, mTextPaint);
            } else
            {
                if (p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS || p.mSeekBarType == IndicatorSeekBarType.DISCRETE_TICKS_TEXTS_ENDS)
                {
                    continue;
                }
                canvas.drawText(text, mTextLocationList.get(i), mPaddingTop + mThumbTouchHeight + textHeight + gap, mTextPaint);
            }
        }
    }

    @NonNull
    private String getStringText(int i)
    {
        String text;
        if (p.mTextArray != null)
        {
            if (i < p.mTextArray.length)
            {
                text = p.mTextArray[i] + "";
            } else
            {
                text = " ";
            }
        } else
        {
            text = mTextList.get(i) + "";
        }
        return text;
    }

    @NonNull
    private String getAllText()
    {
        String allText = "j";
        if (p.mTextArray != null)
        {
            for (CharSequence text : p.mTextArray)
            {
                allText += text;
            }
        }
        return allText;
    }

    private void initLocationListData()
    {
        if (p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS)
        {
            return;
        }
        if (p.mSeekBarType == IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS)
        {
            initEndTexts();
            return;
        }
        if (p.mTickNum > 1)
        {
            mTextLocationList.clear();
            mTextList.clear();
            for (int i = 0; i < p.mTickNum + 1; i++)
            {
                float tickX = mSeekBlockLength * i;
                mTextLocationList.add(tickX + mPaddingLeft);
                float tickProgress = p.mMin + (p.mMax - p.mMin) * tickX / mSeekLength;
                mTextList.add(getProgressString(tickProgress));
            }
            initEndTexts();
        }
    }

    private float getThumbX()
    {
        float mThumbXCache = mTouchX - p.mBackgroundTrackSize / 2.0f;
        float mThumbX;
        if (mThumbXCache <= mSeekStart)
        {
            if (mThumbXCache <= mPaddingLeft)
            {
                mThumbX = getPaddingLeft() - p.mBackgroundTrackSize / 2.0f;
            } else
            {
                mThumbX = mThumbXCache + p.mBackgroundTrackSize / 2.0f;
            }

        } else if (mThumbXCache >= mMeasuredWidth - mPaddingRight - p.mBackgroundTrackSize / 2.0f)
        {
            mThumbX = mMeasuredWidth - mPaddingRight - p.mBackgroundTrackSize / 2.0f;
        } else
        {
            mThumbX = mThumbXCache;
        }
        return mThumbX;
    }

    public int getThumbPosOnTick()
    {
        if (p.mSeekBarType > 1)
        {
            return Math.round(mTouchX / mSeekBlockLength);
        } else
        {
            return -1;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (isTouchSeekBar(event))
                {
                    if (mListener != null)
                    {
                        mListener.onStartTrackingTouch(this, getThumbPosOnTick());
                    }
                    refreshSeekBar(event, 0);
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                setListener();
                refreshSeekBar(event, 1);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mListener != null)
                {
                    mListener.onStopTrackingTouch(this);
                }
                mIsTouching = false;
                invalidate();
                if (p.mShowIndicator)
                {
                    mIndicator.hideIndicator();
                }
                break;
        }
        return false;
    }

    private void setListener()
    {
        if (mListener != null)
        {
            mListener.onProgressChanged(this, getProgress(), getProgressFloat(), true);
            if (p.mSeekBarType > 1)
            {
                int thumbPosOnTick = getThumbPosOnTick();
                if (p.mTextArray != null && thumbPosOnTick < (p.mTextArray.length))
                {
                    mListener.onSectionChanged(this, thumbPosOnTick, p.mTextArray[thumbPosOnTick] + "", true);
                } else
                {
                    mListener.onSectionChanged(this, thumbPosOnTick, "", true);
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE_KEY, super.onSaveInstanceState());
        bundle.putFloat("isb_progress", p.mProgress);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            p.mProgress = bundle.getFloat("isb_progress");
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE_KEY));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    public void setVisibility(int visibility)
    {
        super.setVisibility(visibility);
        if (GONE == visibility || INVISIBLE == visibility)
        {
            if (mIndicator != null && mIndicator.isShowing())
            {
                mIndicator.hideIndicator();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (mIndicator != null && mIndicator.isShowing())
        {
            mIndicator.hideIndicator();
        }
    }

    private void refreshSeekBar(MotionEvent event, int touchFlag)
    {
        calculateTouchX(adjustTouchX(event));
        calculateProgress();
        mIsTouching = true;
        if (0 == touchFlag)
        {
            if (lastProgress != p.mProgress)
            {
                setListener();
            }
            invalidate();
            if (p.mShowIndicator)
            {
                mIndicator.showIndicator(mTouchX, p.mSeekBarType, getThumbPosOnTick());
            }
        } else
        {
            if (lastProgress != p.mProgress)
            {
                setListener();
                invalidate();
                if (p.mShowIndicator)
                {
                    mIndicator.update(mTouchX, p.mSeekBarType, getThumbPosOnTick());
                }
            }
        }
    }

    private float adjustTouchX(MotionEvent event)
    {
        float mTouchXCache;
        if (event.getX() < mPaddingLeft)
        {
            mTouchXCache = mPaddingLeft;
        } else if (event.getX() > mMeasuredWidth - mPaddingRight)
        {
            mTouchXCache = mMeasuredWidth - mPaddingRight;
        } else
        {
            mTouchXCache = event.getX();
        }
        return mTouchXCache;
    }

    private void calculateProgress()
    {
        lastProgress = p.mProgress;
        p.mProgress = p.mMin + (p.mMax - p.mMin) * (mTouchX - mPaddingLeft) / mSeekLength;
    }

    private void calculateTouchX(float touchX)
    {
        int touchBlockSize = Math.round((touchX - mPaddingLeft) / mSeekBlockLength);
        mTouchX = mSeekBlockLength * touchBlockSize + mPaddingLeft;
    }

    private boolean isTouchSeekBar(MotionEvent event)
    {
        float mX = event.getX();
        float mY = event.getY();
        if (mFaultTolerance == -1)
        {
            mFaultTolerance = IndicatorUtils.dp2px(mContext, 5);
        }
        boolean inWidthRange = mX >= (mPaddingLeft - 2 * mFaultTolerance) && mX <= (mMeasuredWidth - mPaddingRight + 2 * mFaultTolerance);
        boolean inHeightRange = mY >= mTrackY - mThumbTouchRadius - mFaultTolerance && mY <= mTrackY + mThumbTouchRadius + mFaultTolerance;
        return inWidthRange && inHeightRange;
    }

    private float getProgressFloat(int newScale)
    {
        BigDecimal bigDecimal = BigDecimal.valueOf(p.mProgress);
        return bigDecimal.setScale(newScale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private float getProgressFloat(int newScale, float progress)
    {
        BigDecimal bigDecimal = BigDecimal.valueOf(progress);
        return bigDecimal.setScale(newScale, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private int getProgress(float progress)
    {
        return Math.round(progress);
    }

    private String getProgressString(float progress)
    {
        String progressString;
        if (p.mIsFloatProgress)
        {
            progressString = String.valueOf(getProgressFloat(1, progress));
        } else
        {
            progressString = String.valueOf(getProgress(progress));
        }
        return progressString;
    }

    /**
     * Get the seek bar's current level of progress in int type.
     *
     * @return progress in int type.
     */
    public int getProgress()
    {
        return Math.round(p.mProgress);
    }

    /**
     * Sets the current progress to the specified value.
     *
     * @param progress a new progress value , if the new progress is less than min , it will set to min ,if over max ,will be max.
     */
    public void setProgress(float progress)
    {
        if (progress < p.mMin)
        {
            p.mProgress = p.mMin;
        } else if (progress > p.mMax)
        {
            p.mProgress = p.mMax;
        } else
        {
            p.mProgress = progress;
        }
        if (mListener != null)
        {
            mListener.onProgressChanged(this, getProgress(), getProgressFloat(), false);
            if (p.mSeekBarType > 1)
            {
                int thumbPosOnTick = getThumbPosOnTick();
                if (p.mTextArray != null && thumbPosOnTick < (p.mTextArray.length - 1))
                {
                    mListener.onSectionChanged(this, thumbPosOnTick, p.mTextArray[thumbPosOnTick] + "", false);
                } else
                {
                    mListener.onSectionChanged(this, thumbPosOnTick, "", true);
                }
            }
        }
        float touchX = (p.mProgress - p.mMin) * mSeekLength / (p.mMax - p.mMin) + mPaddingLeft;
        calculateTouchX(touchX);
        postInvalidate();
    }

    /**
     * Get the seek bar's current level of progress in float type.
     *
     * @return current progress in float type.
     */
    public float getProgressFloat()
    {
        return getProgressFloat(1);
    }

    /**
     * get the current progress in String type , integer or float progress value type is fine.
     *
     * @return the current progress value String.
     */
    public String getProgressString()
    {
        return getProgressString(p.mProgress);
    }

    /**
     * @return the upper limit of this seek bar's range.
     */
    public float getMax()
    {
        return p.mMax;
    }

    /**
     * * @return the lower limit of this seek bar's range.
     *
     * @return the seek bar min value
     */
    public float getMin()
    {
        return p.mMin;
    }

    /**
     * get current indicator
     *
     * @return the indicator
     */
    public Indicator getIndicator()
    {
        return mIndicator;
    }

    /**
     * Set a new indicator view you wanted when touch to show.
     *
     * @param customIndicatorView the view is the indicator you touch to show;
     */
    public void setCustomIndicator(@NonNull View customIndicatorView)
    {
        mIndicator.setCustomIndicator(customIndicatorView);
    }

    /**
     * Set a new indicator view you wanted when touch to show.
     *
     * @param customIndicatorViewId the layout ID for indicator you touch to show;
     */
    public void setCustomIndicator(@LayoutRes int customIndicatorViewId)
    {
        mIndicator.setCustomIndicator(View.inflate(mContext, customIndicatorViewId, null));
    }

    /**
     * Set a new indicator view you wanted when touch to show. ,which can show seeking progress when touch to seek.
     *
     * @param customIndicatorView the view is the indicator you touch to show;
     * @param progressTextViewId  the progress id in the indicator root view , this id view must be a textView to show the progress
     */
    public void setCustomIndicator(@NonNull View customIndicatorView, @IdRes int progressTextViewId)
    {
        View tv = customIndicatorView.findViewById(progressTextViewId);
        if (tv == null)
        {
            throw new IllegalArgumentException(" can not find the textView in topContentView by progressTextViewId. ");
        }
        if (!(tv instanceof TextView))
        {
            throw new ClassCastException(" the view identified by progressTextViewId can not be cast to TextView. ");
        }
        mIndicator.setProgressTextView((TextView) tv);
        mIndicator.setCustomIndicator(customIndicatorView);
    }

    /**
     * set the seek bar build params . not null.
     *
     * @param p BuilderParams
     */
    private void apply(BuilderParams p)
    {
        if (p == null)
        {
            throw new NullPointerException(" BuilderParams can not be a null value. ");
        }
        this.p = p;
        initData();
        requestLayout();
    }


    /**
     * get all the texts below ticks in a array;
     *
     * @return the array of texts below tick
     */
    public CharSequence[] getTextArray()
    {
        return p.mTextArray;
    }

    /**
     * set the texts below ticks
     *
     * @param textArray the array of texts below tick
     */
    public void setTextArray(@ArrayRes int textArray)
    {
        this.p.mTextArray = mContext.getResources().getStringArray(textArray);
        invalidate();
    }

    /**
     * set the texts below ticks
     *
     * @param textArray the array of texts below tick
     */
    public void setTextArray(@NonNull CharSequence[] textArray)
    {
        this.p.mTextArray = textArray;
        invalidate();
    }

    /**
     * set the listener when touch to seek.not null.
     *
     * @param listener OnSeekBarChangeListener
     */
    public void setOnSeekChangeListener(@NonNull OnSeekBarChangeListener listener)
    {
        this.mListener = listener;
    }


    public interface OnSeekBarChangeListener
    {

        /**
         * Notification that the progress level has changed. Clients can use the fromUser parameter
         * to distinguish user-initiated changes from those that occurred programmatically.
         *
         * @param seekBar       The SeekBar whose progress has changed
         * @param progress      The current progress level. This will be in the range 0..max where max
         *                      was set by default. (The default value for max is 100.)
         * @param progressFloat The current progress level. This will be in the range 0.0f.max where max
         *                      was set by default. (The default value for max is 100.0f.)
         * @param fromUserTouch True if the progress change was initiated by the user.
         */
        void onProgressChanged(IndicatorSeekBar seekBar, int progress, float progressFloat, boolean fromUserTouch);

        /**
         * this callback only effect on discrete serious seek bar type ; when the thumb location change on tick ,will call this.
         *
         * @param seekBar        The SeekBar whose progress has changed
         * @param thumbPosOnTick The thumb's position on the seek bar's tick,if seek bar type is DISCRETE serious,
         *                       min thumbPosition is 0 , max is ticks length - 1 ; if seek bar type is CONTINUOUS serious,
         *                       no ticks on seek bar, so the thumbPosition will be always -1.
         * @param tickBelowText  this text  show below tick ,if tick below text is empty . text is "";
         * @param fromUserTouch  True if the seeking change was initiated by the user.
         */
        void onSectionChanged(IndicatorSeekBar seekBar, int thumbPosOnTick, String tickBelowText, boolean fromUserTouch);

        /**
         * Notification that the user has started a touch gesture. Clients may want to use this
         * to disable advancing the seekbar.
         *
         * @param seekBar        The SeekBar in which the touch gesture began
         * @param thumbPosOnTick The thumb's position on the seek bar's tick,if seek bar type is DISCRETE serious,
         *                       min thumbPosition is 0 , max is ticks length - 1 ; if seek bar type is CONTINUOUS serious,
         *                       no ticks on seek bar, so the thumbPosition will be always -1.
         */
        void onStartTrackingTouch(IndicatorSeekBar seekBar, int thumbPosOnTick);

        /**
         * Notification that the user has finished a touch gesture. Clients may want to use this
         * to re-enable advancing the seekbar.
         *
         * @param seekBar The SeekBar in which the touch gesture began
         */
        void onStopTrackingTouch(IndicatorSeekBar seekBar);
    }

    public static class Builder
    {
        BuilderParams p;

        public Builder(Context context)
        {
            this.p = new BuilderParams(context);
        }

        public IndicatorSeekBar build()
        {
            IndicatorSeekBar indicatorSeekBar = new IndicatorSeekBar(p.mContext);
            indicatorSeekBar.apply(p);
            return indicatorSeekBar;
        }

        /**
         * Sets the current progress to the specified value.
         *
         * @param progress a new progress value , if the new progress is less than min , it will set to min ,if over max ,will be max.
         * @return Builder
         */
        public Builder setProgress(float progress)
        {
            p.mProgress = progress;
            return this;
        }

        /**
         * Set the  upper limit of this seek bar's range.
         *
         * @param max range
         * @return Builder
         */
        public Builder setMax(float max)
        {
            p.mMax = max;
            return this;
        }

        /**
         * Set the  lower limit of this seek bar's range.
         *
         * @param min min progress
         * @return Builder
         */
        public Builder setMin(float min)
        {
            p.mMin = min;
            return this;
        }

        public Context getContext()
        {
            return p.mContext;
        }

        /**
         * seek bar has a default padding left and right(16 dp) , call this method to set both to zero.
         *
         * @param clearPadding true to clear the default padding, false to keep.
         * @return Builder
         */
        public Builder clearPadding(boolean clearPadding)
        {
            p.mClearPadding = clearPadding;
            return this;
        }

        /**
         * call this method to show the indicator when touching.
         *
         * @param showIndicator true to show the indicator when touch.
         * @return Builder
         */
        public Builder showIndicator(boolean showIndicator)
        {
            p.mShowIndicator = showIndicator;
            return this;
        }

        /**
         * set the seek bar's background track's Stroke Width
         *
         * @param backgroundTrackSize The dp size.
         * @return Builder
         */
        public Builder setBackgroundTrackSize(int backgroundTrackSize)
        {
            p.mBackgroundTrackSize = IndicatorUtils.dp2px(p.mContext, backgroundTrackSize);
            return this;
        }

        /**
         * set the size for text which below seek bar's tick .
         *
         * @param textSize The scaled pixel size.
         * @return Builder
         */
        public Builder setTextSize(int textSize)
        {
            p.mTextSize = IndicatorUtils.sp2px(p.mContext, textSize);
            return this;
        }

        /**
         * set the seek bar's background track's color.
         *
         * @param backgroundTrackColor colorInt
         * @return Builder
         */
        public Builder setBackgroundTrackColor(@ColorInt int backgroundTrackColor)
        {
            p.mBackgroundTrackColor = backgroundTrackColor;
            return this;
        }

        /**
         * set the seek bar's progress track's color.
         *
         * @param progressTrackColor colorInt
         * @return Builder
         */
        public Builder setProgressTrackColor(@ColorInt int progressTrackColor)
        {
            p.mProgressTrackColor = progressTrackColor;
            return this;
        }

        /**
         * set the seek bar's thumb's color.
         *
         * @param thumbColor colorInt
         * @return Builder
         */
        public Builder setThumbColor(@ColorInt int thumbColor)
        {
            p.mThumbColor = thumbColor;
            return this;
        }

        /**
         * call this method to change the block size of seek bar, when seek bar type is continuous series will be not worked.
         *
         * @param tickNum the tick count show on seek bar. if you want seek bar block size is N , this tickNum should be N+1.
         * @return Builder
         */
        public Builder setTickNum(int tickNum)
        {
            p.mTickNum = tickNum;
            return this;
        }

        /**
         * set the seek bar's tick width , if tick type is rec, call this method will be not worked(tick type is rec,has a regular value 2dp).
         *
         * @param tickSize the dp size.
         * @return Builder
         */
        public Builder setTickSize(int tickSize)
        {
            p.mTickSize = IndicatorUtils.dp2px(p.mContext, tickSize);
            return this;
        }

        /**
         * call this method to show different tick
         *
         * @param tickType TickType.REC;
         *                 TickType.OVAL;
         *                 TickType.NONE;
         * @return Builder
         */
        public Builder setTickType(int tickType)
        {
            p.mTickType = tickType;
            return this;
        }

        /**
         * set the seek bar's tick's color.
         *
         * @param tickColor colorInt
         * @return Builder
         */
        public Builder setTickColor(@ColorInt int tickColor)
        {
            p.mTickColor = tickColor;
            return this;
        }

        /**
         * call this method to show different series seek bar.
         *
         * @param seekBarType IndicatorSeekBarType.CONTINUOUS;
         *                    IndicatorSeekBarType.CONTINUOUS_TEXTS_ENDS;
         *                    IndicatorSeekBarType.DISCRETE;
         *                    IndicatorSeekBarType.DISCRETE_TICKS;
         *                    IndicatorSeekBarType.DISCRETE_TICKS_TEXTS;
         *                    IndicatorSeekBarType.DISCRETE_TICKS_TEXTS_ENDS;
         * @return Builder
         */
        public Builder setSeekBarType(int seekBarType)
        {
            p.mSeekBarType = seekBarType;
            return this;
        }

        /**
         * call this method to hide the ticks which show in the both ends sides of seek bar.
         *
         * @param tickHideBothEnds true for hide.
         * @return Builder
         */
        public Builder hideBothEndsTicks(boolean tickHideBothEnds)
        {
            p.mTickHideBothEnds = tickHideBothEnds;
            return this;
        }

        /**
         * call this method to hide the ticks on seekBar thumb left;
         *
         * @param tickOnThumbLeftHide true for hide.
         * @return Builder
         */
        public Builder hideTickOnThumbLeft(boolean tickOnThumbLeftHide)
        {
            p.mTickOnThumbLeftHide = tickOnThumbLeftHide;
            return this;
        }

        /**
         * call this method to show the seek bar's ends to square corners.default rounded corners.
         *
         * @param trackCornersIsRounded false to show square corners.
         * @return Builder
         */
        public Builder isRoundedTrackCorner(boolean trackCornersIsRounded)
        {
            p.mTrackRoundedCorners = trackCornersIsRounded;
            return this;
        }

        /**
         * set the seek bar's progress track's Stroke Width
         *
         * @param progressTrackSize The dp size.
         * @return Builder
         */
        public Builder setProgressTrackSize(int progressTrackSize)
        {
            p.mProgressTrackSize = IndicatorUtils.dp2px(p.mContext, progressTrackSize);
            return this;
        }

        /**
         * call this method to replace the seek bar's tick's below text.
         *
         * @param textArray the length should same as tickNum.
         * @return Builder
         */
        public Builder setTextArray(CharSequence[] textArray)
        {
            p.mTextArray = textArray;
            return this;
        }

        /**
         * call this method to replace the seek bar's tick's below text.
         *
         * @param textArray the length should same as tickNum.
         * @return Builder
         */
        public Builder setTextArray(@ArrayRes int textArray)
        {
            p.mTextArray = p.mContext.getResources().getStringArray(textArray);
            return this;
        }


        /**
         * set the seek bar's thumb's Width.
         *
         * @param thumbWidth The dp size.
         * @return Builder
         */
        public Builder setThumbWidth(int thumbWidth)
        {
            p.mThumbSize = IndicatorUtils.dp2px(p.mContext, thumbWidth);
            return this;
        }

        /**
         * set the seek bar's indicator's color. have no influence on custom indicator.
         *
         * @param indicatorColor colorInt
         * @return Builder
         */
        public Builder setIndicatorColor(@ColorInt int indicatorColor)
        {
            p.mIndicatorColor = indicatorColor;
            return this;
        }

        /**
         * set the seek bar's indicator's custom indicator layout identify. only effect on custom indicator type.
         *
         * @param mIndicatorCustomLayout the custom indicator layout identify
         * @return Builder
         */
        public Builder setIndicatorCustomLayout(@LayoutRes int mIndicatorCustomLayout)
        {
            p.mIndicatorType = IndicatorType.CUSTOM;
            p.mIndicatorCustomView = View.inflate(p.mContext, mIndicatorCustomLayout, null);
            return this;
        }

        /**
         * set the seek bar's indicator's custom indicator layout identify. only effect on custom indicator type.
         *
         * @param indicatorCustomView the custom view for indicator identify
         * @return Builder
         */
        public Builder setIndicatorCustomView(@NonNull View indicatorCustomView)
        {
            p.mIndicatorType = IndicatorType.CUSTOM;
            p.mIndicatorCustomView = indicatorCustomView;
            return this;
        }

        /**
         * set the seek bar's indicator's custom top content view layout identify. no effect on custom indicator type.
         *
         * @param mIndicatorCustomTopContentLayout the custom indicator top content layout identify
         * @return Builder
         */
        public Builder setIndicatorCustomTopContentLayout(@LayoutRes int mIndicatorCustomTopContentLayout)
        {
            p.mIndicatorCustomTopContentView = View.inflate(p.mContext, mIndicatorCustomTopContentLayout, null);
            return this;
        }

        /**
         * set the seek bar's indicator's custom top content view layout identify. no effect on custom indicator type.
         *
         * @param topContentView the custom view for indicator top content.
         * @return Builder
         */
        public Builder setIndicatorCustomTopContentView(@NonNull View topContentView)
        {
            p.mIndicatorCustomTopContentView = topContentView;
            return this;
        }

        /**
         * call this method to show different shape of indicator.
         *
         * @param indicatorType IndicatorType.SQUARE_CORNERS;
         *                      IndicatorType.ROUNDED_CORNERS;
         *                      IndicatorType.CUSTOM;
         * @return Builder
         */
        public Builder setIndicatorType(int indicatorType)
        {
            p.mIndicatorType = indicatorType;
            return this;
        }

        /**
         * call this method to show the text below thumb ,the text will slide with the thumb. have no influence on discrete series seek bar type.
         *
         * @param thumbProgressStay true to show thumb text.
         * @return Builder
         */
        public Builder thumbProgressStay(boolean thumbProgressStay)
        {
            p.mThumbProgressStay = thumbProgressStay;
            return this;
        }

        /**
         * make the progress in float type. default in int type.
         *
         * @param floatProgress true for float progress
         * @return Builder
         */
        public Builder isFloatProgress(boolean floatProgress)
        {
            p.mIsFloatProgress = floatProgress;
            return this;
        }

        /**
         * replace the left ends text of seek bar.
         * leftEndText the text below SeekBar's left end below
         *
         * @param leftEndText the text of SeekBar's left end below.
         * @return Builder
         */
        public Builder setLeftEndText(String leftEndText)
        {
            p.mLeftEndText = leftEndText;
            return this;
        }

        /**
         * replace the right ends text of seek bar.
         *
         * @param rightEndText the text below SeekBar's right end below
         * @return Builder
         */
        public Builder setRightEndText(String rightEndText)
        {
            p.mRightEndText = rightEndText;
            return this;
        }

        /**
         * call this method to custom the tick showing drawable.
         *
         * @param tickDrawableId the drawableId for tick drawable.
         * @return Builder
         */
        public Builder setTickDrawableId(@DrawableRes int tickDrawableId)
        {
            p.mTickDrawable = p.mContext.getResources().getDrawable(tickDrawableId);
            return this;
        }

        /**
         * call this method to custom the tick showing drawable.
         *
         * @param tickDrawable the drawable show as tick.
         * @return Builder
         */
        public Builder setTickDrawable(@NonNull Drawable tickDrawable)
        {
            p.mTickDrawable = tickDrawable;
            return this;
        }

        /**
         * call this method to custom the thumb showing drawable.
         *
         * @param thumbDrawableId the drawableId for thumb drawable.
         * @return Builder
         */
        public Builder setThumbDrawable(@DrawableRes int thumbDrawableId)
        {
            p.mThumbDrawable = p.mContext.getResources().getDrawable(thumbDrawableId);
            return this;
        }

        /**
         * call this method to custom the thumb showing drawable.
         *
         * @param thumbDrawable the drawable show as Thumb.
         * @return Builder
         */
        public Builder setThumbDrawable(Drawable thumbDrawable)
        {
            p.mThumbDrawable = thumbDrawable;
            return this;
        }

        /**
         * set the color for text below seek bar's tick.
         *
         * @param textColor ColorInt
         * @return Builder
         */
        public Builder setTextColor(@ColorInt int textColor)
        {
            p.mTextColor = textColor;
            return this;
        }

        /**
         * set the color for indicator text . have no influence on custom tickDrawable.
         *
         * @param indicatorTextColor ColorInt
         * @return Builder
         */
        public Builder setIndicatorTextColor(@ColorInt int indicatorTextColor)
        {
            p.mIndicatorTextColor = indicatorTextColor;
            return this;
        }

        /**
         * change the size for indicator text.have no influence on custom indicator.
         *
         * @param indicatorTextSize The scaled pixel size.
         * @return Builder
         */
        public Builder setIndicatorTextSize(int indicatorTextSize)
        {
            p.mIndicatorTextSize = IndicatorUtils.sp2px(p.mContext, indicatorTextSize);
            return this;
        }

    }

}