package back;

public class Parity {
    public static String wysylanie(String str)
    {
        int ilosc=0;
        String newStr;
        for(int i=0;i<str.length();i++)
        {
            if(String.valueOf(str.charAt(i)).equals("1"))
            {
                ilosc++;
            }
        }
        if(ilosc%2==0)
        {
            newStr="0"+str;
        }
        else
        {
            newStr="1"+str;
        }
        return newStr;
    }
}
