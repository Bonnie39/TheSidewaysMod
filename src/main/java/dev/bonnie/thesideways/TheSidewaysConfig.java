package dev.bonnie.thesideways;

import dev.bonnie.thesideways.world.dimension.ModDimensions;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class TheSidewaysConfig {
    public static class Server {
        public final ConfigValue<Boolean> generate_tall_grass;

        public final ConfigValue<String> portal_return_dimension_ID;
        public final ConfigValue<String> portal_destination_dimension_ID;
        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("World Generation");
            generate_tall_grass = builder
                    .define("Generate Tall Grass in The Sideways", true);
            portal_return_dimension_ID = builder
                    .define("Sets portal return dimension", Level.OVERWORLD.location().toString());
            portal_destination_dimension_ID = builder
                    .define("Sets portal destination dimension", ModDimensions.SIDEWAYS_KEY.location().toString());
            builder.pop();
        }
    }

    public static class Common {
        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Gameplay");
            builder.pop();
        }
    }

    public static final ForgeConfigSpec SERVER_SPEC;
    public static final Server SERVER;

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Server, ForgeConfigSpec> serverSpecPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = serverSpecPair.getRight();
        SERVER = serverSpecPair.getLeft();

        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
