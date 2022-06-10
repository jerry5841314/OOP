package media;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.FloatControl;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class AudioPlayer {
    private static final Map<Object, File> sounds = new HashMap<>();
    private static Clip clip;

    public static void addAudioByFilePath(Object audioName, File file) {
        sounds.put(audioName, file);
    }

    public static void addAudioByFilePath(Object audioName, String path) {
        sounds.put(audioName, Paths.get(path).toFile());
    }

    public static void playSoundsInLoop(Object audioName) {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sounds.get(audioName)));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopSounds(){
        clip.stop();
    }

    public static void setVolume(double volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
    
{}}
