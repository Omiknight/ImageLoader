package cins.com.imageloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

public interface ImageLoaderStrategy {

  void init(Context context, ImageLoaderConfig loaderConfig);

  void showImage(@NonNull ImageLoaderOptions loaderOptions);

  void hideImage(@NonNull View view, int visible);

  void clearMemoryCache(Context context);

  void clearDiskCache(Context context);

}
