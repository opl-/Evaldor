package pl.noip.evaldor.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.noip.evaldor.Evaldor;
import pl.noip.evaldor.Messages;

public class CommandTpa implements CommandExecutor {
	public static Map<Player, Player> tpamap;

	public CommandTpa() {
		tpamap = new HashMap<Player, Player>();
	}

	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.playerOnly);
			return true;
		}
		if (alias.equalsIgnoreCase("tpa")) {
			if (args.length == 1) {
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(Messages.playerNotFound.replaceAll("\\{player\\}", args[0]));
					return true;
				} else {
					tpamap.put(target, (Player) sender);
					sender.sendMessage(Messages.tpaRequestSent.replaceAll("\\{player\\}", Evaldor.getName(target)));
					target.sendMessage(Messages.tpaRequestToYou.replaceAll("\\{player\\}", Evaldor.getName(sender)));
					target.sendMessage(Messages.tpaAcceptOrDeny);
					Bukkit.getScheduler().runTaskLater(Evaldor.inst(), new tpreqtime(target), 300);
					return true;
				}
			} else {
				sender.sendMessage(Messages.wrongUsage + "/tpa <gracz>");
				return true;
			}
		} else if (alias.equalsIgnoreCase("tpaccept")) { // doKogo, odKogo
			if (tpamap.containsKey(sender)) {
				Player target = tpamap.get(sender);
				((Player) sender).teleport(target);
				target.sendMessage(Messages.tpaTeleportedToYou.replaceAll("\\{player\\}", Evaldor.getName(sender)));
				sender.sendMessage(Messages.tpTeleportedTo.replaceAll("\\{player\\}", Evaldor.getName(target)));
				tpamap.remove((Player) sender);
				return true;
			} else if (tpamap.containsValue(sender)) {
				Player target = null;
				Iterator<Entry<Player, Player>> iter = tpamap.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<Player, Player> e = iter.next();
					if (e.getValue() == sender) {
						target = e.getKey();
						break;
					}
				}
				iter.remove();
				target.teleport((Player) sender);
				target.sendMessage(Messages.tpSomeoneTeleportedToYou.replaceAll("\\{player\\}", Evaldor.getName(sender)));
				sender.sendMessage(Messages.tpTeleportedTo.replaceAll("\\{player\\}", Evaldor.getName(target)));
				tpamap.remove((Player) sender);
				return true;
			} else {
				sender.sendMessage(Messages.tpaNoRequest);
				return true;
			}
		} else if (alias.equalsIgnoreCase("tpdeny")) {
			if (tpamap.containsKey(sender)) {
				Player target = tpamap.get(sender);
				target.sendMessage(Messages.tpaDenied.replaceAll("\\{player\\}", Evaldor.getName(sender)));
				sender.sendMessage(Messages.tpaToDenied.replaceAll("\\{player\\}", Evaldor.getName(target)));
				tpamap.remove((Player) sender);
				return true;
			} else if (tpamap.containsValue(sender)) {
				Player target = null;
				Iterator<Entry<Player, Player>> iter = tpamap.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<Player, Player> e = iter.next();
					if (e.getValue() == sender) {
						target = e.getKey();
						break;
					}
				}
				iter.remove();
				target.sendMessage(Messages.tpaDenied.replaceAll("\\{player\\}", Evaldor.getName(sender)));
				sender.sendMessage(Messages.tpaToDenied.replaceAll("\\{player\\}", Evaldor.getName(target)));
				tpamap.remove((Player) sender);
				return true;
			} else {
				sender.sendMessage(Messages.tpaNoRequest);
				return true;
			}
		} else {
			sender.sendMessage("§dJak tys to zrobil?!");
			return true;
		}
	}

	public class tpreqtime implements Runnable {
		private Player key;
		public tpreqtime(Player key) {this.key = key;}
		public void run() {
			if (tpamap.containsKey(key)) {
				key.sendMessage(Messages.tpaTimedOut.replaceAll("\\{player\\}", Evaldor.getName(tpamap.get(key))));
				tpamap.get(key).sendMessage(Messages.tpaToTimedOut.replaceAll("\\{player\\}", Evaldor.getName(key)));
				tpamap.remove(key);

			}
		}
	}
}
