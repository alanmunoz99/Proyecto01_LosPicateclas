import java.util.Enumeration;
import java.util.Hashtable;

public class ClientesBDIterator implements Iterator{
	
	//variable items como un Hashtable
    Hashtable items;
    //variable position como un Enumeration
    Enumeration position;

    //constructor que recibe un Hashtable
    public ClientesBDIterator(Hashtable items) {
        this.items = items;
        //inicializamos position con el metodo elements() de Hashtable
        position = items.elements();
    }

    /**
     * Metodo hasNext que verifica si hay un siguiente elemento
     *
     * @return true si hay un siguiente elemento, false si no hay un siguiente
     * elemento
     */
    @Override
    public boolean hasNext() {
        //retornamos si hay mas elementos en position
        return position.hasMoreElements();
    }

    /**
     * Metodo que retorna el siguiente elemento del iterador
     *
     * @return el siguiente elemento
     */
    @Override
    public Object next() {
        //retornamos el siguiente elemento de position
        return position.nextElement();
    }
}