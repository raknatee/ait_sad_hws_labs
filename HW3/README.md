# HW3 Design Patterns
## Builder 
1. Normal java
 
User.java
```java

@Entity
public class User {

	@Id
	private int id;

	private String name;
	private String nationality;
	private String email;
	
    //get and set method
    ...
	
}


```

UserBuilder.java

- basiclly, this class just wrap User class

```java
public class UserBuilder {
	private int uid;
	private String name;
	private String nationality;
	private String email;
	
	public UserBuilder setUid(int uid) {
		this.uid = uid;
		return this;
	}
	public UserBuilder setName(String name) {
		this.name =name;
		return this;
	}
	public UserBuilder setNationality(String nationality) {
		this.nationality=nationality;
		return this;
	}
	public UserBuilder setEmail(String e) {
		this.email=e;
		return this;
	}
	public User build() {
		return new User(this.uid,this.name,this.nationality,this.email);
	}
}

```

2. with Lombok

- we can use java annotation @Builder on User class.

pom.xml
```xml
    ...
    <dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.18</version>
			<scope>provided</scope>
	</dependency>
    ...

```

UserLombok.java
```java

@Entity
@Builder
@Data
public class UserLombok {
	@Id
	private int uid;
	private String name;
	private String nationality;
	private String email;
}

```

## Singleton 

- to make sure that this Object has only one object

```java

public class SingletonLab {
	private static volatile SingletonLab instance;
	private SingletonLab() {}
	public static SingletonLab getInstance() {
		if(SingletonLab.instance==null) {
			synchronized (SingletonLab.class) {
				if(SingletonLab.instance==null) {
					SingletonLab.instance = new SingletonLab();
				}
			}
		}
		return SingletonLab.instance;
	}
}

```

## Factory 

- Factory can manage how to create object with similar class easier.

Role.java (interface class)
```java
public interface Role {
    ...
}
```

AdminRole.java (interface class)
```java
public class AdminRole implements Role{
    ...
}
```


MemberRole.java (interface class)
```java
public class MemberRole implements Role{
    ...
}
```

RoleFactory.java
```java
@Component
public class RoleFactory {
	public Role createRole(String role) {
		switch(role) {
		case "Admin":
			return new AdminRole();
		case "Member":
			return new MemberRole();
		default:
			throw new UnsupportedOperationException("Unsupported role");
		}
	}
}

```


## Adapter 
- Adapter design make us easier for class management because it can reduce a chain of "extends" and "implement" in some cases. Basically, it looks like a plug for class to another class.

CSVFormattable.java
```java
public interface CSVFormattable {
	String formatCSVText(String text);
}
```
TextFormattable.java
```java
public interface TextFormattable {
	String formatText(String text);
}
```
CSVFormatter.java
```java
public class CSVFormatter implements CSVFormattable{
	@Override
	public String formatCSVText(String text) {
		return text.replace(".", ",");
	}
}
```
CSVAdapter.java
	
- if we want CSVFormatter to act like TextFormattable 

```java
public class CSVAdapter implements TextFormattable{
	CSVFormatter csvFormatter;
	public CSVAdapter(CSVFormatter csvFormatter) {
		this.csvFormatter = csvFormatter;
	}
	@Override
	public String formatText(String text) {
		return this.csvFormatter.formatCSVText(text);
	}
}
```

AdapterMain.java
```java
public class AdapterMain {
	public static void runMe() {
		String testString = " Formatting line 1. Formatting line 2. Formatting line 3.";
		
		TextFormattable textFormatterFromCSV = new CSVAdapter(new CSVFormatter());
		System.out.println(textFormatterFromCSV.formatText(testString));
	}
}
```

## Decorator 
- long story short, it is  a wrapter.

Character.java
```java
public abstract class Character {
	String lore;
	public String getLore() {
		return this.lore;
	}
	public abstract double attack();
}
```

Mage.java
```java
public class Mage extends Character{
	public Mage() {
		super();
		this.lore="Magician";
	}
	@Override
	public double attack() {
		return 12.5;
	}
}
```

CharacterDecorator.java (subclass of Character)
```java
public abstract class CharacterDecorator extends Character{
	public abstract String getLore();
}
```

Weapon.java (subclass of CharacterDecorator -> it means subclass of Character too)
```java
public class Weapon extends CharacterDecorator{
	Character c;
	public Weapon(Character c) {
		this.c = c;
	}
	@Override
	public String getLore() {
		return this.c.getLore() + ", with weapon";
	}
	@Override
	public double attack() {
		return 10+this.c.attack();
	}
}
```

DecortatorMain.java
```java
public class DecortatorMain {
	public static void runMe() {
		Character mage = new Mage();
		System.out.println(mage.getLore()+" Attack!!! "+mage.attack());
		
		mage = new Weapon(mage);
		System.out.println(mage.getLore()+" Attack!!! "+mage.attack());
		
		mage = new Armor(mage);
		System.out.println(mage.getLore()+" Attack!!! "+mage.attack());
	}
}
```
## State
- Main concept of OOP each Object has a state in itself.

Swordman.java
```java
public class Swordman implements MyState {
	int agi = 7;
	int atk = 13;
	int def = 5;
	@Override
	public void increaseDefense(int increment) {
		this.def = def + increment;
	}

	@Override
	public void speedUp(int increment) {
		this.atk = this.atk + 2*increment;
		this.agi += increment;
		
	}

	@Override
	public void increaseAttack(int increment) {
		this.atk = this.atk + increment;
		this.def = this.def - (int)0.3 * increment;
	}

	@Override
	public void printStates() {
		System.out.println("Agi-atk-def: "+agi+"-"+atk+"-"+def);
	}
}
```

StateMain.java
```java
public class StateMain {
	public static void runMe() {
		Swordman sm = new Swordman();
		sm.increaseAttack(4);
		sm.speedUp(3);
		sm.increaseDefense(1);
		sm.speedUp(2);
		System.out.println("Character present state:");
		sm.printStates();
	}
}
```