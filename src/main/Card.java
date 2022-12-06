import java.io.ObjectStreamException;

public class Card {
	String name;

	public Card(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object other) {
			if(this == other)
				return true;
			else if(other == null)
				return false;
			else if(other instanceof String)
				return name.equals(other);
			else if(other instanceof Card)
				return name.equals(((Card) other).name);
			else
				return false;
	}
}
