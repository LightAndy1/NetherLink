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
          .then(
            ClientCommandManager
              .literal("nether")
              .executes(context -> {
                context
                  .getSource()
                  .sendFeedback(
                    Text.literal(
                      "Netherlink (from Overworld to Nether) command executed in the client!"
                    )
                  );

                return 1;
              })
          )
          .then(
            ClientCommandManager
              .literal("overworld")
              .executes(context -> {
                context
                  .getSource()
                  .sendFeedback(
                    Text.literal(
                      "Netherlink (from Nether to Overworld) command executed in the client!"
                    )
                  );

                return 1;
              })
          )
      )
    );
  }
}
