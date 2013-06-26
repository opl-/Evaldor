package pl.noip.evaldor.command;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandGamemode implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (args.length == 1) {
			if (getGamemode(args[0]) != null && sender instanceof Player) {
				if (!Evaldor.hasPerm(sender, "evaldor.gamemode") && !Evaldor.hasPerm(sender, "evaldor.gamemode.others")) {
					Evaldor.noPerm(sender);
					return true;
				}
				((Player) sender).setGameMode(getGamemode(args[0]));
				sender.sendMessage(Messages.gamemodeOwnChanged.replaceAll("\\{gamemode\\}", Messages.gamemodes[((Player) sender).getGameMode().getValue()]));
				return true;
			} else {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", args[0]));
					return true;
				} else {
					sender.sendMessage(Messages.gamemodePlayerGamemode.replaceAll("\\{player\\}", target.getDisplayName()).replaceAll("\\{gamemode\\}", Messages.gamemodes[target.getGameMode().getValue()]));
					return true;
				}
			}
		} else if (args.length == 2) {
			if (!Evaldor.hasPerm(sender, "evaldor.gamemode.others")) {
				Evaldor.noPerm(sender);
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", args[0]));
					return true;
				} else {
					if (getGamemode(args[0]) == null) {
						sender.sendMessage(Messages.gamemodeUnknownGamemode.replaceAll("\\{gamemode\\}", args[1]));
						return true;
					}
					target.setGameMode(getGamemode(args[0]));
					sender.sendMessage(Messages.gamemodeChanged.replaceAll("\\{gamemode\\}", Messages.gamemodes[target.getGameMode().getValue()]).replaceAll("\\{player\\}", Evaldor.getName(target)));
					target.sendMessage(Messages.gamemodeChangedByOther.replaceAll("\\{gamemode\\}", Messages.gamemodes[target.getGameMode().getValue()]).replaceAll("\\{player\\}", Evaldor.getName(sender)));
					return true;
				}
			} else {
				if (getGamemode(args[1]) == null) {
					sender.sendMessage(Messages.gamemodeUnknownGamemode.replaceAll("\\{gamemode\\}", args[1]));
					return true;
				}
				target.setGameMode(getGamemode(args[1]));
				sender.sendMessage(Messages.gamemodeChanged.replaceAll("\\{gamemode\\}", Messages.gamemodes[target.getGameMode().getValue()]).replaceAll("\\{player\\}", Evaldor.getName(target)));
				target.sendMessage(Messages.gamemodeChangedByOther.replaceAll("\\{gamemode\\}", Messages.gamemodes[target.getGameMode().getValue()]).replaceAll("\\{player\\}", Evaldor.getName(sender)));
				return true;
			}
		} else {
			sender.sendMessage(Messages.wrongUsage + command.getUsage());
			return true;
		}
	}

	public GameMode getGamemode(String text) {
		if (text.equals("0") || text.equalsIgnoreCase("survival") || text.equalsIgnoreCase("s")) {
			return GameMode.SURVIVAL;
		} else if (text.equals("1") || text.equalsIgnoreCase("creative") || text.equalsIgnoreCase("c")) {
			return GameMode.CREATIVE;
		} else if (text.equals("2") || text.equalsIgnoreCase("adventure") || text.equalsIgnoreCase("a")) {
			return GameMode.ADVENTURE;
		}
		return null;
	}
}
