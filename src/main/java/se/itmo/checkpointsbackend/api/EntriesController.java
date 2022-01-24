package se.itmo.checkpointsbackend.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.exeptions.NotIncludedInTheRangeException;
import se.itmo.checkpointsbackend.service.EntryService;
import se.itmo.checkpointsbackend.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EntriesController {
    private final EntryService entriesService;
    private final UserServiceImpl userService;

    @Autowired
    public EntriesController(UserServiceImpl userService, EntryService entriesService) {
        this.userService = userService;
        this.entriesService = entriesService;
    }

    @GetMapping("/myEntries")
    public ResponseEntity<List<Entry>> getEntries() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("user: {} gets entries", username);
        List<Entry> entries = userService.getUser(username).getEntries();
        return ResponseEntity.ok().body(entries);
    }

    @GetMapping("/clear")
    public ResponseEntity<?> deleteEntries() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            log.info("User : {} clears his entries", username);
            entriesService.clear(username);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/check")
    public   ResponseEntity<?> checkEntry(@Valid @RequestBody EntryReqDto entryReqDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("{} try to check entry", username);
        if (username == null) {
            return ResponseEntity.badRequest().body("userProblem");
        }
        try {
            Entry entry = userService.addEntryToUser(username, entryReqDto);
            return ResponseEntity.ok().body(entry);
        } catch (NotIncludedInTheRangeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(504).body("too much requests");
        }
    }
}
