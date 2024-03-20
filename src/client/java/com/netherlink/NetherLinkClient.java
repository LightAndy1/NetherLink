package com.netherlink;

import com.mojang.brigadier.arguments.IntegerArgumentType;
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
              .then(
                ClientCommandManager
                  .argument("x", IntegerArgumentType.integer())
                  .then(
                    ClientCommandManager
                      .argument("y", IntegerArgumentType.integer())
                      .then(
                        ClientCommandManager
                          .argument("z", IntegerArgumentType.integer())
                          .executes(context -> {
                            int x = IntegerArgumentType.getInteger(
                              context,
                              "x"
                            );
                            int y = IntegerArgumentType.getInteger(
                              context,
                              "y"
                            );
                            int z = IntegerArgumentType.getInteger(
                              context,
                              "z"
                            );

                            context
                              .getSource()
                              .sendFeedback(
                                Text.literal( //? Caclulates overworld coordinates to nether
                                  "§2Overworld coordinates:§r§l " +
                                  x +
                                  " " +
                                  y +
                                  " " +
                                  z +
                                  "\n§4Nether coordinates:§r§l " +
                                  (x / 8) +
                                  " " +
                                  y +
                                  " " +
                                  (z / 8)
                                )
                              );

                            return 1;
                          })
                      )
                  )
              )
          )
          .then(
            ClientCommandManager
              .literal("overworld")
              .then(
                ClientCommandManager
                  .argument("x", IntegerArgumentType.integer())
                  .then(
                    ClientCommandManager
                      .argument("y", IntegerArgumentType.integer())
                      .then(
                        ClientCommandManager
                          .argument("z", IntegerArgumentType.integer())
                          .executes(context -> {
                            int x = IntegerArgumentType.getInteger(
                              context,
                              "x"
                            );
                            int y = IntegerArgumentType.getInteger(
                              context,
                              "y"
                            );
                            int z = IntegerArgumentType.getInteger(
                              context,
                              "z"
                            );

                            context
                              .getSource()
                              .sendFeedback(
                                Text.literal( //? Caclulates nether coordinates to overworld
                                  "§4Nether coordinates:§r§l " +
                                  x +
                                  ", " +
                                  y +
                                  ", " +
                                  z +
                                  "\n§2Overworld coordinates:§r§l " +
                                  (x * 8) +
                                  ", " +
                                  y +
                                  ", " +
                                  (z * 8)
                                )
                              );

                            return 1;
                          })
                      )
                  )
              )
          )
      )
    );
  }
}
