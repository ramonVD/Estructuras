package Listas;

/*Class created to define a node of a linked list that has a generalist element
 * as its stored data
 */
public class Nodo<E> {

		protected E elemento;
		protected Nodo<E> siguiente;

		public Nodo(E elemento){
			this.elemento = elemento;
			siguiente = null;
		}
		
		public String toString(){
			return "Node with element: " + this.elemento.toString();
		}
				
}
