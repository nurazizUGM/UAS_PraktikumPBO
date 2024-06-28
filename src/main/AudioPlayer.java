package main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;;

public class AudioPlayer {

    Long currentFrame;
    Clip clip;
    private String status;
    AudioInputStream audioInputStream;
    static String filePath;

    public AudioPlayer() {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AudioPlayer playFile(String filename) {
        try {
            filePath = "src/resources/sounds/" + filename;
            AudioPlayer audioPlayer = new AudioPlayer();
            audioPlayer.play();
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
