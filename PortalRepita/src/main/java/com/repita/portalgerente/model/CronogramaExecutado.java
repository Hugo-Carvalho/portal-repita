/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repita.portalgerente.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hugo.carvalho
 */
@Entity
@Table(name = "cronograma_executado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CronogramaExecutado.findAll", query = "SELECT c FROM CronogramaExecutado c"),
    @NamedQuery(name = "CronogramaExecutado.findById", query = "SELECT c FROM CronogramaExecutado c WHERE c.id = :id"),
    @NamedQuery(name = "CronogramaExecutado.findByNome", query = "SELECT c FROM CronogramaExecutado c WHERE c.nome = :nome"),
    @NamedQuery(name = "CronogramaExecutado.findByReceptor", query = "SELECT c FROM CronogramaExecutado c WHERE c.receptor = :receptor"),
    @NamedQuery(name = "CronogramaExecutado.findByRobo", query = "SELECT c FROM CronogramaExecutado c WHERE c.robo = :robo"),
    @NamedQuery(name = "CronogramaExecutado.findByDataInicio", query = "SELECT c FROM CronogramaExecutado c WHERE c.dataInicio = :dataInicio"),
    @NamedQuery(name = "CronogramaExecutado.findByHoraInicio", query = "SELECT c FROM CronogramaExecutado c WHERE c.horaInicio = :horaInicio"),
    @NamedQuery(name = "CronogramaExecutado.findByDataTermino", query = "SELECT c FROM CronogramaExecutado c WHERE c.dataTermino = :dataTermino"),
    @NamedQuery(name = "CronogramaExecutado.findByHoraTermino", query = "SELECT c FROM CronogramaExecutado c WHERE c.horaTermino = :horaTermino"),
    @NamedQuery(name = "CronogramaExecutado.findByStatus", query = "SELECT c FROM CronogramaExecutado c WHERE c.status = :status")})
public class CronogramaExecutado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "receptor")
    private String receptor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "robo")
    private String robo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "data_inicio")
    private String dataInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "hora_inicio")
    private String horaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "data_termino")
    private String dataTermino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "hora_termino")
    private String horaTermino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "mensagem")
    private String mensagem;

    public CronogramaExecutado() {
    }

    public CronogramaExecutado(Long id) {
        this.id = id;
    }

    public CronogramaExecutado(Long id, String nome, String receptor, String robo, String dataInicio, String horaInicio, String dataTermino, String horaTermino, String status, String mensagem) {
        this.id = id;
        this.nome = nome;
        this.receptor = receptor;
        this.robo = robo;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.dataTermino = dataTermino;
        this.horaTermino = horaTermino;
        this.status = status;
        this.mensagem = mensagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getRobo() {
        return robo;
    }

    public void setRobo(String robo) {
        this.robo = robo;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CronogramaExecutado)) {
            return false;
        }
        CronogramaExecutado other = (CronogramaExecutado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.repita.portalgerente.model.CronogramaExecutado[ id=" + id + " ]";
    }
    
}
