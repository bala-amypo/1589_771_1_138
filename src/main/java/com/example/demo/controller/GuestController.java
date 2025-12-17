import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Guest Controller", description = "Management APIs for Guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    // POST /api/guests - Create guest
    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestService.saveGuest(guest);
    }

    // PUT /api/guests/{id} - Update guest
    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id, @RequestBody Guest guestDetails) {
        return guestService.updateGuest(id, guestDetails);
    }

    // GET /api/guests/{id} - Get guest
    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    // GET /api/guests - List all
    @GetMapping
    public List<Guest> listAllGuests() {
        return guestService.getAllGuests();
    }

    // PUT /api/guests/{id}/deactivate - Deactivate
    @PutMapping("/{id}/deactivate")
    public void deactivateGuest(@PathVariable Long id) {
        guestService.deactivateGuest(id);
    }
}