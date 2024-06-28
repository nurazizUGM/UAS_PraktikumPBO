package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;;

public class AudioPlayer {

    Long currentFrame;
    Clip clip;
    private String status = "paused";
    AudioInputStream audioInputStream;
    static String filePath;

    public AudioPlayer() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AudioPlayer playFile(String filename) {
        return playFile(filename, true);
    }

    public static AudioPlayer playFile(String filename, boolean play) {
        try {
            filePath = "src/resources/sounds/" + filename;
            AudioPlayer audioPlayer = new AudioPlayer();
            if (play) {
                audioPlayer.play();
            }
            return audioPlayer;
        }

        catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
            return null;
        }
    }

    public void play() {
        clip.start();
        status = "play";
    }

    public void pause() {
        if (!status.equals("paused")) {
            this.currentFrame = this.clip.getMicrosecondPosition();
            clip.stop();
            status = "paused";
        }
    }

    public void resumeAudio() {
        if (status.equals("play")) {
            System.out.println("Audio is already " +
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void resetAudioStream() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File(filePath).getAbsoluteFile());
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPaused() {
        return status.equals("paused");
    }
}
