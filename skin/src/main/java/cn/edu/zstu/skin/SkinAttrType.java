package cn.edu.zstu.skin;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * 要换肤控件属性的类型【background，textColor，src】
 * <p>
 * 各个控件的background，可能为drawable或者color
 * ImageView的src，src中可能为drawable或者mipmap
 * TextView或者EditText的textColor，是颜色值
 * <p>
 * Created by CooLoongWu on 2017-12-6 14:20.
 */

public enum SkinAttrType {

    SRC("src") {
        @Override
        void applyNewAttr(ResourcesManager resourcesManager, View view, String resName) {
            if (view instanceof ImageView) {
                Drawable drawable = resourcesManager.getDrawableByName(resName);
                if (drawable != null) {
                    ((ImageView) view).setImageDrawable(drawable);
                } else {
                    drawable = resourcesManager.getMipmapByName(resName);
                    if (drawable != null) {
                        ((ImageView) view).setImageDrawable(drawable);
                    } else {
                        Log.e("SkinAttrType", "没有" + resName + "资源");
                    }
                }
            }
        }
    };

    String attrType;

    SkinAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrType() {
        return attrType;
    }

    abstract void applyNewAttr(ResourcesManager resourcesManager, View view, String resName);
}