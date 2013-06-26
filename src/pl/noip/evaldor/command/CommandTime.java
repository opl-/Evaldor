package pl.noip.evaldor.command;


import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandTime implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Messages.wrongUsage + command.getUsage());
				return true;
			}
			if (args[0].equalsIgnoreCase("day")) {
				if (!Evaldor.hasPerm(sender, "evaldor.time") && !Evaldor.hasPerm(sender, "evaldor.time.day") && !Evaldor.hasPerm(sender, "evaldor.time.day."+((Player) sender).getWorld().getName())) {
					Evaldor.noPerm(sender);
					return true;
				}
				World w = ((Player) sender).getWorld();
				w.setTime(0L);
				Bukkit.broadcastMessage(Messages.timeChanged.replaceAll("\\{world\\}", w.getName()).replaceAll("\\{player\\}", Evaldor.getName(sender)).replaceAll("\\{time\\}", Messages.timeOfDay[0]));
			} else if (args[0].equalsIgnoreCase("night")) {
				if (!Evaldor.hasPerm(sender, "evaldor.time") && !Evaldor.hasPerm(sender, "evaldor.time.night") && !Evaldor.hasPerm(sender, "evaldor.time.night."+((Player) sender).getWorld().getName())) {
					Evaldor.noPerm(sender);
					return true;
				}
				World w = ((Player) sender).getWorld();
				w.setTime(13500L);
				Bukkit.broadcastMessage(Messages.timeChanged.replaceAll("\\{world\\}", w.getName()).replaceAll("\\{player\\}", Evaldor.getName(sender)).replaceAll("\\{time\\}", Messages.timeOfDay[1]));
			} else {
				sender.sendMessage(Messages.wrongUsage + command.getUsage());
				return true;
			}
			return true;
		} else {
			sender.sendMessage(Messages.wrongUsage + command.getUsage());
			return true;
		}
	}

}
