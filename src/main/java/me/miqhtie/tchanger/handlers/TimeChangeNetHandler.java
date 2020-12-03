package me.miqhtie.tchanger.handlers;

import me.miqhtie.tchanger.TimeChange;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.*;
import net.minecraft.util.IChatComponent;

import java.lang.reflect.Field;

public class TimeChangeNetHandler extends NetHandlerPlayClient {
    private NetHandlerPlayClient parent;
    public TimeChangeNetHandler(final NetHandlerPlayClient parent) {
        super(Minecraft.getMinecraft(), getGuiScreen(parent), parent.getNetworkManager(), parent.getGameProfile());
        this.parent = parent;
    }
    private static GuiScreen getGuiScreen(final NetHandlerPlayClient parent) {
        for (final Field field : parent.getClass().getDeclaredFields()) {
            if (field.getType().equals(GuiScreen.class)) {
                field.setAccessible(true);
                try {
                    return (GuiScreen)field.get(parent);
                }
                catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }


    public void onNetworkTick() {
        ((TimeChangeNetHandler)this.parent).onNetworkTick();
    }

    public void func_147282_a(final S01PacketJoinGame p_147282_1_) {
        this.parent.handleJoinGame(p_147282_1_);
    }

    public void func_147235_a(final S0EPacketSpawnObject p_147235_1_) {
        this.parent.handleSpawnObject(p_147235_1_);
    }

    public void func_147286_a(final S11PacketSpawnExperienceOrb p_147286_1_) {
        this.parent.handleSpawnExperienceOrb(p_147286_1_);
    }

    public void func_147292_a(final S2CPacketSpawnGlobalEntity p_147292_1_) {
        this.parent.handleSpawnGlobalEntity(p_147292_1_);
    }

    public void func_147288_a(final S10PacketSpawnPainting p_147288_1_) {
        this.parent.handleSpawnPainting(p_147288_1_);
    }

    public void func_147244_a(final S12PacketEntityVelocity p_147244_1_) {
        this.parent.handleEntityVelocity(p_147244_1_);
    }

    public void func_147284_a(final S1CPacketEntityMetadata p_147284_1_) {
        this.parent.handleEntityMetadata(p_147284_1_);
    }

    public void func_147237_a(final S0CPacketSpawnPlayer p_147237_1_) {
        this.parent.handleSpawnPlayer(p_147237_1_);
    }

    public void func_147275_a(final S18PacketEntityTeleport p_147275_1_) {
        this.parent.handleEntityTeleport(p_147275_1_);
    }

    public void func_147257_a(final S09PacketHeldItemChange p_147257_1_) {
        this.parent.handleHeldItemChange(p_147257_1_);
    }

    public void func_147259_a(final S14PacketEntity p_147259_1_) {
        this.parent.handleEntityMovement(p_147259_1_);
    }

    public void func_147267_a(final S19PacketEntityHeadLook p_147267_1_) {
        this.parent.handleEntityHeadLook(p_147267_1_);
    }

    public void func_147238_a(final S13PacketDestroyEntities p_147238_1_) {
        this.parent.handleDestroyEntities(p_147238_1_);
    }

    public void func_147258_a(final S08PacketPlayerPosLook p_147258_1_) {
        this.parent.handlePlayerPosLook(p_147258_1_);
    }

    public void func_147287_a(final S22PacketMultiBlockChange p_147287_1_) {
        this.parent.handleMultiBlockChange(p_147287_1_);
    }

    public void func_147263_a(final S21PacketChunkData p_147263_1_) {
        this.parent.handleChunkData(p_147263_1_);
    }

    public void func_147234_a(final S23PacketBlockChange p_147234_1_) {
        this.parent.handleBlockChange(p_147234_1_);
    }

    public void func_147253_a(final S40PacketDisconnect p_147253_1_) {
        this.parent.handleDisconnect(p_147253_1_);
    }

    public void func_147297_a(final Packet p_147297_1_) {
        this.parent.addToSendQueue(p_147297_1_);
    }

    public void func_147231_a(final IChatComponent p_147231_1_) {
        this.parent.onDisconnect(p_147231_1_);
    }

    public void func_147246_a(final S0DPacketCollectItem p_147246_1_) {
        this.parent.handleCollectItem(p_147246_1_);
    }

    public void func_147251_a(final S02PacketChat p_147251_1_) {
        this.parent.handleChat(p_147251_1_);
    }

    public void func_147279_a(final S0BPacketAnimation p_147279_1_) {
        this.parent.handleAnimation(p_147279_1_);
    }

    public void func_147278_a(final S0APacketUseBed p_147278_1_) {
        this.parent.handleUseBed(p_147278_1_);
    }

    public void func_147281_a(final S0FPacketSpawnMob p_147281_1_) {
        this.parent.handleSpawnMob(p_147281_1_);
    }

    public void func_147285_a(final S03PacketTimeUpdate packet) {
        switch (TimeChange.TIME_TYPE) {
            case DAY: {
                this.parent.handleTimeUpdate(new S03PacketTimeUpdate(packet.getWorldTime(), -6000L, true));
            }
            case SUNSET: {
                this.parent.handleTimeUpdate(new S03PacketTimeUpdate(packet.getWorldTime(), -22880L, true));
            }
            case NIGHT: {
                this.parent.handleTimeUpdate(new S03PacketTimeUpdate(packet.getWorldTime(), -18000L, true));
            }
            case VANILLA: {
                this.parent.handleTimeUpdate(packet);
                break;
            }
        }
    }

    public void func_147271_a(final S05PacketSpawnPosition p_147271_1_) {
        this.parent.handleSpawnPosition(p_147271_1_);
    }

    public void func_147243_a(final S1BPacketEntityAttach p_147243_1_) {
        this.parent.handleEntityAttach(p_147243_1_);
    }

    public void func_147236_a(final S19PacketEntityStatus p_147236_1_) {
        this.parent.handleEntityStatus(p_147236_1_);
    }

    public void func_147249_a(final S06PacketUpdateHealth p_147249_1_) {
        this.parent.handleUpdateHealth(p_147249_1_);
    }

    public void func_147295_a(final S1FPacketSetExperience p_147295_1_) {
        this.parent.handleSetExperience(p_147295_1_);
    }

    public void func_147280_a(final S07PacketRespawn p_147280_1_) {
        this.parent.handleRespawn(p_147280_1_);
    }

    public void func_147283_a(final S27PacketExplosion p_147283_1_) {
        this.parent.handleExplosion(p_147283_1_);
    }

    public void func_147265_a(final S2DPacketOpenWindow p_147265_1_) {
        this.parent.handleOpenWindow(p_147265_1_);
    }

    public void func_147266_a(final S2FPacketSetSlot p_147266_1_) {
        this.parent.handleSetSlot(p_147266_1_);
    }

    public void func_147239_a(final S32PacketConfirmTransaction p_147239_1_) {
        this.parent.handleConfirmTransaction(p_147239_1_);
    }

    public void func_147241_a(final S30PacketWindowItems p_147241_1_) {
        this.parent.handleWindowItems(p_147241_1_);
    }

    public void func_147268_a(final S36PacketSignEditorOpen p_147268_1_) {
        this.parent.handleSignEditorOpen(p_147268_1_);
    }

    public void func_147248_a(final S33PacketUpdateSign p_147248_1_) {
        this.parent.handleUpdateSign(p_147248_1_);
    }

    public void func_147273_a(final S35PacketUpdateTileEntity p_147273_1_) {
        this.parent.handleUpdateTileEntity(p_147273_1_);
    }

    public void func_147245_a(final S31PacketWindowProperty p_147245_1_) {
        this.parent.handleWindowProperty(p_147245_1_);
    }

    public void func_147242_a(final S04PacketEntityEquipment p_147242_1_) {
        this.parent.handleEntityEquipment(p_147242_1_);
    }

    public void func_147276_a(final S2EPacketCloseWindow p_147276_1_) {
        this.parent.handleCloseWindow(p_147276_1_);
    }

    public void func_147261_a(final S24PacketBlockAction p_147261_1_) {
        this.parent.handleBlockAction(p_147261_1_);
    }

    public void func_147294_a(final S25PacketBlockBreakAnim p_147294_1_) {
        this.parent.handleBlockBreakAnim(p_147294_1_);
    }

    public void func_147269_a(final S26PacketMapChunkBulk p_147269_1_) {
        this.parent.handleMapChunkBulk(p_147269_1_);
    }

    public void func_147252_a(final S2BPacketChangeGameState packet) {
        this.parent.handleChangeGameState(packet);
    }

    public void func_147264_a(final S34PacketMaps p_147264_1_) {
        this.parent.handleMaps(p_147264_1_);
    }

    public void func_147277_a(final S28PacketEffect p_147277_1_) {
        this.parent.handleEffect(p_147277_1_);
    }

    public void func_175098_a(final S42PacketCombatEvent packetIn) {
        this.parent.handleCombatEvent(packetIn);
    }

    public void func_175101_a(final S41PacketServerDifficulty packetIn) {
        this.parent.handleServerDifficulty(packetIn);
    }

    public void func_175094_a(final S43PacketCamera packetIn) {
        this.parent.handleCamera(packetIn);
    }

    public void func_175093_a(final S44PacketWorldBorder packetIn) {
        this.parent.handleWorldBorder(packetIn);
    }

    public void func_175099_a(final S45PacketTitle packetIn) {
        this.parent.handleTitle(packetIn);
    }

    public void func_147293_a(final S37PacketStatistics p_147293_1_) {
        this.parent.handleStatistics(p_147293_1_);
    }

    public void func_175100_a(final S46PacketSetCompressionLevel packetIn) {
        this.parent.handleSetCompressionLevel(packetIn);
    }

    public void func_175096_a(final S47PacketPlayerListHeaderFooter packetIn) {
        this.parent.handlePlayerListHeaderFooter(packetIn);
    }

    public void func_147260_a(final S1DPacketEntityEffect p_147260_1_) {
        this.parent.handleEntityEffect(p_147260_1_);
    }

    public void func_147262_a(final S1EPacketRemoveEntityEffect p_147262_1_) {
        this.parent.handleRemoveEntityEffect(p_147262_1_);
    }

    public void func_147256_a(final S38PacketPlayerListItem p_147256_1_) {
        this.parent.handlePlayerListItem(p_147256_1_);
    }

    public void func_147272_a(final S00PacketKeepAlive p_147272_1_) {
        this.parent.handleKeepAlive(p_147272_1_);
    }

    public void func_147270_a(final S39PacketPlayerAbilities p_147270_1_) {
        this.parent.handlePlayerAbilities(p_147270_1_);
    }

    public void func_147274_a(final S3APacketTabComplete p_147274_1_) {
        this.parent.handleTabComplete(p_147274_1_);
    }

    public void func_147255_a(final S29PacketSoundEffect p_147255_1_) {
        this.parent.handleSoundEffect(p_147255_1_);
    }

    public void func_175095_a(final S48PacketResourcePackSend packetIn) {
        this.parent.handleResourcePack(packetIn);
    }

    public void func_175097_a(final S49PacketUpdateEntityNBT packetIn) {
        this.parent.handleEntityNBT(packetIn);
    }

    public void func_147240_a(final S3FPacketCustomPayload p_147240_1_) {
        this.parent.handleCustomPayload(p_147240_1_);
    }

    public void func_147291_a(final S3BPacketScoreboardObjective p_147291_1_) {
        this.parent.handleScoreboardObjective(p_147291_1_);
    }

    public void func_147250_a(final S3CPacketUpdateScore p_147250_1_) {
        this.parent.handleUpdateScore(p_147250_1_);
    }

    public void func_147254_a(final S3DPacketDisplayScoreboard p_147254_1_) {
        this.parent.handleDisplayScoreboard(p_147254_1_);
    }

    public void func_147247_a(final S3EPacketTeams p_147247_1_) {
        this.parent.handleTeams(p_147247_1_);
    }

    public void func_147289_a(final S2APacketParticles p_147289_1_) {
        this.parent.handleParticles(p_147289_1_);
    }

    public void func_147290_a(final S20PacketEntityProperties p_147290_1_) {
        this.parent.handleEntityProperties(p_147290_1_);
    }
}
