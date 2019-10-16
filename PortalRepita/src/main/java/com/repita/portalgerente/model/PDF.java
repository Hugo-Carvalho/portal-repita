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
@Table(name = "montrefat_pdf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PDF.findAllPDF", query = "SELECT p FROM PDF p WHERE p.idCadastNota =:id")
})
public class PDF implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "idCadastNota")
    private Long idCadastNota;
    @Column(name = "pdf")
    private byte[] pdfFile;
    @Column(name = "localidade")
    private String localidade;
    @Column(name = "emissorFatura")
    private String emissorFatura;
    @Column(name = "inss")
    private String inss;
    @Column(name = "issqn")
    private String issqn;
    @Column(name = "valor")
    private String valor;
    @Column(name = "pc")
    private String pc;
    @Column(name = "numero")
    private String numero;
    @Column(name = "dataEmissao")
    private String dataEmissao;
    @Column(name = "codVerificacao")
    private String codVerificacao;
    @Column(name = "cnpj")
    private String cnpj;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCadastNota() {
        return idCadastNota;
    }

    public void setIdCadastNota(Long idCadastNota) {
        this.idCadastNota = idCadastNota;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEmissorFatura() {
        return emissorFatura;
    }

    public void setEmissorFatura(String emissorFatura) {
        this.emissorFatura = emissorFatura;
    }

    public String getInss() {
        return inss;
    }

    public void setInss(String inss) {
        this.inss = inss;
    }

    public String getIssqn() {
        return issqn;
    }

    public void setIssqn(String issqn) {
        this.issqn = issqn;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getCodVerificacao() {
        return codVerificacao;
    }

    public void setCodVerificacao(String codVerificacao) {
        this.codVerificacao = codVerificacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
