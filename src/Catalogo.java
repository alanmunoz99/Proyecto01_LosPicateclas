import java.util.Collection;
import java.util.Hashtable;

public class Catalogo {
	private Hashtable catalogoItems = new Hashtable();

	public Catalogo() {
        addItem(1, "Comca Cola 6Lt", "Bebidas", 300.00);/*
        addItem(8, "Hamburguesa especial 2", "de solo verla se te antojara", 60.00, true, true);
        addItem(9, "Hamburgesa especial 3", "tan rica y sabrosa que es especial", 70.00, true, true);*/
    }

    /**
     * Metodo que sirve para agregar un producto al catalogo
     *
     * @param codigo
     * @param nombre
     * @param departamento
     * @param precio
     */
    public void addItem(int codigo, String nombre, String departamento, double precio) {
        CatalogoItem catalogoItem = new CatalogoItem(codigo, nombre, departamento, precio);
        catalogoItems.put(catalogoItem.getNombre(), catalogoItem);
    }

    public Iterator createIterator() {
        return new CatalogoIterator(catalogoItems);
    }
}