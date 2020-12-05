package me.miqhtie.tchanger.commands;

import me.miqhtie.tchanger.util.TimeChangerConfig;
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
            System.out.println(TimeChangerConfig.getTime());
            return;
        }

        if (args[0].equalsIgnoreCase("day")) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set time to " + args[0].toUpperCase()));
            TimeChangerConfig.setTime("6000");
        } else if (args[0].equalsIgnoreCase("sunset")) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set time to " + args[0].toUpperCase()));
            TimeChangerConfig.setTime("22880");
        } else if (args[0].equalsIgnoreCase("night")) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set time to " + args[0].toUpperCase()));
            TimeChangerConfig.setTime("18000");
        } else if (args[0].equalsIgnoreCase("fast")) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set time to " + args[0].toUpperCase()));
            TimeChangerConfig.setTime("fast");
        } else if (args[0].equalsIgnoreCase("vanilla")) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set time to " + args[0].toUpperCase()));
            TimeChangerConfig.setTime("vanilla");
        } else if(args[0].equalsIgnoreCase("fastmultiplier") && args.length == 2){
            double multiplier;
            try{
                multiplier = Double.valueOf(args[1]);
            } catch (NumberFormatException e) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error: Invalid number"));
                return;
            }
            TimeChangerConfig.setFastMultiplier(multiplier);
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set multiplier to " + multiplier));
            return;
        } else{
            Double d;
            try{
                //Makes sure input is a number
                d = Double.parseDouble(args[0]);

                if(d < 1 || d > 24000){
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error: Invalid args. Number must be between 1-24000"));
                    return;
                }

                TimeChangerConfig.setTime(args[0]);
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Set time to " + args[0]));

            } catch (NumberFormatException e) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Error: Invalid args"));
                return;
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
