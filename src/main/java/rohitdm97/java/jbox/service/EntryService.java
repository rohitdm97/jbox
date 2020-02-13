package rohitdm97.java.jbox.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rohitdm97.java.jbox.model.Entry;
import rohitdm97.java.jbox.repository.EntryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class EntryService {

	private final EntryRepository entryRepository;
	private final EntryRedisService entryRedisService;

	public EntryService(EntryRepository entryRepository, EntryRedisService entryRedisService) {
		this.entryRepository = entryRepository;
		this.entryRedisService = entryRedisService;
	}

	public List<Entry> getAll() {
		return entryRepository.findAll();
	}

	public Optional<Entry> get(String id) {
		return entryRepository.findById(id);
	}

	@Scheduled(fixedDelay = 2000)
	public void saveRandomEntry() {
		Entry random = new Entry();
		random.setKey("scheduled_random_entry_" + UUID.randomUUID().toString().replaceAll("-", ""));
		random.setValue(String.valueOf(System.nanoTime()));
		save(random);
	}

	public void save(Entry entry) {
		entryRepository.save(entry);
		trySaveInRedis(entry);
	}

	private void trySaveInRedis(Entry entry) {
		try {
			entryRedisService.put(entry);
		} catch (Exception e) {
			log.error("Failed to save in redis " + entry.getId(), e);
		}
	}

}
