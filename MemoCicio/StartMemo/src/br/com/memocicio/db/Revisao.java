/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author GanGss
 */
@Entity
@Table(name = "REVISAO")
@NamedQueries({
    @NamedQuery(name = "Revisao.findAll", query = "SELECT r FROM Revisao r"),
    @NamedQuery(name = "Revisao.findById", query = "SELECT r FROM Revisao r WHERE r.id = :id"),
    @NamedQuery(name = "Revisao.findByClassificacao", query = "SELECT r FROM Revisao r WHERE r.classificacao = :classificacao"),
    @NamedQuery(name = "Revisao.findByProximaRevisao", query = "SELECT r FROM Revisao r WHERE r.proximaRevisao = :proximaRevisao"),
    @NamedQuery(name = "Revisao.findByUpdatedAt", query = "SELECT r FROM Revisao r WHERE r.updatedAt = :updatedAt")})
public class Revisao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CLASSIFICACAO")
    private Integer classificacao;
    @Column(name = "PROXIMA_REVISAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proximaRevisao;
    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "CARD", referencedColumnName = "ID")
    @ManyToOne
    private Card card;

    public Revisao() {
    }

    public Revisao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Integer classificacao) {
        this.classificacao = classificacao;
    }

    public Date getProximaRevisao() {
        return proximaRevisao;
    }

    public void setProximaRevisao(Date proximaRevisao) {
        this.proximaRevisao = proximaRevisao;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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
        if (!(object instanceof Revisao)) {
            return false;
        }
        Revisao other = (Revisao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.memocicio.db.Revisao[ id=" + id + " ]";
    }
    
}
