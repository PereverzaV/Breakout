package com.shpp.p2p.cs.vpereverza.assignment4;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * A class for playing audio tracks accompanying messages to the player
 */
public class Sound {

    /**
     * Method that opens music file
     *
     * @param path path to the audio file
     */
    public static void playSound(String path) {
        try {
            File soundFile = new File(path); //Open audio file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);// Create inputStream that reads audio
            Clip soundOfBall = AudioSystem.getClip(); // Allows set positon of music
            soundOfBall.open(ais);// Open  stream
            soundOfBall.setFramePosition(0);//Set position of the sound
            soundOfBall.start();// play the sound
        } catch (Exception e) {
            System.out.println("There was supposed to be played music but happened mistake");
        }

    }
}