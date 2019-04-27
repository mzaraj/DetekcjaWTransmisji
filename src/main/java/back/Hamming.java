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

    static void odbieranie(String str, int parity_count) //dokończyć, aktualnie trzeba podać miejsce gdzie dokonano zmian
            //problemy ze znajdowaniem odpowiedniego kodu jaki użytkownik wpisał
    {
        int n = str.length();
        int a[] = new int[n];
        for(int i=0 ; i < n ; i++)
        {
            a[n-i-1] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        int power;
        // We shall use the value stored in 'power' to find the correct bits to check for parity.

        int parity[] = new int[parity_count];
        // 'parity' array will store the values of the parity checks.

        String syndrome = new String();
        // 'syndrome' string will be used to store the integer value of error location.

        for(power=0 ; power < parity_count ; power++) {
            // We need to check the parities, the same no of times as the no of parity bits added.

            for(int i=0 ; i < a.length ; i++) {
                // Extracting the bit from 2^(power):

                int k = i+1;
                String s = Integer.toBinaryString(k);
                int bit = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
                if(bit == 1) {
                    if(a[i] == 1) {
                        parity[power] = (parity[power]+1)%2;
                    }
                }
            }
            syndrome = parity[power] + syndrome;
        }
        // This gives us the parity check equation values.
        // Using these values, we will now check if there is a single bit error and then correct it.

        int error_location = Integer.parseInt(syndrome, 2);
        if(error_location != 0) {
            System.out.println("Error is at location " + error_location + ".");
            a[error_location-1] = (a[error_location-1]+1)%2;
            System.out.println("Corrected code is:");
            for(int i=0 ; i < a.length ; i++) {
                System.out.print(a[a.length-i-1]);
            }
            System.out.println();
        }
        else {
            System.out.println("There is no error in the received data.");
        }

        // Finally, we shall extract the original data from the received (and corrected) code:
        System.out.println("Original data sent was:");
        power = parity_count-1;
        for(int i=a.length ; i > 0 ; i--) {
            if(Math.pow(2, power) != i) {
                System.out.print(a[i-1]);
            }
            else {
                power--;
            }
        }
        System.out.println();
    }
}
