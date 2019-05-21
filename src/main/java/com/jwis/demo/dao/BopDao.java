package com.jwis.demo.dao;

import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

@NodeEntity(label = "bop")
@ToString
public class BopDao implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
//    @Property(name = "oid")
//    private Long oid;
    @Property(name = "name")
    private String name;
    @Property(name = "code")
    private String code;
    @Property(name = "creator")
    private String creator;
    @Property(name = "procedure")
    private String procedure;
    @Property(name = "part_number")
    private String partNumber;
    @Property(name = "part_supplier")
    private String partSupplier;
    @Property(name = "line_attribute")
    private String lineAttribute;
    @Property(name = "research_attribute")
    private String researchAttribute;
    @Property(name = "process_attribute")
    private String processAttribute;
    @Property(name = "make_attribute")
    private String makeAttribute;
    @Property(name = "profession")
    private String profession;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getLineAttribute() {
        return lineAttribute;
    }

    public void setLineAttribute(String lineAttribute) {
        this.lineAttribute = lineAttribute;
    }

    public String getResearchAttribute() {
        return researchAttribute;
    }

    public void setResearchAttribute(String researchAttribute) {
        this.researchAttribute = researchAttribute;
    }

    public String getProcessAttribute() {
        return processAttribute;
    }

    public void setProcessAttribute(String processAttribute) {
        this.processAttribute = processAttribute;
    }

    public String getMakeAttribute() {
        return makeAttribute;
    }

    public void setMakeAttribute(String makeAttribute) {
        this.makeAttribute = makeAttribute;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
