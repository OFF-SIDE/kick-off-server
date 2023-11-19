package offside.server.stadium.dto;

public class StadiumDto {
    public String location;
    public String contact_phone;
    public String name;
    public String address;
    public String comment;
    public String image;
    
    public StadiumDto(String location, String contact_phone, String name, String address,
        String comment, String image) {
        this.location = location;
        this.contact_phone = contact_phone;
        this.name = name;
        this.address = address;
        this.comment = comment;
        this.image = image;
    }
}