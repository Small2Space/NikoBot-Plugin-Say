package com.github.smallru8.plugin.Say;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.github.smallru8.NikoBot.Core;
import com.github.smallru8.NikoBot.event.Event.MessageEvent;
import com.github.smallru8.NikoBot.plugins.PluginsInterface;

public class Say implements PluginsInterface{

	private String pluginName = "Say";
	
	public void onDisable() {
		EventBus.getDefault().unregister(this);
	}

	public void onEnable() {
		EventBus.getDefault().register(this);
	}
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageRecved(MessageEvent e) {
		if(Core.pluginsMGR.isAllowedGuild(e.event.getGuild().getId(), pluginName)&&e.msg.getContentRaw().startsWith("/say")&&!e.msg.getAuthor().isBot()) {
			String[] str = e.msg.getContentRaw().split(" ",2);
			if(str.length==2)
				e.msg.getChannel().sendMessage(str[1]).queue();
		}
	}
	public String pluginsName() {
		return pluginName;
	}

}
