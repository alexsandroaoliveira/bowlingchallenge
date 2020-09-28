<!-- PROJECT HEADER -->

<br />
<p align="center">
  <h3 align="center">Bowling Chalenge by Alexsandro Oliveira</h3>

  <p align="center">
    Capture throws score and print the Bowling Scoreboard
  </p>
</p>


## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Installation](#installation)
* [Usage](#usage)
* [Contact](#contact)


## About The Project

A Java8 Console Application project that input the players throws scores and display the bowling scoreboard at the end.  

Highlights:
* Game configuration using Metadata, some game rules can be modified without change the code. (Number of players, Number of Frames, Number of throws etc)
* Dependency injection using just Spring Framework. No Spring Boot. (To show explicitly the IoC pattern)
* Maven layout project. Build with maven
* JUnit - Unit test and Integration Test
* Streams and Lambda using examples


### Built With

* [JDK8](https://www.oracle.com/java/technologies/javase-downloads.html#javasejdk)
* [Maven](https://maven.apache.org)


## Getting Started

To get a local copy up and running follow these simple example steps.

### Installation

1. Clone the repo

```sh
git clone https://github.com/alexsandroaoliveira/bowlingchallenge.git
```

## Usage

The program require as execution arg one text file containing the results for several players bowling. 

This file must be written according to these guidelines:

* Each line represents a player and a chance with the subsequent number of pins
knocked down.
* An 'F' indicates a foul on that chance and no pins knocked down (identical for
scoring to a roll of 0).
* The rows are tab-separated

Sample

```sh
Jeff	10
John	3
John	7
Jeff	7
Jeff	3
John	6
John	3
Jeff	9
Jeff	0
John	10
Jeff	10
John	8
John	1
Jeff	0
Jeff	8
John	10
Jeff	8
Jeff	2
John	10
Jeff	F
Jeff	6
John	9
John	0
Jeff	10
John	7
John	3
Jeff	10
John	4
John	4
Jeff	10
Jeff	8
Jeff	1
John	10
John	9
John	0
```

Using this input file, the output will be:

```sh
Frame		1		2		3		4		5		6		7		8		9		10
Jeff
Pitfalls		X	7	/	9	0		X	0	8	8	/	F	6		X		X	X	8	1	
Score		20		39		48		66		74		84		90		120		148		167
John
Pitfalls	3	/	6	3		X	8	1		X		X	9	0	7	/	4	4	X	9	0	
Score		16		25		44		53		82		101		110		124		132		151
```

### Execute

```sh
mvn clean compile exec:java -Dexec.args="game-score-sample.txt" 
```

### Test

```sh
mvn test 
```
## Next steps

* Add a Logging Framework
* Better Exception Handling
* BowlingGameEngine Class Refactoring 

## Contact

Alexsandro Oliveira - alexsandroaoliveira@gmail.com@gmail.com

Project Link: [https://github.com/alexsandroaoliveira/bowlingchallenge](https://github.com/alexsandroaoliveira/bowlingchallenge)