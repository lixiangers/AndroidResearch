package com.lixiangers.androidresearch.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiangers.androidresearch.R;

import java.lang.reflect.Method;

public class EmptyView extends RelativeLayout {
    private String mText;
    private String mLoadingText;
    private TextView mTextView;
    private Button mButton;
    private View mBindView;

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, 0, 0);

        String text = ta.getString(R.styleable.EmptyView_emptyText);

        String buttonText = ta.getString(R.styleable.EmptyView_buttonText);

        mLoadingText = ta.getString(R.styleable.EmptyView_loadingText);
        ta.recycle();

        init(text, buttonText);
    }

    private void init(String text, String buttonText) {
        if (TextUtils.isEmpty(text)) text = "暂无数据";
        if (TextUtils.isEmpty(buttonText)) buttonText = "重试";
        if (TextUtils.isEmpty(mLoadingText)) mLoadingText = "加载中...";
        mText = text;
        mTextView = new TextView(getContext());
        mTextView.setText(text);
        LayoutParams textViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mTextView.setId(R.id.id_empty_text);
        addView(mTextView, textViewParams);
        mButton = new Button(getContext());
        mButton.setText(buttonText);
        LayoutParams buttonParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        buttonParams.addRule(RelativeLayout.BELOW, R.id.id_empty_text);
        addView(mButton, buttonParams);
    }

    public void loading() {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        setVisibility(View.VISIBLE);
        mButton.setVisibility(View.INVISIBLE);
        mTextView.setText(mLoadingText);
    }

    public void success() {
        setVisibility(View.GONE);
        if (mBindView != null) mBindView.setVisibility(View.VISIBLE);
    }

    public void empty() {
        if (mBindView != null) mBindView.setVisibility(View.GONE);
        setVisibility(View.VISIBLE);
        mButton.setVisibility(View.VISIBLE);
        mTextView.setText(mText);
    }

    public void bindView(View view) {
        mBindView = view;
    }

    public void buttonClick(final Object base, final String method,
                            final Object... parameters) {
        mButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int length = parameters.length;
                Class[] paramsTypes = new Class[length];
                for (int i = 0; i < length; i++) {
                    paramsTypes[i] = parameters[i].getClass();
                }
                try {

                    Method m = base.getClass().getDeclaredMethod(method, paramsTypes);
                    m.setAccessible(true);
                    m.invoke(base, parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
