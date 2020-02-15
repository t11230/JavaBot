import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main{

    static CSVImport dataTable;

    public static void main(String[] args) throws LoginException, FileNotFoundException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NjU1NjQ1NDkzNDk3NDMwMDQ3.XjtXEQ.U29j31ob4NMOTf3D1-eKGCJuq64";
        dataTable = new CSVImport("/home/ubuntu/Documents/export2.csv");
        builder.setToken(token);
        builder.addEventListener(new MessageHandler(dataTable));
        builder.buildAsync();
    }
}
