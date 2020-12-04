package me.miqhtie.tchanger;

import me.miqhtie.tchanger.commands.TimeChangerCommand;
import me.miqhtie.tchanger.handlers.TimeChangeNetHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import me.miqhtie.tchanger.util.TimeChangerConfig;
import me.miqhtie.tchanger.util.TimeType;


@Mod(
    name = "TimeChanger",
    modid = "timechanger",
    version = "1.1",
    clientSideOnly = true
)
public class TimeChange {

    public static TimeType TIME_TYPE;
    public static double fastTimeMultiplier;
    private static Configuration config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        config = new Configuration(event.getSuggestedConfigurationFile());
        String time = TimeChangerConfig.getTime();
        if (time.equalsIgnoreCase("day")) {
            TIME_TYPE = TimeType.DAY;
        } else if (time.equalsIgnoreCase("sunset")) {
            TIME_TYPE = TimeType.SUNSET;
        } else if (time.equalsIgnoreCase("night")) {
            TIME_TYPE = TimeType.NIGHT;
        } else if (time.equalsIgnoreCase("fast")) {
            TIME_TYPE = TimeType.FAST;
        } else if (time.equalsIgnoreCase("vanilla")) {
            TIME_TYPE = TimeType.VANILLA;
        }

        fastTimeMultiplier = TimeChangerConfig.getFastMultiplier();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new TimeChangerCommand());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        if(Minecraft.getMinecraft().theWorld != null){
            final INetHandler parent = Minecraft.getMinecraft().thePlayer.sendQueue.getNetworkManager().getNetHandler();
            if(!(parent instanceof TimeChangeNetHandler)){
                Minecraft.getMinecraft().thePlayer.sendQueue.getNetworkManager().setNetHandler((INetHandler) new TimeChangeNetHandler((NetHandlerPlayClient) parent));
            }
            if(TimeChange.TIME_TYPE == TimeType.FAST) {
                Minecraft.getMinecraft().theWorld.setWorldTime((long)(System.currentTimeMillis() * TimeChange.fastTimeMultiplier % 24000.0));
            }
        }
    }
    static {
        TimeChange.TIME_TYPE = TimeType.VANILLA;
        TimeChange.fastTimeMultiplier = 1.0;
    }

    public static Configuration getConfig(){
        return config;
    }
}
