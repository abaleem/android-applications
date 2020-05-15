// MusicDataGenerator.aidl
package com.example.musiccommon;

import com.example.musiccentral.MySong;

interface MusicDataGenerator {
     List<MySong> retriveAllSongs();
     List<MySong> retriveSong(int song);
     String retriveURL(int song);
}
