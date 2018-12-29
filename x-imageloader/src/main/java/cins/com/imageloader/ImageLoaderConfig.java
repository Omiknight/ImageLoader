package cins.com.imageloader;

import java.util.HashMap;

public class ImageLoaderConfig {

  private long maxMemory;
  private HashMap<ImageLoaderType, ImageLoaderStrategy> loaderMap = new HashMap<>();

  private ImageLoaderConfig(Builder builder) {
    maxMemory = builder.maxMemory;
    loaderMap = builder.imageLoaderMap;
  }

  public long getMaxMemory() {
    return maxMemory <= 0 ? 40 * 1024 * 1024 : maxMemory;
  }

  public HashMap<ImageLoaderType, ImageLoaderStrategy> getLoaderMap() {
    return loaderMap;
  }

  public static class Builder {
    private long maxMemory = 40 * 1024 * 1024;
    private HashMap<ImageLoaderType, ImageLoaderStrategy> imageLoaderMap = new HashMap<>();

    public Builder(ImageLoaderType loaderType, ImageLoaderStrategy loaderStrategy) {
      imageLoaderMap.put(loaderType, loaderStrategy);
    }

    public Builder addImageLoader(ImageLoaderType loaderType, ImageLoaderStrategy loaderStrategy) {
      imageLoaderMap.put(loaderType, loaderStrategy);
      return this;
    }

    /**
     * @param maxMemory 单位为 Byte
     */
    public Builder maxMemory(Long maxMemory) {
      this.maxMemory = maxMemory;
      return this;
    }

    public ImageLoaderConfig build() {
      return new ImageLoaderConfig(this);
    }
  }
}
