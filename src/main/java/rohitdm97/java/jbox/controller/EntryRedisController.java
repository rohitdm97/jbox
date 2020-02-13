package rohitdm97.java.jbox.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rohitdm97.java.jbox.model.Entry;
import rohitdm97.java.jbox.service.EntryRedisService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cached", produces = MediaType.APPLICATION_JSON_VALUE)
@Log4j2
public class EntryRedisController {

	private EntryRedisService entryRedisService;

	public EntryRedisController(EntryRedisService entryRedisService) {
		this.entryRedisService = entryRedisService;
	}

	@GetMapping
	@RequestMapping("/")
	public List<String> get() {
		return entryRedisService.getAll();
	}

	@GetMapping
	@RequestMapping("/{id}")
	public Optional<Entry> get(@PathVariable String id) {
		return entryRedisService.get(id);
	}


}
