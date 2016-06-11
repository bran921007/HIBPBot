package me.stuntguy3000.java.telegram.hibpbot.command;


import java.util.List;

import me.stuntguy3000.java.telegram.hibpbot.HIBPBot;
import me.stuntguy3000.java.telegram.hibpbot.api.exception.ApiException;
import me.stuntguy3000.java.telegram.hibpbot.api.exception.NoBreachesException;
import me.stuntguy3000.java.telegram.hibpbot.api.exception.NoUserException;
import me.stuntguy3000.java.telegram.hibpbot.api.model.Breach;
import me.stuntguy3000.java.telegram.hibpbot.handler.BreachHandler;
import me.stuntguy3000.java.telegram.hibpbot.object.Util;
import me.stuntguy3000.java.telegram.hibpbot.object.command.Command;
import pro.zackpollard.telegrambot.api.event.chat.message.CommandMessageReceivedEvent;

/**
 * @author stuntguy3000
 */
public class BreachesCommand extends Command {
    public BreachesCommand() {
        super(HIBPBot.getInstance(), "[site] List all breaches in the HIBP database.", false, "breaches");
    }

    @Override
    public void processCommand(CommandMessageReceivedEvent event) throws ApiException {
        event.getChat().sendMessage("Fetching...");

        if (event.getArgs().length > 0) {
            String domain = event.getArgs()[0];

            if (Util.isValidURL(domain)) {
                try {
                    List<Breach> breaches = HIBPBot.getInstance().getHibpApi().getBreachList(event.getArgs()[0]);
                    BreachHandler.sendBreaches(event.getChat(), breaches, null);
                } catch (NoBreachesException | NoUserException ex) {
                    event.getChat().sendMessage("No breaches could be found.");
                }
            } else {
                event.getChat().sendMessage("Domain name is invalid.\n" +
                        "If this is a mistake, contact @stuntguy3000");
            }
        } else {
            List<Breach> breachList = HIBPBot.getInstance().getHibpApi().getBreachList(null);

            if (breachList == null) {
                throw new NoBreachesException();
            } else {
                BreachHandler.sendBreaches(event.getChat(), breachList, null);
            }
        }
    }
}