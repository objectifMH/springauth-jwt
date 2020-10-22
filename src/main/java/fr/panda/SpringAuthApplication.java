package fr.panda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAuthApplication.class, args);
                System.out.println("Début Spring Application sans security : ");
                
                /*String test = "test de hast test ";
                if ( "test de hast test ".hasText("hast"))
                {
                System.out.println("ok je t'ai trouvé ");
                }*/
                    
              
	}

}
