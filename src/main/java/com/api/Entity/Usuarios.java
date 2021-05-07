package com.api.Entity;

import javax.persistence.*;

@Entity
public class Usuarios {

    @Id
    @Column (name = "Us_Id", insertable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long usid;

    @Column (name = "Us_Nombres")
    private String usnombres;

    @Column (name = "Us_Apellidos")
    private String usapellidos;

    @Column (name = "Us_Telefono")
    private String ustelefono;

    @Column (name = "Us_Correo")
    private String uscorreo;

    @Column (name = "Us_Correoalternativo")
    private String uscorreoalternativo;

    @Column (name = "Us_Clave")
    private String usclave;

    @Column (name = "Us_Fotoperfil")
    private String usfoto;

    @Column (name = "Idestado")
    private int usestado;

    @Column (name = "Rol_Id")
    private long rolid;

    @Column (name = "Ar_Id")
    private long arid;


    // ---- INNER JOINS ----//

    // - TABLA ROL
    @ManyToOne
    @JoinColumn (name = "Rol_Id", insertable = false, updatable = false)
    private Rol rol;

    public Rol getRol() {
        return rol;
    }

    // - TABLA AREA

    @ManyToOne
    @JoinColumn (name = "Ar_Id", insertable = false, updatable = false)
    private Areas area;

    public Areas getArea() {
        return area;
    }

    // ----  END INNER JOINS ----//

    public long getUsid() {
        return usid;
    }

    public void setUsid(long usid) {
        this.usid = usid;
    }

    public String getUsnombres() {
        return usnombres;
    }

    public void setUsnombres(String usnombres) {
        this.usnombres = usnombres;
    }

    public String getUsapellidos() {
        return usapellidos;
    }

    public void setUsapellidos(String usapellidos) {
        this.usapellidos = usapellidos;
    }

    public String getUstelefono() {
        return ustelefono;
    }

    public void setUstelefono(String ustelefono) {
        this.ustelefono = ustelefono;
    }

    public String getUscorreo() {
        return uscorreo;
    }

    public void setUscorreo(String uscorreo) {
        this.uscorreo = uscorreo;
    }

    public String getUscorreoalternativo() {
        return uscorreoalternativo;
    }

    public void setUscorreoalternativo(String uscorreoalternativo) {
        this.uscorreoalternativo = uscorreoalternativo;
    }

    public String getUsclave() {
        return usclave;
    }

    public void setUsclave(String usclave) {
        this.usclave = usclave;
    }

    public String getUsfoto() {
        return usfoto;
    }

    public void setUsfoto(String usfoto) {
        this.usfoto = usfoto;
    }

    public int getUsestado() {
        return usestado;
    }

    public void setUsestado(int usestado) {
        this.usestado = usestado;
    }

    public long getRolid() {
        return rolid;
    }

    public void setRolid(long rolid) {
        this.rolid = rolid;
    }

    public long getArid() {
        return arid;
    }

    public void setArid(long arid) {
        this.arid = arid;
    }
}
