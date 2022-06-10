all:
	javac -d build -sourcepath src src/Main.java
run: build
	java -classpath build Main
jar: build
	
	jar cvfe game.jar Main  -C build/ .
build: 
	javac -d build -sourcepath src src/Main.java