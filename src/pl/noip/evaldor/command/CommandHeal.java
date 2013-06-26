package pl.noip.evaldor.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandHeal implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 0) {
			((Player) sender).setHealth(20);
			sender.sendMessage(Messages.healHealed);
			return true;
		} else if (args.length == 1) {
			Player target = sender.getServer().getPlayer(args[0]);
			if (target != null) {
				target.setHealth(20);
				sender.sendMessage(Messages.healYouHealed.replaceAll("\\{player\\}", Evaldor.getName(target)));
				target.sendMessage(Messages.healHealedByOther.replaceAll("\\{player\\}", Evaldor.getName(sender)));
				return true;
			} else {
				sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", args[0]));
				return true;
			}
		} else {
			sender.sendMessage(Messages.wrongUsage+command.getUsage());
			return true;
		}
	}

}
