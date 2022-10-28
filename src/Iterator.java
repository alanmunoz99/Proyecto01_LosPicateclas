public interface Iterator {

    /**
     * Metodo hasNext que verifica si hay un siguiente elemento
     *
     * @return true si hay un siguiente elemento, false si no hay un siguiente
     * elemento
     */

    public boolean hasNext();

    /**
     * Metodo que retorna el siguiente elemento del iterador
     *
     * @return el siguiente elemento
     */
    public Object next();
}