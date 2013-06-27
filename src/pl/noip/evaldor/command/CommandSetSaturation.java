package pl.noip.evaldor.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandSetSaturation implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Messages.playerOnly);
				return true;
			} else if (!Evaldor.hasPerm(sender, "evaldor.setsaturation") && !Evaldor.hasPerm(sender, "evaldor.setsaturation.others")) {
				Evaldor.noPerm(sender);
				return true;
			}
			try {
				float saturation = Float.parseFloat(args[0]);
				if (saturation < 0.0F || saturation > 5.0F) {
					sender.sendMessage(Messages.setsaturationNotFloat);
					return true;
				}
				((Player) sender).setSaturation(saturation);
				sender.sendMessage(Messages.setsaturationSuccess.replaceAll("\\{player\\}", Evaldor.getName(sender)).replaceAll("\\{saturation\\}", args[0]));
			} catch (NumberFormatException e) {
				sender.sendMessage(Messages.setsaturationNotFloat);
			}
			return true;
		} else if (args.length == 2) {
			if (!Evaldor.hasPerm(sender, "evaldor.setsaturation.others")) {
				Evaldor.noPerm(sender);
				return true;
			}
			Player target = sender.getServer().getPlayer(args[0]);
			float saturation;
			try {saturation = Float.parseFloat(args[1]);} catch (NumberFormatException e) {
				sender.sendMessage(Messages.setsaturationNotFloat);
				return true;
			}
			if (target != null) {
				try {
					if (saturation < 0.0F || saturation > 5.0F) {
						sender.sendMessage(Messages.setsaturationNotFloat);
						return true;
					}
					target.setSaturation(saturation);
					sender.sendMessage(Messages.setsaturationSuccess.replaceAll("\\{player\\}", Evaldor.getName(target)).replaceAll("\\{saturation\\}", args[1]));
				} catch (NumberFormatException e) {
					sender.sendMessage(Messages.setsaturationNotFloat);
				}
			} else {
				target = sender.getServer().getPlayer(args[1]);
				try {
					saturation = Float.parseFloat(args[0]);
					if (saturation < 0 || saturation > 20) {
						sender.sendMessage(Messages.setsaturationNotFloat);
						return true;
					}
					target.setSaturation(saturation);
					sender.sendMessage(Messages.setsaturationSuccess.replaceAll("\\{player\\}", Evaldor.getName(target)).replaceAll("\\{saturation\\}", args[0]));
				} catch (NumberFormatException e) {
					sender.sendMessage(Messages.setsaturationNotFloat);
				}
			}
			return true;
		} else {
			sender.sendMessage(Messages.wrongUsage+command.getUsage());
			return true;
		}
	}

}
