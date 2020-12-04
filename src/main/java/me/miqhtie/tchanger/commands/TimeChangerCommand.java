package me.miqhtie.tchanger.commands;

import me.miqhtie.tchanger.util.TimeChangerConfig;
import me.miqhtie.tchanger.TimeChange;
import me.miqhtie.tchanger.util.TimeType;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class TimeChangerCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "timechanger";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return "/" + getCommandName() + " (day,sunset,night,fast,vanilla,fastmultiplier)";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error: Invalid arguments"));
            return;
        }

        if (args[0].equalsIgnoreCase("day")) {
            TimeChange.TIME_TYPE = TimeType.DAY;
        } else if (args[0].equalsIgnoreCase("sunset")) {
            TimeChange.TIME_TYPE = TimeType.SUNSET;
        } else if (args[0].equalsIgnoreCase("night")) {
            TimeChange.TIME_TYPE = TimeType.NIGHT;
        } else if (args[0].equalsIgnoreCase("fast")) {
            TimeChange.TIME_TYPE = TimeType.FAST;
        } else if (args[0].equalsIgnoreCase("vanilla")) {
            TimeChange.TIME_TYPE = TimeType.VANILLA;
        } else if(args[0].equalsIgnoreCase("fastmultiplier") && args.length == 2){
            double multiplier;
            try{
                multiplier = Double.valueOf(args[1]);
            } catch (NumberFormatException e) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error: Invalid number"));
                return;
            }

            TimeChangerConfig.setFastMultiplier(multiplier);
            TimeChange.fastTimeMultiplier = multiplier;
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set multiplier to " + multiplier));
            return;
        } else {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error: Invalid args"));
            return;
        }


        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set time to " + args[0].toUpperCase()));
        TimeChangerConfig.setTime(args[0].toLowerCase());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
