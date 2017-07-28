package Listas;

/*Clase para definir un Nodo de la Linked list, hacer mas general, que el elemento sea Object en vez de String...*/
public class Nodo {

		protected String elemento;
		protected Nodo siguiente;
		
		public Nodo(){
			elemento = "";
			siguiente = null;
		}
		
		public Nodo(String elemento){
			this.elemento = elemento;
			siguiente = null;
		}
		
		/*No pongo getters ni setters porque sera en LinList*/
		
}
