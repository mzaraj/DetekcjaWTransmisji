package back;

public class Parity {
    public static String wysylanie(String str)
    {
        int ilosc=0;
        StringBuilder newStr = new StringBuilder();
        int start=0;
        int koniec=8;
        for(int j=0;j<str.length()/8;j++)
        {
            for(int i=start;i<koniec;i++) //zliczanie "1" w danym przedziale ośmiu znaków w stringu
            {
                if(String.valueOf(str.charAt(i)).equals("1"))
                {
                    ilosc++;
                }
            }
            if(ilosc%2==0) //sprawdzamy czy ilość "1" jest parzysta i dodajemy odpowiedni bit parzystości
            {
                newStr.append("0");
            }
            else
            {
                newStr.append("1");
            }
            for(int k=start;k<koniec;k++) //dodajemy wcześniej sprawdzaną część stringa
            {
                newStr.append(str.charAt(k));
            }
            ilosc=0;
            start+=8;
            koniec+=8;
        }
        return String.valueOf(newStr);
    }
    public static String odbieranie(String str) //work in progress
    {
        int ilosc=0;
        for(int i=0;i<str.length();i++) //zliczanie "1"
        {
            if(String.valueOf(str.charAt(i)).equals("1"))
            {
                ilosc++;
            }
        }
        if(ilosc%2!=0)
        {

        }
        return str;
    }
}
