package nekr0n;

public class Menu {
    public void Options(String[] options) {

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("\nSelect an option: ");
    }


    public boolean check(String s)//Método para checar si un string es numérico
    {
        boolean numeric = true;
        try 
        {
            int n = Integer.parseInt(s);
        } 
        catch (NumberFormatException e) 
        {
            numeric = false;
        }

        if(numeric)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean rango(int min, int max, int x)
    {
        if (x > min && x <= max)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public boolean option(String e, int i, int j)
    {
        if (check(e))
        {
            int n = Integer.parseInt(e);
            if(rango(i, j, n))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false; 
        }
    }
}
