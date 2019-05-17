package Controller;

import java.io.IOException;

public interface IArquivosController 
{
	public void leDir(String caminho) throws IOException; //sempre que se trabalha com arquivos e diretorios precisa-se tratar exceções de entrada e saida
	public void leArquivo(String caminho, String nome)throws IOException;
	public void escreveArquivo(String caminho, String nome) throws IOException;
	public void abreArquivo(String caminho, String nome)throws IOException;
}
