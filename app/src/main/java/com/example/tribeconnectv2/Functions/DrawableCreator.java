package com.example.tribeconnectv2.Functions;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class DrawableCreator {

    String imageUrl ;

    public DrawableCreator() {
    }
    void PicassoDrawer(String imageUrl, ImageView imageView){
        Picasso.get().load(imageUrl).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Drawable drawable = new BitmapDrawable(Resources.getSystem(),bitmap);
                imageView.setImageDrawable(drawable);



            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}
