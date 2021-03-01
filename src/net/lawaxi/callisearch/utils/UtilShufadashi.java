package net.lawaxi.callisearch.utils;

import java.util.ArrayList;

public class UtilShufadashi {

    public static String url = "http://www.sfds.cn/word/script/";//篆书
    public static String[] writer = {"邓石如","鄧石如","邓琰","鄧琰","说文解字"};

    public static String[] getSelectable(char word, String script){
        String u = url.replace("word",toUnicode(word)).replace("script",script);
        String out = UtilHttp.getInputText(u);

        if(!out.equals("")){
            ArrayList<String> selectable = new ArrayList<>();
            String delta = out;

            for(String w : writer) {
                int a = delta.indexOf(w);
                while (a >= 80) {
                    String b = delta.substring(a - 80, a);
                    if (b.contains("http") && b.indexOf("title=")>b.indexOf("http")) {
                        selectable.add(b.substring(b.indexOf("http"), b.indexOf("title=") - 3));
                    }
                    delta = delta.substring(a + 10);
                    a = delta.indexOf(w);
                }
            }

            return selectable.toArray(new String[0]);
        }
            return null;
    }

    private static final String toUnicode(char text) {
        return Integer.toString(text,16).toUpperCase();
    }
}
