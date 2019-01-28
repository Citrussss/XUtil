package com.union.bangbang.zero.util.audiorecord;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Tryking on 2016/3/24.
 */
public class AudioManager {
    private String mDirString;

    private boolean isPrepared;//Audio是否准备好
    private String mCurrentFilePath;
    private MediaRecorder mRecorder;
    private long startTime,endTime;
    private long delay;

    /**
     * 单例模式：获得AudioManager实例
     *
     * @param dir 存储路径
     * @return 返回实例
     */
    private static AudioManager mInstance;

    private AudioManager(String dir) {
        mDirString = dir;
    }

    public static AudioManager getInstance(String dir) {
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(dir);
                }
            }
        }
        return mInstance;
    }


    public File prepareAudio() {
        try {
            isPrepared = false;
            File dir = new File(mDirString);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = generalFileName();
            File file = new File(dir, fileName);
            mCurrentFilePath = file.getAbsolutePath();
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(mCurrentFilePath);
            mRecorder.prepare();
            mRecorder.start();
            startTime = System.currentTimeMillis();
            isPrepared = true;
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generalFileName() {
        return UUID.randomUUID().toString() + ".amr";
    }

    /**
     * 获取声音的等级
     *
     * @param maxLevel 自己设定的最大值
     * @return
     */
    public int getVoiceLevel(int maxLevel) {
        if (isPrepared) {
            try {
                // mRecorder.getMaxAmplitude()，值域是1-32767
                // 不加1取不到最大值
                return maxLevel * mRecorder.getMaxAmplitude() / 32768 + 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public void release() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public void cancel() {
        release();
        if (mCurrentFilePath != null) {
            File file = new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath = null;
        }
    }

    public String getCurrentFilePath() {
        return mCurrentFilePath;
    }


}
