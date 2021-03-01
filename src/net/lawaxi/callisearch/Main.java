package net.lawaxi.callisearch;

import net.lawaxi.callisearch.forms.FormChoose;
import net.lawaxi.callisearch.utils.UtilShufadashi;
import javax.imageio.ImageIO;
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
                "■              Type sentence to search.                                                              ■\n" +
                "■               (You can type 1-number code before your sentence to choose scripts,)                 ■\n" +
                "■               1-Regular 2-Running 3-Cursive 4-Official 5-Seal.)                                    ■\n" +
                "■              Type 'reset' to reset the serial number.                                              ■\n" +
                "■              Type 'clear' to clear the results' folder.                                            ■\n" +
                "■              Type 'auto' to open/close auto-choose when facing more than two options.              ■\n" +
                "■                                                                                                    ■\n" +
                "■                                        by delay, ver. 1.1                                          ■\n" +
                "■                                                                                                    ■\n" +
                "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■\n");
        System.out.print("[INFO] Results will be saved in: "+path+"\n\n");
        while(true){
            searchMain();
        }
    }

    private static int serialNumber = 0; //上一个序号
    private static String path;
    private static boolean auto = false;

    private static void searchMain(){
        Scanner a1=new Scanner(System.in);
        String a2=a1.next();
        if(a2.equals("reset")){
            serialNumber=0;
            System.out.print("[INFO] Successfully switched serial number to 0.\n");
            return;
        }
        else if(a2.equals("clear")){
            new File(path).delete();
            new File(path).mkdir();
            System.out.print("[INFO] Successfully cleared your results' folder.\n");
            return;
        }
        else if (a2.equals("auto")){
            auto=!auto;
            System.out.print("[INFO] Successfully changed auto-choosing to: "+auto+".\n");
            return;
        }

        char[] in = a2.toCharArray();
        String script = "5";
        for(char w : in){

            if(in[0]==w){
                String s = switchScript(w);
                if(!s.equals("null"))
                {
                    script=s;
                    continue;
                }
            }


            serialNumber++;
            String[] selectable = UtilShufadashi.getSelectable(w,script);
            String p = path+getNumber(serialNumber)+w+".png";

            try {
                if (selectable.length == 1 || (selectable.length>0 && auto))
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


    private static String switchScript(char w){
        switch (w){
            case '1': case '2': case '3': case '4': case '5':{
                System.out.print("[INFO] Successfully switched your script to "+w+" (only for this sentence).\n");
                return String.valueOf(w);}
            default:
                return "null";
        }
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
