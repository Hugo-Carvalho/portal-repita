package mensageria;

import java.io.File;

/**
 *
 * @author Hugo Carvalho
 */
public class Resposta implements java.io.Serializable{
    
    public static final int ARQUIVO_EXISTE = 1;
    public static final int ARQUIVO_NAO_ENCONTRADO = 2;
    public static final int CONTEUDO_ACESSIVEL = 3;
    public static final int EOF = 4;
    
    private int codigoResposta;
    private File conteudoResposta;

    public int getCodigoResposta() {
        return codigoResposta;
    }

    public void setCodigoResposta(int codigoResposta) {
        this.codigoResposta = codigoResposta;
    }

    public File getConteudoResposta() {
        return conteudoResposta;
    }

    public void setConteudoResposta(File conteudoResposta) {
        this.conteudoResposta = conteudoResposta;
    }
}
