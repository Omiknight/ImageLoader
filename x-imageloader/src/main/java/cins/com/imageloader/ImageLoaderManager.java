package cins.com.imageloader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

public class ImageLoaderManager {

  private ImageLoaderType loaderType = null;
  private HashMap<ImageLoaderType, ImageLoaderStrategy> imageLoaderMap = new HashMap<>();
  private static volatile ImageLoaderManager loaderManager;

  private ImageLoaderManager() {

  }

  public static ImageLoaderManager getInstance() {
    if (loaderManager == null) {
      synchronized (ImageLoaderManager.class) {
        if (loaderManager == null) {
          loaderManager = new ImageLoaderManager();
        }
      }
    }
    return loaderManager;
  }

  public void init(Context context, ImageLoaderConfig loaderConfig) {
    imageLoaderMap = loaderConfig.getLoaderMap();
    for (Map.Entry<ImageLoaderType, ImageLoaderStrategy> entry : imageLoaderMap.entrySet()) {
      if (entry.getValue() != null) {
        entry.getValue().init(context, loaderConfig);
      }
      if (entry.getKey() != null) {
        loaderType = entry.getKey();
      }
    }
  }

  public void showImage(@NonNull ImageLoaderOptions loaderOptions) {
    showImage(loaderType, loaderOptions);
  }

  public void showImage(ImageLoaderType loaderType, @NonNull ImageLoaderOptions loaderOptions) {
    if (getLoaderStrategy(loaderType) != null) {
      getLoaderStrategy(loaderType).showImage(loaderOptions);
    }
  }

  public void hideImage(@NonNull View view, int visible) {
    if (getLoaderStrategy(loaderType) != null) {
      getLoaderStrategy(loaderType).hideImage(view, visible);
    }
  }

  public void clearMemaryCache(Context context) {
    if (getLoaderStrategy(loaderType) != null) {
      getLoaderStrategy(loaderType).clearMemoryCache(context);
    }
  }

  public void clearDiskCache(Context context) {
    if (getLoaderStrategy(loaderType) != null) {
      getLoaderStrategy(loaderType).clearDiskCache(context);
    }
  }

  public void setLoaderType(ImageLoaderType loaderType) {
    this.loaderType = loaderType;
  }

  /**
   * 可创建默认的 Options 设置，假如不需要使用 ImageView ，
   * 请自行 new 一个 Imageview 传入即可
   * 内部只需要获取 Context
   */
  public static ImageLoaderOptions getDefaultOptions(@NonNull View container, @NonNull String url) {
    return ImageLoaderOptions.createImageOptions(container, url).isCrossFade(true).build();
  }

  private ImageLoaderStrategy getLoaderStrategy(ImageLoaderType loaderType) {
    return imageLoaderMap.get(loaderType);
  }
}
