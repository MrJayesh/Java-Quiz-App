CREATE DATABASE  IF NOT EXISTS `quiz`;
USE `quiz`;
DROP TABLE IF EXISTS `mcq`;
CREATE TABLE `mcq` (
  `id` int NOT NULL AUTO_INCREMENT,
  `questions` varchar(255) NOT NULL,
  `option1` varchar(255) DEFAULT NULL,
  `option2` varchar(255) DEFAULT NULL,
  `option3` varchar(255) DEFAULT NULL,
  `option4` varchar(255) DEFAULT NULL,
  `correct_answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
LOCK TABLES `mcq` WRITE;
INSERT INTO `mcq` VALUES (1,' Who invented Java Programming?','Guido van Rossum',' James Gosling','Dennis Ritchie','Bjarne Stroustrup',' James Gosling'),(2,'Which one of the following is not a Java feature?','Object-oriented','Use of pointers','Portable','Dynamic','Use of pointers'),(3,'What is the extension of java code files?','.js','.txt','.class','.java','.java'),(4,'Which of the following is not an OOPS concept in Java?','Polymorphism','Inheritance','Compilation','Encapsulation','Compilation'),(5,'What is not the use of this keyword in Java?','Referring to the instance variable when a local variable has the same name','Passing itself to the method of the same class','Passing itself to another method','Calling another constructor in constructor chaining','Passing itself to the method of the same class'),(6,'What is the extension of compiled java classes?','.txt','.js','.class','.java','.class'),(7,'Which exception is thrown when java is out of memory?','MemoryError','OutOfMemoryError','MemoryOutOfBoundsException','MemoryFullException','OutOfMemoryError'),(8,'Which of these are selection statements in Java?','break','continue','for()','if()','if()'),(9,'Which of these keywords is used to define interfaces in Java?','intf','Intf','interface','Interface','interface'),(10,'Which of the following is a superclass of every class in Java?','ArrayList','Abstract class','Object class','String','Object class');
UNLOCK TABLES;
