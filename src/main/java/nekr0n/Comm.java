package nekr0n;

import java.util.Collection;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jxmpp.jid.BareJid;

public class Comm {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Comm comm = new Comm(Conn.getConnection());
        String usuario = "nelly";
        String psswrd2 = "contra123";
        Conn.logIn(comm.conn, usuario, psswrd2);
        comm.pingAll();



        // Cerrar sesión
        Conn.logOff(comm.conn);
        
    }


    private AbstractXMPPConnection conn;
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

            System.out.println("Pinging " + entry.getName() + "...");

            String status = presence.isAvailable() ? "online" : "offline";
            System.out.println(entry.getName() + " is " + status);

            String inContatcs = roster.contains(entry.getJid()) ? "in contacts" : "not in contacts";
            System.out.println(entry.getName() + " is " + inContatcs);

            String statusMsg = presence.getStatus();
            System.out.println(entry.getName() + " status message: " + statusMsg);
        }
    }

    // show info of a contact

    

    // show contacts



    // add contact



    // send direct message



    // send group message



    // change status



    // send file

    
    
}
