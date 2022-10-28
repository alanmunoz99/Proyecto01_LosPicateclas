import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.time.LocalDateTime;

public class Tienda{
	
	Catalogo catalogo;
	ClientesBD clientesBD;
	    // Para guardar la lista de ingredientes que el usuario agrega a la baguette.
    private static List<String> listaCompras = new ArrayList<>();
    Double precioCompra = 0.00;

	public Tienda (Catalogo catalogo, ClientesBD clientesBD){
		this.catalogo = catalogo;
		this.clientesBD = clientesBD;
	}

    public void Iniciar(){

    	Scanner in = new Scanner(System.in);
    	System.out.println("Bienvenido a Kowallmart!!\n" + "Ingrese sus datos de accceso \n");
    	System.out.println("Para salir del programa presione x seguido de Enter\n");

    	Iterator clientesBDIterator = clientesBD.createIterator();
    	System.out.println("Nombre de Usuario: \n");
    	String usuarioRecibido = in.nextLine();

    	if (usuarioRecibido.equals("x")) System.exit(0);

    	String usuarioEnBD = iteradorClienteBD(usuarioRecibido, clientesBDIterator, "nombreUsuario");

    	clientesBDIterator = clientesBD.createIterator();
    	System.out.println("\nContraseña: \n");
    	String passRecibida = in.nextLine();

    	if (passRecibida.equals("x")) System.exit(0);

    	String passEnBD = iteradorClienteBD(usuarioRecibido, clientesBDIterator, "contraseña");

    	if (usuarioRecibido.equals("") || passRecibida.equals("")){
    		System.out.print("Nombre de usuario o contraseña incorrectos, vuelve a intentar\n\n");
    		Iniciar();
    	} 
    	else if (usuarioRecibido.equals(usuarioEnBD) && passRecibida.equals(passEnBD)){

    		clientesBDIterator = clientesBD.createIterator();
    		String pais = iteradorClienteBD(usuarioRecibido, clientesBDIterator, "pais");

    		sesionIniciada(in, pais, usuarioRecibido);	
    	} 
    	else {
    		System.out.print("Nombre de usuario o contraseña incorrectos, vuelve a intentar\n\n");
    		Iniciar();
    	}
    }

    public String iteradorClienteBD(String nombreUsuario, Iterator iterator, String nombreDatoBuscado){
    	String dato = "";
    	while (iterator.hasNext()) {
    		ClientesBDItem clientesBDItem = (ClientesBDItem) iterator.next();
	    	if (nombreUsuario.equals(clientesBDItem.getNombreUsuario())){
	    		dato = obtenerAtributoClientesBD(clientesBDItem, nombreDatoBuscado);
	    	}
        }return dato;
    }

    public String obtenerAtributoClientesBD(ClientesBDItem item, String nombreDatoBuscado){
    	String dato = "";
    	switch (nombreDatoBuscado) {
    		case "id":
    			dato = String.valueOf(item.getId());
    			break;
    		case "nombreUsuario":
    			dato = item.getNombreUsuario();
    			break;
    		case "nombre":
    			dato = item.getNombre();
    			break;
    		case "contraseña":
    			dato = item.getContraseña();
    			break;
    		case "telefono" :
    			dato = String.valueOf(item.getTelefono());
    			break;
    		case "direccion":
    			dato = item.getDireccion();
    			break;
    		case "cuentaBanco":
    			dato = String.valueOf(item.getCuentaBanco());
    			break;
    		case "pais":
    			dato = item.getPais();
    			break;
    		}
    		return dato;
    }

    public String iteradorCatalogo(String codigo, Iterator iterator, String nombreDatoBuscado){
    	String dato = "";
    	while (iterator.hasNext()) {
    		CatalogoItem catalogoItem = (CatalogoItem) iterator.next();
	    	if (codigo.equals(String.valueOf(catalogoItem.getCodigo()))){
	    		dato = obtenerAtributoCatalogo(catalogoItem, nombreDatoBuscado);
	    	}
        }return dato;
    }

    public String obtenerAtributoCatalogo(CatalogoItem item, String nombreDatoBuscado){
    	String dato = "";
    	switch (nombreDatoBuscado) {
    		case "codigo":
    			dato = String.valueOf(item.getCodigo());
    			break;
    		case "nombre":
    			dato = item.getNombre();
    			break;
    		case "departamento" :
    			dato = item.getDepartamento();
    			break;
    		case "precio":
    			dato = String.valueOf(item.getPrecio());
    			break;
    		}
    		return dato;
    }

    public void sesionIniciada (Scanner in, String pais, String nombreUsuario){
    	if (pais == "Mexico") {
    		System.out.println("\nBienvenido a tu sesion");
    		sesionIniciadaMX(in, nombreUsuario);
    	}
    	if (pais == "USA") {
    		System.out.println("\nWelcome");
    		sesionIniciadaUSA(in, nombreUsuario);

    	}
    	if (pais == "España") {
    		System.out.println("\nBuenas ti@, que guay tenerte de vuelta!!");
    		sesionIniciadaES(in, nombreUsuario);
    	}
    }

    public void sesionIniciadaMX(Scanner in, String nombreUsuario){
    	
    	System.out.println("\nSelecciona que deseas hacer ahora: ");
    	System.out.println("1.- Ver Catalogo de Productos");
    	System.out.println("2.- Realizar una compra");
    	System.out.println("3.- Cerrar sesion");

    	switch (in.nextLine()) {
	    	case "1": 
	    		printCatalogo();
	    		sesionIniciadaMX(in, nombreUsuario);
	    	break;
	    	case "2": 
	    		printCatalogo();
	    		compraMX(in, nombreUsuario);
	    	break;
	    	case "3": 
	    		System.out.println("Vuelva pronto\n");
	    		Iniciar();
	    	break;
	    	default:
	    		System.out.println("Opcion incorrecta, vuelve a intentarlo");
	    		sesionIniciadaMX(in, nombreUsuario);
    	}
    }

    public void compraMX(Scanner in, String nombreUsuario){

    	Iterator elementoListaCompra = catalogo.createIterator();
    	System.out.println("\nEscribe el codigo del producto y da enter para agregarlo a tu carrito de compra");
    	System.out.println("Para volver a ver el catalogo presiona c");
    	System.out.println("Para cancelar la compra solo presiona x");
    	System.out.println("Para finalizar tu compra y pagar presiona f");


    	String idProducto = in.nextLine();

    	if (idProducto.equals("x") || idProducto.equals("X")) {
    		listaCompras.clear();
    		precioCompra = 0.00;
    		sesionIniciadaMX(in, nombreUsuario);
    	}
    
    	if (idProducto.equals("c") || idProducto.equals("C")) {
    		printCatalogo();
    		compraMX(in, nombreUsuario);
    	}

    	if (idProducto.equals("f") || idProducto.equals("F")) {
    		compraSeguraMX(in, nombreUsuario);
    		listaCompras.clear();
    		precioCompra = 0.00;
    		sesionIniciadaMX(in, nombreUsuario);
    	}

    	if (idProducto.equals("")) compraMX(in, nombreUsuario);

    	elementoListaCompra = catalogo.createIterator();
    	String nombreProducto = iteradorCatalogo(idProducto, elementoListaCompra, "nombre");
    	elementoListaCompra = catalogo.createIterator();
    	String precioProducto = iteradorCatalogo(idProducto, elementoListaCompra, "precio");

    	if(precioProducto.equals("")) {
    		System.out.println("Codigo no valido");
    		compraMX(in, nombreUsuario);
    	}

    	Double preciodDoble = Double.valueOf(precioProducto);

		listaCompras.add("\nCodigo del producto : " + idProducto + ", Articulo: " + nombreProducto + "\nPrecio: " + precioProducto);
		precioCompra += preciodDoble;
		System.out.println("Total en carrito: " + precioCompra);
		System.out.println(nombreProducto + " ha sido añadido a tu carrito de compra");
		compraMX(in, nombreUsuario);
    }

    public void compraSeguraMX(Scanner in, String nombreUsuario){
    	Iterator clientesBDIterador  = clientesBD.createIterator();
    	String cuentaUsuarioActual = iteradorClienteBD(nombreUsuario, clientesBDIterador, "cuentaBanco");
    	System.out.println("\n\n\n//////////////// COMPRA SEGURA /////////////////////");
    	System.out.println("Por favor ingresa con cuidado tu numero de cuenta bancaria para que podamos verificar tu identidad");
    	int n = 4;
    	while(n > 0){
    		String cuentaIngresada = in.nextLine();
    		if (cuentaIngresada.equals(cuentaUsuarioActual)){
    		System.out.println("Su compra ha sido relizada con exito");
    		imprimeTicketMX();
    		n=1;
    		break;
	    	} else {
	    		System.out.println("Cuenta incorrecta te quedan " + (n - 1) + " intentos");
	    		n--;
	    	}
    	}
    	if(n <= 0){
    		System.out.println("\nLo sentimos los datos son incorrectos, la sesion será cerrada\n");
    		listaCompras.clear();
    		precioCompra = 0.00;
    		Iniciar();
    	} else {
    		return;
    	}
    	
    }

    public void imprimeTicketMX(){
    	System.out.println("\n\n\n//////////////// TICKET DE COMPRA /////////////////////");
        for (String elemento : listaCompras) {
            System.out.println(elemento);
        }
        System.out.println("Total: $" + precioCompra + " MXN");
		Random randomDays = ThreadLocalRandom.current();
		LocalDateTime date = LocalDateTime.now().plusDays(randomDays.nextInt(10) + 1);
		System.out.println("La fecha de entrega estimada será: " + date);
        System.out.println("///////////////////////////////////////////////////////\n\n\n");
    }
/*
    public void sesionIniciadaUSA(Scanner in, String nombreUsuario){
    	
    	System.out.println("\nSelect what you want to do now");
    	System.out.println("1.- See Products");
    	System.out.println("2.- Make a purchase");
    	System.out.println("3.- Log Out ");

    	switch (in.nextLine()) {
	    	case "1": 
	    		printCatalogo();
	    		sesionIniciadaUSA(in, nombreUsuario);
	    	break;
	    	case "2": 
	    		printCatalogo();
	    	break;
	    	case "3": 
	    		System.out.println("Come Back Soon\n");
	    		Iniciar();
	    	break;
	    	default:
	    		System.out.println("Wrong input, please try again");
	    		sesionIniciadaUSA(in, nombreUsuario);
    	}
    }

    public void sesionIniciadaES(Scanner in, String nombreUsuario){
    	
    	System.out.println("\nSelecciona que deseas hacer ahora: ");
    	System.out.println("1.- Ver Catalogo de Productos");
    	System.out.println("2.- Realizar una compra");
    	System.out.println("3.- Cerrar sesion");

    	switch (in.nextLine()) {
	    	case "1": 
	    		printCatalogo();
	    		sesionIniciadaES(in, nombreUsuario);
	    	break;
	    	case "2": 
	    		printCatalogo();
	    	break;
	    	case "3": 
	    		System.out.println("Vuelva pronto\n");
	    		Iniciar();
	    	break;
	    	default:
	    		System.out.println("Opcion incorrecta, vuelve a intentarlo");
	    		sesionIniciadaES(in, nombreUsuario);
    	}
    }
*/
    /**
     * Metodo que imprime el catalogo
     */
    public void printCatalogo() {
        Iterator catalogoIterator = catalogo.createIterator();
        System.out.println("CATALOGO DE PRODUCTOS");
        printCatalogo(catalogoIterator);
    }

    /**
     * Metodo que le da forma a la impresión del catalogo
     *
     * @param iterator
     */
    private void printCatalogo(Iterator iterator) {
        while (iterator.hasNext()) {
            CatalogoItem catalogoItem = (CatalogoItem) iterator.next();
            System.out.print("\nCodigo: " + catalogoItem.getCodigo() + ", ");
            System.out.print("Nombre: " + catalogoItem.getNombre() + ", ");
            System.out.print("Departamento: " + catalogoItem.getDepartamento() + ", ");
            System.out.print("Precio: $" + catalogoItem.getPrecio() + "\n");
        }
    }
}