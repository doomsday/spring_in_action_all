package com.springinaction.soundsystem;

import java.util.List;

/**
 * Created by drpsy on 23-Oct-17 (21:58).
 */
public class BlankDisc implements CompactDisc {

  private String artist;
  private String title;
  private List<String> tracks;

  public void setTitle(String title) {
    this.title = title;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public void playTrack(int trackNumber) {
    System.out.println("Playing " + tracks.get(trackNumber));
  }

  public void setTracks(List<String> tracks) {
    this.tracks = tracks;
  }
}
