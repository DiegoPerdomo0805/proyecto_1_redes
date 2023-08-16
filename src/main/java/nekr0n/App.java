package nekr0n;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
                switch (option)
                {
                    case 1:
                        System.out.println("Registrarse");
                        break;
                    case 2:
                        String[] actions = {
                            "\nMostrar online y estados",
                            "Mostrar contacto",
                            "Agregar contacto",
                            "Enviar mensaje directo",
                            "Enviar mensaje grupal",
                            "Cambiar estado",
                            "Enviar archivo",
                            "Cerrar sesión",
                            "Eliminar cuenta"
                        };
                        while (action < 8)
                        {
                            System.out.println("\n");
                            m.Options(actions);
                            String act = sc.nextLine();
                            if (m.option(act, 0, actions.length))
                            {
                                action = Integer.parseInt(act);
                                String who = "";
                                String what = "";

                                switch (action)
                                {
                                    case 1:
                                        // ping
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el nombre de usuario a buscar: ");
                                        who = sc.nextLine();
                                        // lookup
                                        break;
                                    case 3:
                                        System.out.println("Ingrese el nombre de usuario a agregar: ");
                                        who = sc.nextLine();
                                        // add
                                        break;
                                    case 4:
                                        System.out.println("Escriba el destinatario: ");
                                        who = sc.nextLine();
                                        System.out.println("Escriba su mensaje: ");
                                        what = sc.nextLine();
                                        // dm
                                        break;
                                    case 5:
                                        System.out.println("Escriba los destinatarios separados por comas: ");
                                        who = sc.nextLine();
                                        System.out.println("Escriba su mensaje:");
                                        what = sc.nextLine();
                                        // gm
                                        break;
                                    case 6:
                                        System.out.println("Ingrese su nuevo estado: ");
                                        what = sc.nextLine();
                                        // status
                                        break;
                                    case 7:
                                        System.out.println("Enviar archivo");
                                        // attach
                                        break;
                                    case 8:
                                        System.out.println("Cerrando sesión...");
                                        break;
                                    case 9:
                                        System.out.println("Eliminar cuenta");
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
