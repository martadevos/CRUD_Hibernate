import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Agenda")
public class AgendaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgenda")
    private int IdAgenda;
    @Column(name = "nombre")
    private String Nombre;
    @Column(name = "telf")
    private String Telf;

    AgendaEntity(String Nombre, String Telf) {
        this.Nombre = Nombre;
        this.Telf = Telf;
    }

    public AgendaEntity() {
    }

    public int getIidAgenda() {
        return IdAgenda;
    }

    public void setIidAgenda(int idAgenda) {
        this.IdAgenda = idAgenda;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelf() {
        return Telf;
    }

    public void setTelf(String telf) {
        Telf = telf;
    }
}
