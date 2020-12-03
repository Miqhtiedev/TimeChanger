package me.miqhtie.tchanger.util;

import me.miqhtie.tchanger.TimeChange;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class TimeChangerConfig {
    private static final Configuration config = TimeChange.getConfig();

    public static String getTime() {
        String time;
        Property timeProp = config.get(Configuration.CATEGORY_GENERAL, "time", "vanilla", "Time");
        return timeProp.getString();
    }

    public static void setTime(String time) {
        try {
            Property timeProp = config.get(Configuration.CATEGORY_GENERAL, "time", "vanilla", "Time");
            timeProp.set(time);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getFastMultiplier() {
        double multiplier;
        Property multiplierProp = config.get(Configuration.CATEGORY_GENERAL, "fastmultiplier", 1.0, "Multiplier");
        return multiplierProp.getDouble();
    }

    public static void setFastMultiplier(double multiplier) {
        try {
            Property multiplierProp = config.get(Configuration.CATEGORY_GENERAL, "fastmultiplier", 1.0, "Multiplier");
            multiplierProp.set(multiplier);
            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
