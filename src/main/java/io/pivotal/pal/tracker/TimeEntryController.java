package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final TimeEntryRepository  repo;
    public TimeEntry timeEntryToCreate;
    public TimeEntryController(TimeEntryRepository repo) {
        this.repo = repo;
    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return created(null).body(repo.create(timeEntryToCreate));
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        var timeEntryFound = repo.find(timeEntryId);
        return timeEntryFound == null ? notFound().build() :  ok(timeEntryFound);
    }
    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        return ok(repo.list());
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        var timeEntryUpdated = repo.update(timeEntryId, timeEntryToUpdate);

        return timeEntryUpdated == null ?  notFound().build() :   ok(timeEntryUpdated);

    }
        @DeleteMapping("{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
            repo.delete(timeEntryId);
            return noContent().build();
    }
}
