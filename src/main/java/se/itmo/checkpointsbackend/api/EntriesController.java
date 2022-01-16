package se.itmo.checkpointsbackend.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import se.itmo.checkpointsbackend.dto.EntryReqDto;
import se.itmo.checkpointsbackend.entities.Entry;
import se.itmo.checkpointsbackend.exeprions.NotIncludedInTheRangeException;
import se.itmo.checkpointsbackend.filter.CustomAuthenticationFilter;
import se.itmo.checkpointsbackend.filter.CustomAuthorizationFilter;
import se.itmo.checkpointsbackend.model.AreaChecker;
import se.itmo.checkpointsbackend.service.EntryService;
import se.itmo.checkpointsbackend.service.UserService;
import se.itmo.checkpointsbackend.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class EntriesController {
    private final EntryService entriesService;
    private final UserServiceImpl userService;

    @Autowired
    public EntriesController(UserServiceImpl userService, EntryService entriesService, AreaChecker areaChecker, AuthenticationManager authenticationManager) {
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
    public void deleteEntries() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        entriesService.clear(username);
    }


    @PostMapping("/check")
    public ResponseEntity<Entry> checkEntry(HttpServletRequest request, HttpServletResponse response) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null) {
            response.setHeader("error", "userProblem");
            return ResponseEntity.badRequest().build();
        }

        try {

            double x = Double.parseDouble(request.getParameter("x"));
            double y = Double.parseDouble(request.getParameter("y"));
            double r = Double.parseDouble(request.getParameter("r"));
            Entry entry = userService.addEntryToUser(username, new EntryReqDto(x, y, r));
            return ResponseEntity.ok().body(entry);
        } catch (NotIncludedInTheRangeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
