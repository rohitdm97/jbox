package rohitdm97.java.jbox.service;

import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.stereotype.Service;
import rohitdm97.java.jbox.model.Entry;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EntryRedisService {

	private static final JsonJacksonCodec CODEC = new JsonJacksonCodec();
	private static final String GLOBAL_ENTRY_LIST_KEY = "global_entry_list";

	private final RedissonClient redissonClient;
	private final RList<String> globalList;

	public EntryRedisService(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
		globalList = redissonClient.getList(GLOBAL_ENTRY_LIST_KEY);
	}

	public void put(Entry entry) {
		if (entry.getId() == null) {
			return;
		}
		RBucket<Entry> bucket = redissonClient.getBucket(entry.getId(), CODEC);
		bucket.set(entry);
		globalList.add(entry.getId());
	}

	public Optional<Entry> get(String id) {
		RBucket<Entry> bucket = redissonClient.getBucket(id, CODEC);
		return Optional.ofNullable(bucket.get());
	}

	public List<String> getAll() {
		return Collections.unmodifiableList(globalList.range(0, -1));
	}

}
