package com.springinaction.spelsystem;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by drpsy on 01-Nov-17 (00:42).
 */
@Component
public class Jukebox {

  private List<Song> songList =
      Arrays.asList(
          new Song("Cannibal Corpse", "Post Mortal Ejaculation"),
          new Song("Cannibal Corpse", "Absolute Hatred"),
          new Song("Cannibal Corpse", "She Was Asking for It"),
          new Song("Canvas Solaris", "Dark Matter, Accertion Disk, and Interacting Binary Neutron Star "
              + "in a Self-Reproducing Inflationary"),
          new Song("Canvas Solaris", "Cyclotron Emission")
      );

  public List<Song> getSongList() {
    return songList;
  }
}

