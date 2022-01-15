package se.itmo.checkpointsbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.model.AreaChecker;
import se.itmo.checkpointsbackend.service.EntryService;
import se.itmo.checkpointsbackend.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class EntriesController {
    private final EntryService entriesService;
    private final UserService userService;
    private final AreaChecker areaChecker;

    @Autowired
    public EntriesController(UserService userService, EntryService entriesService, AreaChecker areaChecker) {
        this.userService = userService;
        this.entriesService = entriesService;
        this.areaChecker = areaChecker;
    }

    @GetMapping("/myEntries")
    public ResponseEntity<List<Entry>> getEntries() {
        List<Entry> entries = entriesService.getEntries();
        return ResponseEntity.ok().body(entries);
    }

    @GetMapping("/clear")
    public void delete(Principal principal) {
        entriesService.clear(principal.getName());
    }


    @GetMapping("/check")
    public ResponseEntity<String> checkEntry(Principal principal, EntryReqDto entryReq) {
        return ResponseEntity.ok().body(new Entry().toString());
    }


}
