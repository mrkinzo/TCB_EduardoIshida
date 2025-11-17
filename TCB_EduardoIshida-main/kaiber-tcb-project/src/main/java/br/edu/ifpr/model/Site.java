
public class Site {
    public String Name;
    public int sId;
    public String country;
    public String city;
    public boolean publicProprity;
    public boolean visitable;

    public Site(String name, int sId, String country, String city, boolean publicProprity, boolean visitable) {
        Name = name;
        this.sId = sId;
        this.country = country;
        this.city = city;
        this.publicProprity = publicProprity;
        this.visitable = visitable;
    }

    public Site() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isPublicProprity() {
        return publicProprity;
    }

    public void setPublicProprity(boolean publicProprity) {
        this.publicProprity = publicProprity;
    }

    public boolean isVisitable() {
        return visitable;
    }

    public void setVisitable(boolean visitable) {
        this.visitable = visitable;
    }
    
}
