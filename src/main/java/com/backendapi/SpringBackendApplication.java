package com.backendapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBackendApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringBackendApplication.class, args);
//		HashMap<String, IPacketHandler> handlers = new HashMap<String, IPacketHandler>();

//		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
//		List<Employee> employeeList = employeeRepository.findAll();

		SocketServer socketServer = context.getBean(SocketServer.class);
		socketServer.startListening();

//		handlers.put("loginHandler", new HandleLogin());
//		SocketServer.initHanlders(handlers);
//		SocketServer.test();
	}
}
