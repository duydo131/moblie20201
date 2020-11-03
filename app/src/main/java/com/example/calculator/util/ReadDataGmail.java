package com.example.calculator.util;

import com.example.calculator.R;
import com.example.calculator.model.GmailModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadDataGmail {
    public ReadDataGmail() {
    }

    public List<GmailModel> getData() {
        List<GmailModel> list = new ArrayList<>();
        String data = get();
        String item[] = data.split("@@@");
        String line;
        for(int i = 0; i < item.length; i++){
            line = item[i];
            String a[] = line.split("@@");
            String username = a[0];
            String message = a[1];
            SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
            Date date = null;
            try {
                date = f.parse(a[2]);
            } catch (ParseException e) {
                return null;
            }
            boolean favorite = a[3] == "1";
            GmailModel gm = new GmailModel(username, message, date, favorite);
            list.add(gm);
        }
        return list;
    }

    private String get(){
        String data = "Hu Guthrie@@sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin vel arcu eu odio tristique pharetra. Quisque ac@@09/11/2020@@0@@@\n" +
                "Kadeem Armstrong@@velit. Aliquam nisl. Nulla eu neque pellentesque massa lobortis ultrices. Vivamus rhoncus. Donec est. Nunc ullamcorper, velit in aliquet lobortis,@@07/23/2021@@1@@@\n" +
                "Cyrus Merrill@@scelerisque neque sed sem egestas blandit. Nam nulla magna, malesuada vel, convallis in, cursus et, eros. Proin ultrices. Duis volutpat@@10/09/2021@@1@@@\n" +
                "Hasad Mcguire@@imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna.@@01/29/2020@@0@@@\n" +
                "Branden Hill@@tempus mauris erat eget ipsum. Suspendisse sagittis. Nullam vitae diam. Proin dolor. Nulla semper tellus id nunc interdum feugiat. Sed@@12/30/2020@@1@@@\n" +
                "Oliver Miranda@@Nullam velit dui, semper et, lacinia vitae, sodales at, velit. Pellentesque ultricies dignissim lacus. Aliquam rutrum lorem ac risus. Morbi@@12/21/2019@@1@@@\n" +
                "Jamal Mckee@@Sed malesuada augue ut lacus. Nulla tincidunt, neque vitae semper egestas, urna justo faucibus lectus, a sollicitudin orci sem eget@@11/16/2020@@1@@@\n" +
                "Deacon Rose@@odio semper cursus. Integer mollis. Integer tincidunt aliquam arcu. Aliquam ultrices iaculis odio. Nam interdum enim non nisi. Aenean eget@@12/10/2019@@1@@@\n" +
                "Cedric Hays@@urna suscipit nonummy. Fusce fermentum fermentum arcu. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;@@07/27/2020@@1@@@\n" +
                "Ali Barnett@@metus. Aliquam erat volutpat. Nulla facilisis. Suspendisse commodo tincidunt nibh. Phasellus nulla. Integer vulputate, risus a ultricies adipiscing, enim mi@@10/03/2021@@0@@@\n" +
                "Yoshio Ward@@urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum@@01/22/2021@@0@@@\n" +
                "Cain Rodriquez@@dictum. Proin eget odio. Aliquam vulputate ullamcorper magna. Sed eu eros. Nam consequat dolor vitae dolor. Donec fringilla. Donec feugiat@@06/15/2021@@0@@@\n" +
                "Reed Dotson@@montes, nascetur ridiculus mus. Proin vel nisl. Quisque fringilla euismod enim. Etiam gravida molestie arcu. Sed eu nibh vulputate mauris@@05/02/2020@@1@@@\n" +
                "Marvin Fisher@@ornare, elit elit fermentum risus, at fringilla purus mauris a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque@@11/30/2019@@1@@@\n" +
                "Grady Roth@@quis lectus. Nullam suscipit, est ac facilisis facilisis, magna tellus faucibus leo, in lobortis tellus justo sit amet nulla. Donec@@11/02/2020@@1@@@\n" +
                "Edward Jennings@@Curabitur vel lectus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec dignissim magna a tortor.@@11/13/2019@@1@@@\n" +
                "Arden Conley@@orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu vel quam dignissim pharetra. Nam ac nulla. In tincidunt@@03/28/2021@@1@@@\n" +
                "Dieter Mckenzie@@mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl sem, consequat nec, mollis vitae, posuere at, velit. Cras@@07/30/2020@@1@@@\n" +
                "Oliver Lowery@@dictum placerat, augue. Sed molestie. Sed id risus quis diam luctus lobortis. Class aptent taciti sociosqu ad litora torquent per@@01/21/2021@@0@@@\n" +
                "Gregory Watkins@@erat. Vivamus nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula.@@02/15/2020@@1@@@\n" +
                "Gary Adams@@ipsum dolor sit amet, consectetuer adipiscing elit. Etiam laoreet, libero et tristique pellentesque, tellus sem mollis dui, in sodales elit@@10/05/2020@@0@@@\n" +
                "Lucian Woods@@Aliquam tincidunt, nunc ac mattis ornare, lectus ante dictum mi, ac mattis velit justo nec ante. Maecenas mi felis, adipiscing@@11/21/2020@@1@@@\n" +
                "Lionel Kerr@@nonummy ac, feugiat non, lobortis quis, pede. Suspendisse dui. Fusce diam nunc, ullamcorper eu, euismod ac, fermentum vel, mauris. Integer@@11/07/2019@@0@@@\n" +
                "Cyrus Love@@ac mi eleifend egestas. Sed pharetra, felis eget varius ultrices, mauris ipsum porta elit, a feugiat tellus lorem eu metus.@@03/03/2020@@0@@@\n" +
                "Aaron Dominguez@@velit justo nec ante. Maecenas mi felis, adipiscing fringilla, porttitor vulputate, posuere vulputate, lacus. Cras interdum. Nunc sollicitudin commodo ipsum.@@01/29/2021@@0@@@\n" +
                "Malcolm Farrell@@ligula. Aliquam erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque neque.@@09/26/2020@@0@@@\n" +
                "Francis Villarreal@@dictum ultricies ligula. Nullam enim. Sed nulla ante, iaculis nec, eleifend non, dapibus rutrum, justo. Praesent luctus. Curabitur egestas nunc@@09/09/2021@@0@@@\n" +
                "Myles Delgado@@mauris ut mi. Duis risus odio, auctor vitae, aliquet nec, imperdiet nec, leo. Morbi neque tellus, imperdiet non, vestibulum nec,@@04/24/2020@@0@@@\n" +
                "Holmes Battle@@mollis nec, cursus a, enim. Suspendisse aliquet, sem ut cursus luctus, ipsum leo elementum sem, vitae aliquam eros turpis non@@06/04/2021@@1@@@\n" +
                "Boris Shields@@massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu@@02/29/2020@@0@@@\n" +
                "Talon Chen@@odio. Aliquam vulputate ullamcorper magna. Sed eu eros. Nam consequat dolor vitae dolor. Donec fringilla. Donec feugiat metus sit amet@@09/24/2021@@1@@@\n" +
                "Fritz Haley@@Aenean eget metus. In nec orci. Donec nibh. Quisque nonummy ipsum non arcu. Vivamus sit amet risus. Donec egestas. Aliquam@@03/10/2020@@1@@@\n" +
                "Laith Rowland@@Donec felis orci, adipiscing non, luctus sit amet, faucibus ut, nulla. Cras eu tellus eu augue porttitor interdum. Sed auctor@@09/15/2020@@0@@@\n" +
                "Ryan Byrd@@quis urna. Nunc quis arcu vel quam dignissim pharetra. Nam ac nulla. In tincidunt congue turpis. In condimentum. Donec at@@09/28/2021@@0@@@\n" +
                "Branden Villarreal@@eu odio tristique pharetra. Quisque ac libero nec ligula consectetuer rhoncus. Nullam velit dui, semper et, lacinia vitae, sodales at,@@06/14/2020@@0";
        return data;
    }
}
