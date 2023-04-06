package de.qandq.core;

import de.qandq.core.listener.ChatListener;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class ChatCopyAddon extends LabyAddon<ChatCopyConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
    registerListener(new ChatListener(this));
    this.logger().info("Enabled chat copy Addon");
  }

  protected Class<ChatCopyConfiguration> configurationClass() {
    return ChatCopyConfiguration.class;
  }
}
