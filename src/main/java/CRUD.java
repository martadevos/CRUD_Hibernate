import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Scanner;

public class CRUD {
    private static SessionFactory sessionFactory;
    private static Session session;

    protected static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void create(String nombre, String telf) {
        AgendaEntity contacto = new AgendaEntity(nombre, telf);
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(contacto);
        transaction.commit();
        System.out.println("Id insertado: " + id);
    }

    public static void read(int id) {
        Transaction transaction = session.beginTransaction();
        AgendaEntity contacto = session.get(AgendaEntity.class, id);
        transaction.commit();
        System.out.println("Nombre: " + contacto.getNombre() + "    Telf: " + contacto.getTelf());
    }

    public static void update(int id, String nombre, String telf) {
        Transaction transaction = session.beginTransaction();
        AgendaEntity contacto = session.get(AgendaEntity.class, id);
        contacto.setNombre(nombre);
        contacto.setTelf(telf);
        AgendaEntity contactoActualizado = session.merge(contacto);
        transaction.commit();
        System.out.println("Nombre: " + contactoActualizado.getNombre() + "    Telf: " + contactoActualizado.getTelf());
    }

    public static void delete(int id) {
        Transaction transaction = session.beginTransaction();
        AgendaEntity contacto = session.get(AgendaEntity.class, id);
        session.remove(contacto);
        transaction.commit();
    }

    public static boolean menu(int resp) {
        Scanner s = new Scanner(System.in);
        Boolean salir = false;
        setUp();
        session = sessionFactory.openSession();
        switch (resp) {
            case 1 -> {
                String nombre, telf;
                System.out.println("Introduce el nombre nuevo para el contacto a crear");
                nombre = s.next();
                System.out.println("Introduce el telefono nuevo para el contacto a crear");
                telf = s.next();
                CRUD.create(nombre, telf);
            }
            case 2 -> {
                int id;
                System.out.println("Introduce el id del contacto a buscar");
                id = s.nextInt();
                CRUD.read(id);
            }
            case 3 -> {
                int id;
                String nombre, telf;
                System.out.println("Introduce el id del contacto a cambiar");
                id = s.nextInt();
                System.out.println("Introduce el nombre nuevo para el contacto a cambiar");
                nombre = s.next();
                System.out.println("Introduce el telefono nuevo para el contacto a cambiar");
                telf = s.next();
                CRUD.update(id, nombre, telf);
            }
            case 4 -> {
                int id;
                System.out.println("Introduce el id del contacto a borrar");
                id = s.nextInt();
                CRUD.delete(id);
            }
            default -> salir = true;
        }
        sessionFactory.close();
        return salir;
    }
}
