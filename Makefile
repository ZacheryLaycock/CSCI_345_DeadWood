JC = javac

.SUFFIXES: .java .class
.java.class: $(JC) $*.java

.PHONY: all commit

CLASSES = \
		Bank.java \
		BoardLayersListener.java \
		BoardManager.java \
		CastingOfficeRoom.java \
		DeadWood.java \
		Dice.java \
		LocationManager.java \
		Player.java \
		RehearsalManager.java \
		Role.java Room.java \
		SceneCard.java \
		SetRoom.java \
		XML_Test.java






default:
	javac $(CLASSES)
	java DeadWood

clean:
	$(RM) $(wildcard *.class)

add:
	git add $(CLASSES) Makefile

run:
	java DeadWood
