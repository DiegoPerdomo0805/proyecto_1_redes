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
                                    case 1:
                                        // ping
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el nombre de usuario a buscar: ");
                                        who = sc.nextLine();
                                        System.out.println(who);
                                        // lookup
                                        break;
                                    case 3:
                                        System.out.println("Ingrese el nombre de usuario a agregar: ");
                                        who = sc.nextLine();
                                        System.out.println(who);
                                        // add
                                        break;
                                    case 4:
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
                                        System.out.println("Escriba los destinatarios separados por comas: ");
                                        who = sc.nextLine();
                                        System.out.println("Escriba su mensaje:");
                                        what = sc.nextLine();
                                        System.out.println(what + " " + who);
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
                                    case 8:
                                        System.out.println("Cerrando sesión...");
                                        Conn.logOff(comm.conn);
                                        break;
                                    case 9:
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


                        // igaul que con login
                        break;
                    case 2:
                        System.out.println("Ingrese su nombre de usuario: ");
                        user = sc.nextLine();
                        System.out.println("Ingrese su contraseña: ");
                        psswrd = sc.nextLine();
                        Conn.logIn(comm.conn, user, psswrd);

                        
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
                                    case 1:
                                        // ping
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el nombre de usuario a buscar: ");
                                        who = sc.nextLine();
                                        System.out.println(who);
                                        // lookup
                                        break;
                                    case 3:
                                        System.out.println("Ingrese el nombre de usuario a agregar: ");
                                        who = sc.nextLine();
                                        System.out.println(who);
                                        // add
                                        break;
                                    case 4:
                                        System.out.println("Escriba el destinatario: ");
                                        who = sc.nextLine();                                    
                                        System.out.println(" - Para salir del chat, escriba -exit y presione enter");
                                        comm.listener();
                                        while (!what.equals("-exit"))
                                        {
                                            //System.out.print(comm.conn.getUser().asBareJid().toString() + ": ");
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
                                        System.out.println("Escriba los destinatarios separados por comas: ");
                                        who = sc.nextLine();
                                        System.out.println("Escriba su mensaje:");
                                        what = sc.nextLine();
                                        System.out.println(what + " " + who);
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
                                    case 8:
                                        System.out.println("Cerrando sesión...");
                                        Conn.logOff(comm.conn);
                                        break;
                                    case 9:
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
