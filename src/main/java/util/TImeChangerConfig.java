package util;

import me.miqhtie.tchanger.TimeChange;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class TImeChangerConfig {
    private static final Configuration config = TimeChange.getConfig();

    public static String getTime(){
        String time;
        Property timeProp = config.get(Configuration.CATEGORY_GENERAL, "time", "vanilla", "Time");
        return timeProp.getString();
    }

    public static void setTime(String time){
        try{
            Property timeProp = config.get(Configuration.CATEGORY_GENERAL, "time", "vanilla", "Time");
            timeProp.set(time);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
