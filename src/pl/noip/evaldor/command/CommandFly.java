package pl.noip.evaldor.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandFly implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 0) {
			((Player) sender).setAllowFlight(!((Player) sender).getAllowFlight());
			if (((Player) sender).getAllowFlight()) sender.sendMessage(Messages.flyOwnChanged.replaceAll("\\{state\\}", Messages.can));
			else sender.sendMessage(Messages.flyOwnChanged.replaceAll("\\{state\\}", Messages.cant));
			return true;
		} else if (args.length == 1 || args.length == 2) {
			if (convert(args[0]) != 2) {
				((Player) sender).setAllowFlight((convert(args[0])==1?true:false));
				if ((convert(args[0])==1?true:false)) sender.sendMessage(Messages.flyChanged.replaceAll("\\{state\\}", Messages.can).replaceAll("\\{player\\}", Evaldor.getName(sender)));
				else sender.sendMessage(Messages.flyChanged.replaceAll("\\{state\\}", Messages.cant).replaceAll("\\{player\\}", Evaldor.getName(sender)));
				return true;
			} else {
				if (sender.getServer().getPlayer(args[0]) != null) {
					Player target = sender.getServer().getPlayer(args[0]);
					if (args.length == 2) {
						if ((convert(args[1])==1?true:false)) {
							target.setAllowFlight(true);
							target.sendMessage(Messages.flyChangedByOther.replaceAll("\\{state\\}", Messages.can).replaceAll("\\{player\\}", Evaldor.getName(sender)));
							sender.sendMessage(Messages.flyChanged.replaceAll("\\{state\\}", Messages.can).replaceAll("\\{player\\}", Evaldor.getName(sender)));
						} else {
							target.setAllowFlight(false);
							target.sendMessage(Messages.flyChangedByOther.replaceAll("\\{state\\}", Messages.cant).replaceAll("\\{player\\}", Evaldor.getName(sender)));
							sender.sendMessage(Messages.flyChanged.replaceAll("\\{state\\}", Messages.cant).replaceAll("\\{player\\}", Evaldor.getName(sender)));
						}
						return true;
					} else {
						if (sender.getServer().getPlayer(args[0]).getAllowFlight()) {
							sender.sendMessage(Messages.flyPlayerIsFlying.replaceAll("\\{state\\}", Messages.can).replaceAll("\\{player\\}", Evaldor.getName(sender.getServer().getPlayer(args[0]))));
						} else {
							sender.sendMessage(Messages.flyPlayerIsFlying.replaceAll("\\{state\\}", Messages.cant).replaceAll("\\{player\\}", Evaldor.getName(sender.getServer().getPlayer(args[0]))));
						}
						return true;
					}
				} else {
					sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", args[0]));
					return true;
				}
			}
		} else {
			sender.sendMessage(Messages.wrongUsage+command.getUsage());
			return true;
		}
	}

	private byte convert(String text) {
		if (text.equalsIgnoreCase("on") || text.equalsIgnoreCase("1")) {
			return 1;
		} else if (text.equalsIgnoreCase("off") || text.equalsIgnoreCase("0")) {
			return 0;
		} else {
			return 2;
		}
	}
}
