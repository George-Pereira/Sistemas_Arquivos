package Controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class arquivosController implements IArquivosController
{

	@Override
	public void leDir(String caminho) throws IOException 
	{
		File dir = new File(caminho);
		if(dir.isDirectory() && dir.exists()) 
		{
			File [] vetArquivos = dir.listFiles();//vetor de files - só abriu o diretorio
			for(File f: vetArquivos) //for each 
			{
				if(f.isDirectory()) 
				{
					System.out.println("[" + f.getName()+"]");
				}
			}
			for(File f: vetArquivos)
			{
				if(f.isFile()) 
				{
					//leArquivo(f.getAbsolutePath(), f.getName()); permite ler qualquer arquivo
					System.out.println(f.getName());
				}
			}
		}
		else 
		{
			throw new IOException("Dir. Inválido");
		}
	}

	@Override
	public void leArquivo(String caminho, String nome) throws IOException 
	{
		File arq = new File(caminho, nome);
		if(arq.isFile() && arq.exists()) //apenas abriu o arquivo
		{
			FileInputStream fluxo = new FileInputStream(arq);//a partir daqui ele começa a ler o arquivo
			InputStreamReader leitor = new InputStreamReader(fluxo); //fluxo de bits e converte em string
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) 
			{
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}
		else 
		{
			throw new IOException("Arquivo Inválido");
		}
	}

	@Override
	public void escreveArquivo(String caminho, String nome) throws IOException 
	{
		boolean existe = false;
		File dir = new File(caminho);
		if(dir.isDirectory()) 
		{
			File arq = new File(dir, nome);//forma de criar um arquivo
			if(arq.exists()) 
			{
				existe = true;
			}
			String linha = JOptionPane.showInputDialog(null, "Digite:");
			StringBuffer buffer = new StringBuffer();
			while(!linha.equals("fim")) 
			{
				buffer.append(linha);
				buffer.append("\r\n");// \r para o bloco de notas que não reconhece o \n
				linha = JOptionPane.showInputDialog(null, "Digite: ");
			}
			FileWriter abreArq = new FileWriter(arq, existe); //quando o boolean for false ele faz a operação de write, se for true ele faz append automaticamente
			PrintWriter escreveArq = new PrintWriter(abreArq);
			escreveArq.write(buffer.toString()); //Print Writer não escreve String Buffer
			escreveArq.flush();
			escreveArq.close();
			abreArq.close();
		}
		else 
		{
			throw new IOException("Não é dir."); //já trata o try e catch se não for um diretorio ou caminho valido
		}
	}

	@Override
	public void abreArquivo(String caminho, String nome) throws IOException 
	{
		Desktop desk = Desktop.getDesktop(); //pega caracteristicas relevantes do sistema de arquivos do Sistema Operacional
		File arq = new File (caminho, nome);
		if(arq.isFile() && arq.exists()) 
		{
			desk.open(arq);
		}
		else 
		{
			throw new IOException("Arquivo Inválido");
		}
	}
	
}
