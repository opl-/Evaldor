package pl.noip.evaldor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.noip.evaldor.command.CommandEvaldor;
import pl.noip.evaldor.command.CommandFeed;
import pl.noip.evaldor.command.CommandFly;
import pl.noip.evaldor.command.CommandGamemode;
import pl.noip.evaldor.command.CommandHeal;
import pl.noip.evaldor.command.CommandSay;
import pl.noip.evaldor.command.CommandSetHealth;
import pl.noip.evaldor.command.CommandSetHunger;
import pl.noip.evaldor.command.CommandSetSaturation;
import pl.noip.evaldor.command.CommandSetSpawn;
import pl.noip.evaldor.command.CommandSpawn;
import pl.noip.evaldor.command.CommandStop;
import pl.noip.evaldor.command.CommandTp;
import pl.noip.evaldor.command.CommandTpa;
import pl.noip.evaldor.command.CommandTime;
import pl.noip.evaldor.command.CommandWeather;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public final class Evaldor extends JavaPlugin {
	private static Evaldor instance;
	
	private Map<String, PlayerSession> sessions;

	public File spawnsFile;
	public YamlConfiguration spawns;

	public Evaldor() {
		instance = this;
		sessions = new HashMap<String, PlayerSession>();
	}

	public void onEnable() {
		saveDefaultConfig();

		spawnsFile = new File(getDataFolder()+File.separator+"spawns.yml");
		spawns = YamlConfiguration.loadConfiguration(spawnsFile);

		getCommand("gamemode").setExecutor(new CommandGamemode());
		getCommand("time").setExecutor(new CommandTime());
		getCommand("weather").setExecutor(new CommandWeather());
		getCommand("evaldor").setExecutor(new CommandEvaldor());
		getCommand("time").setExecutor(new CommandTime());
		getCommand("spawn").setExecutor(new CommandSpawn());
		getCommand("setspawn").setExecutor(new CommandSetSpawn());
		getCommand("fly").setExecutor(new CommandFly());
		getCommand("heal").setExecutor(new CommandHeal());
		getCommand("feed").setExecutor(new CommandFeed());
		getCommand("sethealth").setExecutor(new CommandSetHealth());
		getCommand("tp").setExecutor(new CommandTp());
		getCommand("tpa").setExecutor(new CommandTpa());
		getCommand("say").setExecutor(new CommandSay());
		getCommand("/stop").setExecutor(new CommandStop());
		getCommand("sethunger").setExecutor(new CommandSetHunger());
		getCommand("setsaturation").setExecutor(new CommandSetSaturation());

		getServer().getPluginManager().registerEvents(new EventListener(), this);

		/** Players Sessions **/
		for (Player p : getServer().getOnlinePlayers()) {
			sessions.put(p.getName(), new PlayerSession());
			p.sendMessage(Messages.reload);
		}
		
		getServer().getScheduler().runTaskTimer(this, new poison(), 0, 5);

		getLogger().info("Evaldor has been enabled!");
	}
	
	private class poison implements Runnable {
		public void run() {
			ItemStack i = new ItemStack(276, 1);
			i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
			for (Player p : getServer().getOnlinePlayers()) {
				if (p.getInventory().contains(i)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 6, 127));
				}
			}
		}
	}

	public void onDisable() {
		getLogger().info("Evaldor has been disabled!");
	}
	
	public static boolean hasPerm(CommandSender sender, String permission) {
		if (!(sender instanceof Player)) {
			return true;
		}
		PermissionUser perm  = PermissionsEx.getUser((Player) sender);
		if (!perm.has(permission))
			return false;
		return true;
	}
	
	public static void noPerm(CommandSender sender) {
		sender.sendMessage(Messages.noPermission);
	}
	
	public static String getName(CommandSender sender) {
		if (sender instanceof Player) {
			return ((Player) sender).getDisplayName();
		}
		return Messages.console;
	}
	
	public static Evaldor inst() {
		return instance;
	}
	
	public PlayerSession getPlayerSession(String name) {
		return sessions.get(name);
	}
	
	public void addPlayerSession(String name) {
		sessions.put(name, new PlayerSession());
	}
	
	public void removePlayerSession(String name) {
		sessions.remove(name);
	}
	
	public String getString(String path) {
		String text = getConfig().getString(path);
		if (text == null) {
			getLogger().warning(path + " is undefined! Please specify that string in your config.");
			return "";
		}
		return text;
	}
}
