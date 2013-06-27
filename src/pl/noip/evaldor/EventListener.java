package pl.noip.evaldor;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import pl.noip.evaldor.util.StringUtils;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class EventListener implements Listener {
	@EventHandler
	public void formatPlayerChat(AsyncPlayerChatEvent event) {
		if (Evaldor.inst().getConfig().getBoolean("chat.slow-chat")) {
			if (Evaldor.inst().getPlayerSession(event.getPlayer().getName()).lastMessage + Evaldor.inst().getConfig().getInt("chat.slow-chat-delay")*1000 >= System.currentTimeMillis()) {
				event.getPlayer().sendMessage(Messages.slowchatBlocked.replaceAll("\\{delay\\}", ""+Evaldor.inst().getConfig().getInt("chat.slow-chat-delay")));
				event.setCancelled(true);
				return;
			}
		}
		PermissionUser perm = PermissionsEx.getUser(event.getPlayer());
		String format = Evaldor.inst().getString("chat.format");
		if (Evaldor.inst().getConfig().getBoolean("chat.group-specific")) {
			format = Evaldor.inst().getString("chat.groups."+perm.getGroupsNames()[0]);
			if (format == null) {
				format = Evaldor.inst().getString("chat.format");
			}
		}
		format = StringUtils.colorize(format.replaceAll("\\{player\\}", event.getPlayer().getDisplayName())
				.replaceAll("\\{prefix\\}", perm.getPrefix())
				.replaceAll("\\{suffix\\}", perm.getSuffix())
				.replaceAll("\\{message\\}", event.getMessage())
				.replaceAll("\\{world\\}", event.getPlayer().getWorld().getName()));
		Evaldor.inst().getPlayerSession(event.getPlayer().getName()).lastMessage = System.currentTimeMillis();
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!Evaldor.inst().getPlayerSession(p.getName()).isIgnoring(event.getPlayer())) {
				p.sendMessage((Evaldor.inst().getConfig().getBoolean("chat.mark-nick")?(format.contains(p.getName())?"§d>>§r":"  "):"")+format);
			}
		}
		Bukkit.getLogger().info("[chat] "+format);
		event.setCancelled(true);
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		Evaldor.inst().addPlayerSession(event.getPlayer().getName());
	}
	
	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {
		Evaldor.inst().removePlayerSession(event.getPlayer().getName());
	}
	
	@EventHandler
	public void playerDeath(EntityDeathEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		Firework fw = (Firework) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.FIREWORK); // rakieta sygnalowa
		FireworkMeta fwm = fw.getFireworkMeta();
		fwm.setPower(2);
		fwm.addEffect(FireworkEffect.builder().flicker(true).trail(true).withColor(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build());
		fw.setFireworkMeta(fwm);
		fw = (Firework) event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.FIREWORK); // krew
		fwm = fw.getFireworkMeta();
		fwm.setPower(0);
		fwm.addEffect(FireworkEffect.builder().trail(true).withColor(Color.RED).with(FireworkEffect.Type.STAR).build());
		fw.setFireworkMeta(fwm);
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.HURT, 1.0F, .3F);
		}
		Bukkit.getScheduler().runTaskLater(Evaldor.inst(), new playDelayedSound(), 4);
	}

	private class playDelayedSound implements Runnable {
		public void run() {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				p.playSound(p.getLocation(), Sound.GHAST_DEATH, 1.0F, .1F);
			}
		}
	}

	@EventHandler(priority=EventPriority.MONITOR)
	public void checkWhitelist(PlayerLoginEvent event) {
		event.setKickMessage(Evaldor.inst().getString("whitelist.message").replaceAll("/n", "\n")); // TODO: do poprawki bo nie dziala za dobrze... :/
	}
}
