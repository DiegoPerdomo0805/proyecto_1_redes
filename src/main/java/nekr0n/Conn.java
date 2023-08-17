package nekr0n;
import java.io.IOException;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.ping.PingManager;

public class Conn {

    public static void main(String[] args) {
        AbstractXMPPConnection conn = getConnection();
        System.out.println(conn);
        //pingAll(conn);
        String yo = "momoP";
        String psswrd = "123456789";
        logIn(conn, yo, psswrd);

        System.out.println("Clan Brujah");
        String usr = "Annabelle";
        String pss = "1234";
        signUp(conn, usr, pss);
        //System.out.println("Clan Gangrel");
        //try {
        //    conn.login(usr, psswrd, null);
        //    System.out.println("Clan Malkavian");
        //    System.out.println("Logged in");
        //    deleteAccount(conn);
        //} catch (XMPPException | SmackException | IOException | InterruptedException e) {
        //    e.printStackTrace();
        //}
//

        // return connection;
    }


    // Método para obtener la conexión con el servidor del chat
    public static AbstractXMPPConnection getConnection() {
        AbstractXMPPConnection connection = null;
        
        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain("alumchat.xyz") // Replace with a valid XMPP domain
                    .setHost("alumchat.xyz")       // Replace with a valid host
                    .setPort(5222)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.required)
                    .build();

            connection = new XMPPTCPConnection(config);
            
            try {
                connection.connect();
            } catch (SmackException | IOException | XMPPException | InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("Connected");
        } catch (XmppStringprepException e) {
            e.printStackTrace();
            System.out.println("Error");
        }

        if (connection != null) {
            return connection;
        } else {
            return null;
        }
    }


    // Método para registrarse en el servidor del chat
    public static void signUp(AbstractXMPPConnection conn, String user, String psswrd){
        AccountManager accountManager = AccountManager.getInstance(conn);
        Localpart name = null;
        try {
            name = Localpart.from(user);
            accountManager.createAccount(name, psswrd, null);
            
        } catch (NoResponseException | XMPPErrorException | NotConnectedException | InterruptedException |XmppStringprepException e) {
            e.printStackTrace();
        }        
    }

    // Método para iniciar sesión en el servidor del chat

    public static void logIn(AbstractXMPPConnection conn, String user, String psswrd){
        try {
            conn.login(user, psswrd, null);
            //System.out.println("Logged in");
        } catch (XMPPException | SmackException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar sesión en el servidor del chat

    //public static 

    // Método para eliminar la cuenta del servidor del chat

    public static void deleteAccount(AbstractXMPPConnection conn){
        AccountManager accountManager = AccountManager.getInstance(conn);
        try {
            accountManager.deleteAccount();
        } catch (NoResponseException | XMPPErrorException | NotConnectedException | InterruptedException e) {
            e.printStackTrace();
        }
    }





    
}
