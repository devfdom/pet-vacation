package com.petvacation.petvacation.domain;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    private Long id;
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "properties_id")
    private Properties properties;
    private Boolean mainPhoto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setHotel(Properties properties) {
        this.properties = properties;
    }

    public Boolean getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(Boolean mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

}