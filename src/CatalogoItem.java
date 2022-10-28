public class CatalogoItem {

	int codigo;
    String nombre;
    String departamento;
    double precio;

    public CatalogoItem(int codigo, String nombre, String departamento, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.departamento = departamento;
        this.precio = precio;
    }

    /**
     * Metodo que obtiene el nombre de el producto
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que obtiene el codigo de el producto
     *
     * @return codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Metodo que obtiene la departamento de el producto
     *
     * @return descripción
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Metodo que obtiene el precio de el producto
     *
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }
}