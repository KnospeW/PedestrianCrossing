clean:
	@rm -rf documentation
	@rm -rf classes
	@mkdir classes	
build:
	@javac -d classes -classpath classes -sourcepath src src/comp3110/mechanismsDomain/*.java
	@echo mechanisms domain done.
	@javac -d classes -classpath classes -sourcepath src src/comp3110/userInterfaceDomain/*.java
	@echo user interface domain done.
	@javac -d classes -classpath classes -sourcepath src src/comp3110/crossingDomain/*.java
	@echo crossing domain done.
	@javac -d classes -classpath classes -sourcepath src src/comp3110/simulatedCrossingHardwareDomain/*.java
	@echo simulated crossing hardware domain done.
	@javac -d classes -classpath classes -sourcepath src src/comp3110/Crossing.java
	@echo main program done.
	
run:
	@java -classpath classes comp3110.Crossing
	
doc:
	@javadoc -sourcepath src -d documentation -classpath classes -overview overview.html -linksource -private -use comp3110.mechanismsDomain comp3110.crossingDomain comp3110.simulatedCrossingHardwareDomain comp3110.userInterfaceDomain comp3110
	
		
