# MC833 
# Projeto 3
# Ruy Castilho Barrichelo	RA 177012
# Heitor Boschirolli Comel	RA 169477

# Makefile
JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	src/User.java \
	src/Subject.java \
	src/ISubjectManager.java \
	src/IServer.java \
	src/SubjectManager.java \
	src/Server.java \
	src/Client.java

default: \
	classes \
	cd bin && rmiregistry & \
	javac Server \
	javac Client \

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class