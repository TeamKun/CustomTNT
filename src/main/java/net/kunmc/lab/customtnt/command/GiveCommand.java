package net.kunmc.lab.customtnt.command;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.CommandContext;
import net.kunmc.lab.customtnt.CustomTNT;
import net.kunmc.lab.customtnt.CustomTNTRegistry;
import org.bukkit.entity.Player;

import java.util.List;

public class GiveCommand extends Command {
    private final CustomTNTRegistry registry = CustomTNTRegistry.getInstance();

    public GiveCommand() {
        super("give");

        argument(builder -> {
            builder.playersArgument("target")
                    .objectArgument("name", registry.registeredTNTs(), null, ctx -> {
                        List<Player> players = ((List<Player>) ctx.getParsedArg(0));
                        CustomTNT tnt = ctx.getParsedArg(1, CustomTNT.class);
                        give(players, tnt, 1);
                        sendMessage(ctx, players, tnt, 1);
                    })
                    .integerArgument("quantity", 1, 1000, null, ctx -> {
                        List<Player> players = ((List<Player>) ctx.getParsedArg(0));
                        CustomTNT tnt = ctx.getParsedArg(1, CustomTNT.class);
                        int quantity = ctx.getParsedArg(2, Integer.class);
                        give(players, tnt, quantity);
                        sendMessage(ctx, players, tnt, quantity);
                    });
        });
    }

    private void give(List<Player> players, CustomTNT tnt, int quantity) {
        players.forEach(x -> {
            x.getInventory().addItem(tnt.getItem().asQuantity(quantity));
        });
    }

    private void sendMessage(CommandContext ctx, List<Player> players, CustomTNT tnt, int quantity) {
        ctx.sendSuccess(String.format("%d人のプレイヤーに[%s]を%d個配布しました", players.size(), tnt, quantity));
    }
}
