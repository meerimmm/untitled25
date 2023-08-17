package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Movie;
import peaksoft.model.Room;
import peaksoft.model.Session;
import peaksoft.service.impl.MovieServiceImpl;
import peaksoft.service.impl.RoomServiceImpl;
import peaksoft.service.impl.SessionServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/session")
public class SessionController {
    private final SessionServiceImpl sessionService;
    private final RoomServiceImpl roomService;
    private final MovieServiceImpl movieService;

    @Autowired
    public SessionController(SessionServiceImpl sessionService, MovieServiceImpl movieService, RoomServiceImpl roomService, MovieServiceImpl movieService1) {
        this.sessionService = sessionService;
        this.roomService = roomService;
        this.movieService = movieService1;
    }

    @ModelAttribute("roomList")
    public List<Room> roomList() {
        return roomService.findAll();
    }

    @ModelAttribute("movieList")
    public List<Movie> movieList() {
        return movieService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        Session session = new Session();

        model.addAttribute("ses", session);
//            model.addAttribute("room1",roomService.findAll());
        return "SessionHtml/session";
    }

    @PostMapping("/save_session")
    public String saveUser(@ModelAttribute Session session) {
        sessionService.save(session);
        return "redirect:find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
//            System.out.println("Hello");
//            for (Session session : sessionService.findAll()) {
//            System.out.println(session.getName());
//                System.out.println(session.getMovie().getName());
//        }
        model.addAttribute("all_sessions", sessionService.findAll());

        return "SessionHtml/all_sessions";
    }

    @GetMapping("/findAllId/{id}")
    public String findAllId(@PathVariable("id") int id, Model model) {
        model.addAttribute("all_session_id", sessionService.findAllId(id));
        return "SessionHtml/all_session_id";
    }


    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("find_by_id", sessionService.findById(id));
        return "SessionHtml/find_by_id";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Session session = sessionService.findById(id);
        model.addAttribute("ses", session);
        return "SessionHtml/update_session";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Session session, @PathVariable int id) {
        sessionService.update(id, session);
        return "redirect:/session/find_all";
    }


    @GetMapping("/delete_by_id/{id}")
    public String deleteById(@PathVariable int id) {
        sessionService.deleteById(id);
        return "redirect:/session/find_all";
    }
}
