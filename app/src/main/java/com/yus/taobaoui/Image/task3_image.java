package com.yus.taobaoui.Image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.yus.taobaoui.R;

public class task3_image extends android.support.v7.widget.AppCompatImageView {
    private task3_image task_3;

    public task3_image(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public task3_image(Context context) {
        super(context);
        init(context);
    }

    public task3_image(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        task_3 =(task3_image)findViewById(R.id.task_3);
        Picasso.with(context)
                .load("https://www.zhouzhihui.cn/media/avatar/default.jpg")
                .into(task_3);
    }

}
