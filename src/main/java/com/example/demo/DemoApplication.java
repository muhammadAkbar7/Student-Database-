package com.example.demo;

//import com.example.demo.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

//import java.time.LocalDate;
//import java.time.Month;


@SpringBootApplication
//@ComponentScan(basePackageClasses = DemoApplication.class)
//@GetMapping
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

/*	@GetMapping("/")
	public List<player> hello () {

		return List.of(new player(1L, "Mariam","mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 5),21));
	}*/
}
