package net.islandersmc.client

import com.mojang.blaze3d.platform.InputConstants
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.minecraft.client.KeyMapping
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier

object IslanderUtilsClient : ClientModInitializer {
	override fun onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		val category = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath("islanderutils", "general")
		)

		val testKey = KeyMappingHelper.registerKeyMapping(
			KeyMapping(
				"key.islanderutils.test_message",
				InputConstants.Type.KEYSYM,
				InputConstants.KEY_J,
				category
			)
		)

		ClientTickEvents.END_CLIENT_TICK.register { client ->
			while (testKey.consumeClick()) {
				client.player?.sendSystemMessage(
					Component.literal("Hm, seems like you pressed it")
				)
			}
		}
	}
}