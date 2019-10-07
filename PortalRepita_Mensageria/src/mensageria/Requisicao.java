package mensageria;
/**
 *
 * @author Hugo Carvalho
 */
public class Requisicao implements java.io.Serializable{
    
    public static final int NOMEARQUIVO_REQUISICAO = 0;
    public static final int CONTEUDOARQUIVO_REQUISICAO = 1;  
    
    private int mensagemTipo;
    private String mensagemConteudo;

    public int getMensagemTipo() {
        return mensagemTipo;
    }

    public void setMensagemTipo(int mensagemTipo) {
        this.mensagemTipo = mensagemTipo;
    }

    public String getMensagemConteudo() {
        return mensagemConteudo;
    }

    public void setMensagemConteudo(String mensagemConteudo) {
        this.mensagemConteudo = mensagemConteudo;
    }
}
