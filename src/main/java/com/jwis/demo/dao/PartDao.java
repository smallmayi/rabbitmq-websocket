package com.jwis.demo.dao;

import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

@NodeEntity(label = "plm")
@ToString
public class PartDao implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
//    @Property(name = "oid")
//    private Long oid;
    @Property(name = "name")
    private String name;
    @Property(name = "code")
    private String code;
    @Property(name = "type")
    private String type;
    @Property(name = "keypart")
    private String keypart;
    @Property(name = "version")
    private String version;
    @Property(name = "profession")
    private String profession;
    @Property(name = "procedure")
    private String procedure;
    @Property(name = "part_number")
    private String partNumber;
    @Property(name = "part_supplier")
    private String partSupplier;
    @Property(name = "via_outdiameter")
    private String viaOutdiameter;
    @Property(name = "via_indiameter")
    private String viaIndiameter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeypart() {
        return keypart;
    }

    public void setKeypart(String keypart) {
        this.keypart = keypart;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartSupplier() {
        return partSupplier;
    }

    public void setPartSupplier(String partSupplier) {
        this.partSupplier = partSupplier;
    }

    public String getViaOutdiameter() {
        return viaOutdiameter;
    }

    public void setViaOutdiameter(String viaOutdiameter) {
        this.viaOutdiameter = viaOutdiameter;
    }

    public String getViaIndiameter() {
        return viaIndiameter;
    }

    public void setViaIndiameter(String viaIndiameter) {
        this.viaIndiameter = viaIndiameter;
    }
}
