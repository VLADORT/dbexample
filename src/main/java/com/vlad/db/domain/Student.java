package com.vlad.db.domain;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    private String name;

    private int discount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private Group group;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the group
     */
    //@JsonIgnore
    public Group getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    //@JsonIgnore
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /*@Override
    public String toString(){
      return "{\"id\":\""+getId()+"\",\"name\":\""+getName()+
              "\",\"group\":{\"name\":\""+group.getName()+"\"}}";

    }*/


}