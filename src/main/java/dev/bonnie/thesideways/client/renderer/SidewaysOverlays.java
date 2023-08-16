package dev.bonnie.thesideways.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.block.ModBlocks;
import dev.bonnie.thesideways.capability.player.SidewaysPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheSideways.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SidewaysOverlays {
    private static final ResourceLocation NAUSEA_LOCATION = new ResourceLocation("textures/misc/nausea.png");
    @SubscribeEvent
    public static void registerOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("sideways_portal_overlay", (gui, pStack, partialTicks, screenWidth, screenHeight) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Window window = minecraft.getWindow();
            LocalPlayer player = minecraft.player;

            if (player != null) {
                SidewaysPlayer.get(player).ifPresent(handler -> renderSidewaysPortalOverlay(pStack, minecraft, window, handler, partialTicks));
                //renderSidewaysConfusionOverlay(pStack, window, 1.0F);
            }
        });
    }

    /**
     * [CODE COPY] - {Gui#renderPortalOverlay(PoseStack, float)}.<br><br>
     * Warning for "deprecation" is suppressed because vanilla calls {@link net.minecraft.client.renderer.block.BlockModelShaper#getParticleIcon(BlockState)} just fine.
     */
    @SuppressWarnings("deprecation")
    private static void renderSidewaysPortalOverlay(PoseStack poseStack, Minecraft minecraft, Window window, SidewaysPlayer handler, float partialTicks) {
        float timeInPortal = handler.getPrevPortalAnimTime() + (handler.getPortalAnimTime() - handler.getPrevPortalAnimTime()) * partialTicks;
        if (timeInPortal > 0.0F) {
            if (timeInPortal < 1.0F) {
                timeInPortal = timeInPortal * timeInPortal;
                timeInPortal = timeInPortal * timeInPortal;
                timeInPortal = timeInPortal * 0.8F + 0.2F;
            }

            /*poseStack.pushPose();
            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
            TextureAtlasSprite textureAtlasSprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(ModBlocks.SIDEWAYS_PORTAL.get().defaultBlockState());
            GuiComponent.blit(poseStack, 0, 0, -90, window.getGuiScaledWidth(), window.getGuiScaledHeight(), textureAtlasSprite);
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
            poseStack.popPose();*/

            //  The top and bottom versions of the function are visually the same, though the bottom uses
            //  fewer lines of code, so I assume it's faster lmao

            poseStack.pushPose();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, timeInPortal);
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
            TextureAtlasSprite textureatlassprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(ModBlocks.SIDEWAYS_PORTAL.get().defaultBlockState());
            GuiComponent.blit(poseStack, 0, 0, -90, window.getGuiScaledWidth(), window.getGuiScaledHeight(), textureatlassprite);
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
        }
    }

    private static void renderSidewaysConfusionOverlay(PoseStack pStack, Window window, float effectScale) {
        int screenWidth = window.getGuiScaledWidth();
        int screenHeight = window.getGuiScaledHeight();
        double scale = Mth.lerp(effectScale, 2.0D, 1.0D);
        float red = 0.2F * effectScale;
        float green = 0.4F * effectScale;
        float blue = 0.2F * effectScale;
        double scaledWidth = (double) screenWidth * scale;
        double scaledHeight = (double) screenHeight * scale;
        double offsetX = ((double) screenWidth - scaledWidth) / 2.0D;
        double offsetY = ((double) screenHeight - scaledHeight) / 2.0D;

        pStack.pushPose();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE,
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        RenderSystem.setShaderColor(red, green, blue, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, NAUSEA_LOCATION);

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(offsetX, offsetY + scaledHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(offsetX + scaledWidth, offsetY + scaledHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(offsetX + scaledWidth, offsetY, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(offsetX, offsetY, -90.0D).uv(0.0F, 0.0F).endVertex();
        tesselator.end();

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();

        pStack.popPose();
    }
}