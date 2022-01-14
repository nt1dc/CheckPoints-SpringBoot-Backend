//package se.itmo.checkpointsbackend.api;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import se.itmo.checkpointsbackend.POJO.req.CheckEntryReq;
//import se.itmo.checkpointsbackend.entities.Entry;
//import se.itmo.checkpointsbackend.model.AreaChecker;
//import se.itmo.checkpointsbackend.service.EntriesService;
//import se.itmo.checkpointsbackend.service.UserService;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//@RequestMapping("/app")
//@RequiredArgsConstructor
//public class EntriesController {
//    private final EntriesService entriesService;
//    private final UserService userService;
//    private final AreaChecker areaChecker;
//
//    @Autowired
//    public EntriesController(UserService userService, EntriesService entriesService, AreaChecker areaChecker) {
//        this.userService = userService;
//        this.entriesService = entriesService;
//        this.areaChecker = areaChecker;
//    }
////
////    @GetMapping("/myEntries")
////    public ResponseEntity<List<Entry>> getEntries(Principal principal) {
////        List<Entry> entries = entriesService.getEntries(principal.getName());
////        return ResponseEntity.ok().body(entries);
////    }
//
//    @PostMapping("/clear")
//    public void delete(Principal principal) {
//        entriesService.clear(principal.getName());
//    }
//
//
//    @PostMapping("/check")
//    public ResponseEntity<Entry> checkEntry(Principal principal, CheckEntryReq entryReq) {
//        return ResponseEntity.ok().body(new Entry(1, 2, 3));
//    }
//
//
//}
