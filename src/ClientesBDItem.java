public class ClientesBDItem {

	int id;
    String nombreUsuario;
    String nombre; 
    String contraseña; 
    int telefono; 
    String direccion; 
    int cuentaBanco; 
    String pais;

    public ClientesBDItem(int id, String nombreUsuario, String nombre, String contraseña, int telefono, String direccion, int cuentaBanco, String pais) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cuentaBanco = cuentaBanco;
        this.pais = pais;
    }

    /**
     * Metodo que obtiene el id del usuario
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo que obtiene el nombre del usuario
     *
     * @return nombre
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Metodo que obtiene el nombre del usuario
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que obtiene la contraseña del usuario
     *
     * @return contraseña
     */
    public String getContraseña() {
        return contraseña;
    }    

    /**
     * Metodo que obtiene el telefono del usuario
     *
     * @return telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Metodo que obtiene la direccion del usuario
     *
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo que obtiene la cuenta de banco del usuario
     *
     * @return cuentaBanco
     */
    public int getCuentaBanco() {
        return cuentaBanco;
    }

     /**
     * Metodo que obtiene el pais del usuario
     *
     * @return pais
     */
    public String getPais() {
        return pais;
    }


}