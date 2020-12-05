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


@Mod(
    name = "TimeChanger",
    modid = "timechanger",
    version = "1.1",
    clientSideOnly = true
)
public class TimeChange {

    private static Configuration config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        config = new Configuration(event.getSuggestedConfigurationFile());
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
            if(TimeChangerConfig.getTime().equalsIgnoreCase("fast")) {
                Minecraft.getMinecraft().theWorld.setWorldTime((long)(System.currentTimeMillis() * TimeChangerConfig.getFastMultiplier() % 24000.0));
            }
        }
    }

    public static Configuration getConfig(){
        return config;
    }
}
