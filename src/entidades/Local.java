/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author adoniran
 */
@Entity
@Table(name = "local")

public class Local implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_atividades")
    private Integer idAtividades;
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @Column(name = "rua")
    private String rua;
    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;
    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "UF")
    private String uf;
    @JoinColumn(name = "id_atividades", referencedColumnName = "id_atividades", insertable = false, updatable = false)
    @OneToOne(optional = false)//é necessario colocar um  cascade = CascadeType.ALL??
    private Atividade atividade;

    public Local() {
    }

    public Local(Integer idAtividades) {
        this.idAtividades = idAtividades;
    }

    public Local(Integer idAtividades, String rua, String bairro, String cidade) {
        this.idAtividades = idAtividades;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public Integer getIdAtividades() {
        return idAtividades;
    }

    public void setIdAtividades(Integer idAtividades) {
        this.idAtividades = idAtividades;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

   
}
