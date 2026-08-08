package Model;

import java.net.URL;
import javax.sound.sampled.*;

public class Sound {
    private Clip beginningClip;
    private Clip chompClip;
    private Clip winClip;
    private Clip loseClip;
    private Clip backgroundClip;

    private String pacmanBeginning = "/assets/pacmanBeginning.wav";
    private  String pacmanChomp = "/assets/pacmanChomp.wav";
    private  String pacmanlose = "/assets/pacmanLose.wav";
    private String pacmanWin = "/assets/pacmanWin.wav";
    private String background = "/assets/background.wav";

    public Sound () {
        beginningClip = loadClip(pacmanBeginning);
        chompClip = loadClip(pacmanChomp);
        winClip = loadClip(pacmanWin);
        loseClip = loadClip(pacmanlose);
        backgroundClip = loadClip(background);
    }
    
    public Clip loadClip (String path) {
        try {
            URL url = Sound.class.getResource(path);
            if (url != null) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                return clip;
            }
            else {
                System.out.println("File cannot be found");
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void playMusic (Clip clip) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void playBackMusic () {
        if (backgroundClip != null) {
            backgroundClip.start();
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void playBeginningMusic () {
        playMusic(beginningClip);
    }
    public  void playChompMusic () {
        playMusic(chompClip);
    }
    public  void playLoseMusic () {
        playMusic(loseClip);
    }
    public void playWinMusic () {
        playMusic(winClip);
    }
}