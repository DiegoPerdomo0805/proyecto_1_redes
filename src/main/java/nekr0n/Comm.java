package nekr0n;

import java.util.Collection;
import java.util.Scanner;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jxmpp.jid.BareJid;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;

public class Comm {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Comm comm = new Comm(Conn.getConnection());
        String usuario = "nelly";
        String psswrd2 = "contra123";
        Conn.logIn(comm.conn, usuario, psswrd2);
        String command ="";
        Scanner sc = new Scanner(System.in);
        comm.pingAll();
    

        //comm.pingAll();
        //comm.dm("momoP", "Hola, soy Nelly");
        //comm.showContactInfo("momoP");


        // Cerrar sesión
        Conn.logOff(comm.conn);
        
    }


    public AbstractXMPPConnection conn;
    private ChatManager chatManager;

    public Comm(AbstractXMPPConnection conn) {
        this.conn = conn;
        this.chatManager = ChatManager.getInstanceFor(conn);
    }


    // ping everybody

    public void pingAll() {
        Roster roster = Roster.getInstanceFor(conn);
        Collection<RosterEntry> entries = roster.getEntries();
        System.out.println(entries.size() + " contacts found");
        System.out.println("Pinging everybody connected...");
        for (RosterEntry entry : entries) {
            Presence presence = roster.getPresence(entry.getJid());

            System.out.println("Pinging " + entry.getJid() + "...");

            String status = presence.isAvailable() ? "online" : "offline";
            System.out.println(entry.getName() + " is " + status);

            String inContatcs = roster.contains(entry.getJid()) ? "in contacts" : "not in contacts";
            System.out.println(entry.getName() + " is " + inContatcs);

            String statusMsg = presence.getStatus();
            System.out.println(entry.getName() + " status message: " + statusMsg);
        }
    }

    // show info of a contact

    public void showContactInfo(String jid) {
        Roster roster = Roster.getInstanceFor(conn);
        Presence presence = roster.getPresence(getJid(jid));

        String status = presence.isAvailable() ? "online" : "offline";
        System.out.println(jid + " is " + status);
        System.out.println(jid + " status message: " + presence.getStatus());

        /*RosterEntry entry = roster.getEntry(getJid(jid));
        System.out.println("Name: " + entry.getName());
        System.out.println("JID: " + entry.getJid());
        System.out.println("Online Status: " + roster.getPresence(entry.getJid()).isAvailable());
        System.out.println("Status Message: " + roster.getPresence(entry.getJid()).getStatus()); */
    }

    

    // show contacts



    // add contact



    // send direct message
    public void dm(String jid, String message) {

        Chat chat = chatManager.chatWith(getJid(jid));
        try {
            chat.send(message);
        } catch (Exception e) {
            System.out.println("Error sending message to " + jid);
        }
    }

    public void listener(){
        chatManager.addIncomingListener(new IncomingChatMessageListener() {
            @Override
            public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {                
                System.out.println(from + ": " + message.getBody());
            }
        });
    }

    public void stopListener(){
        chatManager.removeIncomingListener(new IncomingChatMessageListener() {
            @Override
            public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {                
                System.out.println(from + ": " + message.getBody());
            }
        });
    }



    // send group message



    // change status



    // send file

    
    public EntityBareJid getJid(String jid) {
        try {
            return JidCreate.entityBareFrom(jid + "@" + conn.getXMPPServiceDomain());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
