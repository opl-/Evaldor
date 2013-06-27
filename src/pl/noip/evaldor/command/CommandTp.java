package pl.noip.evaldor.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandTp implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(Messages.playerOnly);
				return true;
			}
			Player target = (Bukkit.getServer().getPlayer(args[0]));
			if (target == null) {
				sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", args[0]));
				return true;
			} else {
				((Player) sender).teleport(target.getLocation());
				sender.sendMessage(Messages.tpTeleportedTo.replaceAll("\\{player\\}", Evaldor.getName(target)));
				return true;
			}
		} else if (args.length == 2) {
			Player target = (Bukkit.getServer().getPlayer(args[0]));
			Player target2 = (Bukkit.getServer().getPlayer(args[1]));
			if (target != null && target2 != null) {
				sender.getServer().getPlayer(args[0]).teleport(sender.getServer().getPlayer(args[1]).getLocation());
				sender.sendMessage(Messages.tpTeleportedFromTo.replaceAll("\\{player\\}", Evaldor.getName(target)).replaceAll("\\{player2\\}", Evaldor.getName(target2)));
				target.sendMessage(Messages.tpSomeoneTeleportedYouTo.replaceAll("\\{player\\}", Evaldor.getName(sender)).replaceAll("\\{player2\\}", Evaldor.getName(target2)));
				target2.sendMessage(Messages.tpSomeoneTeleportedToYou.replaceAll("\\{player\\}", Evaldor.getName(sender)).replaceAll("\\{player2\\}", Evaldor.getName(target)));
				return true;
			} else if (target == null && target2 == null) {
				sender.sendMessage(Messages.tpBothPlayersOffline);
				return true;
			} else if (target == null) {
				sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", Evaldor.getName(target)));
				return true;
			} else if (target2 == null) {
				sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", Evaldor.getName(target2)));
				return true;
			} else {
				return true;
			}
		} else {
			sender.sendMessage(Messages.wrongUsage + command.getUsage());
			return true;
		}
	}
}
