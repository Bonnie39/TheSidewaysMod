package dev.bonnie.thesideways.network;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.network.packet.SidewaysPlayerSyncPacket;
import dev.bonnie.thesideways.network.packet.clientbound.PortalTravelSoundPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Function;

public class SidewaysPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TheSideways.MOD_ID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    private static int index;

    public static synchronized void register() {
        // CLIENTBOUND
        register(PortalTravelSoundPacket.class, PortalTravelSoundPacket::decode);

        // SERVERBOUND
        /*  none yet */

        // BOTH
        register(SidewaysPlayerSyncPacket.class, SidewaysPlayerSyncPacket::decode);
    }

    private static <MSG extends BasePacket> void register(final Class<MSG> packet, Function<FriendlyByteBuf, MSG> decoder) {
        INSTANCE.messageBuilder(packet, index++).encoder(BasePacket::encode).decoder(decoder).consumerMainThread(BasePacket::handle).add();
    }
}
