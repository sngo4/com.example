package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientManagerController {
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String login(ModelMap model) {
//		Client client = new Client();
//		model.addAttribute("client", client);
		return "clientManager";
	}
	
//	@RequestMapping(value = { "/" }, method = RequestMethod.POST)
//	public String loginForm(@Valid Client client, 
//			ModelMap model, HttpServletRequest requestclient){
//		System.out.println("sang client" + client.getCliName());
//		client manager = clientService.checkclient(client.getUsername(), client.getPassword());
//		
//		if(manager != null){
//		requestclient.getSession().setAttribute("manager", manager);
//			
//			return "redirect:/account/list";
//		}
//		return "redirect:/";
//	}
}
