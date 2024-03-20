package com.netherlink;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.text.Text;

public class NetherLinkClient implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    ClientCommandRegistrationCallback.EVENT.register(
        (dispatcher, registryAccess) ->
      dispatcher.register(
        ClientCommandManager
          .literal("netherlink")
          .executes(context -> {
            context
              .getSource()
              .sendFeedback(
                Text.literal("Netherlink command executed in the client!")
              );
            return 1;
          })
      )
    );
  }
}
