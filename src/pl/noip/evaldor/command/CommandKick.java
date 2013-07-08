package pl.noip.evaldor.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandKick implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if (Evaldor.hasPerm(sender, "evaldor.kick")) {
			Evaldor.noPerm(sender);
			return true;
		}
		if (args.length == 1) {
			Player target = sender.getServer().getPlayer(args[0]);
			if (target != null) {
				target.kickPlayer(Messages.defaultkickmsg.replaceAll("\\{kicker\\}", Evaldor.getName(sender)));
				sender.sendMessage(Messages.kickSuccess.replaceAll("\\{player\\}", Evaldor.getName(target)));
				return true;
			} else {
				sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", args[0]));
				return true;
			}
		} else if (args.length > 1) {
			Player target = sender.getServer().getPlayer(args[0]);
			if (target != null) {
				String reason = args[0];
				for (int i = 1; i < args.length; i++) { reason += " " + args[i]; }
				target.kickPlayer(reason.replaceAll("\\{kicker\\}", Evaldor.getName(sender)).replaceAll("\\{reason\\}", reason.replaceAll("/n", "\n")));
				return true;
			} else {
				sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", args[0]));
				return true;
			}
		} else {
			sender.sendMessage(Messages.wrongUsage+cmd.getUsage());
			return true;
		}
	}

}
