package FinalExam.UniBot;

import FinalExam.UniBot.Entity.QnA;
import FinalExam.UniBot.Repository.QRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniBotApplication {



	@Autowired
	QRepo qRepo;

	public static void main(String[] args) {
		SpringApplication.run(UniBotApplication.class, args);
	}

	@Bean
	public void addData(){

//		qRepo.save(new QnA( null, "Campus" , "we have a beautiful campus of around 1000acres" , "Campus" , "CAMPUS"));
//		qRepo.save(new QnA( null, "hi" , "Hello" , "Greeting" ,"GREETING" ));
//		qRepo.save(new QnA( null, "Course" , "Course we offer : AI, Blockchain, CyberSecurity,DataScience" , "Courses" , "COURSES"));
//		qRepo.save(new QnA( null, "Number of students" , "We are extremely proud of our 4689 students" , "Students" , "STUDENTS"));
//		qRepo.save(new QnA( null, "Number of professor" , "We have 44 highly qualified professors" , "Professors" , "PROFESSORS"));
//		qRepo.save(new QnA( null, "Location" , "We are an online University, you can access from anypart of the world" , "location" , "LOCATION"));
//		qRepo.save(new QnA( null, "know more" , "To get to know more please contact us via email info@tonystarkuniversity.com" , "Know more" , "MORE"));


	}

}
