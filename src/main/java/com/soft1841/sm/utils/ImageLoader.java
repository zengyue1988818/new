package com.soft1841.sm.utils;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ImageLoader extends Thread {
    double width = 800;
    double height = 600;
    // 阻塞队列存储图片
    BlockingQueue blockingQueue = new ArrayBlockingQueue<>(2);
    // 图片结束
    Image eof = new WritableImage(1, 1);
    boolean cancelled = false;
    private String[] resources;

    public void setResources(String[] resources) {
        this.resources = resources;
    }

    public void cancel() throws InterruptedException {
        cancelled = true;
        interrupt();
        join();
    }

    public Image getNextImage() {
        try {
            Image res = (Image) blockingQueue.take();
            if (res != eof) {
                return res;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void run() {
        int id = 0;
        try {
            while (true) {
                String path = resources[id];
                // System.out.println(path);
                InputStream is = getClass().getResourceAsStream(path);
                Image image = null;
                if (is != null) {
                    image = new Image(is, width, height, true, true);
                    if (!image.isError()) {
                        blockingQueue.put(image);
                        System.out.println(image.isSmooth());

                    }
                }
                id++;
                if (id >= resources.length) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("出现异常");
        } finally {
            if (!cancelled) {
                try {
                    blockingQueue.put(eof);
                } catch (InterruptedException e) {
                    System.err.println("中断异常");
                }
            }
        }
    }

    public static void main(String[] args) {
        new ImageLoader().start();
    }
}