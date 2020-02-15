import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public class MessageHandler extends ListenerAdapter {

    CSVImport dataTable;

    MessageHandler(CSVImport constructDataTable){
        dataTable = constructDataTable;
    }




    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //super.onMessageReceived(event);
        if(event.getAuthor().isBot()){
            return;
        }

        if(event.getMessage().getContentRaw().startsWith("!!pull")){
            event.getChannel().sendMessage("It's not like I wanted to help your server, \nB-Baka").queue();
            event.getMessage().delete().queue();
            /*String command = event.getMessage().getContentRaw();
            if(command.contains("\"")) {
                command = command.split("\"")[1];
                event.getChannel().sendMessage(command).queue();
            }else{
                event.getChannel().sendMessage("Invalid Command").queue();
            }*/
        }else if(event.getMessage().getContentRaw().startsWith("!!getLocation")){
            String command = event.getMessage().getContentRaw();
            if(command.contains(" ")){
                command = command.split(" ", 2)[1];
                int result = dataTable.getLocation(command);
                if(result == -1){
                    event.getChannel().sendMessage("You B-Baka, that's not an item.").queue();
                }else{
                    event.getChannel().sendMessage("It's on row: " + Integer.toString(result)).queue();
                }
            }else{
                event.getChannel().sendMessage("Invalid Command").queue();
            }
        }else if(event.getMessage().getContentRaw().startsWith("!!getPrice")){
            String command = event.getMessage().getContentRaw();
            if(command.contains(" ")){
                command = command.split(" ", 2)[1];
                String result = dataTable.getPrice(command);
                if(result.equals("-1")){
                    event.getChannel().sendMessage("Thats not an item, B-Baka").queue();
                }else{
                    event.getChannel().sendMessage("It Costs: " + result).queue();
                }
            }else{
                event.getChannel().sendMessage("Invalid Command").queue();
            }
        }else if(event.getMessage().getContentRaw().startsWith("!!getRarity")){
            String command = event.getMessage().getContentRaw();
            if(command.contains(" ")){
                command = command.split(" ", 2)[1];
                String result = dataTable.getRarity(command);
                if(result.equals("-1")){
                    event.getChannel().sendMessage("Thats not an item, B-Baka").queue();
                }else{
                    event.getChannel().sendMessage("IThis item is " + result).queue();
                }
            }else{
                event.getChannel().sendMessage("Invalid Command").queue();
            }
        }else if(event.getMessage().getContentRaw().startsWith("!!item")) {
            String command = event.getMessage().getContentRaw();
            if (command.contains(" ")) {
                command = command.split(" ", 2)[1];
                String name = dataTable.fuzzy(command).get(3).toString();
                String rarity = dataTable.getRarity(command);
                String cost = dataTable.getPrice(command);
                String attunement = dataTable.getAttunement(command);
                if (rarity.contains("-1") || cost.contains("-1") || attunement.contains("-1")) {
                    event.getChannel().sendMessage("Thats not an item, B-Baka").queue();
                } else {
                    event.getChannel().sendMessage(
                            "```***" + name + "***\n" +
                                    "Cost: " + cost + "\n" +
                                    "Rarity: " + rarity + "\n" +
                                    "Attunement: " + attunement + "```"
                    ).queue();
                }
            } else {
                event.getChannel().sendMessage("Invalid Command").queue();
            }
        }
    }
}
