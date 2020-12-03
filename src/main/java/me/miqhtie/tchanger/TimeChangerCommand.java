package me.miqhtie.tchanger;

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
        return "/" + getCommandName() + " (day,sunset,night,fast,vanilla)";
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
        } else {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error: Invalid args"));
        }


        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set time to " + args[0].toUpperCase()));
        TImeChangerConfig.setTime(args[0].toLowerCase());
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
