package com.aronkatona.controllersWebManager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aronkatona.gameTable.Boat;
import com.aronkatona.gameTable.Destroyed;
import com.aronkatona.gameTable.Empty;
import com.aronkatona.gameTable.GameTable;
import com.aronkatona.manager.UserManager;
import com.aronkatona.model.User;
import com.aronkatona.server.ActiveUsers;
import com.aronkatona.service.UserService;

@Controller
public class GameController {

	private UserManager userManager = new UserManager();
	private ActiveUsers activeUsers = ActiveUsers.getInstance();
	private List<GameTable> gameTables = new ArrayList<>();
	private GameTable gameTableMe = null;
	private GameTable gameTableOpponent = null;
	private int shootMeId ;
	private int shootOpponentId;
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService us) {
		this.userManager.setUserService(us);
	}
	
	@RequestMapping(value="/logout")
	public String logout(Model model, HttpSession session){
		User user = this.userManager.getUserByName(session.getAttribute("userName").toString());
		gameTables.remove(user);
		session.setAttribute("userName", "");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/welcome/checkEnemy")
	public String checkEnemy(Model model, HttpSession session) {
		
		User user = this.userManager.getUserByName(session.getAttribute("userName").toString());
		User userOpponent = null;
		 for(GameTable gameTable : gameTables){
			 if(gameTable.getUser().equals(user)){
				userOpponent = gameTable.getOpponent();
				break;
			 }
		 }

		 if(userOpponent != null){
			 System.out.println("enemyFound");
			 return "enemyFound";
		 }
		 System.out.println("noEnemy");
		 return "noEnemy";


	}
	
	@RequestMapping(value="/welcome")
	public String welcome(Model model, HttpSession session){
		model.addAttribute("userName", session.getAttribute("userName"));
		User user = this.userManager.getUserByName(session.getAttribute("userName").toString());
		
		if(!activeUsers.getActiveUsers().contains(user)){
			activeUsers.getActiveUsers().add(user);		
			gameTables.add(new GameTable());
			gameTables.get(gameTables.size()-1).setUser(user);
		}
		
		
			
		return "welcome";
	}
	
	@RequestMapping(value="/welcome/listActiveUsers")
	public String listActiveUsers(Model model,HttpSession session){
		model.addAttribute("userName", session.getAttribute("userName"));
		model.addAttribute("activeUsers", activeUsers.getActiveUsers());
		return "welcome";
	}
	
	@RequestMapping(value="/welcome/pickOpponent.{userId}")
	public String pickOpponent(Model model,HttpSession session, @PathVariable int userId){
		User userMe = this.userManager.getUserByName(session.getAttribute("userName").toString());
		User userOpponent = this.userManager.getUserById(userId);
		session.setAttribute("userOpponent", userOpponent.getName());
		
		 for(GameTable gameTable : gameTables){
			 if(gameTable.getUser().equals(userMe)){
				 gameTable.clearTable();
				 gameTable.setOpponent(userOpponent);
				 gameTable.getFieldAt(3, 7).setBoat(new Boat());
				 gameTable.getFieldAt(1, 5).setBoat(new Boat());
				 gameTable.getFieldAt(3, 3).setBoat(new Boat());
				 gameTable.getFieldAt(4, 9).setBoat(new Boat());
				 gameTable.getFieldAt(8, 1).setBoat(new Boat());
				 gameTable.getFieldAt(3,7).setEmpty(null);
				 gameTable.getFieldAt(1,5).setEmpty(null);
				 gameTable.getFieldAt(3, 3).setEmpty(null);
				 gameTable.getFieldAt(4, 9).setEmpty(null);
				 gameTable.getFieldAt(8, 1).setEmpty(null);
			 }
			 if(gameTable.getUser().equals(userOpponent)){
				 gameTable.clearTable();
				 gameTable.setOpponent(userMe);
				 gameTable.getFieldAt(6,3).setBoat(new Boat());
				 gameTable.getFieldAt(5, 9).setBoat(new Boat());
				 gameTable.getFieldAt(7, 3).setBoat(new Boat());
				 gameTable.getFieldAt(2, 4).setBoat(new Boat());
				 gameTable.getFieldAt(4, 6).setBoat(new Boat());
				 gameTable.getFieldAt(6,3).setEmpty(null);
				 gameTable.getFieldAt(5, 9).setEmpty(null);
				 gameTable.getFieldAt(7, 3).setEmpty(null);
				 gameTable.getFieldAt(2, 4).setEmpty(null);
				 gameTable.getFieldAt(4, 6).setEmpty(null);
			 }
		 }
		
		return "redirect:/welcome/gameboard";
	}
	
	@RequestMapping(value="/welcome/shoot.{i}.{j}")
	public String shoot(Model model,HttpSession session, @PathVariable int i, @PathVariable int j){
		
		User userMe = this.userManager.getUserByName(session.getAttribute("userName").toString());
		User userOpponent = null;
		
		for(GameTable gameTable : gameTables){
			if(gameTable.getUser().equals(userMe)){
				userOpponent = gameTable.getOpponent();
				break;
			}
		}
		for(GameTable gameTable : gameTables){
			if(gameTable.getUser().equals(userOpponent)){
				if(gameTable.getFieldAt(i, j).getBoat() != null){
					gameTable.getFieldAt(i, j).setBoat(null);
					gameTable.getFieldAt(i, j).setDestroyed(new Destroyed());
					gameTable.minusHp();
				}
				else if(gameTable.getFieldAt(i, j).getEmpty() != null){
					gameTable.getFieldAt(i, j).setEmpty(null);
					gameTable.getFieldAt(i, j).setDestroyed(new Destroyed());
				}
				break;
			}
		}
		
		
		
		
		return "redirect:/welcome/gameboard";
	}
	
	@RequestMapping(value="/welcome/gameboard")
	public String gameBoard(Model model,HttpSession session){
		
		model.addAttribute("tableSize", 10);
		
		User userMe = this.userManager.getUserByName(session.getAttribute("userName").toString());
		User userOpponent = null;
		
		for(GameTable gameTable : gameTables){
			if(gameTable.getUser().equals(userMe)){
				userOpponent = gameTable.getOpponent();
				break;
			}
		}
		
		
		
		for(GameTable gameTable : gameTables){
			if(gameTable.getUser().equals(userMe)){
				gameTableMe = gameTable;
			}
			if(gameTable.getUser().equals(userOpponent)){
				gameTableOpponent = gameTable;
			}
		}

		model.addAttribute("yourHp",gameTableMe.getHp());
		model.addAttribute("enemyHp", gameTableOpponent.getHp());
		model.addAttribute("gameTableMe", gameTableMe.getGameTable());
		model.addAttribute("gameTableOpponent", gameTableOpponent.getGameTable());
		
		return "gameboard";
	}

}
