package Client;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by yudaikichise on 2017/07/21.
 */
public class Music {
    static Clip getClipFromFilePath(String filePath) {
        //System.out.println("Music, filePath: " + filePath);
        AudioInputStream audioInputStream;
        Clip returnClip = null;
        try {
            File soundFile = new File(filePath);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            returnClip = (Clip) AudioSystem.getLine(info);
            returnClip.open(audioInputStream);
            // 再生したらしっぱなしで良さげ
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        return returnClip;
    }

    static void volumeControlByLinerScaler(Clip clip, double linerScaler) {
        // 音量調節を行う
        // 0.5 -> 50%
        // 2.0 -> 200%
        FloatControl control = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float)Math.log10(linerScaler) * 20);
        return;
    }
}
