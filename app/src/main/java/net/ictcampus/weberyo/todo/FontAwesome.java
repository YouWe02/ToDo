package net.ictcampus.weberyo.todo;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontAwesome extends android.support.v7.widget.AppCompatTextView {

    public FontAwesome(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
    }

    public FontAwesome(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public FontAwesome(Context context){
        super(context);
        init();
    }

    private void init(){

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fontawesome.otf");
        setTypeface(tf);
    }

}
