package org.baeldung;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class Test {

	public static void main(String[] args) {
		// upload image:
		// mli ghatbghi t upladi l image ghatchdha flcontroller oghatstockeha f ${home}/gestion-avocat/images
		// ${home} t9dr tjbdo b System.getProperty('user.home")
		// ghatgenerer unique id l imge filname (i9dr ikon current date YYYY-MM-DD-H-m-s) awla UUID
		// ghatstocker l'image bhad lfilename
		// katkhd relative path li hwa /gestnio-avocat/images/image_id.jpg
		// ghatstoki had relative path f database fhadak field dyal image
		
		// afficher image:
		// 1 - katjbd relative path mn databasei
		// 2 - concatenate l relative path m3a ${home}
		// 3 - kat loadi l'image
		// 4  - katafficher l'image f lview b theamlyf
		System.out.println(System.getProperty("user.home") + "/gestion-avocat/images/");
	}
}
