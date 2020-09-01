package sudoku;

public class console {

static sudokuCozucu sudoku;
	
	private static int[] coz()
	{
		int[] yeniTablo = sudoku.coz();
		
		return yeniTablo;		
	}
	
	public static void main(String[] args) 
	{
		long baslangic = System.currentTimeMillis();
		//Tablo oluþturduk
		int[] tablo = new int[]{9,0,0 ,0,0,0 ,0,6,5,
		  						0,2,0 ,0,0,3 ,0,0,1,
		  						0,7,0 ,0,0,0 ,9,0,8,
		  
		  						0,0,3 ,6,0,0 ,0,0,0,
		  						0,8,0 ,1,0,0 ,7,0,0,
		  						7,0,1 ,8,0,0 ,2,9,0,
		  		
		  						8,0,0 ,0,2,0 ,0,3,0,
		  						1,0,9 ,0,0,0 ,0,8,2,
		  						2,5,0 ,0,9,0 ,0,0,0};
		
		sudoku = new sudokuCozucu(tablo);
		int[] yeniTablo = coz();
		
		for(int i=0;i<81;i++)
		{
			if(i%9==0)
				System.out.println("");
			System.out.print(" "+yeniTablo[i]);
		}
		long son = System.currentTimeMillis();
		
		double saniye = (double)(son - baslangic) / 1000;
		System.out.println("\nGeçen süre : "+ saniye);
	}

}
