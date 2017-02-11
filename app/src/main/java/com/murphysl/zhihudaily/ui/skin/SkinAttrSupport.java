package com.murphysl.zhihudaily.ui.skin;

import android.content.Context;
import android.util.AttributeSet;

import com.murphysl.zhihudaily.config.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * SkinAttrSupport
 *
 * @author: MurphySL
 * @time: 2017/2/9 17:27
 */


public class SkinAttrSupport {

    public static List<SkinAttr> getSkinAttrs(AttributeSet attrs , Context context){
        List<SkinAttr> skinAttrs = new ArrayList<>();
        SkinAttrType attrType = null;
        SkinAttr skinAttr = null;

        for(int i = 0 , n = attrs.getAttributeCount() ;i < n ;i ++){
            String attrName = attrs.getAttributeName(i);
            String attrVal = attrs.getAttributeValue(i);
            if(attrVal.startsWith("@")){
                int id = -1;
                try {
                    id = Integer.parseInt(attrVal.substring(1));
                }catch (Exception e){

                }
                if(id == -1) continue;

                String resName = context.getResources().getResourceEntryName(id);
                if(resName.startsWith(Constants.SKIN_HEAD)){
                    attrType = getSupportAttrType(attrName);
                    if(attrType == null) continue;
                    skinAttr = new SkinAttr(resName , attrType);
                    skinAttrs.add(skinAttr);
                }
            }
        }
        return skinAttrs;
    }

    private static SkinAttrType getSupportAttrType(String attrName) {
        for(SkinAttrType attrType : SkinAttrType.values()){
            if(attrType.getResType().equals(attrName)){
                return attrType;
            }
        }
        return null;
    }
}
