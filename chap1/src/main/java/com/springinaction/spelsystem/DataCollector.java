package com.springinaction.spelsystem;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 28-Oct-17 (22:56).
 */
@Component
public class DataCollector {

  // The T() operator evaluates java.lang.System as a type so that the static
  // currentTimeMillis() method can be invoked.
  @Value("#{T(System).currentTimeMillis()}")
  private String time;

  // Refer to value of the artist property on a bean whose ID is sgtPeppers.
  // Getter in 'sgtPeppers' is necessary.
  @Value("#{sgtPeppers.artist}")
  private String artist;

  // Refer to system properties + default placeholder for unknown property.
  @Value("#{systemProperties['java.specification.vendor' ?: 'unknown']}")
  private String vendor;

  // Wire one bean into another bean's property.
  @Value("#{sgtPeppers}")
  private SgtPeppers sgtPeppers;

  // Call bean's method.
  @Value("#{artistSelector.selectArtist()}")
  private String selected;

  // Guard against NullPointerException. The expression will evaluate to null and will not
  // try to call 'toUpperCase'.
  @Value("#{artistSelector.nullArtist?.toUpperCase()}")
  private String nullArtist;

  // Working with class-scoped constant.
  @Value("#{T(java.lang.Math).PI}")
  private Double PI;

  // SpEL operators.
  @Value("#{2 * T(java.lang.Math).PI * circle.radius}")
  private Double circumference;

  // Regular expressions.
  @Value("#{email.email matches '^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$'}")
  private Boolean isEmailValid;

  // Evaluating collections.
  @Value("#{jukebox.songList.?[artist eq 'Cannibal Corpse']}")
  private List<Song> cannibalCorpseSongs;

  // Evaluating collections: projection
  @Value("#{jukebox.songList.?[artist eq 'Canvas Solaris'].![songName]}")
  private List<String> canvasSolarisSongsStrings;


  public List<String> getCanvasSolarisSongsStrings() {
    return canvasSolarisSongsStrings;
  }

  public List<Song> getCannibalCorpseSongs() {
    return cannibalCorpseSongs;
  }

  public Boolean getEmailValid() {
    return isEmailValid;
  }

  public Double getCircumference() {
    return circumference;
  }

  public Double getPI() {
    return PI;
  }

  public String getNullArtist() {
    return nullArtist;
  }

  public String getSelected() {
    return selected;
  }

  public SgtPeppers getSgtPeppers() {
    return sgtPeppers;
  }

  public String getTime() {
    return time;
  }

  public String getArtist() {
    return artist;
  }

  public String getVendor() {
    return vendor;
  }

}
