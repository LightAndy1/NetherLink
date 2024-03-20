package com.netherlink;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

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
          .executes(context -> {
            PlayerEntity player = MinecraftClient.getInstance().player;
            int x = (int) (
              player.getX() >= 0 ? player.getX() : player.getX() - 1
            );
            int y = (int) player.getY();
            int z = (int) player.getZ();
            int ix, iz;

            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            World world = minecraftClient.world;
            String worldName = world.getRegistryKey().getValue().toString();
            System.out.println(worldName);
            String iWorldName;

            if (worldName.equals("minecraft:overworld")) {
              //? Caclulates nether coordinates to overworld
              ix = x / 8;
              iz = z / 8;
              worldName = "§2Overworld";
              iWorldName = "§4Nether";
            } else if (worldName.equals("minecraft:the_nether")) {
              //? Caclulates overworld coordinates to nether
              ix = x * 8;
              iz = z * 8;
              worldName = "§4Nether";
              iWorldName = "§2Overworld";
            } else {
              context
                .getSource()
                .sendFeedback(Text.literal("§cInvalid world!"));

              return 1;
            }

            context
              .getSource()
              .sendFeedback(
                Text.literal(
                  worldName +
                  " coordinates:§r§l " +
                  x +
                  " " +
                  y +
                  " " +
                  z +
                  "\n" +
                  iWorldName +
                  " coordinates:§r§l " +
                  ix +
                  " " +
                  y +
                  " " +
                  iz
                )
              );

            return 1;
          })
      )
    );
  }
}
