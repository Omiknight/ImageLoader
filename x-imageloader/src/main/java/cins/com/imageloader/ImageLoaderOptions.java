package cins.com.imageloader;

import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;

public class ImageLoaderOptions {

  private boolean asGif;
  private int blurValue;
  private boolean blurImage;
  private int imageRadius;
  private boolean isCircle;
  private int resourceId;
  private String imageUrl;
  private View viewContainer;
  private int holderDrawable;
  private ImageSize imageSize;
  private int errorDrawable;
  private boolean isCrossFade;
  private boolean isSkipMemoryCache;
  private DiskCacheStrategy diskCacheStrategy;

  private LoaderListener loaderListener;
  private ProgressListener progressListener;

  private ImageLoaderOptions(OptionBuilder builder) {
    this.asGif = builder.asGif;
    this.isCircle = builder.isCircle;
    this.imageUrl = builder.imageUrl;
    this.resourceId = builder.resource;
    this.imageSize = builder.imageSize;
    this.blurImage = builder.blurImage;
    this.blurValue = builder.blurValue;
    this.imageRadius = builder.imageRadius;
    this.isCrossFade = builder.isCrossFade;
    this.viewContainer = builder.viewContainer;
    this.errorDrawable = builder.errorDrawable;
    this.holderDrawable = builder.holderDrawable;
    this.isSkipMemoryCache = builder.isSkipMemoryCache;
    this.diskCacheStrategy = builder.diskCacheStrategy;

    this.loaderListener = builder.loaderListener;
    this.progressListener = builder.progressListener;
  }

  public LoaderListener getLoaderListener() {
    return loaderListener;
  }

  public int getBlurValue() {
    return blurValue;
  }

  public boolean needImageRadius() {
    return imageRadius > 0;
  }

  public int getImageRadius() {
    return imageRadius;
  }

  public int getResourceId() {
    return resourceId;
  }

  public boolean isBlurImage() {
    return blurImage;
  }

  public View getViewContainer() {
    return viewContainer;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public boolean isCircle() {
    return isCircle;
  }

  public int getHolderDrawable() {
    return holderDrawable;
  }

  public ProgressListener getProgressListener() {
    return progressListener;
  }

  public ImageSize getImageSize() {
    return imageSize;
  }

  public int getErrorDrawable() {
    return errorDrawable;
  }

  public boolean isAsGif() {
    return asGif;
  }

  public boolean isCrossFade() {
    return isCrossFade;
  }

  public boolean isSkipMemoryCache() {
    return isSkipMemoryCache;
  }

  public DiskCacheStrategy getDiskCacheStrategy() {
    return diskCacheStrategy;
  }

  public static OptionBuilder createImageOptions(@NonNull View v, @NonNull String url) {
    return new OptionBuilder(v, url);
  }

  public static OptionBuilder createImageOptions(@NonNull View v, @NonNull int resource) {
    return new OptionBuilder(v, resource);
  }

  public void show() {
    ImageLoaderManager.getInstance().showImage(this);
  }

  public static class OptionBuilder {
    private String imageUrl;
    private int resource = -1;
    private View viewContainer;
    private int blurValue = 15;
    private int imageRadius = 0;
    private ImageSize imageSize;
    private boolean asGif = false;
    private int errorDrawable = -1;
    private int holderDrawable = -1;
    private boolean isCircle = false;
    private boolean blurImage = false;
    private boolean isCrossFade = false;
    private boolean isSkipMemoryCache = false;
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.DEFAULT;
    private LoaderListener loaderListener;
    private ProgressListener progressListener;

    public OptionBuilder(@NonNull View v, @NonNull String imageUrl) {
      this.imageUrl = imageUrl;
      this.viewContainer = v;
    }

    public OptionBuilder(@NonNull View v, @NonNull int resourceId) {
      this.resource = resourceId;
      this.viewContainer = v;
    }

    public OptionBuilder placeholder(@DrawableRes int holderDrawable) {
      this.holderDrawable = holderDrawable;
      return this;
    }

    public OptionBuilder asGif(boolean asGif) {
      this.asGif = asGif;
      return this;
    }

    public OptionBuilder isCircle() {
      this.isCircle = true;
      return this;
    }

    public OptionBuilder isCrossFade(boolean isCrossFade) {
      this.isCrossFade = isCrossFade;
      return this;
    }

    public OptionBuilder blurImage(boolean blurImage) {
      this.blurImage = blurImage;
      return this;
    }

    public OptionBuilder imageRadiusPx(@Dimension(unit = Dimension.PX) int rdius) {
      this.imageRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, rdius,
          viewContainer.getContext().getApplicationContext().getResources().getDisplayMetrics());

      return this;
    }

    public OptionBuilder imageRadiusDp(@Dimension(unit = Dimension.DP) int rdius) {
      this.imageRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rdius,
          viewContainer.getContext().getApplicationContext().getResources().getDisplayMetrics());
      return this;
    }

    public OptionBuilder blurValue(@IntRange(from = 0) int blurvalue) {
      this.blurValue = blurvalue;
      return this;
    }

    public OptionBuilder resetSize(int width, int height) {
      this.imageSize = new ImageSize(width, height);
      return this;
    }

    public OptionBuilder isSkipMemoryCache(boolean isSkipMemoryCache) {
      this.isSkipMemoryCache = isSkipMemoryCache;
      return this;
    }

    public OptionBuilder diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
      this.diskCacheStrategy = diskCacheStrategy;
      return this;
    }

    public OptionBuilder error(@DrawableRes int errorDrawable) {
      this.errorDrawable = errorDrawable;
      return this;
    }

    public OptionBuilder listener(LoaderListener loaderListener) {
      this.loaderListener = loaderListener;
      return this;
    }

    public OptionBuilder progress(ProgressListener progressListener) {
      this.progressListener = progressListener;
      return this;
    }

    public ImageLoaderOptions build() {
      return new ImageLoaderOptions(this);
    }
  }

  public final static class ImageSize {
    private int width;
    private int height;

    public ImageSize(int width, int height) {
      this.width = width;
      this.height = height;
    }

    public int getHeight() {
      return height;
    }

    public int getWidth() {
      return width;
    }
  }

  public enum DiskCacheStrategy {
    All, NONE, SOURCE, RESULT, DEFAULT
  }
}
