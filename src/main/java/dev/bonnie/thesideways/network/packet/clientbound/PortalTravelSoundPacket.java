package dev.bonnie.thesideways.network.packet.clientbound;

import dev.bonnie.thesideways.block.custom.portal.SidewaysPortalSoundUtil;
import dev.bonnie.thesideways.network.BasePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;

public record PortalTravelSoundPacket() implements BasePacket {
    @Override
    public void encode(FriendlyByteBuf buf) { }

    public static PortalTravelSoundPacket decode(FriendlyByteBuf buf) {
        return new PortalTravelSoundPacket();
    }

    @Override
    public void execute(Player playerEntity) {
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null) {
            SidewaysPortalSoundUtil.playPortalSound(Minecraft.getInstance().player);
        }
    }
}
