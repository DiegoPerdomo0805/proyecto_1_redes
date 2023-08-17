package nekr0n;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

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
        //pingAll(conn);
        String yo = "momoP";
        String psswrd = "123456789";
        logIn(conn, yo, psswrd);
        getSessionInfo(conn);
        



        /*System.out.println("Clan Brujah");
        String usr = "Annabelle";
        String pss = "1234";
        logOff(conn);
        conn = getConnection();
        logIn(conn, usr, pss);
        getSessionInfo(conn);*/


        String usuario = "nelly";
        String psswrd2 = "contra123";
        //signUp(conn, usuario, psswrd2);
        
        String u = "jasper";
        String p = "987654321";
        //signUp(conn, usr, psswrd);
        logOff(conn);
        conn = getConnection();
        logIn(conn, usuario, psswrd2);
        getSessionInfo(conn);
    }

    public static void getSessionInfo(AbstractXMPPConnection connection) {
        if (connection.isAuthenticated()) {
            System.out.println("Connected user: " + connection.getUser());
            System.out.println("Is secure connection: " + connection.isSecureConnection());
            System.out.println("Is using compression: " + connection.isUsingCompression());
            System.out.println("Is connected: " + connection.isConnected());
        } else {
            System.out.println("Not authenticated.");
        }
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
                    .setCustomX509TrustManager(createTrustManager())
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
            Map <String, String> attr = new HashMap<>();
            accountManager.createAccount(name, psswrd, attr);
            System.out.println("Account created");
        } catch (NoResponseException | XMPPErrorException | NotConnectedException | InterruptedException |XmppStringprepException e) {
            e.printStackTrace();
        }        
    }

    // Método para iniciar sesión en el servidor del chat

    public static void logIn(AbstractXMPPConnection conn, String user, String psswrd){
        try {
            conn.login(user, psswrd, null);
            System.out.println("Logged in");
        } catch (XMPPException | SmackException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar sesión en el servidor del chat

    public static void logOff(AbstractXMPPConnection conn){
        conn.disconnect();
        System.out.println("Logged off");
    }

    //public static 

    // Método para eliminar la cuenta del servidor del chat

    public static void deleteAccount(AbstractXMPPConnection conn){
        AccountManager accountManager = AccountManager.getInstance(conn);
        try {
            accountManager.deleteAccount();
            System.out.println("Account deleted");
        } catch (NoResponseException | XMPPErrorException | NotConnectedException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Generación de certificado SSL para el servidor del chat
    public static X509TrustManager createTrustManager() {
        return new X509TrustManager() {
            public boolean isClientTrusted(X509Certificate[] cert) {
                return true;
            }

            public boolean isServerTrusted(X509Certificate[] cert) {
                return true;
            }

            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }
}
