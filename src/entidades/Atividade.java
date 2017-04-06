/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name = "atividade")

public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_atividades")
    private Integer idAtividades;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    @Basic(optional = false)
    @Column(name = "data_fnal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFnal;
    @Basic(optional = true)
    @Column(name = "nota")
    private int nota;
    @Basic(optional = true)
    @Column(name = "Descricao")
    private String descrição;
    @JoinTable(name = "usuario_has_atividade", joinColumns = {
        @JoinColumn(name = "id_atividades", referencedColumnName = "id_atividades")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_Usuario")})    
    //carregamento lento ,uso possivel para fins publicitarios
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Usuario> usuarioCollection;
    @OneToOne(cascade = CascadeType.ALL, optional = false, mappedBy = "atividade")
    private Local local;

    public Atividade() {
    }

    

    public Atividade(Integer idAtividades, String nome, Date dataInicio, Date dataFnal, int nota, String descrição) {
        this.idAtividades = idAtividades;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFnal = dataFnal;
        this.nota = nota;
        this.descrição = descrição;
    }
    
    public Atividade(Integer idAtividades) {
        this.idAtividades = idAtividades;
    }

    public Integer getIdAtividades() {
        return idAtividades;
    }

    public void setIdAtividades(Integer idAtividades) {
        this.idAtividades = idAtividades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFnal() {
        return dataFnal;
    }

    public void setDataFnal(Date dataFnal) {
        this.dataFnal = dataFnal;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    
}
