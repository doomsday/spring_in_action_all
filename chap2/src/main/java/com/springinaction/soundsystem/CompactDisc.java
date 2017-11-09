package com.springinaction.soundsystem;

import java.util.List;

/**
 * Created by drpsy on 23-Oct-17 (21:56).
 */
public interface CompactDisc {
  void setTitle(String title);
  void setArtist(String artist);

  void playTrack(int trackNumber);
  void setTracks(List<String> tracks);
}
