package org.example.core.listener;

import net.labymod.api.client.chat.ChatMessage;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.client.component.serializer.legacy.LegacyComponentSerializer;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import org.example.core.ChatCopyAddon;
import org.example.core.ChatCopyConfiguration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener {

  private final ChatCopyAddon addon;
  private final ChatCopyConfiguration config;

  public ChatListener(ChatCopyAddon addon) {
    this.addon = addon;
    this.config = addon.configuration();
  }

  @Subscribe
  public void onChatReceive(ChatReceiveEvent event) {
    ChatMessage chatMessage = event.chatMessage();
    if (chatMessage.getSenderProfile() != null) {
      // The message is sent by a player
      String playerName = chatMessage.getSenderProfile().getUsername();
      String message = chatMessage.getOriginalPlainText();

      Component textComponent;
      String copyText;
      //Kannst du nicht irgendwie den Spieler/Client kriegen
      if (config.copyPlayerName()) {
        copyText = message;
      } else {
        copyText = message.substring(playerName.length() + 2);
      }

      textComponent = Component.text()
          .append(Component.text(" [COPY]", NamedTextColor.YELLOW)
              .clickEvent(ClickEvent.copyToClipboard(copyText)))
          .build();

      event.message().append(textComponent);
    }
  }


}