package Gson;

import java.util.List;

public class RB {
    Interest interest;
    Item item;


    //public String formattedTime;
    //public int [] value;
    //public String time;
    public List<RB> resultSet;

    public class Interest {
        String name;
        String startOn;
    }

    public class Item {
        String title;
        Description descriptions;
    }

    public class Description {
        String service;
        String  shortDescription;
        String  longDescription;
    }
}
