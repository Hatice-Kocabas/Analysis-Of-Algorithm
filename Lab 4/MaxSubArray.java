 import java.util.Arrays;
import java.util.Random;

public class MaxSubArray
{
	public static void main(String [] args)
	{
		int array_size = 65536;
		int [] A = new int[array_size];
		int [] test_A = new int[5];
		int [] diff_test_A = new int[4];
                int [] diff_A = new int[array_size-1];
                int [] outputs = new int[3];
		int best_left_index = 0;
		int best_right_index = 0;
		int max_difference = 0;

		long start_time, end_time, elapsed_time;
		long elapsed_time_brute_force, elapsed_time_divide_and_conquer;

		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		//part 1(b)
		        test_A[0] = 10;
                test_A[1] = 11;
                test_A[2] = 7;
                test_A[3] = 10;
                test_A[4] = 6;
                brute_force(test_A);
                System.out.println();

		//initialize elements of array with random integers and compute difference array
		for (int i = 0; i < A.length; i++)
		{
			A[i] = rand.nextInt(100);
			if (i > 0)
				diff_A[i-1] = A[i] - A[i-1];
		}

		//part 1(c) compute the elapsed time for brute-force algorithm
		start_time = System.nanoTime();
		brute_force(A);
		end_time = System.nanoTime();
		elapsed_time = end_time - start_time;
	
		System.out.println( elapsed_time);
		
		System.out.println();

		//part 2(b)
                diff_test_A[0] = 1;
                diff_test_A[1] = -4;
                diff_test_A[2] = 3;
                diff_test_A[3] = -4;
                start_time = System.nanoTime();
                
                brute_force(diff_test_A);		
        		end_time = System.nanoTime();
        		elapsed_time = end_time - start_time;
        		
        		System.out.println(elapsed_time);
        		System.out.println();

                //print the left index, right index and maximum difference (i.e. net profit)

                //part 2(c) compute the elapsed time for divide and conquer algorithm
        		start_time = System.nanoTime();
            find_maximum_subarray(diff_A, max_difference, best_right_index, outputs);
        		
        		end_time = System.nanoTime();
        		elapsed_time = end_time - start_time;
        		
        		System.out.println( elapsed_time);
		
                //print the left index, right index and maximum difference (i.e. net profit)

		//part 3

		int max_array_size = 1000;
                int [] A_2 = new int[max_array_size];
		int [] diff_A_2 = new int[max_array_size-1];

                //initialize elements of array with random integers and compute difference array
                for (int i = 0; i < A_2.length; i++)
                {
                        A_2[i] = rand.nextInt(100);
                        if (i > 0)
                                diff_A_2[i-1] = A_2[i] - A_2[i-1];
                }

		for (array_size = 2; array_size <= max_array_size; array_size++)
		{


		}

	}

	//part 1(a) implementing the brute-force algorithm
	public static void brute_force(int [] A)
	{
		int element1= A[0];
		int element2= A[1];
		
		int i,j;
		j=1;

		int max_diff = A[1] - A[0];
		
		for (i = 0; i < A.length; i++) 
	    {
	        for (j = i + 1; j < A.length; j++) 
	        {
	            if (A[j] - A[i] > max_diff) {
	                max_diff = A[j] - A[i];
	                element1= i+1;
	                element2= j+1;
	            }
	        }
	        
	    }
		
		 System.out.println("i  = " + element1 + "   j = " + element2 +" max difference = " + max_diff );
		

	}

        public static void brute_force_2(int [] A, int array_size)
        {       

                //print the left index, right index and maximum difference (i.e. net profit)
        
        }

        //part 2(a) implementing the recursive algorithm that uses divide and conquer and finds the maximum subarray
        public static int[] find_maximum_subarray(int [] diff_A, int low, int high, int [] outputs)
        {       
                //print the left index, right index and maximum difference (i.e. net profit)
        	if (high == low) {
    			int[] outputs1 = {low, high, diff_A[low]};
    			return outputs1;
    		}
    		else {
    			int mid = (low+high)/2;
    			int[] outputs_left = find_maximum_subarray(diff_A, low, mid,outputs);
    			int[] outputs_right = find_maximum_subarray(diff_A, mid+1, high,outputs);
    			int[] outputs_cross = find_max_crossing_subarray(diff_A, low, mid, high,outputs);
    			
    			if (outputs_left[2] >= outputs_right[2] && outputs_left[2] >= outputs_cross[2]) {
    				return outputs_left;
    			}
    			else if (outputs_right[2] >= outputs_left[2] && outputs_right[2] >= outputs_cross[2]) {
    				return outputs_right;
    			}
    			else {
    				return outputs_cross;
    			}
    		}

        }

        public static int[] find_max_crossing_subarray(int [] diff_A, int low, int mid, int high, int [] outputs)
	{
        	
        	int[] outputs1 = {-1,-1,0};
    		int left_sum = Integer.MIN_VALUE;
    		int sum = 0;
    		for (int i = mid; i >= low; i--) {
    			sum += diff_A[i];
    			if (sum > left_sum) {
    				left_sum = sum;
    				outputs1[0] = i;
    			}
    		}
    		int right_sum = Integer.MIN_VALUE;
    		sum = 0;
    		for (int i = mid+1; i < high; i++) {
    			sum += diff_A[i];
    			if (sum > right_sum) {
    				right_sum = sum;
    				outputs1[1] = i; 
    			}
    		}
    		outputs1[2] = left_sum + right_sum;
    		return outputs1;

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