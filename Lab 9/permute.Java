package lab9;
import java.util.Arrays;
import java.util.Random;

public class permute
{
	public static void main(String [] args)
	{
		int array_size = 10;
		int array_size2 = 10;
		int array_size3 = 4;
		int [] array = new int[array_size];
		int [] array2 = new int[array_size2];
		int [] array3 = new int[array_size3];
		int [] frequency_array = new int[24];
		int [] frequency_array2 = new int[24];
		int permutation_index = 0;

		Random rand = new Random();

		for (int i = 0; i < array_size; i++)
			array[i] = rand.nextInt(100);

		for (int i = 0; i < array_size2; i++)
			array2[i] = rand.nextInt(100);
		
		
		
		System.out.println("1.(b)");
		System.out.println("Original array: ");
        print_array(array);
        for (int i=0;i<5;i++) {
        	System.out.println("Permutation "+(i+1));
        	randomize_in_place(array);
            print_array(array);
        }
        System.out.println();
        System.out.println("2.(b)");
		System.out.println("Original array: ");
        print_array(array2);
        for (int i=0;i<5;i++) {
        	System.out.println("Permutation "+(i+1));
        	randomize_in_place(array2);
            print_array(array2);
        }
                
              
		array3[0] = 4;
        array3[1] = 3;
        array3[2] = 2;
        array3[3] = 1;
        
        System.out.println();
        System.out.println("3.(a)");
        for(int i=0;i<24000;i++) {
        	randomize_in_place(array3);
        	permutation_index = compute_permutation_index(array3);
        	frequency_array [permutation_index]+=1;
        }
        print_array_q3(frequency_array);
        
        System.out.println();
        System.out.println("3.(b)");
        for(int i=0;i<24000;i++) {
        	permute_with_all(array3);
        	permutation_index = compute_permutation_index(array3);
        	frequency_array2 [permutation_index]+=1;
        }
        print_array_q3(frequency_array2);
	
	}
	
	//Implement randomize in place algorithm below
	public static void randomize_in_place(int A[])
	{
		Random r = new Random();
		int n = A.length;
		for(int i=0;i<n;i++) {
			int temp = A[i];
			int rand = r.nextInt(n-i) + i;
			A[i] = A[rand];
			A[rand] = temp;
		}
	}

        //Implement permute with all algorithm below
        public static void permute_with_all(int A[])
        {
        	Random r = new Random();
    		int n = A.length;
    		for(int i=0;i<n;i++) {
    			int temp = A[i];
    			int rand = r.nextInt(n);
    			A[i] = A[rand];
    			A[rand] = temp;
    		}
        }

	public static int compute_permutation_index(int [] A)
	{
		int permutation_index = 0;
		int next_number = 0;
		int index_next_number = 0;

		for (int starting_index = 0; starting_index < A.length-1; starting_index++)
		{	
			int [] remaining_numbers = new int [A.length-starting_index];
                        int [] remaining_numbers_sorted = new int [A.length-starting_index];

			for (int i = 0; i < remaining_numbers.length; i++)
			{
				remaining_numbers[i] = A[starting_index+i];
				remaining_numbers_sorted[i] = remaining_numbers[i];
			}

			insertion_sort(remaining_numbers_sorted);

			next_number = A[starting_index];

			for (int i = 0; i < remaining_numbers_sorted.length; i++)
			{
				if (remaining_numbers_sorted[i] == next_number)
				{
					index_next_number = i;
					break;
				}
			}

			permutation_index += index_next_number*factorial(remaining_numbers.length-1);	
		}	

		return permutation_index;
		
	}

	public static int factorial(int x)
	{	
		int product = 1;

		for (int i = x; i >= 1; i--)
			product *= i;

		return product;
	}

        public static void insertion_sort(int [] A)
        {
                int key;
                int i;

                for (int j = 1; j < A.length; j++)
                {
                        key = A[j];

                        //insert A[j] into the sorted sequence A[1..j-1]
                        i = j-1;

                        while ((i >= 0) && (A[i] > key))
                        {
                                A[i+1] = A[i];
                                i = i-1;
                        }

                        A[i+1] = key;
                }
        }

        //prints the elements of the array A on the screen
        public static void print_array(int [] A)
        {       
                System.out.printf("[");
                for (int i = 0; i < A.length-1; i++)
                {       
                        System.out.printf("%d, ", A[i]);
                }
                
                System.out.printf("%d]\n", A[A.length-1]);
        
        }
        
        public static void print_array_q3(int [] A)
        {       
        		int i = 0;
                System.out.printf("[");
                for (; i < A.length-1; i++)
                {       
                        System.out.printf("%d = %d, ",(i), A[i]);
                }
                
                System.out.printf("%d = %d]\n",i, A[A.length-1]);
        
        }
}