package armor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HUYVU
 */
public class ArmorDTO implements Serializable {

    private String armorID;
    private String classification;
    private String description;
    private String status;
    private Date timeOfCreate;
    private int defense;

    public ArmorDTO() {
    }

    public ArmorDTO(String armorID, String classification, String description, String status, Date timeOfCreate, int defense) {
        this.armorID = armorID;
        this.classification = classification;
        this.description = description;
        this.status = status;
        this.timeOfCreate = timeOfCreate;
        this.defense = defense;
    }

    public String getArmorID() {
        return armorID;
    }

    public void setArmorID(String armorID) {
        this.armorID = armorID;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeOfCreate() {
        return timeOfCreate;
    }

    public void setTimeOfCreate(Date timeOfCreate) {
        this.timeOfCreate = timeOfCreate;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFomat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return getArmorID() + "`" + getClassification() + "`" + getDescription() 
                + "`" + getStatus() + "`"+ dateFomat.format(getTimeOfCreate()) + "`" + getDefense();
        
    }
}
