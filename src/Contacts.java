
public class Contacts{
    private final String name;
    private final String phone;

    public Contacts(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String printContact() {
        return name + " | " + phone;
    }

}
