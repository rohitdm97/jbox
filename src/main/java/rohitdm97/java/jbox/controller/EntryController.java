package rohitdm97.java.jbox.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rohitdm97.java.jbox.model.Entry;
import rohitdm97.java.jbox.service.EntryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/entry", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntryController {

	private EntryService entryService;

	public EntryController(EntryService entryService) {
		this.entryService = entryService;
	}

	@GetMapping
	public List<Entry> get() {
		return entryService.getAll();
	}

	@GetMapping
	@RequestMapping("/{id}")
	public Optional<Entry> get(@PathVariable String id) {
		return entryService.get(id);
	}

}
