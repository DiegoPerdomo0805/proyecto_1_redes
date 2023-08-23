package nekr0n;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //conn.getConnection();
        int option = 0;
        Scanner sc = new Scanner(System.in);
        while (option < 3)
        {
            Menu m = new Menu();
            String[] options = {
                "Registrarse",
                "Iniciar sesión",
                "Salir"
            };

            int action = 0;
            System.out.println("\n");
            m.Options(options);
            String op = sc.nextLine();
            if (m.option(op, 0, options.length))
            {
                option = Integer.parseInt(op);

                // declarar variables que se usan en todos los casos
                Comm comm = new Comm(Conn.getConnection());
                String user;
                String psswrd;
                String who;
                String what;
                // acciones disponibles
                String[] actions = {                            "Mostrar online y estados",                            "Mostrar contacto",                            "Agregar contacto",                            "Enviar mensaje directo",                            "Enviar mensaje grupal",                            "Cambiar estado",                            "Enviar archivo",                            "Cerrar sesión",                            "Eliminar cuenta"                        };
                switch (option)
                {
                    case 1:
                        System.out.println("Registrarse");
                        System.out.println("Ingrese su nombre de usuario: ");
                        user = sc.nextLine();
                        System.out.println("Ingrese su contraseña: ");
                        psswrd = sc.nextLine();
                        Conn.signUp(comm.conn, user, psswrd);
                        comm.messageNotification();

                        while (action < 8)
                        {
                            System.out.println("\n");
                            m.Options(actions);
                            String act = sc.nextLine();
                            if (m.option(act, 0, actions.length))
                            {
                                action = Integer.parseInt(act);
                                who = "";
                                what = "";

                                switch (action)
                                {
                                    case 1://DONE
                                        comm.pingAll();
                                        break;
                                    case 2://DONE
                                        System.out.println("Ingrese el nombre de usuario a buscar: ");
                                        who = sc.nextLine();
                                        comm.getUserInfo(who);
                                        break;
                                    case 3://DONE
                                        System.out.println("Ingrese el nombre de usuario a agregar: ");
                                        who = sc.nextLine();
                                        System.out.println("Ingrese el nombre del grupo (Si no se ingresa, se agregará a contactos únicamente): ");
                                        what = sc.nextLine();
                                        comm.addContact(who, what);
                                        // add
                                        break;
                                    case 4://DONE
                                        System.out.println("Escriba el destinatario: ");
                                        who = sc.nextLine();                                    
                                        System.out.println(" - Para salir del chat, escriba -exit y presione enter");
                                        comm.listener();
                                        while (!what.equals("-exit"))
                                        {
                                            // System.out.print(comm.conn.getUser().asBareJid().toString() + ": ");
                                            what = sc.nextLine();
                                            if (!what.equals("-exit"))
                                            {
                                                comm.dm(who, what);
                                            }
                                        }
                                        comm.stopListener();
                                        // dm
                                        break;
                                    case 5:
                                        System.out.println("Escriba el nombre del grupo: ");
                                        who = sc.nextLine();
                                        if(who.isEmpty() || who.equals(" ")){
                                            System.out.println("No se ha ingresado un grupo válido");
                                        }
                                        else{
                                            System.out.println("Para salir del chat, escriba -exit y presione enter");
                                            comm.GroupListener(who);
                                            while (!what.equals("-exit"))
                                            {
                                                // System.out.print(comm.conn.getUser().asBareJid().toString() + ": ");
                                                what = sc.nextLine();
                                                if (!what.equals("-exit"))
                                                {
                                                    comm.sendGroupMessage(who, what);
                                                }
                                            }
                                            comm.stopGroupListener(who);
                                        }
                                        // gm
                                        break;
                                    case 6:
                                        System.out.println("Ingrese su nuevo estado: ");
                                        what = sc.nextLine();
                                        System.out.println(what);
                                        // status
                                        break;
                                    case 7:
                                        System.out.println("Enviar archivo");
                                        // attach
                                        break;
                                    case 8://DONE
                                        System.out.println("Cerrando sesión...");
                                        Conn.logOff(comm.conn);
                                        break;
                                    case 9://DONE
                                        System.out.println("Eliminar cuenta");
                                        Conn.deleteAccount(comm.conn);
                                        break;
                                }
                            }
                            else
                            {
                                System.out.println("Opción no válida");
                            }
                        }
                        comm.stopMessageNotification();


                        // igaul que con login
                        break;
                    case 2:
                        System.out.println("Ingrese su nombre de usuario: ");
                        user = sc.nextLine();
                        System.out.println("Ingrese su contraseña: ");
                        psswrd = sc.nextLine();
                        Conn.logIn(comm.conn, user, psswrd);
                        comm.messageNotification();

                        
                        while (action < 8)
                        {
                            System.out.println("\n");
                            m.Options(actions);
                            String act = sc.nextLine();
                            if (m.option(act, 0, actions.length))
                            {
                                action = Integer.parseInt(act);
                                who = "";
                                what = "";

                                switch (action)
                                {
                                    case 1://DONE
                                        comm.pingAll();
                                        break;
                                    case 2://DONE
                                        System.out.println("Ingrese el nombre de usuario a buscar: ");
                                        who = sc.nextLine();
                                        comm.getUserInfo(who);
                                        break;
                                    case 3://DONE
                                        System.out.println("Ingrese el nombre de usuario a agregar: ");
                                        who = sc.nextLine();
                                        System.out.println("Ingrese el nombre del grupo (Si no se ingresa, se agregará a contactos únicamente): ");
                                        what = sc.nextLine();
                                        comm.addContact(who, what);
                                        // add
                                        break;
                                    case 4://DONE
                                        System.out.println("Escriba el destinatario: ");
                                        who = sc.nextLine();                                    
                                        System.out.println(" - Para salir del chat, escriba -exit y presione enter");
                                        comm.listener();
                                        while (!what.equals("-exit"))
                                        {
                                            // System.out.print(comm.conn.getUser().asBareJid().toString() + ": ");
                                            what = sc.nextLine();
                                            if (!what.equals("-exit"))
                                            {
                                                comm.dm(who, what);
                                            }
                                        }
                                        comm.stopListener();
                                        // dm
                                        break;
                                    case 5:
                                        System.out.println("Escriba el nombre del grupo: ");
                                        who = sc.nextLine();
                                        if(who.isEmpty() || who.equals(" ")){
                                            System.out.println("No se ha ingresado un grupo válido");
                                        }
                                        else{
                                            System.out.println("Para salir del chat, escriba -exit y presione enter");
                                            comm.GroupListener(who);
                                            while (!what.equals("-exit"))
                                            {
                                                // System.out.print(comm.conn.getUser().asBareJid().toString() + ": ");
                                                what = sc.nextLine();
                                                if (!what.equals("-exit"))
                                                {
                                                    comm.sendGroupMessage(who, what);
                                                }
                                            }
                                            comm.stopGroupListener(who);
                                        }
                                        // gm
                                        break;
                                    case 6:
                                        System.out.println("Ingrese su nuevo estado: ");
                                        what = sc.nextLine();
                                        System.out.println(what);
                                        // status
                                        break;
                                    case 7:
                                        System.out.println("Enviar archivo");
                                        // attach
                                        break;
                                    case 8://DONE
                                        System.out.println("Cerrando sesión...");
                                        Conn.logOff(comm.conn);
                                        break;
                                    case 9://DONE
                                        System.out.println("Eliminar cuenta");
                                        Conn.deleteAccount(comm.conn);
                                        break;
                                }
                            }
                            else
                            {
                                System.out.println("Opción no válida");
                            }
                        }
                        comm.stopMessageNotification();
                        break;
                    case 3:
                        System.out.println("Salir");
                        break;
                }
            }
            else
            {
                System.out.println("Opción no válida");
            }
            
        }
        sc.close();
    }
}
