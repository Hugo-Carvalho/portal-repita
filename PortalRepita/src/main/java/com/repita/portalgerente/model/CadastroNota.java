package com.repita.portalgerente.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "montrefat_cadastronota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadastroNota.findByAllNotaNaoAprovAndNaoAtrib", query = "SELECT c FROM CadastroNota c WHERE id NOT IN (SELECT na.idNota FROM NotaAprovada na) AND id NOT IN (SELECT nr.idNota from NotaRealizada nr) AND c.atribuidoPara = ''"),
    @NamedQuery(name = "CadastroNota.findByAllNotaNaoAprovAndAtrib", query = "SELECT c FROM CadastroNota c WHERE id NOT IN (SELECT na.idNota FROM NotaAprovada na) AND id NOT IN (SELECT nr.idNota from NotaRealizada nr) AND c.atribuidoPara != ''"),
    @NamedQuery(name = "CadastroNota.findByAllNotaNaoAprovAndAtribByUser", query = "SELECT c FROM CadastroNota c WHERE id NOT IN (SELECT na.idNota FROM NotaAprovada na) AND id NOT IN (SELECT nr.idNota from NotaRealizada nr) AND c.atribuidoPara =:user"),
    @NamedQuery(name = "CadastroNota.findByAllNotaNaoAprov", query = "SELECT c FROM CadastroNota c WHERE id NOT IN (SELECT na.idNota FROM NotaAprovada na) AND id NOT IN (SELECT nr.idNota from NotaRealizada nr)"),
    @NamedQuery(name = "CadastroNota.findByAllNotaAprov", query = "SELECT c FROM CadastroNota c WHERE id IN (SELECT na.idNota FROM NotaAprovada na)"),
    @NamedQuery(name = "CadastroNota.findByAllNotaRealiz", query = "SELECT c FROM CadastroNota c WHERE id IN (SELECT nr.idNota from NotaRealizada nr)")
})
public class CadastroNota implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "numChamado")
    private String numChamado;
    @Column(name = "atribuidoPara")
    private String atribuidoPara;
    @Column(name = "tipoChamado")
    private String tipoChamado;
    @Column(name = "contrato")
    private String contrato;
    @Column(name = "contratada")
    private String contratada;
    @Column(name = "gerencia")
    private String gerencia;
    @Column(name = "campoTexto")
    private String campoTexto;
    @Column(name = "mandadoJudicial")
    private String mandadoJudicial;
    @Column(name = "fiscalAdm")
    private String fiscalAdm;
    @Column(name = "email")
    private String email;

    public CadastroNota() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumChamado() {
        return numChamado;
    }

    public void setNumChamado(String numChamado) {
        this.numChamado = numChamado;
    }

    public String getAtribuidoPara() {
        return atribuidoPara;
    }

    public void setAtribuidoPara(String atribuidoPara) {
        this.atribuidoPara = atribuidoPara;
    }

    public String getTipoChamado() {
        return tipoChamado;
    }

    public void setTipoChamado(String tipoChamado) {
        this.tipoChamado = tipoChamado;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getContratada() {
        return contratada;
    }

    public void setContratada(String contratada) {
        this.contratada = contratada;
    }

    public String getGerencia() {
        return gerencia;
    }

    public void setGerencia(String gerencia) {
        this.gerencia = gerencia;
    }

    public String getCampoTexto() {
        return campoTexto;
    }

    public void setCampoTexto(String campoTexto) {
        this.campoTexto = campoTexto;
    }

    public String getMandadoJudicial() {
        return mandadoJudicial;
    }

    public void setMandadoJudicial(String mandadoJudicial) {
        this.mandadoJudicial = mandadoJudicial;
    }

    public String getFiscalAdm() {
        return fiscalAdm;
    }

    public void setFiscalAdm(String fiscalAdm) {
        this.fiscalAdm = fiscalAdm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
