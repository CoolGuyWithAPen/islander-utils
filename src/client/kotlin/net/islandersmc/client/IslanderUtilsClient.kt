package net.islandersmc.client

import com.mojang.blaze3d.platform.InputConstants
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import net.minecraft.resources.Identifier

object IslanderUtilsClient : ClientModInitializer {
	override fun onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		//Categories for keybinds
		val category = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath("islanderutils", "general")
		)

		//Keymappings
		val tpMenuKey = KeyMappingHelper.registerKeyMapping(
			KeyMapping(
				"key.islanderutils.tpMenu",
				InputConstants.Type.KEYSYM,
				InputConstants.KEY_RSHIFT,
				category
			)
		)

		val rtpKey = KeyMappingHelper.registerKeyMapping(
			KeyMapping(
				"key.islanderutils.rtp_key",
				InputConstants.Type.KEYSYM,
				InputConstants.UNKNOWN.value,
				category
			)
		)
		val spawnKey = KeyMappingHelper.registerKeyMapping(
			KeyMapping(
				"key.islanderutils.spawn_key",
				InputConstants.Type.KEYSYM,
				InputConstants.UNKNOWN.value,
				category
			)
		)
		val homeKey = KeyMappingHelper.registerKeyMapping(
			KeyMapping(
				"key.islanderutils.home_key",
				InputConstants.Type.KEYSYM,
				InputConstants.UNKNOWN.value,
				category
			)
		)
		val claimKey = KeyMappingHelper.registerKeyMapping(
			KeyMapping(
				"key.islanderutils.claim_key",
				InputConstants.Type.KEYSYM,
				InputConstants.UNKNOWN.value,
				category
			)
		)
		val bedKey = KeyMappingHelper.registerKeyMapping(
			KeyMapping(
				"key.islanderutils.bed_key",
				InputConstants.Type.KEYSYM,
				InputConstants.UNKNOWN.value,
				category
			)
		)

		//Register keypress
		ClientTickEvents.END_CLIENT_TICK.register { client ->
			//open home menu
			while (tpMenuKey.consumeClick()) {
				client.gui.setScreen(HomeMenu())
			}
			//run quick commands
			while (rtpKey.consumeClick()) {
				Minecraft.getInstance().player?.connection?.sendCommand(
					"rtp"
				)
			}
			while (spawnKey.consumeClick()) {
				Minecraft.getInstance().player?.connection?.sendCommand(
					"spawn"
				)
			}
			while (homeKey.consumeClick()) {
				Minecraft.getInstance().player?.connection?.sendCommand(
					"home home"
				)
			}
			while (claimKey.consumeClick()) {
				Minecraft.getInstance().player?.connection?.sendCommand(
					"hs home"
				)
			}
			while (bedKey.consumeClick()) {
				Minecraft.getInstance().player?.connection?.sendCommand(
					"home bed"
				)
			}
		}



	}
}