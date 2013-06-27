package pl.noip.evaldor;

public class Messages {
	public static final String noPermission = "§f[§a§lEvaldor§f] §cNie masz uprawnien aby to zrobic!";
	public static final String wrongUsage = "§cSkladnia: §e";
	public static final String playerOnly = "Tej komendy moze uzyc tylko gracz.";
	public static final String consoleOnly = "§cTej komendy moze uzyc tylko konsola.";
	public static final String console = "Konsola";
	public static final String reload = "§eSerwer zostal przeladowany.";
	
	public static final String slowchatBlocked = "§cMozesz napisac jedna wiadomosc co §6{delay}§c sekund(y).";

	public static final String can = "moze";
	public static final String cant = "nie moze";
	
	public static final String playerNotFound = "§cNie znaleziono gracza §4{player}§c.";
	
	/** GameModes **/
	public static final String survival = "Survival";
	public static final String creative = "Creative";
	public static final String adventure = "Adventure";
	public static final String[] gamemodes = new String[] {survival, creative, adventure};
	
	/** CommandGamemode **/
	public static final String gamemodePlayerGamemode = "§eTryb gry gracza §6{player}§e to §6{gamemode}§e.";
	public static final String gamemodeChanged = "§aTryb gry gracza §2{player}§a to teraz §2{gamemode}§a.";
	public static final String gamemodeOwnChanged = "§eTwoj tryb gry to teraz §6{gamemode}§e.";
	public static final String gamemodeChangedByOther = "§6{player}§e zmienil twoj tryb gry na §6{gamemode}§e.";
	public static final String gamemodeUnknownGamemode = "§cTryb gry §4{gamemode}§a nie jest poprawnym trybem gry.";
	
	/** ComandWeather **/
	public static final String[] weatherStates = new String[] {"slonicznie", "deszczowo", "burzliwie"};
	public static final String weatherGet = "§eAktualnie jest §6{state}§e i bedzie tak jeszcze przez §6{duration}§e sekund.";
	public static final String weatherUnknownState = "§4{state}§c nie zostalo rozpoznane jako prawidlowy stan pogody.";
	public static final String weatherUnknownWorld = "§4{world}§c nie jest nazwa jakiegokolwiek swiata.";
	public static final String weatherChanged = "§6{player}§e sprawil ze jest teraz §6{state}§e przez kolejne §6{duration}§e sekund(e) na swiecie §6{world}§e.";
	
	/** CommandTime **/
	public static final String timeChanged = "§eGracz §6{player}§e zmienil czas na §6{time}§e na swiecie §6{world}§e.";
	public static final String[] timeOfDay = new String[] {"dzien", "noc"};

	/** CommandSpawn **/
	public static final String spawnDefaultTeleported = "§eZostales przeteleportowany na spawn.";
	public static final String spawnTeleported = "§eZostales przeteleportowany na spawn o nazwie §6{spawn}§e.";
	public static final String spawnDoesntExists = "§cSpawn o nazwie §4{spawn}§c nie istnieje.";
	
	/** CommandSetSpawn **/
	public static final String setspawnChanged = "§aPozycja spawnu §2{spawn}§a zostala zaktualizowana.";
	public static final String setspawnCantOverrideDefault = "§cNie mozesz ustawic spawna ktorego nazwa jest jednoczesnie nazwa innej mapy.";
	public static final String setspawnError = "§cBlad podczas aktualizowania pozycji spawnu §4{spawn}§c.";
	
	/** CommandFly **/
	public static final String flyPlayerIsFlying = "§eGracz §6{player} {state}§e latac.";
	public static final String flyChanged = "§eGracz §6{player} {state}§e od teraz latac.";
	public static final String flyOwnChanged = "§eOd teraz §6{state}sz§e latac.";
	public static final String flyChangedByOther = "§6{player}§e sprawil ze od teraz §6{state}sz§e latac.";
	
	/** CommandHeal **/
	public static final String healHealed = "§aZostales uleczony.";
	public static final String healHealedByOther = "§aZostales uleczony przez §2{player}§a.";
	public static final String healYouHealed = "§eUleczyles §6{player}§e.";
	
	/** CommandHeal **/
	public static final String feedFed = "§aZostales nakarmiony.";
	public static final String feedFedByOther = "§aZostales nakarmiony przez §2{player}§a.";
	public static final String feedYouFed = "§eNakarmiles §6{player}§e. Powiedzial ze wolalby pieniadze.";

	/** CommandSetHealth **/
	public static final String sethealthSuccess = "§2{player}§a ma teraz §2{health}§a punktow zycia.";
	public static final String sethealthNotInteger = "§cPunkty zycia musza byc liczba calkowita i wynosic od 0 do 20.";
	
	/** CommandSetHunger **/
	public static final String sethungerSuccess = "§2{player}§a ma teraz §2{hunger}§a punktow glodu.";
	public static final String sethungerNotInteger = "§cPunkty glodu musza byc liczba calkowita i wynosic od 0 do 20.";
	
	/** CommandSetSaturation **/
	public static final String setsaturationSuccess = "§2{player}§a ma teraz §2{saturation}§a punktow nasycenia.";
	public static final String setsaturationNotFloat = "§cPunkty nasycenia musza byc liczba calkowita i wynosic od 0.0 do 5.0.";
	
	/** CommandTp **/
	public static final String tpTeleportedTo = "§aPrzeteleportowano do §2{player}§a.";
	public static final String tpTeleportedFromTo = "§aPrzeteleportowano gracza §2{player}§a do §2{player2}§a.";
	public static final String tpSomeoneTeleportedYouTo = "§6{player}§e przeteleportowal Cie do §6{player2}§e.";
	public static final String tpSomeoneTeleportedToYou = "§6{player}§e przeteleportowal do Ciebie §6{player2}§e.";
	public static final String tpBothPlayersOffline = "§cZaden z podanych graczy nie jest na serwerze.";
	
	/** CommandTpa **/
	public static final String tpaRequestSent = "§eProsba o teleportacje do gracza §6{player}§e zostala wyslana.";
	public static final String tpaRequestToYou = "§eGracz §6{player}§e prosi o teleportacje do Ciebie.";
	public static final String tpaRequestYouTo = "§eGracz §6{player}§e prosi abys sie do niego przeteleportowal.";
	public static final String tpaAcceptOrDeny = "§eAby zakceptowac wpisz §2/tpaccept§e, lub wpisz §c/tpdeny§e aby odrzucic.";
	public static final String tpaToTimedOut = "§eZadanie teleportacji do §6{player}§e wygaslo.";
	public static final String tpaTimedOut = "§eZadanie teleportacji od §6{player}§e wygaslo.";
	public static final String tpaNoRequest = "§cNie masz zadnej oczekajacej prosby o teleportacje.";
	public static final String tpaTeleportedToYou = "§2{player}§ zostal do Ciebe przeteleportowany.";
	public static final String tpaDenied = "§4{player}§c nie zgodzil sie na teleportacje.";
	public static final String tpaToDenied = "§cOdrzuciles prosbe o teleport.";
	
	/** CommandStop **/
	public static final String stopMessage = "§cPolaczenie utracone:\n§7{reason}\n§8Komendy /stop uzyl: {player}";
}
