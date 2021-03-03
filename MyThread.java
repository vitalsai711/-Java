package ru;

import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

    private DbxClientV2 clientV2;
    BufferedImage image;

    public MyThread(DbxClientV2 clientV2, BufferedImage image){
        this.clientV2 = clientV2;
        this.image = image;
    }

    @Override
    public void run() {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, "png", os);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream is = new ByteArrayInputStream(os.toByteArray());

            //InputStream in = new FileInputStream("E:\\рабочий стол\\фото рабочего стола\\photo1.png");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date now = new Date();
            String date = formatter.format(now);

            clientV2.files().uploadBuilder("/" + date +  ".png")
                    .uploadAndFinish(is);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
