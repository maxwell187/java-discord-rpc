import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Main {
    public static void main(String[] args) {
        DiscordRPC lib = DiscordRPC.INSTANCE;
        //refers from the discordrpc class
        String clientId = "<CLIENT ID HERE>";
        //"login" token, not really a login, just to clarify
        //what application discord should choose
        String steamId = "";
        //not needed if you dont want to
        //connect this to any games
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        //needed for further events, not really necessary in this context
        lib.Discord_Initialize(clientId, handlers, true, steamId);  //for initialization
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000; //shows timestamp
        System.out.println("started."); //just to debug: can be disabled since its not really viewed
        //by the user 
        
        // I hope thats clear
        presence.details = "<details text here>";
        presence.state = "<state text here>";
        presence.largeImageKey = "<image key here>";
        presence.largeImageText = "<image text here>";
        presence.smallImageKey = "<image key here>";
        presence.smallImageText = "<image text here>";
        
        //updates presence on start
        lib.Discord_UpdatePresence(presence);
        
        //starts new thread that gets the values from discord
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "RPC-Callback-Handler").start();

    }
}
