package cins.com.imageloader;

import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;

public interface LoaderListener {

  void onSuccess(@Nullable BitmapDrawable bitmapDrawable);

  void onFailure();

}
