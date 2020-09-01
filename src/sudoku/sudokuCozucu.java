package sudoku;

public class sudokuCozucu 
{

	private int[] tablo;
	
	public sudokuCozucu(int[] tablo) 
	{
		// SUDOKU TABLOSU dahil ediliyor
		this.tablo = tablo;
	}
	
	public void arayuzTablosu()
	{
		
	}
	
	//Ana fonksiyon Cözücü
	//Geri dönümü tablo cinsinden olacak
	public int[] coz()
	{
		boolean bitti = false;
		int sayac=0;
		
		while(!bitti) // standart döngü
		{
			sayac=0;
			for(int i = 0 ; i < 81 ; i++)
			{
				if(tablo[i] == 0)
				{
					sayac++;
				}
			}
			if(sayac == 0)
			{
				bitti =true;
				break;
			}
			
			for(int i = 0 ; i < 81 ; i ++)
			{
				
				if(tablo[i]==0) // sadece tablonun boþ elemanlarýný dolduracaz deðilmi
				{
					int deger = sonKontrolcu(grupBul(yatayYonBul(i), dikeyYonBul(i)), yatayKontrolcu(i), dikeyKontrolcu(i));
					if(deger != -1) //eðer geri dönüþ -1 ise birden fazla olasýlýk var iþimizi þansa býrakamayýz
					{
						tablo[i] = deger;
						System.out.printf("tablo %d deðeri %d ile deðiþtirildi oye\n",i,deger);
					}
				}		
			}
		}
		return tablo;
	}
	
	
	//Hangi grupta olduðunu bulmak için
	//Çünkü sudokuda ayný tablodaki sayýlar ayný olmamalý felan filan :)
	//a grup üsten ilk b orta c ise sondaki tablo
	private int yatayYonBul(int i)
	{
		i+=1;
		if(i<=27)
		{
			return 0;
		}
		else if(i<=54)
		{
			return 1;
		}
		else if(i<=81)
		{
			return 2;
		}
		else
		{return -1;}
	}
	//Üstekinin aynýsý iþte
	//a ise soldaki b orta c saðdaki tablo
	private int dikeyYonBul(int i)
	{
		i += 1;
		if((i % 9 <= 3) && (i % 9 > 0) )
		{
			return 0;
		}
		else if(i%9<=6 && i % 9 > 3)
		{
			return 1;
		}
		else if(i%9<=9 && (i%9 == 0 || i % 9 > 6))
		{
			return 2;
		}
		else
		{
			return -1;
		}
	}
	// yatay ve dikeyi birleþtirerek tam konumu bulur bkz: sað üst tablo :	7  8  9
	//																		16 17 18
	//																		25 26 27
	private int[] grupBul(int yatay,int dikey)
	{
		int[] grup = new int[9];
		
		int[][] yatayAralýk = {{1,27} , {28,54}, {55,81}}; //a b ve c 'nin yatay aralýðý
		int[][] dikeyAralýk = {{1,2,3} ,{4,5,6}, {7,8,0}};
		
		if(yatay == -1)
			System.out.println("HATA YATAY");
		if(dikey == -1)
			System.out.println("HATA DIKEY");
		
		int grupIndýs=0;
		for(int i = yatayAralýk[yatay][0] ; i <= yatayAralýk[yatay][1]; i++)
		{
			for(int j = 0 ; j < 3 ; j++)
			{
				if(dikeyAralýk[dikey][j] == i%9)
				{
					grup[grupIndýs] = i;
					grupIndýs++;
				}
			}
		}
		int[] tabloGrup = new int[9];
		for(int i = 0 ; i < 9 ; i++)
		{
			tabloGrup[i] = tablo[grup[i]-1];
		}
		
		return tabloGrup;
	}
	
	//Sayýnýn yukarýsýndaki ve aþasýndaki sayýlarý daha sonra iþlenmek üzere hafýzaya alýr
	private int[] dikeyKontrolcu(int index)
	{
		int[] arabellek = new int[9];
		int arabellekIndex = 0;
		//yukarý
		for(int i = index ; i >= 0 ; i-=9)
		{
			arabellek[arabellekIndex] = tablo[i];
			arabellekIndex++;
		}
		//asagý
		for(int i = index+9 ; i < 81 ; i+=9)
		{
			arabellek[arabellekIndex] = tablo[i];
			arabellekIndex++;
		}
		
		return arabellek;
	}
	//Sayýnýn saðýndaki ve solundaki sayýlarý daha sonra iþlenmek üzere hafýzaya alýr
	private int[] yatayKontrolcu(int index)
	{
		int[] arabellek = new int[9];
		int arabellekIndex = 0;
		
		int sola =0,saga=0;
		if(index+1%9 != 0)
		{
			sola = index - (index % 9);
			saga = index + (9-(index % 9));
		}
		
		//sola
		for(int i = index ; i >= sola ; i--)
		{
			arabellek[arabellekIndex] = tablo[i];
			arabellekIndex++;
		}
		//saga
		for(int i = index+1 ; i < saga ; i++)
		{
			arabellek[arabellekIndex] = tablo[i];
			arabellekIndex++;
		}
		
		return arabellek;
	}
	
	//Gelen deðerleri kontrol eder
	//dikey yatay çizgileri ve grubunda olan sayýlarý yazar eðer bulunmayan tek karakter varsa yazýlýr yoksa devamke
	private int sonKontrolcu(int[] grup,int[] yatay , int [] dikey)
	{
		int[] geriDonus = new int[9];
		int[] tampon = new int[27];
		System.arraycopy(grup, 0, tampon, 0, 9);
		System.arraycopy(yatay, 0, tampon, 9, 9);
		System.arraycopy(dikey, 0, tampon, 18, 9);
		
		for(int i : tampon)
		{
		int sayi =i;
		switch(sayi)
		{
			case 1:geriDonus[0]=1;break;
			case 2:geriDonus[1]=1;break;
			case 3:geriDonus[2]=1;break;
			case 4:geriDonus[3]=1;break;
			case 5:geriDonus[4]=1;break;
			case 6:geriDonus[5]=1;break;
			case 7:geriDonus[6]=1;break;
			case 8:geriDonus[7]=1;break;
			case 9:geriDonus[8]=1;break;
		}
		
		}
		
		int sifirSayaci=0;
		for(int j = 0 ; j < 9 ; j++)
		{
			if(geriDonus[j]==0)
			{
				sifirSayaci++;
			}
		}
		
		int donus = -1;
		if(sifirSayaci == 1)
		{
			for(int j = 0 ; j < 9 ; j++)
			{
				if(geriDonus[j] == 0)
				{
					donus = j+1;
				}
			}
		}
		
		return donus;
	}
}
