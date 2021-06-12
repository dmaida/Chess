JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
    $(JC) $(JFLAGS) $*.java

CLASSES = \
    Board.java \
    Controller.java \
    Pawn.java \
    Piece.java \
    Bishop.java \
    King.java \
    Knight.java \
    Queen.java \
    Rook.java \
    Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
    $(RM) *.class

run:
    javac *.java && java Main && make clean