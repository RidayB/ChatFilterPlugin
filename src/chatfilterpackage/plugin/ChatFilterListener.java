package chatfilterpackage.plugin;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFilterListener implements Listener{
	
	public ChatFilterListener(ChatFilterMain plugin) {
		//Constructor
	}

	

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		
	    
		for (String i : ChatFilterMain.listBadwords) {
			if (message.toUpperCase().contains(i.toUpperCase())) {
				if (!(event.getPlayer().hasPermission("chatfilter.exemptfromfilter"))) {
					event.setCancelled(true);
					event.getPlayer().sendMessage(ChatColor.DARK_RED + "Your message contains a filtered word!");
				}
			}
	     }
	}
}
