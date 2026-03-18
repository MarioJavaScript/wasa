import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Contacto {
    private String name;
    private String email;
    private String phone;

    public Contacto(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void save() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name + ".dat", true))) {
            out.writeObject(this);
        }
    }

    public static List<Contacto> loadAll() throws IOException, ClassNotFoundException {
        List<Contacto> contacts = new ArrayList<>();
        File dir = new File("."); // Current directory
        File[] files = dir.listFiles((d, name) -> name.endsWith(".dat"));

        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    contacts.add((Contacto) in.readObject());
                }
            }
        }
        return contacts;
    }
}