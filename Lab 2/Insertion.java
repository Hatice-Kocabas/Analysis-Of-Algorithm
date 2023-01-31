import java.util.Arrays;
import java.util.Random;

public class Insertion
{
	public static void main(String [] args)
	{
		int array_size = 1000;
		long start_time, end_time, elapsed_time;

		int array [] = new int[array_size];

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		//initialize elements of array with random integers
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(100);}

		//part 1(c)
		 long startTime = System.nanoTime(); 
	     insertion_sort(array);
	     long endTime = System.nanoTime();
	     long elapsed = endTime - startTime; 
	     System.out.println(elapsed);

        

		//part 1(d)
	     startTime = System.nanoTime(); 
	     insertion_sort_reverse(array);
	     endTime = System.nanoTime();
	     elapsed = endTime - startTime;
	     System.out.println(elapsed);

		//part 1(e)
	     
	     int array_larger[]=new int[100000];
	     
	     for (int i = 0; i < array_larger.length; i++) {
				array_larger[i] = rand.nextInt(100);}

	     
	     //part1(e-c)
	     
	     startTime = System.nanoTime(); 
	     insertion_sort(array_larger);
	     endTime = System.nanoTime();
	     elapsed = endTime - startTime;
	     System.out.println(elapsed);
	     
	     //part1(e-d)
	     startTime = System.nanoTime(); 
	     insertion_sort_reverse(array_larger);
	     endTime = System.nanoTime();
	     elapsed = endTime - startTime;
	     System.out.println(elapsed);
	     
	}
	

        //part 1(a) implementing insertion sort algorithm as a method below
        public static void insertion_sort(int [] A)
        {
        	int n = A.length;
            for (int i = 1; i < n; ++i) {
                int key = A[i];
                int j = i - 1;
     
                while (j >= 0 && A[j] > key) {
                    A[j + 1] = A[j];
                    j = j - 1;
                }
                A[j + 1] = key;
            }

        }

        //part 1(b) implementing insertion sort algorithm that sorts in descending order as a method below
        public static void insertion_sort_reverse(int [] A)
        {
        	int size = A.length;
        	for (int step = 1; step < size; step++) {
        	int key = A[step];
        	int j = step - 1;
        	while (j >= 0 && key > A[j]) {
        	A[j + 1] = A[j];
        	--j;
        	}
        	A[j + 1] = key;
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
}

