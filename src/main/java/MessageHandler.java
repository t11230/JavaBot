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
        }else if(event.getMessage().getContentRaw().startsWith("!!getLocation")){
            String command = event.getMessage().getContentRaw();
            if(command.contains(" ")){
                command = command.split(" ", 2)[1];
                if(dataTable.getItem(command) == null){
                    event.getChannel().sendMessage("You B-Baka, that's not an item.").queue();
                }else{
                    int result = dataTable.getItem(command).getIndex();
                    event.getChannel().sendMessage("It's on row: " + Integer.toString(result)).queue();
                }
            }else{
                event.getChannel().sendMessage("Invalid Command").queue();
            }
        }else if(event.getMessage().getContentRaw().startsWith("!!getPrice")){
            String command = event.getMessage().getContentRaw();
            if(command.contains(" ")){
                command = command.split(" ", 2)[1];
                if(dataTable.getItem(command) == null){
                    event.getChannel().sendMessage("Thats not an item, B-Baka").queue();
                }else{
                    int result = dataTable.getItem(command).getPrice();
                    event.getChannel().sendMessage("It Costs: " + result).queue();
                }
            }else{
                event.getChannel().sendMessage("Invalid Command").queue();
            }
        }else if(event.getMessage().getContentRaw().startsWith("!!getRarity")){
            String command = event.getMessage().getContentRaw();
            if(command.contains(" ")){
                command = command.split(" ", 2)[1];
                if(dataTable.getItem(command) == null){
                    event.getChannel().sendMessage("Thats not an item, B-Baka").queue();
                }else{
                    String result = dataTable.getItem(command).getRarity();
                    event.getChannel().sendMessage("IThis item is " + result).queue();
                }
            }else{
                event.getChannel().sendMessage("Invalid Command").queue();
            }
        }else if(event.getMessage().getContentRaw().startsWith("!!item")) {
            String command = event.getMessage().getContentRaw();
            if (command.contains(" ")) {
                command = command.split(" ", 2)[1];
                if(dataTable.getItem(command) == null){
                    event.getChannel().sendMessage("Thats not an item, B-Baka").queue();
                } else {
                    Item result = dataTable.getItem(command);
                    event.getChannel().sendMessage(
                            "```***" + result.getName() + "***\n" +
                                    "Cost: " + result.getPrice() + "\n" +
                                    "Rarity: " + result.getRarity() + "\n" +
                                    "Attunement: " + result.getAttunement() + "```"
                    ).queue();
                }
            } else {
                event.getChannel().sendMessage("Invalid Command").queue();
            }
        }
    }
}
