package back;

public class Hamming {
    public static String wysylanie(String str)
    {
        int n = str.length();
        int a[] = new int[n];
        StringBuilder stringBuilder= new StringBuilder();

        for(int i=0 ; i < n ; i++)
        {
            a[n-i-1] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }

        int b[] = generateCode(a);

        for(int i=0 ; i < b.length ; i++)
        {
            stringBuilder.append(b[b.length-i-1]);
        }

        return String.valueOf(stringBuilder);
    }

    static int[] generateCode(int a[])
    {
        int b[];
        int i=0, parity_count=0 ,j=0, k=0;
        while(i < a.length)
        {
            if(Math.pow(2,parity_count) == i+parity_count + 1)
            {
                parity_count++;
            }
            else
            {
                i++;
            }
        }
        b = new int[a.length + parity_count];
        for(i=1 ; i <= b.length ; i++)
        {
            if(Math.pow(2, j) == i)
            {
                b[i-1] = 2;
                j++;
            }
            else
            {
                b[k+j] = a[k++];
            }
        }
        for(i=0 ; i < parity_count ; i++)
        {
            b[((int) Math.pow(2, i))-1] = getParity(b, i);
        }
        return b;
    }

    static int getParity(int b[], int power)
    {
        int parity = 0;
        for(int i=0 ; i < b.length ; i++)
        {
            if(b[i] != 2)
            {
                int k = i+1;
                String s = Integer.toBinaryString(k);
                int x = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
                if(x == 1)
                {
                    if(b[i] == 1)
                    {
                        parity = (parity+1)%2;
                    }
                }
            }
        }
        return parity;
    }

    static String[] odbieranie(String str, int parity_count) //parity_count dla 8 bitów jest zawsze 4
    {
        StringBuilder fixedMsg= new StringBuilder();
        StringBuilder orgMsg= new StringBuilder();
        String[] strings = new String[2];
        int n = str.length();
        int a[] = new int[n];
        for(int i=0 ; i < n ; i++)
        {
            a[n-i-1] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        int power;
        int parity[] = new int[parity_count];
        String syndrome = new String();
        for(power=0 ; power < parity_count ; power++)
        {
            for(int i=0 ; i < a.length ; i++)
            {
                int k = i+1;
                String s = Integer.toBinaryString(k);
                int bit = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
                if(bit == 1)
                {
                    if(a[i] == 1)
                    {
                        parity[power] = (parity[power]+1)%2;
                    }
                }
            }
            syndrome = parity[power] + syndrome;
        }
        int error_location = Integer.parseInt(syndrome, 2);
        if(error_location != 0)
        {
            a[error_location-1] = (a[error_location-1]+1)%2;
            for(int i=0 ; i < a.length ; i++)
            {
                fixedMsg.append(a[a.length-i-1]); //wiadomość po poprawieniu
            }
        }
        else
        {
            fixedMsg.append(str); //jeśli nie ma błędu to zwracamy to co dostaliśmy
        }
        power = parity_count-1;
        for(int i=a.length ; i > 0 ; i--)
        {
            if(Math.pow(2, power) != i)
            {
                orgMsg.append(a[i-1]); //wysłana wiadomośc po zdekodowaniu
            }
            else
            {
                power--;
            }
        }
        strings[0] = String.valueOf(fixedMsg);
        strings[1] = String.valueOf(orgMsg);
        return strings;
    }
    static String send(String string)
    {
        int start=0;
        int end=8;
        StringBuilder stringToSend = new StringBuilder();
        StringBuilder tempString = new StringBuilder();

        for(int i=0; i<string.length()/8;i++) //wykona się tyle razy ile jest 8 bitowych znaków
        {
            for(int j=start;j<end;j++) //pracuje na odpowiednim przedziale bitów
            {
                tempString.append(string.charAt(j)); //robi z nich string
            }
            stringToSend.append(wysylanie(String.valueOf(tempString))); // do zwracanej wartości dodawany jest zakodowany wynik
            start+=8;
            end+=8;
            tempString.setLength(0);
        }
        return String.valueOf(stringToSend);
    }
}
