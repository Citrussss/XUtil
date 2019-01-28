package com.union.bangbang.zero.util.audiorecord;

import android.media.MediaPlayer;
import android.support.annotation.IntDef;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MediaUtil {

    public static int getDuration(File file, @TimeHandle int i) {
        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(file.getPath());
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int duration = player.getDuration();
        player.release();
        player = null;
        return duration / i;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            TimeHandle.NONE,
            TimeHandle.SECOND
    })
    public @interface TimeHandle {
        public int NONE = 1;
        public int SECOND = 1000;
    }
}
