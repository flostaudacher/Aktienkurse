import java.util.Random;

public class BubbleMain {
	
	static int length = 100000;
	static int a[] = new int[length];
	
	static Random zufall = new Random();
	public static void main(String[] args) {
		fillArray();
		bubblesort(a);
	}
	
	static void fillArray()
	{
		for(int i = 0 ; i<a.length ; i++)
		{
			a[i] = zufall.nextInt(10);
		}
	}
	
	public static void bubblesort(int[] zusortieren) {
		final long timeStart = System.currentTimeMillis();
		int temp;
		for(int i=1; i<zusortieren.length; i++) {
			for(int j=0; j<zusortieren.length-i; j++) {
				if(zusortieren[j]>zusortieren[j+1]) {
					temp=zusortieren[j];
					zusortieren[j]=zusortieren[j+1];
					zusortieren[j+1]=temp;
				}
				
			}
		}
		final long timeEnd = System.currentTimeMillis();
        System.out.print(timeEnd-timeStart);
		
	}
}
