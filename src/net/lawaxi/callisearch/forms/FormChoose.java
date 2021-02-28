/*
 * Created by JFormDesigner on Sun Feb 28 01:10:09 CST 2021
 */

package net.lawaxi.callisearch.forms;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import net.lawaxi.callisearch.Main;
import net.miginfocom.swing.MigLayout;

public class FormChoose extends JFrame {

    public FormChoose(String[] selectable, String path){
        initComponents();
        this.path = path;
        this.frame = this;
        dealPic(selectable[0],label1);
        if(selectable.length>1)
            dealPic(selectable[1],label2);
        if(selectable.length>2)
            dealPic(selectable[2],label3);

        setSize(1000,500);
        setLocationRelativeTo(null);
        toFront();
        setVisible(true);
    }


    private String path;
    private JFrame frame;
    private JJLabel label1;
    private JJLabel label2;
    private JJLabel label3;

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JJLabel();
        label2 = new JJLabel();
        label3 = new JJLabel();

        //======== this ========
        setTitle("Please choose. (When you see this page, there must be at least 2 choices for you, please wait.)");
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));
        contentPane.add(label1, "cell 1 2 5 12");
        contentPane.add(label2, "cell 7 2 5 12");
        contentPane.add(label3, "cell 13 2 5 12");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    private void dealPic (String pic, JJLabel labe){
        new Thread(()->{
            try {
                BufferedImage i = ImageIO.read(new URL(pic));
                labe.setIcon(new ImageIcon(i));
                labe.setImage(i);


                labe.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Main.save(path,labe.getImage());
                        frame.dispose();
                    }
                });

            }catch (Exception e){

            }}).start();
    }

    private class JJLabel extends JLabel{
        private BufferedImage image;

        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
    }

}
