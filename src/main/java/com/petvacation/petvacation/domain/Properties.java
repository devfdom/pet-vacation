package com.petvacation.petvacation.domain;


import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Set;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Properties {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private int bedrooms;
    private int capacity;
    private boolean pool;
    private boolean garden;
    private String photo;
    private String available;
    private boolean approved;
    private String description;
    private int pricePerNight;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User owner;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User guest;*/

    @OneToMany(mappedBy = "properties")
    Set<Booking> booking;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

   /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "properties")
    private List<Photo> photos;*/



    /*public User getUser() {
        return guest;
    }

    public void setUser(User guest) {
        this.guest = guest;
    }
*/
    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long propertyId) {
        this.id = id;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        owner = owner;
    }

    public Set<Booking> getBooking() {
        return booking;
    }

    public void setBooking(Set<Booking> booking) {
        this.booking = booking;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    /*public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
*/
    public Properties(Long id, int bedrooms, int capacity, boolean pool, boolean garden, String photo, String available, boolean approved, String description, int pricePerNight, User owner, Set<Booking> booking, City city,  User guest) {
        this.id = id;
        this.bedrooms = bedrooms;
        this.capacity = capacity;
        this.pool = pool;
        this.garden = garden;
        this.photo = photo;
        this.available = available;
        this.approved = approved;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.owner = owner;
        this.booking = booking;
        this.city = city;
        //this.photos = photos;
        //this.guest = guest;
    }

    public Properties(Long id) {
        this.id = id;
    }

    public Properties() {
    }

    public Properties(int bedrooms, int capacity, boolean pool, boolean garden, String photo, String available, String description, int pricePerNight, City city) {
        this.bedrooms = bedrooms;
        this.capacity = capacity;
        this.pool = pool;
        this.garden = garden;
        this.photo = photo;
        this.available = available;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.city = city;
    }

    public Properties(Long id, User owner) {
        this.id = id;
        this.owner = owner;
        //this.guest = guest;
    }
}
