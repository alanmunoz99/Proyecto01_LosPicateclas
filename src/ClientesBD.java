import java.util.Collection;
import java.util.Hashtable;

public class ClientesBD {
	private Hashtable ClientesBDItems = new Hashtable();

	public ClientesBD() {
        addItem(0, "JuanMoxo17", "Juan Rodriguez", "contraseña", 55669420, "Callejón Salsipuedes 1, en Barrio Coltongo, Azcapotzalco, CDMX.", 10000001, "Mexico");
    }

    /**
     * Metodo que sirve para agregar un producto al catalogo
     *
     * @param id
     * @param nombreUsuario
     * @param nombre
     * @param contraseña
     * @param telefono
     * @param direccion
     * @param cuentaBanco
     * @param pais
     */
    public void addItem(int id, String nombreUsuario, String nombre, String contraseña, int telefono, String direccion, int cuentaBanco, String pais) {
        ClientesBDItem clientesBDItem = new ClientesBDItem(id, nombreUsuario, nombre, contraseña, telefono, direccion, cuentaBanco, pais);
        ClientesBDItems.put(clientesBDItem.getNombre(), clientesBDItem);
    }

    public Iterator createIterator() {
        return new CatalogoIterator(ClientesBDItems);
    }
}
