package cins.com.imageloader;

import android.support.annotation.NonNull;
import android.view.View;

public class ImageLoader {

  public static ImageLoaderOptions.OptionBuilder createImageOptions(@NonNull View v,
      @NonNull String url) {
    return new ImageLoaderOptions.OptionBuilder(v, url);
  }

  public static ImageLoaderOptions.OptionBuilder createImageOptions(@NonNull View v,
      @NonNull int resId) {
    return new ImageLoaderOptions.OptionBuilder(v, resId);
  }
}
