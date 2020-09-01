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
	
	//Ana fonksiyon C�z�c�
	//Geri d�n�m� tablo cinsinden olacak
	public int[] coz()
	{
		boolean bitti = false;
		int sayac=0;
		
		while(!bitti) // standart d�ng�
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
				
				if(tablo[i]==0) // sadece tablonun bo� elemanlar�n� dolduracaz de�ilmi
				{
					int deger = sonKontrolcu(grupBul(yatayYonBul(i), dikeyYonBul(i)), yatayKontrolcu(i), dikeyKontrolcu(i));
					if(deger != -1) //e�er geri d�n�� -1 ise birden fazla olas�l�k var i�imizi �ansa b�rakamay�z
					{
						tablo[i] = deger;
						System.out.printf("tablo %d de�eri %d ile de�i�tirildi oye\n",i,deger);
					}
				}		
			}
		}
		return tablo;
	}
	
	
	//Hangi grupta oldu�unu bulmak i�in
	//��nk� sudokuda ayn� tablodaki say�lar ayn� olmamal� felan filan :)
	//a grup �sten ilk b orta c ise sondaki tablo
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
	//�stekinin ayn�s� i�te
	//a ise soldaki b orta c sa�daki tablo
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
	// yatay ve dikeyi birle�tirerek tam konumu bulur bkz: sa� �st tablo :	7  8  9
	//																		16 17 18
	//																		25 26 27
	private int[] grupBul(int yatay,int dikey)
	{
		int[] grup = new int[9];
		
		int[][] yatayAral�k = {{1,27} , {28,54}, {55,81}}; //a b ve c 'nin yatay aral���
		int[][] dikeyAral�k = {{1,2,3} ,{4,5,6}, {7,8,0}};
		
		if(yatay == -1)
			System.out.println("HATA YATAY");
		if(dikey == -1)
			System.out.println("HATA DIKEY");
		
		int grupInd�s=0;
		for(int i = yatayAral�k[yatay][0] ; i <= yatayAral�k[yatay][1]; i++)
		{
			for(int j = 0 ; j < 3 ; j++)
			{
				if(dikeyAral�k[dikey][j] == i%9)
				{
					grup[grupInd�s] = i;
					grupInd�s++;
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
	
	//Say�n�n yukar�s�ndaki ve a�as�ndaki say�lar� daha sonra i�lenmek �zere haf�zaya al�r
	private int[] dikeyKontrolcu(int index)
	{
		int[] arabellek = new int[9];
		int arabellekIndex = 0;
		//yukar�
		for(int i = index ; i >= 0 ; i-=9)
		{
			arabellek[arabellekIndex] = tablo[i];
			arabellekIndex++;
		}
		//asag�
		for(int i = index+9 ; i < 81 ; i+=9)
		{
			arabellek[arabellekIndex] = tablo[i];
			arabellekIndex++;
		}
		
		return arabellek;
	}
	//Say�n�n sa��ndaki ve solundaki say�lar� daha sonra i�lenmek �zere haf�zaya al�r
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
	
	//Gelen de�erleri kontrol eder
	//dikey yatay �izgileri ve grubunda olan say�lar� yazar e�er bulunmayan tek karakter varsa yaz�l�r yoksa devamke
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
