package net.islandersmc.client

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

class HomeMenu : Screen(Component.literal("Home Menu")) {

    override fun init() {
        val buttonWidth = 200
        val buttonHeight = 20
        val x = (width - buttonWidth) / 2
        val y = height / 2
        //test button
        addRenderableWidget(
            Button.builder(Component.literal("testing")) { _ ->
                //action
                Minecraft.getInstance().player?.connection?.sendCommand(
                    "spawn"
                )
            }
                .bounds(x, y, buttonWidth, buttonHeight)
                .build()
        )
        //close button ig
        addRenderableWidget(
            Button.builder(Component.literal("Close")) { _ ->
                //close menu
                onClose()
            }
                .bounds(x, y + 24, buttonWidth, buttonHeight)
                .build()
        )
    }

    override fun extractRenderState(
        graphics: GuiGraphicsExtractor,
        mouseX: Int,
        mouseY: Int,
        delta: Float
    ) {
        super.extractRenderState(graphics, mouseX, mouseY, delta)

        val heading = "Home Menu"

        graphics.text(
            font,
            heading,
            (width - font.width(heading)) /2,
            height /2 -30,
            0xFFFFFFFFF.toInt(),
            true
        )
    }
}