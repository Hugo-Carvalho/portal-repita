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
@Table(name = "cronograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cronograma.findAll", query = "SELECT c FROM Cronograma c"),
    @NamedQuery(name = "Cronograma.findById", query = "SELECT c FROM Cronograma c WHERE c.id = :id"),
    @NamedQuery(name = "Cronograma.findByNome", query = "SELECT c FROM Cronograma c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cronograma.findByReceptor", query = "SELECT c FROM Cronograma c WHERE c.receptor = :receptor"),
    @NamedQuery(name = "Cronograma.findByRobo", query = "SELECT c FROM Cronograma c WHERE c.robo = :robo"),
    @NamedQuery(name = "Cronograma.findByDataInicio", query = "SELECT c FROM Cronograma c WHERE c.dataInicio = :dataInicio"),
    @NamedQuery(name = "Cronograma.findByHoraInicio", query = "SELECT c FROM Cronograma c WHERE c.horaInicio = :horaInicio")})
public class Cronograma implements Serializable {
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
    @Size(max = 255)
    @Column(name = "robo")
    private String robo;
    @Size(max = 255)
    @Column(name = "data_inicio")
    private String dataInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "hora_inicio")
    private String horaInicio;

    public Cronograma() {
    }

    public Cronograma(Long id) {
        this.id = id;
    }

    public Cronograma(Long id, String nome, String receptor, String horaInicio) {
        this.id = id;
        this.nome = nome;
        this.receptor = receptor;
        this.horaInicio = horaInicio;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cronograma)) {
            return false;
        }
        Cronograma other = (Cronograma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.repita.portalgerente.model.Cronograma[ id=" + id + " ]";
    }
    
}
