package View;

import java.io.IOException;

import Controller.IArquivosController;
import Controller.arquivosController;
public class Main 
{
	public static void main(String[] args) 
	{
		IArquivosController arqCont= new arquivosController();
		String caminho = "C:\\TEMP"; //file.separator - de acordo com o SO
		String nome = "testeSO1.txt";
		try 
		{
			//arqCont.escreveArquivo(caminho, nome);
			arqCont.leArquivo(caminho, nome);
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}
