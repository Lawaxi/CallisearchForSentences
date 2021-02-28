package net.lawaxi.callisearch;

import net.lawaxi.callisearch.forms.FormChoose;
import net.lawaxi.callisearch.utils.UtilShufadashi;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        path = System.getProperty("user.dir")+"\\results\\";
        File pathFile = new File(path);
        if(!pathFile.exists())
            pathFile.mkdir();

        System.out.print(
                "\n■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n" +
                "■                                                                                                    ■\n" +
                "■                                      Welcome to Callisearch                                        ■\n" +
                "■                                                                                                    ■\n" +
                "■                               Type sentence to search.                                             ■\n" +
                "■                               Type 'reset' to reset the serial number.                             ■\n" +
                "■                               Type 'clear' to clear the results' folder.                           ■\n" +
                "■                                                                                                    ■\n" +
                "■                                        by delay, ver. 1.0                                          ■\n" +
                "■                                                                                                    ■\n" +
                "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n");
        System.out.print("[INFO] Results will be saved in: "+path+"\n\n");
        while(true){
            searchMain();
        }
    }

    private static int serialNumber = 0; //上一个序号
    private static String path;

    private static void searchMain(){
        Scanner a1=new Scanner(System.in);
        String a2=a1.next();
        if(a2.equals("reset")){
            serialNumber=0;
            System.out.print("[INFO] Successfully switch serial number to 0.\n");
            return;
        }

        char[] in = a2.toCharArray();
        for(char w : in){
            serialNumber++;
            String[] selectable = UtilShufadashi.getSelectable(w);
            String p = path+getNumber(serialNumber)+w+".png";

            try {
                if (selectable.length == 1)
                    save(p, ImageIO.read(new URL(selectable[0])));
                else if (selectable.length > 0) {
                    FormChoose b = new FormChoose(selectable,p);
                    b.setTitle(w + " (" + getNumber(serialNumber)+ ") "+b.getTitle());

                    /*
                    b.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                    也许可以用的代码，就是让子窗口执行完再继续执行主线程，但我用似乎不成功。
                    */
                    while(b.isVisible()){
                        TimeUnit.MILLISECONDS.sleep(300);
                    }

                } else
                    System.out.print("[INFO] Couldn't find " + w + " (" + getNumber(serialNumber)+ ").\n");
            }catch (Exception e){}
        }

        System.out.print("[INFO] Done.\n");
    }

    public static void save(String name, BufferedImage image){
        try {
            ImageIO.write(image,"png",new File(name));
        }catch (Exception e){}
    }

    private static String getNumber(int number){
        if(number<10)
            return "00"+number;
        else if(number<100)
            return "0"+number;
        return String.valueOf(number);
    }
}
