public class Main {

	public static void main(String[] args) {
		Catalogo a = new Catalogo();
		ClientesBD c = new ClientesBD();
		Tienda b = new Tienda(a, c);
		b.Iniciar();
	}
	
}