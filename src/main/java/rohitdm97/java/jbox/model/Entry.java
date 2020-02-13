package rohitdm97.java.jbox.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table
@Data
@NoArgsConstructor
public class Entry {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "entry_string_generator")
	@GenericGenerator(
		name = "entry_string_generator",
		strategy = "uuid"
	)
	private String id;

	@Column
	private String key;

	@Column
	private String value;

	@Version
	@Column
	private int version;

	@Builder
	public Entry(String key, String value) {
		this.key = key;
		this.value = value;
	}

}
