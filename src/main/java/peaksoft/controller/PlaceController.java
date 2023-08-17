package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Place;
import peaksoft.model.Room;
import peaksoft.service.impl.PlaceServiceImpl;
import peaksoft.service.impl.RoomServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/place")
public class PlaceController {
    private final PlaceServiceImpl placeService;
    private final RoomServiceImpl roomService;


    @Autowired
    public PlaceController(PlaceServiceImpl placeService, RoomServiceImpl roomService) {
        this.placeService = placeService;
        this.roomService = roomService;
    }

    @ModelAttribute("roomList")
    public List<Room> roomList() {
        return roomService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        Place place = new Place();
        List<Room> rooms = roomService.findAll();
        model.addAttribute("place", place);
        model.addAttribute("room1", rooms);
        return "PlaceHtml/place";
    }

    @PostMapping("/save_place")
    public String saveUser(@ModelAttribute Place place) {
        placeService.save(place);
        return "redirect:find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model) {
        model.addAttribute("all_places", placeService.findAll());
        return "PlaceHtml/all_places";
    }
    @GetMapping("/findAllId/{id}")
    public String findAllId(@PathVariable("id") int id, Model model) {
        model.addAttribute("all_place_id", roomService.findAllId(id));
        return "PlaceHtml/all_place_id";
    }
    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("find_by_id", id);
        return "PlaceHtml/find_by_id";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Place place = placeService.findById(id);
        model.addAttribute("place", place);
        return "PlaceHtml/update_place";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Place place, @PathVariable int id) {
        placeService.update(id, place);
        return "redirect:/place/find_all";
    }


    @GetMapping("/delete_by_id/{id}")
    public String deleteById(@PathVariable int id) {
        placeService.deleteById(id);
        return "redirect:/place/find_all";
    }

}
