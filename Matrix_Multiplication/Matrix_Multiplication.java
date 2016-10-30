import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
//import java.util.logging;
public class Matrix_Multiplication {

	
	public static int[][] m1;
	public static int[][] m2;
	public static int[][] m3;
	
	
	
	public static int[][] LoadMatrix(String filename)throws Exception {

       Scanner s= new Scanner(new FileInputStream(filename));	
		int rows = s.nextInt();
	 int cols = s.nextInt();
	 int mat[][] =new int [rows][cols];
	 for(int i=0;i<rows;i++)
		 for (int j=0;j<cols;j++)
			 mat[i][j]=s.nextInt();
	
	s.close();
	return mat;	
}	
	
	public static void DisplayMatrix(String filename)throws Exception {
	
			PrintWriter p=new PrintWriter(new FileOutputStream(filename));
		int rows = m3.length;
		 int cols = m3[0].length;
		 //int mat[][] =new int [rows][cols];
		 
		   p.println("After multiplication: ");
		   p.println();
		   for(int i=0;i<rows;i++)
		 {	 for (int j=0;j<cols;j++)
				p.print(m3[i][j]+"      ");
		     p.println();
		 }
		 p.println();
		
		p.close();
			
	}

	 
	
	public static class Thread1 extends Thread{
		@Override
		public  void run(){
			
		//int r= m1.length;
		int c = m2[0].length;
		int k=(m1.length)/4;
		for(int i=0;i<=k;i++)
			for(int j=0;j<c;j++)
				for(int l=0;l<m2.length;l++)
					m3[i][j]+=m1[i][l]*m2[l][j];
			 
			
		}
		
		
	}
	
	
	public static class Thread2 extends Thread{
		@Override
		public  void run(){
			
		//int r= m1.length;
		int c = m2[0].length;
		int k=(m1.length)/2+1;
		int s=(m1.length)/4+1;
		
		for(int i=s;i<k;i++)
			for(int j=0;j<c;j++)
				for(int l=0;l<m2.length;l++)
					m3[i][j]+=m1[i][l]*m2[l][j];
			 
			
		}
		
		
	}
	
	public static class Thread3 extends Thread{
		@Override
		public  void run(){
			
		int r= m1.length;
		int c = m2[0].length;
		int k=((3*m1.length)/4)+1;
		int s=(m1.length)/2+1;
		for(int i=s;i<k;i++)
			for(int j=0;j<c;j++)
				for(int l=0;l<m2.length;l++)
					m3[i][j]+=m1[i][l]*m2[l][j];
			 
			
		}
		
		
	}
	
	
	public static class Thread4 extends Thread{
		@Override
		public  void run(){
			
		int r= m1.length;
		int c = m2[0].length;
		int k=((3*m1.length)/4)+1;
		for(int i=k;i<r;i++)
			for(int j=0;j<c;j++)
				{for(int l=0;l<m2.length;l++)
					m3[i][j]+=m1[i][l]*m2[l][j];
				}    
			
		}
		
		
	}

    public static void main(String[] args)throws Exception {
        m1 = LoadMatrix("input_a.txt");
        m2= LoadMatrix("input_b.txt");
        m3 = new int [m1.length][m2[0].length];
        Thread1 th1=new Thread1();
        Thread2 th2= new Thread2();
        Thread3 th3= new Thread3();
        Thread4 th4= new Thread4();
        
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th1.join();
        th2.join();
        th3.join();
        th4.join();
      
        DisplayMatrix("output.txt");
       
    	
    	
    }
}
	
	
