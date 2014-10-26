
package com.wilmeramezquita.cola1;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

/**
 * @author Wilmer Manuel Amézquita
 * @Details Estructuras dinámicas utilizando el lenguaje de programación Java 
 * @version 1.0
 * 
 */
public class Cola1 extends JApplet{

	static double SALDO=0;
	public void init(){
		int i, opc, nit;
		Cola A = new Cola(10);
		Cola B = new Cola(20);
		Cliente C = new Cliente();
		Object[]valores={"1.Adición", 
							"2.Borrar", 
							"3.Consulta por atender", 
							"4.Consulta Atendidos", 
							"5.Salir"};
		do{
			String resp=(String) JOptionPane.showInputDialog(null,
																"Elija Opción",
																"Entrada de datos",
																JOptionPane.QUESTION_MESSAGE,
																null, 
																valores,
																valores[0]);
			opc=Character.digit(resp.charAt(0),10);
			switch(opc){
				case 1:
					C.nit=Integer.parseInt(JOptionPane.showInputDialog(null,
																		"Escriba Número de NIT:"));
					if(A.existe(C.nit)||B.existe(C.nit)){
						JOptionPane.showMessageDialog(null, "Existe Nit");
					}else if(A.llena()){
						JOptionPane.showMessageDialog(null, "No se puede atender mas");
					}else{
						C.nomCliente = JOptionPane.showInputDialog(null, "Digite Nombre");
						C.telefono = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite Teléfono"));
						A.adicion(C);
					}
					break;
				case 2:
					if(A.vacia()){
						JOptionPane.showMessageDialog(null, "Cola de Atención Vacía");
					}else{
						A.borra(C);
						if(B.llena()){
							B.adicion(C);
						}
					}
					break;
				case 3:
					if(A.vacia()){
						JOptionPane.showMessageDialog(null, "Cola de Atención Vacía");
					}else{
						JOptionPane.showMessageDialog(null, A.imprime("Atención"));
					}
					break;
				case 4:
					if(B.vacia()){
						JOptionPane.showMessageDialog(null, "Cola de Atendidos Vacía");
					}else{
						JOptionPane.showMessageDialog(null, B.imprime("Atención"));
					}
			}
		}while(opc!=5);
	}
}

class Cliente{
	int nit;
	String nomCliente;
	int telefono;
}

class Cola{
	int min,max,n;
	Cliente A[];
	int i;
	
	public Cola(int n){
		min = -1;
		max = -1;
		this.n = n;
		A = new Cliente[n];
	}
	
	boolean vacia(){
		if(min==-1){
			return true;
		}else{
			return false;
		}
	}
	
	boolean llena(){
		if(max == n-1){
			return true;
		}else{
			return false;
		}
	}
	
	void adicion(Cliente C){
		if(min==-1){
			min++;
		}
		max++;
		A[max]=new Cliente();
		A[max].nit=C.nit;
		A[max].telefono=C.telefono;
		A[max].nomCliente=C.nomCliente;
	}
	
	void borra(Cliente C){
		C.nit=A[min].nit;
		C.telefono=A[min].telefono;
		C.nomCliente=A[min].nomCliente;
		if(min==max){
			min=max=-1;
		}else{
			min++;
		}
	}
	
	boolean existe(int nit){
		boolean esta=false;
		if(!vacia()){
			for(i=min;i<=max&&!esta;i++){
				if(A[i].nit==nit){
					esta=true;
				}
			}
		}
		return esta;
	}
	
	String imprime(String Aviso){
		String S = " Elementos de la cola de: "+Aviso.toString()+"\n";
		for(i=min;i<=max;i++){
			S=S+" "+ new String().valueOf(A[i].nit).toString();
			S=S+" "+ A[i].nomCliente;
			S=S+" "+ new String().valueOf(A[i].telefono).toString();
		}
		return S.toString();
	}
}
