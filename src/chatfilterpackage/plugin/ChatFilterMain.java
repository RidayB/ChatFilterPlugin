package chatfilterpackage.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatFilterMain extends JavaPlugin {
	
	static List<String> listBadwords; // Creates a static ArrayList for bad words. 
	
	@Override
	public void onEnable() {

		
		
		PluginManager pm = getServer().getPluginManager();
		ChatFilterListener listener = new ChatFilterListener(this);
		pm.registerEvents(listener, this); // Register the listener class
		getConfig().options().copyDefaults(true); 
		List<String> listBadwords = new ArrayList<>(getConfig().getStringList("badwords")); // Creates a nonstatic array list of config
		ChatFilterMain.listBadwords = listBadwords; // Assigns the non static arraylist to the static one. 
		getConfig().set("badwords", listBadwords); // Sets config as the non static array list
		

	}
	
	@Override
	public void onDisable() {

		
		

		getConfig().set("badwords", listBadwords);// Sets config as the non static array list
		saveConfig(); // Saves config
		
	}
	
	


	
	
	
	
	
	public static String badword; // Creates a static string for badword
	
	

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
				String lowerCmd = cmd.getName().toLowerCase();
				
			switch (lowerCmd) {
			
				case "bwlist":
					if (sender.hasPermission("chatfilter.listbadwords") ) { // Conditional for permission check
						if (args.length == 0) { // If argument size is 0
							if (listBadwords.size() > 0) { // If arraylist size is greater than 0 
							    for (String i :listBadwords) { // For loop to send player all bad words
							        sender.sendMessage(ChatColor.DARK_RED + "Badword: " + i);
							      }
							    return true;
							}
							
							else {
								sender.sendMessage(ChatColor.RED + "No bad words yet... add some with /bwadd."); // If listbadwords.size() = 0
								return true;
							}
						}
						
						else {
							sender.sendMessage("This command uses no arguments!"); // If argument size is not equal to 0
							return true;
						}
					}
					else {
						sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command!"); // If player doesn't have perms
						return true;
					}
					
							

			
				case "badwordlist":
				
					if (sender.hasPermission("chatfilter.listbadwords") ) { // Conditional for permission check
						if (args.length == 0) { // If argument size is 0
							if (listBadwords.size() > 0) { // If arraylist size is greater than 0 
							    for (String i :listBadwords) { // For loop to send player all bad words
							        sender.sendMessage(ChatColor.DARK_RED + "Badword: " + i);
							      }
							    return true;
							}
							
							else {
								sender.sendMessage(ChatColor.RED + "No bad words yet... add some with /bwadd."); // If listbadwords.size() = 0
								return true;
							}
						}
						
						else {
							sender.sendMessage("This command uses no arguments!"); // If argument size is not equal to 0
							return true;
						}
					}
					else {
						sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command!"); // If player doesn't have perms
						return true;
					}
					
				case "badwordadd":
					if (sender.hasPermission("chatfilter.addbadwords") ) { // Conditional for if player has perms
						if (args.length == 1) { // Conditional for args length being 1
							badword = ((args[0])); // Badword is the 0th argument
							if (listBadwords.contains(badword)) { // Conditional to see if the arraylist already contains badword
								sender.sendMessage(ChatColor.RED + "\"" + badword + "\" is already a filtered word.");
								return true;
							}
							
							else { // Adding badword to list
								ChatFilterMain.listBadwords.add(badword);
								sender.sendMessage(ChatColor.AQUA + "Sucessfully added \"" + badword + "\" to the list of not allowed words.");
								return true;
							}
						}
						else { // If argument length is not equal 1
							sender.sendMessage("This command uses 1 argument!");
							return true;
						}
					}
					
					else { // if player doesn't have permission
						sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command!");
						return true;
					}
					
			
			
				case "bwadd":
					if (sender.hasPermission("chatfilter.addbadwords") ) { // Conditional for if player has perms
						if (args.length == 1) { // Conditional for args length being 1
							badword = ((args[0])); // Badword is the 0th argument
							if (listBadwords.contains(badword)) { // Conditional to see if the arraylist already contains badword
								sender.sendMessage(ChatColor.RED + "\"" + badword + "\" is already a filtered word.");
								return true;
							}
							
							else { // Adding badword to list
								ChatFilterMain.listBadwords.add(badword);
								sender.sendMessage(ChatColor.AQUA + "Sucessfully added \"" + badword + "\" to the list of not allowed words.");
								return true;
							}
						}
						else { // If argument length is not equal 1
							sender.sendMessage("This command uses 1 argument!");
							return true;
						}
					}
					
					else { // if player doesn't have permission
						sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command!");
						return true;
					}
					
				
				case "badwordremove":
					if (sender.hasPermission("chatfilter.removebadwords")) { // Conditional to check if player has permission
						if (args.length == 1) { // Conditional to see if arg length equals 1
							badword = ((args[0])); // badword is the 0th argument
							if (listBadwords.contains(badword)) { // Check to see if list already contains badword
								ChatFilterMain.listBadwords.remove(badword);
								
								
								sender.sendMessage(ChatColor.AQUA + "Sucessfully removed \"" + badword + "\" from the list of not allowed words.");
								
								
								return true;
							}
							
							else { // If list does not have badword
								sender.sendMessage(ChatColor.RED + "\"" + badword + "\" is not a filtered word.");
								return true;
							}
					
						}
						else { // If arg length isn't 1
							sender.sendMessage("This command uses 1 argument!");
							return true;
						}
					}
					
					else { // If player doesn't have permission
						sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command!");
						return true;
					}
				case "bwremove":
					if (sender.hasPermission("chatfilter.removebadwords")) { // Conditional to check if player has permission
						if (args.length == 1) { // Conditional to see if arg length equals 1
							badword = ((args[0])); // badword is the 0th argument
							if (listBadwords.contains(badword)) { // Check to see if list already contains badword
								ChatFilterMain.listBadwords.remove(badword);
								
								
								sender.sendMessage(ChatColor.AQUA + "Sucessfully removed \"" + badword + "\" from the list of not allowed words.");
								
								
								return true;
							}
							
							else { // If list does not have badword
								sender.sendMessage(ChatColor.RED + "\"" + badword + "\" is not a filtered word.");
								return true;
							}
					
						}
						else { // If arg length isn't 1
							sender.sendMessage("This command uses 1 argument!");
							return true;
						}
					}
					
					else { // If player doesn't have permission
						sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to execute this command!");
						return true;
					}
		}
		return true; 
		}
		return true;	
}}
