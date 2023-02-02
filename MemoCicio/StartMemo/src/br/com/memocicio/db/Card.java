/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.memocicio.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author GanGss
 */
@Entity
@Table(name = "CARD")
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c"),
    @NamedQuery(name = "Card.findById", query = "SELECT c FROM Card c WHERE c.id = :id"),
    @NamedQuery(name = "Card.findByCreateAt", query = "SELECT c FROM Card c WHERE c.createAt = :createAt"),
    @NamedQuery(name = "Card.findByFrente", query = "SELECT c FROM Card c WHERE c.frente = :frente"),
    @NamedQuery(name = "Card.findByTras", query = "SELECT c FROM Card c WHERE c.tras = :tras")})
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CREATE_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt = new Date();
    @Column(name = "FRENTE")
    private String frente;
    @Column(name = "TRAS")
    private String tras;
    @OneToMany(mappedBy = "card")
    private List<Revisao> revisaoList;
    @JoinColumn(name = "CONJUNTO_FK", referencedColumnName = "ID")
    @ManyToOne
    private Conjunto conjuntoFk;

    public Card() {
    }

    public Card(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getFrente() {
        return frente;
    }

    public void setFrente(String frente) {
        this.frente = frente;
    }

    public String getTras() {
        return tras;
    }

    public void setTras(String tras) {
        this.tras = tras;
    }

    public List<Revisao> getRevisaoList() {
        return revisaoList;
    }

    public void setRevisaoList(List<Revisao> revisaoList) {
        this.revisaoList = revisaoList;
    }

    public Conjunto getConjuntoFk() {
        return conjuntoFk;
    }

    public void setConjuntoFk(Conjunto conjuntoFk) {
        this.conjuntoFk = conjuntoFk;
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
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " - " + frente;
    }

}
