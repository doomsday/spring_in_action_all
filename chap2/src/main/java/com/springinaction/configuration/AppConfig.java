package com.springinaction.configuration;

import com.springinaction.aspects.TrackCounter;
import com.springinaction.soundsystem.App;
import com.springinaction.soundsystem.BlankDisc;
import com.springinaction.soundsystem.CompactDisc;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by drpsy on 23-Oct-17 (22:06).
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {TrackCounter.class, App.class})
public class AppConfig {

  @Bean
  public CompactDisc stgPeppers() {
    BlankDisc cd = new BlankDisc();
    cd.setTitle("Sgt. Pepper's Lonely Hearts Club Band");
    cd.setArtist("The Beatles");
    List<String> tracks = new ArrayList<String>();
    tracks.add("Sgt. Pepper's Lonely Hearts Club Band");
    tracks.add("With a Little Help from My Friends");
    tracks.add("Lucy in the Sky with Diamonds");
    tracks.add("Getting Better");
    tracks.add("Fixing a Hole");
    tracks.add("Fucking a Hole");
    tracks.add("Raping a Hole");
    tracks.add("Making a Hole");
    cd.setTracks(tracks);
    return cd;
  }

}
